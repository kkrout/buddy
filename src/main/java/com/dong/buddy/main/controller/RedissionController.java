// package com.dong.buddy.main.controller;
//
// import java.util.concurrent.TimeUnit;
//
// import javax.servlet.http.HttpServletRequest;
//
// import org.redisson.api.RFuture;
// import org.redisson.api.RLock;
// import org.redisson.api.RedissonClient;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseBody;
//
// @Controller
// @RequestMapping(value = "/api/redssion")
// public class RedissionController
// {
//
// @Autowired
// private RedissonClient redissonClient;
//
// @RequestMapping(value = "/lock/{lockerName}")
// @ResponseBody
// public String normarlLock(@PathVariable() String lockerName) throws Exception
// {
// RLock lock = null;
// try
// {
// lock = redissonClient.getLock(lockerName);
// boolean tryLock = lock.tryLock(2, 10, TimeUnit.SECONDS);
// // boolean tryLock = lock.tryLock();
// if (tryLock)
// {
// return "得到锁------------------------";
// }
// }
// finally
// {
// if (lock != null)
// lock.unlock();
// }
// return "没有得到锁";
// }
//
// @RequestMapping(value = "/ayncLock/{lockerName}")
// @ResponseBody
// public String ayncLock(@PathVariable() String lockerName) throws Exception
// {
// RLock lock = null;
// try
// {
// lock = redissonClient.getLock(lockerName);
// RFuture<Boolean> lockFutrure = lock.tryLockAsync(30, 100, TimeUnit.SECONDS);
// // boolean tryLock = lock.tryLock();
// if (lockFutrure.get())
// {
// return "得到锁------------------------";
// }
// }
// finally
// {
// if (lock != null)
// lock.unlock();
// }
// return "没有得到锁";
// }
//
// @RequestMapping(value = "/session")
// @ResponseBody
// public String sessionGet(HttpServletRequest req)
// {
// return (String) req.getSession().getAttribute(req.getParameter("key"));
// }
//
// @RequestMapping(value = "/sessionPut")
// @ResponseBody
// public String sessionPut(HttpServletRequest req)
// {
// req.getSession().setAttribute(req.getParameter("key"), "test");
// return "OK";
// }
//
// static int count = 0;
//
// @RequestMapping(value = "/notify/{lockerName}")
// @ResponseBody
// public String notifyLock(@PathVariable() String lockerName) throws Exception
// {
// RLock lock = null;
// try
// {
// lock = redissonClient.getLock(lockerName);
// boolean tryLock = lock.tryLock();
// if (tryLock)
// {
// Thread.sleep(5000);
// return "第一个拿到锁";
// }
// else
// {
// while (!lock.isLocked())
// {
// Thread.sleep(1000);
// }
// return "锁已经释放，其他人可以那东西了";
// }
// }
// finally
// {
// if (lock != null)
// lock.forceUnlock();
// }
// }
//
// }
