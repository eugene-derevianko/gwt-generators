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
