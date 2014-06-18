package com.github.symulakr.gwt.generators.annotation.celltable;


import com.github.symulakr.gwt.generators.client.celltable.CellTableModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table
{
   Class<? extends CellTableModel> parameterisingType();
}
