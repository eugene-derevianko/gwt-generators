package com.github.symulakr.gwt.generators.rebind.celltable;

import com.github.symulakr.gwt.generators.annotation.celltable.Column;
import com.github.symulakr.gwt.generators.annotation.celltable.ColumnStyle;
import com.github.symulakr.gwt.generators.annotation.celltable.HtmlHeader;
import com.github.symulakr.gwt.generators.client.celltable.DefaultFieldUpdater;
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
   private JMethod annotatedMethod;
   private JClassType fieldUpdater;
   private ColumnStyleContext styleContext;
   private int index;
   private CellContext cell;

   public ColumnContext(TypeOracle typeOracle, JMethod annotatedMethod) throws NotFoundException
   {
      super(typeOracle);
      this.annotatedMethod = annotatedMethod;
      Column column = annotatedMethod.getAnnotation(Column.class);
      columnName = annotatedMethod.getName() + "Column";
      cell = decideCell();
      index = column.index();
      extractHeader(column);
      if (column.fieldUpdater() != DefaultFieldUpdater.class)
      {
         fieldUpdater = findType(column.fieldUpdater());
      }
      if (annotatedMethod.isAnnotationPresent(ColumnStyle.class))
      {
         styleContext = new ColumnStyleContext(annotatedMethod);
      }
      if (annotatedMethod.isAnnotationPresent(HtmlHeader.class))
      {
         HtmlHeader htmlHeader = annotatedMethod.getAnnotation(HtmlHeader.class);
         reachHeader = findType(htmlHeader.value());
      }
   }

   private CellContext decideCell()
   {
      CellInfoExtractor cellInfoExtractor = new CellInfoExtractor(typeOracle);
      return cellInfoExtractor.getCellInfo(annotatedMethod);
   }

   private void extractHeader(Column column)
   {
      if (StringUtils.isEmpty(column.header()))
      {
         header = annotatedMethod.getName();
      }
      else
      {
         header = column.header();
      }
   }

   //-----Getters for Velocity-----------

   public String getColumnName()
   {
      return columnName;
   }

   public String getGetterName()
   {
      return annotatedMethod.getName();
   }

   public boolean isDefaultCell()
   {
      return cell.isDefaultCell();
   }

   public JType getReturnType()
   {
      return cell.getReturnType();
   }

   public JClassType getCellType()
   {
      return cell.getCellType();
   }

   //----------------------------------------


   public int getIndex()
   {
      return index;
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
