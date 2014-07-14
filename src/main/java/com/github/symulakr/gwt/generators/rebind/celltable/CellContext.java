package com.github.symulakr.gwt.generators.rebind.celltable;

import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JType;

public class CellContext
{

   private boolean isDefaultCell = false;
   private JType returnType;
   private JClassType cellType;

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

   public JClassType getCellType()
   {
      return cellType;
   }

   public void setCellType(JClassType cellType)
   {
      this.cellType = cellType;
   }

}
