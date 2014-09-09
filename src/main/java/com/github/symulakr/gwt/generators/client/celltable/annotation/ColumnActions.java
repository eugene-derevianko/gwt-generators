package com.github.symulakr.gwt.generators.client.celltable.annotation;

import static com.github.symulakr.gwt.generators.client.celltable.DefaultValue.UNSPECIFIED;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.symulakr.gwt.generators.client.celltable.DefaultFieldUpdater;
import com.google.gwt.cell.client.FieldUpdater;


@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface ColumnActions
{

   Class<? extends FieldUpdater> fieldUpdater() default DefaultFieldUpdater.class;

   int position() default UNSPECIFIED;

   boolean enableSorting() default false;

}
