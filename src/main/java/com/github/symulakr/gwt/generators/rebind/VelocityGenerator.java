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
      validateModel(new Logger(treeLogger), typeOracle, modelType);
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
}
