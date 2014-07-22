package com.github.symulakr.gwt.generators.rebind.celltable.extractor;

import com.github.symulakr.gwt.generators.client.celltable.DefaultValue;
import com.github.symulakr.gwt.generators.client.celltable.annotation.Table;
import com.google.gwt.core.ext.typeinfo.JClassType;

public class TableTypeExtractor
{

   public Class extractType(JClassType modelType)
   {
      if (modelType != null)
      {
         if (modelType.isAnnotationPresent(Table.class))
         {
            Table table = modelType.getAnnotation(Table.class);
            return table.value();
         }
         return extractType(modelType.getSuperclass());
      }
      return DefaultValue.DEFAULT_TABLE_TYPE;
   }

}
