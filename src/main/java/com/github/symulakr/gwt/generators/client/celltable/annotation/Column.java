package com.github.symulakr.gwt.generators.client.celltable.annotation;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.TextCell;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.github.symulakr.gwt.generators.client.celltable.DefaultValue.EMPTY_STRING;

/**
 * Indicates that method should be represented as column in CellTable
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface Column
{

   String header() default EMPTY_STRING;

   String footer() default EMPTY_STRING;

   Class<? extends AbstractCell> cellType() default TextCell.class;

}
