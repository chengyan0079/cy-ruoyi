package com.cy.ruoyi.common.redis.annotation;

import java.lang.annotation.*;

/**
 * <p>Title: redis删除注解</p>
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisEvict
{
    String key();

    String fieldKey();
}