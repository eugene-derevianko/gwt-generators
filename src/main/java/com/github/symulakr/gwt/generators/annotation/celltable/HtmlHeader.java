package com.github.symulakr.gwt.generators.annotation.celltable;

import com.google.gwt.user.client.ui.UIObject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface HtmlHeader
{
   Class<? extends UIObject> value();
}
