package com.dong.buddy.util;

import com.dong.buddy.mongo.entity.SeqInfo;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class UUIDUtil
{

    public static String getId()
    {
        return UUID.randomUUID().toString();
    }

    public synchronized static String getTimeId()
    {
        return String.valueOf(System.currentTimeMillis());
    }

    private static AtomicLong mlock = new AtomicLong();

    public static String getLongId()
    {
        return String.valueOf(mlock.incrementAndGet());
    }

    /**
     * 获取下一个自增ID
     *
     * @param collName 集合（这里用类名，就唯一性来说最好还是存放长类名）名称
     * @return 序列值
     */
    public static Long getNextId(String collName, MongoTemplate mongoTemplate) {
        Query query = new Query(Criteria.where("collName").is(collName));
        Update update = new Update();
        update.inc("seqId", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.upsert(true);
        options.returnNew(true);
        SeqInfo seq = mongoTemplate.findAndModify(query, update, options, SeqInfo.class);
        return seq.getSeqId();
    }

}
