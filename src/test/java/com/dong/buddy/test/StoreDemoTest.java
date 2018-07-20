package com.dong.buddy.test;

public class StoreDemoTest
{

    public static void main(String[] args) throws Exception
    {

        new ThreadTestUtil(1, 1, () -> {
            try
            {
                TreadTest.httpsRequest(
                        "http://172.16.80.175:8080//ECW-Mall-WXMP/mp/goods/800-000046.ihtml?token=6b0e90c9c2508c6901b643c8c2e1ecbb0ada4346ec30ac278b8d1c62636438b77013dbef8621ea54289cf3df6ce70deb",
                        "GET", null);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
    }
}
