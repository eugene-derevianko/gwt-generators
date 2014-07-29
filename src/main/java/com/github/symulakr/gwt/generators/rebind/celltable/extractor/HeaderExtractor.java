package com.github.symulakr.gwt.generators.rebind.celltable.extractor;

import java.lang.reflect.Constructor;

import com.github.symulakr.gwt.generators.client.celltable.DefaultValue;
import com.github.symulakr.gwt.generators.client.celltable.annotation.Column;
import com.github.symulakr.gwt.generators.client.celltable.annotation.HtmlFooter;
import com.github.symulakr.gwt.generators.client.celltable.annotation.HtmlHeader;
import com.github.symulakr.gwt.generators.rebind.celltable.HeaderContext;
import com.github.symulakr.gwt.generators.rebind.utils.ReflectUtils;
import com.github.symulakr.gwt.generators.rebind.utils.StringUtils;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.client.ui.UIObject;

public class HeaderExtractor
{

   public HeaderContext getHeaderInfo(JMethod annotatedMethod)
   {
      HeaderContext context = new HeaderContext();
      if (annotatedMethod.isAnnotationPresent(HtmlHeader.class))
      {
         HtmlHeader annotation = annotatedMethod.getAnnotation(HtmlHeader.class);
         if (useUiObject(context, annotation.uiObject()))
         {
            return context;
         }
         useStringValue(context, annotation.value(), annotation.text());
      }
      else
      {
         useSimpleHeader(context, annotatedMethod);
      }
      return context;
   }

   public HeaderContext getFooterInfo(JMethod annotatedMethod)
   {
      HeaderContext context = new HeaderContext();
      if (annotatedMethod.isAnnotationPresent(HtmlFooter.class))
      {
         HtmlFooter annotation = annotatedMethod.getAnnotation(HtmlFooter.class);
         if (useUiObject(context, annotation.uiObject()))
         {
            return context;
         }
         useStringValue(context, annotation.value(), annotation.text());
      }
      else
      {
         useSimpleFooter(context, annotatedMethod);
      }
      return context;
   }

   private void useSimpleHeader(HeaderContext context, JMethod annotatedMethod)
   {
      Column column = annotatedMethod.getAnnotation(Column.class);
      context.setStringValue(extractHeader(column.header(), annotatedMethod));
   }

   private void useSimpleFooter(HeaderContext context, JMethod annotatedMethod)
   {
      Column column = annotatedMethod.getAnnotation(Column.class);
      context.setStringValue(StringUtils.isNotEmpty(column.footer()) ? column.footer() : null);
   }

   private boolean useUiObject(HeaderContext context, Class<? extends UIObject> uiObject)
   {
      if (!DefaultValue.DEFAULT_UIOBJECT_TYPE.equals(uiObject))
      {
         context.setHeaderUiObject(uiObject);
         return true;
      }
      return false;
   }

   private String extractHeader(String candidate, JMethod annotatedMethod)
   {
      return StringUtils.isNotEmpty(candidate) ? candidate : annotatedMethod.getName();
   }

   private void useStringValue(HeaderContext context, Class<? extends Header> value, String text)
   {
      if (hasConstructorBySafeHtml(value) && StringUtils.isNotEmpty(text))
      {
         context.setHeaderValue(value);
         context.setStringValue(text);
      }
      else if (ReflectUtils.hasDefaultConstructor(value))
      {
         context.setHeaderValue(value);
      }
      else
      {
         context.setStringValue(text);
      }
   }

   private boolean hasConstructorBySafeHtml(Class<? extends Header> value)
   {
      Constructor[] constructors = value.getDeclaredConstructors();
      for (Constructor constructor : constructors)
      {
         Class[] parameters = constructor.getParameterTypes();
         if (parameters.length == 1)
         {
            if (ReflectUtils.isPublic(constructor) && SafeHtml.class.isAssignableFrom(parameters[0]))
            {
               return true;
            }
         }
      }
      return false;
   }

}
