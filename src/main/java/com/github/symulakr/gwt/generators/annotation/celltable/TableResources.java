package com.github.symulakr.gwt.generators.annotation.celltable;

import com.github.symulakr.gwt.generators.client.celltable.CellTableRes;
import com.google.gwt.user.cellview.client.CellTable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TableResources
{
   Class<? extends CellTable.Resources> value() default CellTableRes.class;
}
