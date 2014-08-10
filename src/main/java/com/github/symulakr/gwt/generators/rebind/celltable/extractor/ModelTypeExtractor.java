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

import com.github.symulakr.gwt.generators.client.celltable.CellTableModel;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

public class ModelTypeExtractor extends AbstractExtractor
{

   private JClassType modelInterface = findType(CellTableModel.class);

   public ModelTypeExtractor(TypeOracle typeOracle)
   {
      super(typeOracle);
   }

   public JClassType extractModelType(JClassType modelType)
   {
      JClassType[] implementedInterfaces = modelType.getImplementedInterfaces();
      for (JClassType implementedInterface : implementedInterfaces)
      {
         if (modelInterface.equals(implementedInterface))
         {
            return modelType;
         }
      }
      return extractModelType(modelType.getSuperclass());
   }

}
