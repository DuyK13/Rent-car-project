package com.iuh.rencar_project.utils.mapper.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.mapstruct.Qualifier;

/**
 * @author Trần Thế Duy
 * @datetime May 2, 2021 2:17:11 AM
 * @version 0.1
 */

@Qualifier
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.CLASS)
public @interface StringToRoleMapping {

}
