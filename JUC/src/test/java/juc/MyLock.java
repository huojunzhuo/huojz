package juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

//自定义锁（不可重入锁）
public class MyLock implements Lock {
    //有了自定义同步器，很容易复用 AQS ，实现一个功能完备的自定义锁
    private MySync sync = new MySync();
    //独占锁 同步器类
    class MySync extends AbstractQueuedSynchronizer {
        //自定义尝试加锁逻辑，本次自定义为不成功不加入阻塞队列
        @Override
        protected boolean tryAcquire(int acquires) {
            //多线程操作state，需要应用CAS原理修改其状态
            if (compareAndSetState(0, 1)) {
                //加上锁，设置持有锁的线程owner为当前线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }
        //自定义尝试释放锁，本次定义直接释放
        @Override
        protected boolean tryRelease(int acquires) {
            //state属性是volatile的，写操作在其后加入写凭证，其及其之前的所有修改都是对其他线程可见的
            setExclusiveOwnerThread(null); //1.抹去owner线程
            setState(0);//设置state为0，未包含唤醒其他线程机制
            return true;
        }

        //获取Condition对象
        protected Condition newCondition() {
            return new ConditionObject();
        }
        //是否持有独占锁？state == 1 时是持有状态
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

    }

    @Override
    // 加锁（不成功，直接进入等待队列）
    public void lock() {
        sync.acquire(1);
    }

    @Override
    // 尝试加锁，不成功，进入等待队列，可打断
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    // 尝试加锁，只尝试一次，不成功返回false，不进入队列
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    // 尝试加锁，不成功，进入等待队列，有时限
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    // 释放锁
    public void unlock() {
        //内部间接调用我们自定义的tryRelease，完成1、2操作，同时新增了唤醒等待线程的方法
        sync.release(1);
    }

    @Override
    // 生成条件变量
    public Condition newCondition() {
        return sync.newCondition();
    }
}



