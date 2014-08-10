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
import com.github.symulakr.gwt.generators.rebind.VelocityGenerator;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import org.apache.velocity.VelocityContext;

public class CellTableGenerator extends VelocityGenerator
{

   private static final String TABLE_CONTEXT_KEY = "tableContext";

   @Override
   protected void populateVelocityContext(VelocityContext velocityContext, TypeOracle typeOracle, JClassType modelType)
         throws UnableToCompleteException
   {
      try
      {
         super.populateVelocityContext(velocityContext, typeOracle, modelType);
         TableContext tableContext = new TableContext(typeOracle, modelType);
         velocityContext.put("generatorType", getClass());
         velocityContext.put(TABLE_CONTEXT_KEY, tableContext);
      }
      catch (NotFoundException e)
      {
         throw new UnableToCompleteException();
      }
   }

   @Override
   protected String getTemplateName()
   {
      return "com/github/symulakr/gwt/generators/rebind/celltable/cell_table.vm";
   }

   @Override
   protected void validateModel(Logger logger, TypeOracle typeOracle, JClassType modelType) throws UnableToCompleteException
   {

   }

   @Override
   protected void validateVelocityContext(Logger logger, VelocityContext velocityContext) throws UnableToCompleteException
   {
      AbstractContext tableContext = (AbstractContext) velocityContext.get(TABLE_CONTEXT_KEY);
      tableContext.validate(logger);
   }
}
