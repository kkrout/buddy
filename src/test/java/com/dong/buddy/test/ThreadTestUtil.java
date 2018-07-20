package com.dong.buddy.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTestUtil
{

    private int maxNum;
    private int activeNum;
    private Runnable task;

    private ExecutorService newFixedThreadPool = null;

    public ThreadTestUtil(int maxNum, int activeNum, Runnable task) throws CloneNotSupportedException
    {
        this.maxNum = maxNum;
        this.activeNum = activeNum;
        this.task = task;
        init();
    }

    public void init() throws CloneNotSupportedException
    {
        newFixedThreadPool = Executors.newFixedThreadPool(activeNum);
        for (int i = 0; i < maxNum; i++)
        {
            // Runnable task_ = CloneUtils.cloneObject(task);
            newFixedThreadPool.execute(task);
        }
        newFixedThreadPool.shutdown();
    }

}
