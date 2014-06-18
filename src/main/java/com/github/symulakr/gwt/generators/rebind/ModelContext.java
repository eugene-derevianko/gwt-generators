package com.github.symulakr.gwt.generators.rebind;

import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

public class ModelContext
{
   private JClassType modelType;

   public ModelContext(TypeOracle typeOracle, JClassType modelType)
   {
      this.modelType = modelType;
   }

   public JClassType getModelType()
   {
      return modelType;
   }
}
