package com.nuc.zp.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

class MyData {
    BlockingQueue<String> shareQueue = null;

    public MyData(BlockingQueue<String> queue) {
        this.shareQueue = queue;
    }

    /**
     * 队列的添加（offer(e,time,unit)）方法，指定时间内添加成功返回true，失败返回false
     * 队列的移除（poll(time,unit)）方法，指定时间内移除成功返回对应的值，失败返回null
     *
     */
    protected void offerValueWithDeadLine() throws Exception {
        while (true) {
            boolean flag = shareQueue.offer("AAA", 2L, TimeUnit.SECONDS);
            if (flag) {
                System.out.println(Thread.currentThread().getName() + "\t 入队列成功");
            }else {
                System.out.println(Thread.currentThread().getName() + "\t 入队列失败" );
                return;
            }
            Thread.sleep(1000);
        }
    }

    protected void polldValueWithDeadLine() throws Exception {
        String polldValue;
        while (true) {
            polldValue = shareQueue.poll(2L, TimeUnit.SECONDS);
            if (polldValue != null && !"".equals(polldValue)) {
                System.out.println(Thread.currentThread().getName() + "\t 出队列：" + polldValue);
            }else {
                System.out.println(Thread.currentThread().getName() + "\t 出队列失败" );
                return;
            }
        }
    }

    /**
     * 队列的添加（put）方法，在队列满时，会进行阻塞，直至数据插入队列成功。
     * 队列的删除（take）方法，在队列为空时，会进行阻塞，直至数据被移除成功。
     *
     * Tip：不建议使用此方法操作队列，因为如果某一个线程一直持有CPU使用权，该线程会一直被阻塞。
     */
    protected void putValue(String value) throws Exception {
        while (true) {
            System.out.println(Thread.currentThread().getName());
            shareQueue.put(value);
            System.out.println(Thread.currentThread().getName() + "\t 入队列成功，当前队列容量：" + shareQueue.size());
            Thread.sleep(1000);
        }
    }

    protected String takeValue() throws Exception {
        String polldValue;
        while (true) {
            System.out.println(Thread.currentThread().getName());
            polldValue = shareQueue.take();
            if (polldValue != null && !"".equals(polldValue)) {
                System.out.println(Thread.currentThread().getName() + "\t 出队列：" + polldValue);
            }
            Thread.sleep(1000);
            return polldValue;
        }
    }

    /**
     * 队列的添加（offer）方法,添加成功返回true，失败返回false
     * 队列的删除（poll）方法删除失败会抛出NoSuchElementException。
     */
    protected void offerValue(String value) throws Exception {
        boolean offerFlag = shareQueue.offer(value);
        System.out.println(Thread.currentThread().getName() + "\t 入队列：" + offerFlag);
        Thread.sleep(1000);
    }

    protected String pollValue() {
        String polldValue = shareQueue.remove();
        System.out.println(Thread.currentThread().getName() + "\t 出队列：" + polldValue);
        return polldValue;
    }

    /**
     * 队列的添加（add）方法在队列容量满和删除（remove）方法在队列为空调用时
     * 会分别抛出IllegalStateException和NoSuchElementException。
     * <p>
     * add()   ：   java.lang.IllegalStateException: Queue full
     * remove()：	java.util.NoSuchElementException
     */
    protected void addValue(String value) {
        shareQueue.add(value);
        System.out.println(Thread.currentThread().getName() + "\t 入队列");
    }

    protected String removeValue() {
        String removedValue = shareQueue.remove();
        System.out.println(Thread.currentThread().getName() + "\t 出队列：" + removedValue);
        return removedValue;
    }
}

public class BlockingQueueDemo {
    public static void main(String[] args) {
        MyData myData = new MyData(new ArrayBlockingQueue<>(1));
        //new LinkedBlockingQueue<>()
        //new SynchronousQueue<>()
        new Thread(() -> {
            try {
                myData.offerValueWithDeadLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(() -> {
            try {
                myData.polldValueWithDeadLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}