package com.github.symulakr.gwt.generators.rebind.celltable;


import org.apache.velocity.VelocityContext;

import com.github.symulakr.gwt.generators.rebind.Logger;
import com.github.symulakr.gwt.generators.rebind.VelocityGenerator;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

public class CellTableGenerator extends VelocityGenerator
{

   @Override
   protected void populateVelocityContext(VelocityContext velocityContext, TypeOracle typeOracle, JClassType modelType)
         throws UnableToCompleteException
   {
      try
      {
         super.populateVelocityContext(velocityContext, typeOracle, modelType);
         TableContext tableContext = new TableContext(typeOracle, modelType);
         velocityContext.put("generatorType", getClass());
         velocityContext.put("tableContext", tableContext);
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
      //todo throw exception for primitive types
   }
}
