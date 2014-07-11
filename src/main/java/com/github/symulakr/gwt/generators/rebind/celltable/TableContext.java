package com.github.symulakr.gwt.generators.rebind.celltable;

import com.github.symulakr.gwt.generators.annotation.celltable.Table;
import com.github.symulakr.gwt.generators.annotation.celltable.TableResources;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.cellview.client.CellTable.Resources;

public class TableContext extends AbstractContext
{

   private JClassType resourceType = findType(Resources.class);
   private ColumnContext[] columns;
   private JClassType parameterisingType;
   private boolean hasHtmlHeader = false;

   public TableContext(TypeOracle typeOracle, JClassType modelType) throws NotFoundException
   {
      super(typeOracle);
      extractParameterisingType(modelType);
      extractResourceType(modelType);
      extractColumns(typeOracle, modelType);
   }

   private void extractParameterisingType(JClassType modelType)
   {
      if (modelType.isAnnotationPresent(Table.class))
      {
         Table table = modelType.getAnnotation(Table.class);
         parameterisingType = findType(table.parameterisingType());
      }
      else
      {
         parameterisingType = modelType;
      }
   }

   private void extractResourceType(JClassType modelType)
   {
      if (modelType.isAnnotationPresent(TableResources.class))
      {
         TableResources tableResources = modelType.getAnnotation(TableResources.class);
         resourceType = findType(tableResources.value());
      }
   }

   private void extractColumns(TypeOracle typeOracle, JClassType modelType) throws NotFoundException
   {
      columns = ColumnExtractor.extractColumns(typeOracle, modelType);
      for (ColumnContext columnContext : columns)
      {
         if (columnContext.getReachHeader() != null)
         {
            hasHtmlHeader = true;
         }
      }
   }

   public JClassType getResourceType()
   {
      return resourceType;
   }

   public ColumnContext[] getColumns()
   {
      return columns;
   }

   public JClassType getParameterisingType()
   {
      return parameterisingType;
   }

   public boolean isHasHtmlHeader()
   {
      return hasHtmlHeader;
   }
}
