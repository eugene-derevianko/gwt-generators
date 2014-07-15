package com.github.symulakr.gwt.generators.rebind.celltable.extractor;

import com.github.symulakr.gwt.generators.annotation.celltable.Table;
import com.github.symulakr.gwt.generators.client.celltable.DefaultValue;
import com.google.gwt.core.ext.typeinfo.JClassType;

public class TableTypeExtractor
{

   public Class extractResourceType(JClassType modelType)
   {
      if (modelType != null)
      {
         if (modelType.isAnnotationPresent(Table.class))
         {
            Table tableResources = modelType.getAnnotation(Table.class);
            return tableResources.value();
         }
         return extractResourceType(modelType.getSuperclass());
      }
      return DefaultValue.DEFAULT_TABLE_TYPE;
   }

}
