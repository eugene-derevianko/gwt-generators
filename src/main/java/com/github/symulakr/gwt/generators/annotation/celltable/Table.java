package com.github.symulakr.gwt.generators.annotation.celltable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.gwt.user.cellview.client.CellTable;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Table
{
   Class<? extends CellTable> value() default CellTable.class;
}
