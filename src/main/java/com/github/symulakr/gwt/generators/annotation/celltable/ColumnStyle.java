package com.github.symulakr.gwt.generators.annotation.celltable;

import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.VerticalAlign;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ColumnStyle
{

   TextAlign horizontalAlignment() default TextAlign.CENTER;

   VerticalAlign verticalAlignment() default VerticalAlign.MIDDLE;

}
