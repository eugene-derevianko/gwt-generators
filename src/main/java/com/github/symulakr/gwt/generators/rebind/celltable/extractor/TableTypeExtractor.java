/*
 * Copyright (c) 2014 Eugene
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
