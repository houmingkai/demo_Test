package com.example.demo.Lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：TryLock使用
 */
public class TryLockDemo {
    // new一个锁对象
    Lock lock = new ReentrantLock();
    public void readFile(String fileMessage){
        if(lock.tryLock()){
            try{
                System.out.println(Thread.currentThread().getName()+"得到了锁，正在读取文件……");
                System.out.println("文件读取完毕！");
            }finally{
                System.out.println(Thread.currentThread().getName()+"释放了锁！");
                lock.unlock();
            }
        }else{
            System.out.println(Thread.currentThread().getName()+"获取锁失败!");
        }
    }

    public void demo(final String fileMessage){
        // 创建若干个线程
        ExecutorService service = Executors.newCachedThreadPool();
        // 提交200个任务
        for(int i=0; i<100; i++){
            service.execute(new Runnable() {
                @Override
                public void run() {
                    readFile(fileMessage);
                }
            });
        }
        // 释放线程池中的线程
        service.shutdown();
    }

    public static void main(String[] args){
        TryLockDemo tryLockDemo = new TryLockDemo();
        tryLockDemo.demo("hello hmk,zifuchuanneirongjiachang");
    }
}