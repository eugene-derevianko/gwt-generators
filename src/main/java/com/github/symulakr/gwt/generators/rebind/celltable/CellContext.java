package com.github.symulakr.gwt.generators.rebind.celltable;

import com.google.gwt.core.ext.typeinfo.JType;

public class CellContext
{

   private boolean thisIsTextCell = false;
   private JType returnType;
   private Class cellType;
   private JType rawReturnType;

   public CellContext(JType rawReturnType)
   {
      this.rawReturnType = rawReturnType;
   }

   public boolean isThisTextCell()
   {
      return thisIsTextCell;
   }

   public void markAsTextCell(boolean thisIsTextCell)
   {
      this.thisIsTextCell = thisIsTextCell;
   }

   public JType getReturnType()
   {
      return returnType;
   }

   public void setReturnType(JType returnType)
   {
      this.returnType = returnType;
   }

   public Class getCellType()
   {
      return cellType;
   }

   public void setCellType(Class cellType)
   {
      this.cellType = cellType;
   }

   public JType getRawReturnType()
   {
      return rawReturnType;
   }

   public void setRawReturnType(JType rawReturnType)
   {
      this.rawReturnType = rawReturnType;
   }

   public static enum DefaultReturnType
   {
      STRING("String"),
      SAFE_HTML("SafeHtml"),
      NUMBER("? extends Number");

      private String description;

      DefaultReturnType(String description)
      {
         this.description = description;
      }

      @Override
      public String toString()
      {
         return description;
      }

   }
}
