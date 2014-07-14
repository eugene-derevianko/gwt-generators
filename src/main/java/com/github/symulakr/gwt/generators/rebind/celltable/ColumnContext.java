package com.github.symulakr.gwt.generators.rebind.celltable;

import com.github.symulakr.gwt.generators.annotation.celltable.Column;
import com.github.symulakr.gwt.generators.annotation.celltable.ColumnStyle;
import com.github.symulakr.gwt.generators.annotation.celltable.HtmlHeader;
import com.github.symulakr.gwt.generators.client.celltable.DefaultFieldUpdater;
import com.github.symulakr.gwt.generators.client.celltable.DefaultValue;
import com.github.symulakr.utils.StringUtils;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

public class ColumnContext extends AbstractContext
{

   private String header;
   private JClassType reachHeader;
   private String columnName;
   private JType cellDataType;
   private JClassType cellType;
   private JMethod getter;
   private JClassType fieldUpdater;
   private ColumnStyleContext styleContext;
   private int index;
   private boolean isTextCell = false;

   public ColumnContext(TypeOracle typeOracle, JMethod method) throws NotFoundException
   {
      super(typeOracle);
      Column column = method.getAnnotation(Column.class);
      columnName = method.getName() + "Column";
      getter = method;
      decideCellType(method.getReturnType(), column.cellType());
      index = column.index();
      extractHeader(column, method);
      if (column.fieldUpdater() != DefaultFieldUpdater.class)
      {
         fieldUpdater = findType(column.fieldUpdater());
      }
      if (method.isAnnotationPresent(ColumnStyle.class))
      {
         styleContext = new ColumnStyleContext(typeOracle, method);
      }
      if (method.isAnnotationPresent(HtmlHeader.class))
      {
         HtmlHeader htmlHeader = method.getAnnotation(HtmlHeader.class);
         reachHeader = findType(htmlHeader.value());
      }
   }

   private void decideCellType(JType returnType, Class cellType)
   {
      //todo throw exception for primitive types
      if (DefaultValue.DEFAULT_CELL_TYPE.equals(cellType))
      {
         this.cellDataType = findType(String.class);
         this.isTextCell = true;
      }
      this.cellType = findType(cellType);
      this.cellDataType = returnType;
   }

   private void extractHeader(Column column, JMethod method)
   {
      if (StringUtils.isEmpty(column.header()))
      {
         header = method.getName();
      }
      else
      {
         header = column.header();
      }
   }

   //Getters for Velocity
   //----------------------------------------

   public String getGetterName()
   {
      return getter.getName();
   }

   public boolean isTextCell()
   {
      return isTextCell;
   }

   //----------------------------------------


   public int getIndex()
   {
      return index;
   }

   public String getColumnName()
   {
      return columnName;
   }

   public JType getCellDataType()
   {
      return cellDataType;
   }

   public JClassType getCellType()
   {
      return cellType;
   }

   public JMethod getGetter()
   {
      return getter;
   }

   public String getHeader()
   {
      return header;
   }

   public JClassType getFieldUpdater()
   {
      return fieldUpdater;
   }

   public ColumnStyleContext getStyleContext()
   {
      return styleContext;
   }

   public JClassType getReachHeader()
   {
      return reachHeader;
   }
}
