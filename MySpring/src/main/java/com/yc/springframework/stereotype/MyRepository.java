package com.yc.springframework.stereotype;

import java.lang.annotation.*;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-05 14:26
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRepository {
}
