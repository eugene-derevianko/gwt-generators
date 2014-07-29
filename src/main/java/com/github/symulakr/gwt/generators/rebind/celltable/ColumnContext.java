package com.github.symulakr.gwt.generators.rebind.celltable;

import com.github.symulakr.gwt.generators.client.celltable.DefaultFieldUpdater;
import com.github.symulakr.gwt.generators.client.celltable.DefaultValue;
import com.github.symulakr.gwt.generators.client.celltable.annotation.ColumnActions;
import com.github.symulakr.gwt.generators.client.celltable.annotation.ColumnAlignment;
import com.github.symulakr.gwt.generators.rebind.celltable.extractor.CellInfoExtractor;
import com.github.symulakr.gwt.generators.rebind.celltable.extractor.HeaderExtractor;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

public class ColumnContext
{

   private String columnName;
   private JMethod annotatedMethod;
   private Class fieldUpdater;
   private ColumnAlignmentContext alignmentContext;
   private int index = DefaultValue.UNSPECIFIED;
   private boolean sortable = false;
   private CellContext cell;
   private HeaderContext headerContext;
   private HeaderContext footerContext;

   public ColumnContext(TypeOracle typeOracle, JMethod annotatedMethod) throws NotFoundException
   {
      this.annotatedMethod = annotatedMethod;
      columnName = annotatedMethod.getName() + "Column";
      cell = decideCell(typeOracle);
      decideActions(annotatedMethod);
      extractHeaderAndFooter(annotatedMethod);
      if (annotatedMethod.isAnnotationPresent(ColumnAlignment.class))
      {
         alignmentContext = new ColumnAlignmentContext(annotatedMethod);
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

   private void extractHeaderAndFooter(JMethod annotatedMethod)
   {
      HeaderExtractor headerExtractor = new HeaderExtractor();
      headerContext = headerExtractor.getHeaderInfo(annotatedMethod);
      footerContext = headerExtractor.getFooterInfo(annotatedMethod);
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

   public HeaderContext getHeaderContext()
   {
      return headerContext;
   }

   public HeaderContext getFooterContext()
   {
      return footerContext;
   }

   public boolean hasHtmlHeaders()
   {
      return headerContext.isHtmlHeader() || footerContext.isHtmlHeader();
   }
}
