package com.github.symulakr.gwt.generators.rebind.celltable;

import com.github.symulakr.gwt.generators.client.celltable.DefaultFieldUpdater;
import com.github.symulakr.gwt.generators.client.celltable.DefaultValue;
import com.github.symulakr.gwt.generators.client.celltable.annotation.Column;
import com.github.symulakr.gwt.generators.client.celltable.annotation.ColumnActions;
import com.github.symulakr.gwt.generators.client.celltable.annotation.ColumnAlignment;
import com.github.symulakr.gwt.generators.client.celltable.annotation.HtmlHeader;
import com.github.symulakr.gwt.generators.rebind.celltable.extractor.CellInfoExtractor;
import com.github.symulakr.gwt.generators.rebind.utils.StringUtils;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

public class ColumnContext
{

   private String header;
   private String footer;
   private Class reachHeader;
   private String columnName;
   private JMethod annotatedMethod;
   private Class fieldUpdater;
   private ColumnAlignmentContext alignmentContext;
   private int index = DefaultValue.UNSPECIFIED;
   private boolean sortable = false;
   private CellContext cell;

   public ColumnContext(TypeOracle typeOracle, JMethod annotatedMethod) throws NotFoundException
   {
      this.annotatedMethod = annotatedMethod;
      Column column = annotatedMethod.getAnnotation(Column.class);
      columnName = annotatedMethod.getName() + "Column";
      cell = decideCell(typeOracle);
      decideActions(annotatedMethod);
      extractHeader(column);
      extractFooter(column);
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

   private void decideActions(JMethod annotatedMethod)
   {
      if (annotatedMethod.isAnnotationPresent(ColumnActions.class))
      {
         ColumnActions columnActions = annotatedMethod.getAnnotation(ColumnActions.class);
         index = columnActions.position();
         sortable = columnActions.enableSorting();
         if (columnActions.fieldUpdater() != DefaultFieldUpdater.class)
         {
            fieldUpdater = columnActions.fieldUpdater();
         }
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

   private void extractFooter(Column column)
   {
      if (StringUtils.isNotEmpty(column.footer()))
      {
         footer = column.footer();
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

   public String getFooter()
   {
      return footer;
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
