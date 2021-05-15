package com.iuh.rencar_project.utils.mapper.annotation;

import org.mapstruct.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Trần Thế Duy
 * @datetime May 2, 2021 2:33:48 AM
 * @version 0.1
 */
@Qualifier
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.CLASS)
public @interface StatusEncoderMapping {

}
