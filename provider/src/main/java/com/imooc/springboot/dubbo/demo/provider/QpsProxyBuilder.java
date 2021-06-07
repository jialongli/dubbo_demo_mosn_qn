package com.imooc.springboot.dubbo.demo.provider;

public class QpsProxyBuilder {
    private QpsProxy qpsProxy = new QpsProxy();

    public static QpsProxyBuilder newBuilder() {
        return new QpsProxyBuilder();

    }

    public QpsProxyBuilder withDelay2Start(long delay2TimeByMillisSeconds) {

        qpsProxy.setDelay2Start(delay2TimeByMillisSeconds);

        return this;

    }

    public QpsProxyBuilder withQps(long qps) {

        qpsProxy.setQps(qps);

        return this;

    }

    public QpsProxyBuilder withRunnable(Runnable runnable) {

        qpsProxy.setRunnable(runnable);

        return this;

    }

    public QpsProxyBuilder withThreads(int threads) {

        qpsProxy.setThreads(threads);

        return this;

    }

    public QpsProxy build() {
        return qpsProxy;

    }

}
