package com.nuc.zp.juc.thread;


class HoldLockThred implements Runnable
{
    private String lockA;
    private String lockB;

    public HoldLockThred(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){

            System.out.println(Thread.currentThread().getName()+"\t 持有"+lockA +"\t尝试获取"+lockB);
            synchronized (lockB){

            }
        }
    }
}

/**
 * 死锁检测
 *
 * localhost:juc_study mdk$ jps -l
 * 3622 com.nuc.zp.juc.thread.DeadLockDemo
 * 2310 org.gradle.launcher.daemon.bootstrap.GradleDaemon
 * 871 org.jetbrains.idea.maven.server.RemoteMavenServer
 * 859
 * 3631 sun.tools.jps.Jps
 *
 *
 *
 * localhost:juc_study mdk$ jstack 3622
 * 2019-06-02 15:09:42
 * Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.211-b12 mixed mode):
 *
 * "Attach Listener" #12 daemon prio=9 os_prio=31 tid=0x00007f9d28840800 nid=0x4e0b waiting on condition [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "DestroyJavaVM" #11 prio=5 os_prio=31 tid=0x00007f9d28804000 nid=0x1703 waiting on condition [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "BBB" #10 prio=5 os_prio=31 tid=0x00007f9d2902d000 nid=0x4203 waiting for monitor entry [0x0000700002a07000]
 *    java.lang.Thread.State: BLOCKED (on object monitor)
 *         at com.nuc.zp.juc.thread.HoldLockThred.run(DeadLockDemo.java:21)
 *         - waiting to lock <0x00000007955f3ce0> (a java.lang.String)
 *         - locked <0x00000007955f3d18> (a java.lang.String)
 *         at java.lang.Thread.run(Thread.java:748)
 *
 * "AAA" #9 prio=5 os_prio=31 tid=0x00007f9d2902c000 nid=0x3a03 waiting for monitor entry [0x0000700002904000]
 *    java.lang.Thread.State: BLOCKED (on object monitor)
 *         at com.nuc.zp.juc.thread.HoldLockThred.run(DeadLockDemo.java:21)
 *         - waiting to lock <0x00000007955f3d18> (a java.lang.String)
 *         - locked <0x00000007955f3ce0> (a java.lang.String)
 *         at java.lang.Thread.run(Thread.java:748)
 *
 * "Service Thread" #8 daemon prio=9 os_prio=31 tid=0x00007f9d28036800 nid=0x4603 runnable [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "C1 CompilerThread2" #7 daemon prio=9 os_prio=31 tid=0x00007f9d2883b000 nid=0x3703 waiting on condition [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "C2 CompilerThread1" #6 daemon prio=9 os_prio=31 tid=0x00007f9d2883a800 nid=0x3603 waiting on condition [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "C2 CompilerThread0" #5 daemon prio=9 os_prio=31 tid=0x00007f9d28838800 nid=0x4903 waiting on condition [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "Signal Dispatcher" #4 daemon prio=9 os_prio=31 tid=0x00007f9d28813000 nid=0x3403 runnable [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 *
 * "Finalizer" #3 daemon prio=8 os_prio=31 tid=0x00007f9d29025800 nid=0x5103 in Object.wait() [0x00007000021ef000]
 *    java.lang.Thread.State: WAITING (on object monitor)
 *         at java.lang.Object.wait(Native Method)
 *         - waiting on <0x0000000795588ed0> (a java.lang.ref.ReferenceQueue$Lock)
 *         at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:144)
 *         - locked <0x0000000795588ed0> (a java.lang.ref.ReferenceQueue$Lock)
 *         at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:165)
 *         at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:216)
 *
 * "Reference Handler" #2 daemon prio=10 os_prio=31 tid=0x00007f9d29024800 nid=0x2e03 in Object.wait() [0x00007000020ec000]
 *    java.lang.Thread.State: WAITING (on object monitor)
 *         at java.lang.Object.wait(Native Method)
 *         - waiting on <0x0000000795586bf8> (a java.lang.ref.Reference$Lock)
 *         at java.lang.Object.wait(Object.java:502)
 *         at java.lang.ref.Reference.tryHandlePending(Reference.java:191)
 *         - locked <0x0000000795586bf8> (a java.lang.ref.Reference$Lock)
 *         at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:153)
 *
 * "VM Thread" os_prio=31 tid=0x00007f9d2802c800 nid=0x5303 runnable
 *
 * "GC task thread#0 (ParallelGC)" os_prio=31 tid=0x00007f9d29800000 nid=0x1f07 runnable
 *
 * "GC task thread#1 (ParallelGC)" os_prio=31 tid=0x00007f9d29801000 nid=0x1e03 runnable
 *
 * "GC task thread#2 (ParallelGC)" os_prio=31 tid=0x00007f9d2880c800 nid=0x2b03 runnable
 *
 * "GC task thread#3 (ParallelGC)" os_prio=31 tid=0x00007f9d2880d800 nid=0x2c03 runnable
 *
 * "VM Periodic Task Thread" os_prio=31 tid=0x00007f9d29029800 nid=0x4503 waiting on condition
 *
 * JNI global references: 5
 *
 *
 * Found one Java-level deadlock:
 * =============================
 * "BBB":
 *   waiting to lock monitor 0x00007f9d28031b68 (object 0x00000007955f3ce0, a java.lang.String),
 *   which is held by "AAA"
 * "AAA":
 *   waiting to lock monitor 0x00007f9d28034558 (object 0x00000007955f3d18, a java.lang.String),
 *   which is held by "BBB"
 *
 * Java stack information for the threads listed above:
 * ===================================================
 * "BBB":
 *         at com.nuc.zp.juc.thread.HoldLockThred.run(DeadLockDemo.java:21)
 *         - waiting to lock <0x00000007955f3ce0> (a java.lang.String)
 *         - locked <0x00000007955f3d18> (a java.lang.String)
 *         at java.lang.Thread.run(Thread.java:748)
 * "AAA":
 *         at com.nuc.zp.juc.thread.HoldLockThred.run(DeadLockDemo.java:21)
 *         - waiting to lock <0x00000007955f3d18> (a java.lang.String)
 *         - locked <0x00000007955f3ce0> (a java.lang.String)
 *         at java.lang.Thread.run(Thread.java:748)
 *
 * Found 1 deadlock.
 *
 * localhost:juc_study mdk$
 */
public class DeadLockDemo {
    public static void main(String[] args) {

        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThred(lockA,lockB),"AAA").start();
        new Thread(new HoldLockThred(lockB,lockA),"BBB").start();
    }
}
