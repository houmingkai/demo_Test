package com.example.demo.BlockingQueueTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueDemo {
    private final static ArrayBlockingQueue<Apple> queue = new ArrayBlockingQueue<>(1);

    public static void main(String[] args) {
        new Thread(new Producer(queue)).start();
        new Thread(new Producer(queue)).start();
        new Thread(new Consumer(queue)).start();
        new Thread(new Consumer(queue)).start();
    }
}

class Apple {
    public Apple() {
    }
}

/**
 * 生产者线程
 */
class Producer implements Runnable {
    private final ArrayBlockingQueue<Apple> mAbq;

    Producer(ArrayBlockingQueue<Apple> arrayBlockingQueue) {
        this.mAbq = arrayBlockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            Produce();
        }
    }

    //调用put()方法添加元素，当队列满时就阻塞
    private void Produce() {
        try {
            Apple apple = new Apple();
            mAbq.put(apple);
            System.out.println("生产:" + apple);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 消费者线程
 */
class Consumer implements Runnable {

    private ArrayBlockingQueue<Apple> mAbq;

    Consumer(ArrayBlockingQueue<Apple> arrayBlockingQueue) {
        this.mAbq = arrayBlockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
                comsume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //take()方法获取元素当队列没有元素就阻塞
    private void comsume() throws InterruptedException {
        Apple apple = mAbq.take();
        System.out.println("消费Apple=" + apple);
    }
}

