package com.github.symulakr.gwt.generators.client.celltable.annotation;

import static com.github.symulakr.gwt.generators.client.celltable.DefaultValue.EMPTY_STRING;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.gwt.thirdparty.guava.common.annotations.Beta;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.SafeHtmlHeader;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

@Beta
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface HtmlFooter
{
   Class<? extends Header> value() default SafeHtmlHeader.class;

   Class<? extends UIObject> uiObject() default Widget.class;

   String text() default EMPTY_STRING;
}
