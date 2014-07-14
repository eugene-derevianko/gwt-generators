package com.github.symulakr.gwt.generators.annotation.celltable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.symulakr.gwt.generators.client.celltable.DefaultValue;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.VerticalAlign;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ColumnStyle
{

   TextAlign horizontalAlignment() default DefaultValue.DEFAULT_HORIZONTAL_ALIGNMENT;

   VerticalAlign verticalAlignment() default DefaultValue.DEFAULT_VERTICAL_ALIGNMENT;

}
