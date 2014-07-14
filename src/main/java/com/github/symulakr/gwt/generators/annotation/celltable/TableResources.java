package com.github.symulakr.gwt.generators.annotation.celltable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.symulakr.gwt.generators.client.celltable.DefaultValue;
import com.google.gwt.user.cellview.client.CellTable.Resources;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TableResources
{
   Class<? extends Resources> value() default DefaultValue.DEFAULT_RESOURCES;
}
