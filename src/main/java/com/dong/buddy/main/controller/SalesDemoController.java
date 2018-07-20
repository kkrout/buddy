package com.dong.buddy.main.controller;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dong.buddy.exception.CommonException;

@RestController
public class SalesDemoController
{

    @Autowired
    private RedissonClient redisson;

    @RequestMapping(value = "/api/publish/{prodNo}/{num}")
    public String publish(@PathVariable("prodNo") String prodNo, @PathVariable("num") String num) throws Exception
    {
        RMap<Object, Object> map = redisson.getMap("sales_prodno");
        map.put(prodNo, num);
        return "OK";
    }

    @RequestMapping(value = "/api/qiang/{prodNo}/{num}")
    public String qiang(@PathVariable("prodNo") String prodNo, @PathVariable("num") String numStr,
            HttpServletRequest request) throws Exception
    {
        String sessionId = UUID.randomUUID().toString();
        // 缓存抢购记录
        RMap<Object, Object> map = redisson.getMap("qiang_prodno_" + prodNo);
        map.put(sessionId, numStr);
        RLock lock = null;
        RMap<Object, Object> mapProds = null;
        try
        {
            int num = Integer.parseInt(numStr);

            mapProds = redisson.getMap("sales_prodno");
            // 对操作数据进行加锁
            lock = mapProds.getLock(prodNo);
            lock.lock(5, TimeUnit.SECONDS);
            String numStore = (String) mapProds.get(prodNo);
            int numStoreInt = Integer.parseInt(numStore);

            int shenyu = numStoreInt - num;

            if (shenyu < 0)
            {
                throw new CommonException("数量不足", "bus.00021321");
            }

            mapProds.put(prodNo, shenyu);
        }
        catch (NumberFormatException e)
        {
            throw new CommonException("数量转化有误", "sys.00005");
        }
        finally
        {
            if (lock != null)
            {
                lock.unlock();
            }
        }
        return "OK";
    }

}
