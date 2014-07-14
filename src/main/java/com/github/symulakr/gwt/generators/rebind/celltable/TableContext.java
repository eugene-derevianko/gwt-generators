package com.github.symulakr.gwt.generators.rebind.celltable;

import com.github.symulakr.gwt.generators.rebind.celltable.extractor.ColumnExtractor;
import com.github.symulakr.gwt.generators.rebind.celltable.extractor.ModelTypeExtractor;
import com.github.symulakr.gwt.generators.rebind.celltable.extractor.ResourceTypeExtractor;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

public class TableContext
{

   private JClassType resourceType;
   private ColumnContext[] columns;
   private JClassType modelType;
   private boolean hasHtmlHeader = false;

   public TableContext(TypeOracle typeOracle, JClassType modelType) throws NotFoundException
   {
      extractModelType(typeOracle, modelType);
      extractResourceType(typeOracle, modelType);
      extractColumns(typeOracle, modelType);
   }

   private void extractModelType(TypeOracle typeOracle, JClassType modelType)
   {
      ModelTypeExtractor modelTypeExtractor = new ModelTypeExtractor(typeOracle);
      this.modelType = modelTypeExtractor.extractModelType(modelType);
   }

   private void extractResourceType(TypeOracle typeOracle, JClassType modelType)
   {
      ResourceTypeExtractor resourceTypeExtractor = new ResourceTypeExtractor(typeOracle);
      resourceType = resourceTypeExtractor.extractResourceType(modelType);
   }

   private void extractColumns(TypeOracle typeOracle, JClassType modelType) throws NotFoundException
   {
      ColumnExtractor columnExtractor = new ColumnExtractor();
      columns = columnExtractor.extractColumns(typeOracle, modelType);
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

   public JClassType getModelType()
   {
      return modelType;
   }

   public boolean isHasHtmlHeader()
   {
      return hasHtmlHeader;
   }
}
