package com.github.symulakr.gwt.generators.annotation.celltable;

import static com.github.symulakr.gwt.generators.client.celltable.DefaultValue.DEFAULT_CELL_TYPE;
import static com.github.symulakr.gwt.generators.client.celltable.DefaultValue.EMPTY_STRING;
import static com.github.symulakr.gwt.generators.client.celltable.DefaultValue.FIELD_UPDATER;
import static com.github.symulakr.gwt.generators.client.celltable.DefaultValue.UNSPECIFIED;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.FieldUpdater;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Column
{

   String header() default EMPTY_STRING;

   Class<? extends FieldUpdater> fieldUpdater() default FIELD_UPDATER;

   Class<? extends AbstractCell> cellType() default DEFAULT_CELL_TYPE;

   int index() default UNSPECIFIED;

}
