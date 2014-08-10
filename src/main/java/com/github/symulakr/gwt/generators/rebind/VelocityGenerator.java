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

package com.github.symulakr.gwt.generators.rebind;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.PrintWriter;

public abstract class VelocityGenerator extends Generator
{

   @Override
   public String generate(TreeLogger treeLogger, GeneratorContext generatorContext, String modelTypeName) throws UnableToCompleteException
   {
      TypeOracle typeOracle = generatorContext.getTypeOracle();
      JClassType modelType = typeOracle.findType(modelTypeName);
      Logger logger = new Logger(treeLogger);
      validateModel(logger, typeOracle, modelType);
      String implName = modelType.getName().replace('.', '_') + "Impl";
      String packageName = modelType.getPackage()
            .getName();

      PrintWriter printWriter = generatorContext.tryCreate(treeLogger, packageName, implName);

      if (printWriter != null)
      {
         Injector injector = Guice.createInjector(new RebindModule(treeLogger, generatorContext));
         ModelContext modelContext = new ModelContext(typeOracle, modelType);

         VelocityContext velocityContext = new VelocityContext();
         velocityContext.put("implName", implName);
         velocityContext.put("modelContext", modelContext);

         populateVelocityContext(velocityContext, typeOracle, modelType);
         validateVelocityContext(logger, velocityContext);
         VelocityEngine velocityEngine = injector.getInstance(VelocityEngine.class);
         velocityEngine.mergeTemplate(getTemplateName(), "UTF-8", velocityContext, printWriter);
         generatorContext.commit(treeLogger, printWriter);
      }
      return packageName + "." + implName;
   }

   protected void populateVelocityContext(VelocityContext velocityContext, TypeOracle typeOracle, JClassType modelType)
         throws UnableToCompleteException
   {
   }

   /**
    * Returns the velocity template which should be merged.
    * 
    * @return the velocity template which should be merged.
    */
   protected abstract String getTemplateName();

   protected abstract void validateModel(Logger logger, TypeOracle typeOracle, JClassType modelType) throws UnableToCompleteException;

   protected abstract void validateVelocityContext(Logger logger, VelocityContext velocityContext) throws UnableToCompleteException;
}
