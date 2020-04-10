package com.example.bbcb.annotation;

import java.lang.annotation.*;

//@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Fruit {

    String value() default "vv";
}
