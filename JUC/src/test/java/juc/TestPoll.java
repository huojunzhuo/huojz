package juc;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: Poll
 *
 * @Description 线程池
 * @Author huojz
 * @project huojz
 * @create 2024 07 06 15:44
 */
public class TestPoll {

    public static void main(String[] args) {
        //声明拒绝策略
        RejectPolicy rejectPolicy = (RejectPolicy<Runnable>) (queue, task) -> {
            //1）死等并阻塞策略
//                queue.put(task);
            //2) 带超时时间的等待
//            queue.offer(task, 1500, TimeUnit.MILLISECONDS);
            //3) 让调用者放弃任务执行-未做任何操作
//            System.out.println("调用者放弃该任务" + task);
            //4) 让调用者抛出异常；
//            throw new RuntimeException("任务执行失败，未加入等待队列，后续任务不再执行" + task);
            //5) 让调用者自己执行任务；
            System.out.println("主线程自己执行任务" + task);
            task.run();//主线程自己执行
        };
        MyThreadPool threadPool = new MyThreadPool(1, 1000, TimeUnit.MILLISECONDS, 1 ,rejectPolicy);
        for (int i = 0; i < 4; i++) {
            int j = i;
            //定义task任务执行具体内容
            Runnable task = () -> {
               try {
                   //假设每个任务执行很长时间
                   Thread.sleep(1000L);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println("j = " + j + "执行任务的线程为：" + Thread.currentThread().getName());
           };
            threadPool.execute(task);
        }
    }
}

//线程池
class MyThreadPool {
    //任务队列，类型为Runnable（任务）
    private BlockingQueue<Runnable> taskQueue;
    //线程集合
    private HashSet<Worker> workers = new HashSet<>();
    //核心线程数
    private int coreSize;
    //获取任务时的超时时间
    private long timeout;
    //时间单位
    private TimeUnit timeUnit;
    //拒绝策略
    private RejectPolicy<Runnable> rejectPolicy;

    public MyThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCap ,RejectPolicy rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue = new BlockingQueue<>(queueCap);
        this.rejectPolicy = rejectPolicy;
    }

    /**
     * 线程池执行任务
     * @param task 任务
     */
    public void execute(Runnable task) {
        //操作共享变量workers，确保线程安全的
        synchronized (workers){
            if (workers.size() < coreSize) {
                //当任务数小于coreSize时，直接交给Worker执行
                Worker worker = new Worker(task);
                //开启线程任务
                worker.start();
                //加入线程集合
                System.out.println("加入线程池执行" + task);
                workers.add(worker);
            }else {
                //当任务数超过coreSize，则交给taskQueue暂存
                //1) 阻塞则死等策略
//                taskQueue.put(task);
                //2) 带超时时间的等待
//                taskQueue.offer(task, timeout, timeUnit);
                //3) 让调用者放弃任务执行
                //4) 让调用者抛出异常；
                //5) 让调用则自己执行任务；
                //建议决策权交给线程的使用者——策略模式
                taskQueue.tryPut(rejectPolicy , task);
            }
        }
    }

    //线程类，内部维护一个Runnable任务对象
    class Worker extends Thread {
        Runnable task;
        @Override
        public void run() {
            //执行任务
            //1)当线程构造时已经赋给任务，task不为空,则直接执行任务；
            //2)当 task 执行完毕，再接着从任务队列获取任务并执行
//            while (task != null || (task = taskQueue.take()) != null){ //不带超时策略，则线程持续运行不会关闭
            while (task != null || (task = taskQueue.poll(5,TimeUnit.SECONDS)) != null){ //从队列获取任务带超时关闭策略；
                try {
//                    Thread.sleep(2000);
                    System.out.println("线程准备执行任务：" + Thread.currentThread().getName() +task);
                    task.run();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    //执行完毕 任务置为空
                    task = null;
                }
            }
            System.out.println("任务执行完毕，移除线程" + this);
            //任务全部执行完毕，退出循环，移除当前线程
            //多个线程操作共享变量workers，需要加锁保护
            synchronized (workers){
                workers.remove(this);
            }
        }

        public Worker(Runnable task) {
            this.task = task;
        }
    }
}
@FunctionalInterface //拒绝策略接口
interface RejectPolicy<T>{
    void reject(BlockingQueue<T> blockingQueue , T task) ;
}
//阻塞队列
class BlockingQueue<T> {
    // 1. 任务队列：双向链表
    private Deque<T> queue = new ArrayDeque<>();
    // 2. 锁
    private ReentrantLock lock = new ReentrantLock();
    // 3. 生产者条件变量
    private Condition fullWaitSet = lock.newCondition();
    // 4. 消费者条件变量
    private Condition emptyWaitSet = lock.newCondition();
    // 5. 容量
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 从队列阻塞获取元素（多线程执行，需要获取锁）
     * @return
     */
    public T take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    //任务队列为空，则阻塞
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            //唤醒添加任务的线程
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 带超时的阻塞获取元素
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return
     * @throws InterruptedException
     */
    public T poll(long timeout, TimeUnit unit) {
        lock.lock();
        try {
            //将timeout统一转换为纳秒
            long nanos = unit.toNanos(timeout);
            while (queue.isEmpty()) {
                try {
                    //如果剩余时间为0，已超时
                    if (nanos <= 0) {
                        return null;
                    }
                    //返回的是剩余时间，赋值给下次需等待的时间
                    long awaitedNanos = emptyWaitSet.awaitNanos(nanos);
                    nanos = awaitedNanos;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            //唤醒添加元素的线程
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 向队列阻塞添加元素
     * @param task
     */
    public void put(T task) {
        lock.lock();
        try {
            //如果超出队列限制，则等待
            while (queue.size() == capacity) {
                try {
                    System.out.println("等待加入阻塞队列..." + task);
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //否则，添加元素
            queue.addLast(task);
            System.out.println("加入阻塞队列" + task);
            //唤醒获取元素的线程
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 带超时时间的向等待队列添加任务
     * @param task
     * @param timeout
     * @param unit
     * @return
     */
    public boolean offer(T task , long timeout, TimeUnit unit) {
        lock.lock();
        long nanos = unit.toNanos(timeout);
        try {
            //如果超出队列限制，则等待
            while (queue.size() == capacity) {
                try {
                    System.out.println("等待加入阻塞队列..." + task);
                    if (nanos <= 0) {
                        System.out.println("加入阻塞队列等待超时" + task);
                        return false;
                    }
                    nanos = fullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //否则，添加元素
            queue.addLast(task);
            System.out.println("加入阻塞队列" + task);
            //唤醒获取元素的线程
            emptyWaitSet.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 获取阻塞队列的大小
     * @return
     */
    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 尝试加入阻塞队列（自定义拒绝策略）
     * @param rejectPolicy
     * @param task
     */
    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();
        try {
            if(queue.size() >= capacity){
                //队列是否已满
                System.out.println("队列已满，未加入队列，该任务走自定义拒绝策略" + task);
                rejectPolicy.reject(this ,task);
            }else {
                //队列空闲
                queue.addLast(task);
                System.out.println("加入阻塞队列" + task);
                //唤醒消费者获取元素的线程
                emptyWaitSet.signal();
            }
        }finally {
            lock.unlock();
        }
    }
}

