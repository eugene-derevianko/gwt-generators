package com.github.symulakr.gwt.generators.rebind.celltable.extractor;

import com.github.symulakr.gwt.generators.client.celltable.DefaultValue;
import com.github.symulakr.gwt.generators.client.celltable.annotation.TableResources;
import com.google.gwt.core.ext.typeinfo.JClassType;

public class ResourceTypeExtractor
{

   public Class extractResourceType(JClassType modelType)
   {
      if (modelType != null)
      {
         if (modelType.isAnnotationPresent(TableResources.class))
         {
            TableResources tableResources = modelType.getAnnotation(TableResources.class);
            return tableResources.value();
         }
         return extractResourceType(modelType.getSuperclass());
      }
      return DefaultValue.DEFAULT_RESOURCES;
   }

}
