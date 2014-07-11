package com.github.symulakr.gwt.generators.rebind.celltable;

import com.github.symulakr.gwt.generators.annotation.celltable.Column;
import com.github.symulakr.gwt.generators.annotation.celltable.ColumnStyle;
import com.github.symulakr.gwt.generators.annotation.celltable.HtmlHeader;
import com.github.symulakr.gwt.generators.client.celltable.EmptyFieldUpdater;
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

   public ColumnContext(TypeOracle typeOracle, JMethod method) throws NotFoundException
   {
      super(typeOracle);
      Column column = method.getAnnotation(Column.class);
      columnName = method.getName() + "Column";
      cellType = findType(column.cellType());
      getter = method;
      cellDataType = getter.getReturnType();
      index = column.index();
      extractHeader(column, method);
      if (column.fieldUpdater() != EmptyFieldUpdater.class)
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
