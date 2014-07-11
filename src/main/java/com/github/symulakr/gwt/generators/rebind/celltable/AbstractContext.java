package com.github.symulakr.gwt.generators.rebind.celltable;

import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

public abstract class AbstractContext
{
   protected TypeOracle typeOracle;

   protected AbstractContext(TypeOracle typeOracle)
   {
      this.typeOracle = typeOracle;
   }

   protected JClassType findType(Class<?> clazz)
   {
      return typeOracle.findType(clazz.getName().replaceAll("\\$", "."));
   }
}
