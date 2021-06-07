package com.imooc.springboot.dubbo.demo.provider;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;

import java.util.concurrent.ScheduledExecutorService;

import java.util.concurrent.TimeUnit;

/**
 * 若Runable执行的时间超过 MAX_QPS / qps，则实际的QPS会低于实际的值。
 */

public class QpsProxy {
    private final Logger logger = LoggerFactory.getLogger(QpsProxy.class);

    private final static int MAX_QPS = 1000;

    private ScheduledExecutorService scheduledExecutorService;

    private long qps = 1;

    private Runnable runnable;

    private long delay2Start = 0;

    private int threads = 10;

    public QpsProxy() {
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;

    }

    public void setScheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = scheduledExecutorService;

    }

    public long getQps() {
        return qps;

    }

    public void setQps(long qps) {
        this.qps = qps;

    }

    public Runnable getRunnable() {
        return runnable;

    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;

    }

    public long getDelay2Start() {
        return delay2Start;

    }

    public void setDelay2Start(long delay2Start) {
        this.delay2Start = delay2Start;

    }

    public int getThreads() {
        return threads;

    }

    public void setThreads(int threads) {
        this.threads = threads;

    }

    public void start() {
        long period = (long) Math.floor((double) MAX_QPS / qps);

        logger.info("间隔周期:{}ms", period);

        scheduledExecutorService = Executors.newScheduledThreadPool(threads);

        scheduledExecutorService.scheduleAtFixedRate(runnable, delay2Start,

                period, TimeUnit.MILLISECONDS);

    }

    public void stop() {
        scheduledExecutorService.shutdown();

    }

}

