/**
 * Created by Administrator on 2017-6-2.
 */

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test1 {

    /*public static void main(String[] args) throws InterruptedException {

        ExecutorService newFixThreadPool= Executors.newFixedThreadPool(5);
        System.out.println(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i <100 ; i++) {

            final int a=i;

            newFixThreadPool.execute(new Runnable() {
                @Override
                public void run() {

                    System.out.println(Thread.currentThread().getName()+">>>>>"+a);
                   *//* try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*//*
                }
            });
        }
    }*/

   /* public static void main(String[] args) throws InterruptedException {
        final CountDownLatch begin = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(20);
        final ExecutorService newFixThreadPool = Executors.newFixedThreadPool(5);
        final List<Integer> list = new ArrayList<>();
      *//*  for (int i = 0; i <20 ; i++) {
            list.add(i);
        }
*//*
        for (int i = 0; i < 20; i++) {
            final int a = i;
            newFixThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        begin.await();
                        list.add(a);
                        //System.out.println(Thread.currentThread().getName() + ">>>>>" + a + "No." + a + " arrived");
                        System.out.print(list.get(a)+",");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        end.countDown();
                    }

                }
            });
        }
        long startTime = System.currentTimeMillis();
        System.out.println("开始时间：=====" + new Timestamp(System.currentTimeMillis()));
        begin.countDown();
//        Thread.sleep(1000);
        end.await();
        System.out.println("结束时间：======" + new Timestamp(System.currentTimeMillis()));
        long endTime = System.currentTimeMillis();
        System.out.println((endTime-startTime)/1000);
        System.out.println(list.toString());
        newFixThreadPool.shutdown();
    }*/


    /*public static void main(String[] args) throws InterruptedException {
        final CountDownLatch begin = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(20);
        final ExecutorService newFixThreadPool = Executors.newFixedThreadPool(5);
        final int[] list = new int[20];
        for (int i = 0; i < 20; i++) {
            final int a = i;
            newFixThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        begin.await();
                        System.out.println("-------------"+a);
                        list[a] = a;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        end.countDown();
                    }

                }
            });
        }
        long startTime = System.currentTimeMillis();
        System.out.println("开始时间：=====" + new Timestamp(System.currentTimeMillis()));
        begin.countDown();
//        Thread.sleep(1000);
        end.await();
        System.out.println("结束时间：======" + new Timestamp(System.currentTimeMillis()));
        newFixThreadPool.shutdown();
        long endTime = System.currentTimeMillis();
        System.out.println((endTime-startTime)/1000);
        for (int i = 0; i < list.length; i++) {

        System.out.print(list[i]+",");
        }

    }*/

    public static void main(String[] args) {
        ExecutorService cachethreadPool= Executors.newCachedThreadPool();
        System.out.println(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i <100 ; i++) {
            try {
                final int  a=i;
                //Thread.sleep(i*1000);
                cachethreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName()+">>>>"+a);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
