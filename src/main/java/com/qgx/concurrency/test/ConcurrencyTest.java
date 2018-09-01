package com.qgx.concurrency.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Executor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 *@Author: goxcheer
 *@Date:19:59 2018/9/1
 *@email:604721660@qq.com
 *@decription:模拟并发测试类
 */
@Slf4j
public class ConcurrencyTest {

    public static int clientTotal = 5000; //客户请求数

    public static int threadTotal = 200; //并发执行的线程数

    public static int count = 0; //计数器

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();  //创建一个新的线程池
        final Semaphore semaphore = new Semaphore(threadTotal); //允许执行线程的信号量
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal); //客户请求次数计数的锁

        for (int i=0; i< clientTotal ; i++) {
            executorService.execute(() ->   //lamda定律的使用
                    {
                        try {
                            semaphore.acquire();  //信号量获取一个可使用的线程
                            add();
                            semaphore.release(); //释放一个线程
                        } catch (InterruptedException e) {
                            log.error("Exception",e);
                            e.printStackTrace();
                        }
                        countDownLatch.countDown(); //倒计时一次
                    }
                    );
        }
        countDownLatch.await();
        executorService.shutdown();  //关闭线程池，释放资源
        log.info("count:{}",count);


    }

    private static void add () {
        count++;
    }
}
