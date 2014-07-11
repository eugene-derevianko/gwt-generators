package com.github.symulakr.gwt.generators.annotation.celltable;

import com.github.symulakr.gwt.generators.client.celltable.EmptyFieldUpdater;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Column
{

   String header() default "";
   Class<? extends FieldUpdater> fieldUpdater() default EmptyFieldUpdater.class;
   Class<? extends AbstractCell> cellType() default TextCell.class;

}
