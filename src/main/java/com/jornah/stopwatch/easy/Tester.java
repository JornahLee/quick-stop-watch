package com.jornah.stopwatch.easy;

public class Tester {

    public long doSomething() {
        long sum = 0;
        for (int i = 0; i <  2; i++) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        Tester tester = new Tester();
        Tester cglibProxy = StopWatchAop.getTimeWatchProxy(tester, new String[]{"doSomething"});//获取代理对象
        Tester cglibProxy2 = StopWatchAop.getTimeWatchProxy(tester, new String[]{"doSomething"});//获取代理对象
        cglibProxy.doSomething();
        cglibProxy2.doSomething();
    }
}
