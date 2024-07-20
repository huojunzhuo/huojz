package juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * ClassName: ConnectPool
 *
 * @Description 连接池
 * @Author huojz
 * @project huojz
 * @create 2024 07 20 16:41
 */
public class ConnectPool {
    // 1. 连接池大小
    private final int poolSize;

    // 2. 连接对象数组
    private Connection[] connections;

    // 3. 连接状态数组 0 表示空闲， 1 表示繁忙
    private AtomicIntegerArray states;

    // 限流控制
    private Semaphore semaphore;

    // 4. 构造方法初始化
    public ConnectPool(int poolSize) {
        this.poolSize = poolSize;
        this.semaphore = new Semaphore(poolSize);
        // 让许可数与资源数一致
        this.connections = new Connection[poolSize];
        this.states = new AtomicIntegerArray(new int[poolSize]);
        for (int i = 0; i < poolSize; i++) {
            connections[i] = new MockConnection("连接" + (i+1));
        }
    }
    // 5. 借连接
    public Connection borrow() {// t1, t2, t3
    // 获取信号量控制的许可
        try {
            // 获取许可，如果没有许可的线程，在此等待,实现了wait功能
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < poolSize; i++) {
            // 获取空闲连接
            if(states.get(i) == 0) {
                if (states.compareAndSet(i, 0, 1)) {
                    return connections[i];
                }
            }
        }
        // 不会执行到这里
        return null;
    }
    // 6. 归还连接
    public void free(Connection conn) {
        for (int i = 0; i < poolSize; i++) {
            if (connections[i] == conn) {
                states.set(i, 0);
                semaphore.release();
                break;
            }
        }
    }
    class MockConnection implements Connection {
        String name ;

        public MockConnection(String name) {
            this.name = name;
        }
    }

    interface Connection{
    }
}
