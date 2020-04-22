package com.example.demo.Lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  当不加锁时,可能会出现内容读取不完整的情况(多运行几次可以看出效果)
 *  把readFile方法前面加上synchronized关键字，然后把锁去掉，效果是一样的。
 */
public class LockDemo {

    // new一个锁对象，注意此处必须声明成类对象，保持只有一把锁,ReentrantLock是Lock的唯一实现类
    Lock lock = new ReentrantLock();
    public void readFile(String fileMessage) {
        lock.lock();// 上锁
        try {
            System.out.println(Thread.currentThread().getName() + "得到了锁，正在读取文件……");
            for (int i = 0; i < fileMessage.length(); i++) {
                System.out.print(fileMessage.charAt(i));
            }
            System.out.println();
            System.out.println("文件读取完毕！");
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了锁！");
            lock.unlock();  //释放锁
        }
    }

        public void demo(final String fileMessage){
            // 创建若干个线程
            ExecutorService service = Executors.newCachedThreadPool();
            // 提交20个任务
            for(int i=0; i<20; i++){
                service.execute(new Runnable() {
                    @Override
                    public void run() {
                        readFile(fileMessage);
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            // 释放线程池中的线程
            service.shutdown();
        }
        
        public static void main(String[] args){
            LockDemo lockDemo = new LockDemo();
            lockDemo.demo("hello hmk,zifuchuanneirongjiachang");
        }

}
