package com.github.symulakr.gwt.generators.annotation.celltable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.gwt.user.client.ui.UIObject;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface HtmlHeader
{
   Class<? extends UIObject> value();
}
