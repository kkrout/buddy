package com.dong.buddy.mulit;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.dong.buddy.mongo.EntityFactory;
import com.dong.buddy.mongo.entity.BaseEntity;
import com.dong.buddy.mongo.entity.UserEntity;

public class MulitTask
{

    private static ThreadLocal<Map<String, Future<?>>> local = new ThreadLocal<>();

    private static ExecutorService threadPool;

    static
    {
        local.set(new HashMap<>());
        threadPool = Executors.newCachedThreadPool();
    }

    public static void put(String k, Callable<?> task) throws InterruptedException, ExecutionException
    {
        local.get().put(k, threadPool.submit(task));
    }

    public static <T> T get(String k) throws InterruptedException, ExecutionException
    {
        local.get().get(k);
        return null;// (T) local.get().get(k).get();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        put("test", () -> {
            Thread.sleep(2000);
            return EntityFactory.createBaseEntity(UserEntity.class);
        });
        BaseEntity t = get("test");
        // System.out.println(new JSONObject(t));
        System.out.println(t);
        System.out.println(local.get().get("test").get());
        threadPool.shutdown();
    }

}
