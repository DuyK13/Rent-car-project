package com.iuh.rencar_project.utils.mapper.annotation;

import org.mapstruct.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/8/2021 2:00 PM
 */
@Qualifier
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface TagToStringMapping {
}
