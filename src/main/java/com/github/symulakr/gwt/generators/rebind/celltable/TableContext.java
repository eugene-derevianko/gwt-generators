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

package com.github.symulakr.gwt.generators.rebind.celltable;

import com.github.symulakr.gwt.generators.rebind.Logger;
import com.github.symulakr.gwt.generators.rebind.celltable.extractor.ColumnExtractor;
import com.github.symulakr.gwt.generators.rebind.celltable.extractor.ModelTypeExtractor;
import com.github.symulakr.gwt.generators.rebind.celltable.extractor.ResourceTypeExtractor;
import com.github.symulakr.gwt.generators.rebind.celltable.extractor.TableTypeExtractor;
import com.github.symulakr.gwt.generators.rebind.celltable.validator.ColumnContextValidator;
import com.github.symulakr.gwt.generators.rebind.celltable.validator.ModelTypeValidator;
import com.github.symulakr.gwt.generators.rebind.celltable.validator.ResourceTypeValidator;
import com.github.symulakr.gwt.generators.rebind.celltable.validator.TableTypeValidator;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

public class TableContext extends AbstractContext
{

   private Class resourceType;
   private ColumnContext[] columns;
   private JClassType modelType;
   private Class tableType;

   public TableContext(TypeOracle typeOracle, JClassType modelType) throws NotFoundException
   {
      extractModelType(typeOracle, modelType);
      extractTableType(modelType);
      extractResourceType(modelType);
      extractColumns(typeOracle, modelType);
   }

   private void extractTableType(JClassType modelType)
   {
      TableTypeExtractor tableTypeExtractor = new TableTypeExtractor();
      this.tableType = tableTypeExtractor.extractType(modelType);
   }

   private void extractModelType(TypeOracle typeOracle, JClassType modelType)
   {
      ModelTypeExtractor modelTypeExtractor = new ModelTypeExtractor(typeOracle);
      this.modelType = modelTypeExtractor.extractModelType(modelType);
   }

   private void extractResourceType(JClassType modelType)
   {
      ResourceTypeExtractor resourceTypeExtractor = new ResourceTypeExtractor();
      resourceType = resourceTypeExtractor.extractType(modelType);
   }

   private void extractColumns(TypeOracle typeOracle, JClassType modelType) throws NotFoundException
   {
      ColumnExtractor columnExtractor = new ColumnExtractor();
      columns = columnExtractor.extractColumns(typeOracle, modelType);
   }

   public Class getTableType()
   {
      return tableType;
   }

   public Class getResourceType()
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

   @Override
   public void validate(Logger logger) throws UnableToCompleteException
   {
      TableTypeValidator.validate(logger, tableType);
      ResourceTypeValidator.validate(logger, resourceType);
      ModelTypeValidator.validate(logger, modelType);
      ColumnContextValidator.validate(logger, columns);
   }
}
