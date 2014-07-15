package com.github.symulakr.gwt.generators.annotation.celltable;

import static com.github.symulakr.gwt.generators.client.celltable.DefaultValue.EMPTY_STRING;
import static com.github.symulakr.gwt.generators.client.celltable.DefaultValue.UNSPECIFIED;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.symulakr.gwt.generators.client.celltable.DefaultFieldUpdater;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Column
{

   String header() default EMPTY_STRING;

   Class<? extends FieldUpdater> fieldUpdater() default DefaultFieldUpdater.class;

   Class<? extends AbstractCell> cellType() default TextCell.class;

   int position() default UNSPECIFIED;

}
