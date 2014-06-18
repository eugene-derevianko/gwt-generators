package com.github.symulakr.gwt.generators.rebind.celltable;

import com.github.symulakr.gwt.generators.annotation.celltable.Column;
import com.github.symulakr.gwt.generators.annotation.celltable.HtmlHeader;
import com.github.symulakr.gwt.generators.annotation.celltable.Table;
import com.github.symulakr.gwt.generators.annotation.celltable.TableResources;
import com.github.symulakr.gwt.generators.client.celltable.CellTableRes;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

import java.util.ArrayList;
import java.util.List;

public class TableContext extends AbstractContext
{

   private JClassType resourceType;
   private List<ColumnContext> columns;
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

   private void extractResourceType(JClassType modelType) throws NotFoundException
   {
      if (modelType.isAnnotationPresent(TableResources.class))
      {
         TableResources tableResources = modelType.getAnnotation(TableResources.class);
         resourceType = findType(tableResources.value());
      }
      else
      {
         resourceType = findType(CellTableRes.class);
      }
   }

   private void extractColumns(TypeOracle typeOracle, JClassType modelType) throws NotFoundException
   {
      columns = new ArrayList<ColumnContext>();
      for (JField field : modelType.getFields())
      {
         if (field.isAnnotationPresent(HtmlHeader.class))
         {
            hasHtmlHeader = true;
         }
         if (field.isAnnotationPresent(Column.class))
         {
            columns.add(new ColumnContext(typeOracle, modelType, field));
         }
      }
   }

   public JClassType getResourceType()
   {
      return resourceType;
   }

   public List<ColumnContext> getColumns()
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
