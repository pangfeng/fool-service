package com.github.yfge.fool.common.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlGenerate {
    SqlGenerateConfig value() default SqlGenerateConfig.AUTO_INCREMENT;
}