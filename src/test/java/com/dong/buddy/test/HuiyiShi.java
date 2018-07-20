package com.dong.buddy.test;

import java.util.concurrent.CountDownLatch;

public class HuiyiShi {


    private static CountDownLatch latch = new CountDownLatch(5);


    public static void main(String[] args) {

        for (int i=0;i<5;i++){
            new Thread(){
                @Override
                public void run() {
                    System.out.println("人员到达");
                    latch.countDown();
                }
            };
        }

    }
}
