package com.github.symulakr.gwt.generators.rebind.celltable;

import com.google.gwt.core.ext.typeinfo.JType;

public class CellContext
{

   private boolean isDefaultCell = false;
   private JType returnType;
   private Class cellType;
   private JType rawReturnType;

   public CellContext(JType rawReturnType)
   {
      this.rawReturnType = rawReturnType;
   }

   public boolean isDefaultCell()
   {
      return isDefaultCell;
   }

   public void setDefaultCell(boolean isDefaultCell)
   {
      this.isDefaultCell = isDefaultCell;
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
}
