package com.github.symulakr.gwt.generators.rebind.celltable;

import com.github.symulakr.gwt.generators.annotation.celltable.Column;
import com.github.symulakr.gwt.generators.annotation.celltable.ColumnStyle;
import com.github.symulakr.gwt.generators.annotation.celltable.HtmlHeader;
import com.github.symulakr.gwt.generators.client.celltable.EmptyFieldUpdater;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import org.apache.commons.lang3.StringUtils;


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

   public ColumnContext(TypeOracle typeOracle, JClassType modelType, JField field) throws NotFoundException
   {
      super(typeOracle);
      Column column = field.getAnnotation(Column.class);
      columnName = field.getName() + "Column";
      cellType = findType(column.cellType());
      setGetter(column, modelType, field);
      cellDataType = getter.getReturnType();
      extractHeader(column, field);
      if (column.fieldUpdater() != EmptyFieldUpdater.class)
      {
         fieldUpdater = findType(column.fieldUpdater());
      }
      if (field.isAnnotationPresent(ColumnStyle.class))
      {
         styleContext = new ColumnStyleContext(typeOracle, field);
      }
      if (field.isAnnotationPresent(HtmlHeader.class))
      {
         HtmlHeader htmlHeader = field.getAnnotation(HtmlHeader.class);
         reachHeader = findType(htmlHeader.value());
      }
   }

   private void extractHeader(Column column, JField field)
   {
      if (StringUtils.isEmpty(column.header()))
      {
         header = field.getName();
      }
      else
      {
         header = column.header();
      }
   }

   private void setGetter(Column column, JClassType modelType, JField field) throws NotFoundException
   {
      if (StringUtils.isNotEmpty(column.getter()))
      {
         getter = modelType.getMethod(column.getter(), new JType[]
         {});
      }
      else
      {
         String fieldName = field.getName();
         for (JMethod method : modelType.getMethods())
         {
            if (method.getName()
                  .toLowerCase()
                  .contains("get" + fieldName.toLowerCase()))
            {
               getter = method;
               return;
            }
         }
         throw new NotFoundException();
      }
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
