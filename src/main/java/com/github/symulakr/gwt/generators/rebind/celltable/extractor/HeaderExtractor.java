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
         fillContext(context, annotation.uiObject(), annotation.value(), annotation.text());
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
         fillContext(context, annotation.uiObject(), annotation.value(), annotation.text());
      }
      else
      {
         useSimpleFooter(context, annotatedMethod);
      }
      return context;
   }

   private void fillContext(HeaderContext context, Class<? extends UIObject> uiObject, Class<? extends Header> header, String text)
   {
      if (useUiObject(context, uiObject, text))
      {
         return;
      }
      if (useHeaderObject(context, header, text))
      {
         return;
      }
      context.setStringValue(text);
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

   private boolean useUiObject(HeaderContext context, Class<? extends UIObject> uiObject, String text)
   {
      if (!DefaultValue.DEFAULT_UIOBJECT_TYPE.equals(uiObject))
      {
         context.setHeaderUiObject(uiObject);
         useStringValue(context, uiObject, text);
         return true;
      }
      return false;
   }

   private boolean useHeaderObject(HeaderContext context, Class<? extends Header> header, String text)
   {
      if (useStringValue(context, header, text) || ReflectUtils.hasDefaultConstructor(header))
      {
         context.setHeaderValue(header);
         return true;
      }
      return false;
   }

   private String extractHeader(String candidate, JMethod annotatedMethod)
   {
      return StringUtils.isNotEmpty(candidate) ? candidate : annotatedMethod.getName();
   }

   private boolean useStringValue(HeaderContext context, Class clazz, String text)
   {
      if (hasConstructorBySafeHtml(clazz) && StringUtils.isNotEmpty(text))
      {
         context.setStringValue(text);
         return true;
      }
      return false;
   }

   private boolean hasConstructorBySafeHtml(Class clazz)
   {
      Constructor[] constructors = clazz.getDeclaredConstructors();
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
