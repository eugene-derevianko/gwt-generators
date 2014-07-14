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
      return findType(clazz.getName());
   }

   protected JClassType findType(String className)
   {
      return typeOracle.findType(className.replaceAll("\\$", "."));
   }
}
