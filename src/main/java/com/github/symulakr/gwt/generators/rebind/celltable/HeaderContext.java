package com.github.symulakr.gwt.generators.rebind.celltable;

public class HeaderContext
{

   private Class headerValue;
   private Class headerUiObject;
   private String stringValue;

   public Class getHeaderValue()
   {
      return headerValue;
   }

   public void setHeaderValue(Class headerValue)
   {
      this.headerValue = headerValue;
   }

   public Class getHeaderUiObject()
   {
      return headerUiObject;
   }

   public void setHeaderUiObject(Class headerUiObject)
   {
      this.headerUiObject = headerUiObject;
   }

   public String getStringValue()
   {
      return stringValue;
   }

   public void setStringValue(String stringValue)
   {
      this.stringValue = stringValue;
   }

   public boolean isHtmlHeader()
   {
      return headerUiObject != null || headerValue != null;
   }

}
