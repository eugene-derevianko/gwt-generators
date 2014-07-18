package com.github.symulakr.gwt.generators.client.celltable.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.VerticalAlign;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface ColumnAlignment
{

   TextAlign horizontalAlignment() default TextAlign.CENTER;

   VerticalAlign verticalAlignment() default VerticalAlign.MIDDLE;

}
