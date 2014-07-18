package com.github.symulakr.gwt.generators.rebind.celltable;

import org.apache.commons.lang3.StringUtils;

import com.github.symulakr.gwt.generators.annotation.celltable.Column;
import com.github.symulakr.gwt.generators.annotation.celltable.ColumnAlignment;
import com.github.symulakr.gwt.generators.annotation.celltable.HtmlHeader;
import com.github.symulakr.gwt.generators.client.celltable.DefaultFieldUpdater;
import com.github.symulakr.gwt.generators.rebind.celltable.extractor.CellInfoExtractor;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

public class ColumnContext
{

   private String header;
   private Class reachHeader;
   private String columnName;
   private JMethod annotatedMethod;
   private Class fieldUpdater;
   private ColumnAlignmentContext alignmentContext;
   private int index;
   private boolean sortable = false;
   private CellContext cell;

   public ColumnContext(TypeOracle typeOracle, JMethod annotatedMethod) throws NotFoundException
   {
      this.annotatedMethod = annotatedMethod;
      Column column = annotatedMethod.getAnnotation(Column.class);
      columnName = annotatedMethod.getName() + "Column";
      cell = decideCell(typeOracle);
      index = column.position();
      sortable = column.enableSorting();
      extractHeader(column);
      if (column.fieldUpdater() != DefaultFieldUpdater.class)
      {
         fieldUpdater = column.fieldUpdater();
      }
      if (annotatedMethod.isAnnotationPresent(ColumnAlignment.class))
      {
         alignmentContext = new ColumnAlignmentContext(annotatedMethod);
      }
      if (annotatedMethod.isAnnotationPresent(HtmlHeader.class))
      {
         HtmlHeader htmlHeader = annotatedMethod.getAnnotation(HtmlHeader.class);
         reachHeader = htmlHeader.value();
      }
   }

   private CellContext decideCell(TypeOracle typeOracle)
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

   // -----Getters for Velocity-----------

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

   public Class getCellType()
   {
      return cell.getCellType();
   }

   public String getHeader()
   {
      return header;
   }

   public Class getFieldUpdater()
   {
      return fieldUpdater;
   }

   public ColumnAlignmentContext getAlignmentContext()
   {
      return alignmentContext;
   }

   public boolean isSortable()
   {
      return sortable;
   }

   // ----------------------------------------

   public int getIndex()
   {
      return index;
   }

   public Class getReachHeader()
   {
      return reachHeader;
   }
}
