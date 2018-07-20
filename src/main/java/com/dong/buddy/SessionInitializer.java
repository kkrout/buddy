package com.dong.buddy;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

import com.dong.buddy.redis.RedissonConfig;

public class SessionInitializer extends AbstractHttpSessionApplicationInitializer
{
    public SessionInitializer()
    {
        super(RedissonConfig.class);
    }
}
