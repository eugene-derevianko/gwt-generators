package com.github.symulakr.gwt.generators.rebind.celltable.extractor;

import com.github.symulakr.gwt.generators.annotation.celltable.TableResources;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.cellview.client.CellTable.Resources;

public class ResourceTypeExtractor extends AbstractExtractor
{

   public ResourceTypeExtractor(TypeOracle typeOracle)
   {
      super(typeOracle);
   }

   public JClassType extractResourceType(JClassType modelType)
   {
      if (modelType != null)
      {
         if (modelType.isAnnotationPresent(TableResources.class))
         {
            TableResources tableResources = modelType.getAnnotation(TableResources.class);
            return findType(tableResources.value());
         }
         return extractResourceType(modelType.getSuperclass());
      }
      return findType(Resources.class);
   }

}
