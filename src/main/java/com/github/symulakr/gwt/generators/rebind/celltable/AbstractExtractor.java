package com.github.symulakr.gwt.generators.rebind.celltable;

import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

public abstract class AbstractExtractor
{

   protected TypeOracle typeOracle;

   protected AbstractExtractor(TypeOracle typeOracle)
   {
      this.typeOracle = typeOracle;
   }

   protected JClassType findType(Class<?> clazz)
   {
      return typeOracle.findType(clazz.getName()
            .replaceAll("\\$", "."));
   }

}
