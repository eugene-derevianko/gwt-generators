package com.github.symulakr.gwt.generators.rebind.celltable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.github.symulakr.gwt.generators.annotation.celltable.Column;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

public class ColumnExtractor
{

   public static List<ColumnContext> extractColumns(TypeOracle typeOracle, JClassType modelType) throws NotFoundException
   {
      Map<String, JMethod> methods = new LinkedHashMap<String, JMethod>();
      extractMethods(methods, modelType);
      List<ColumnContext> columns = new ArrayList<ColumnContext>();
      for (JMethod method : methods.values())
      {
         columns.add(new ColumnContext(typeOracle, method));
      }
      return columns;
   }

   private static void extractMethods(Map<String, JMethod> methods, JClassType type)
   {
      if (type != null)
      {
         extractMethods(methods, type.getSuperclass());
         extractMethods(methods, type.getMethods());
      }
   }

   private static void extractMethods(Map<String, JMethod> columns, JMethod[] methods)
   {
      for (JMethod method : methods)
      {
         if (method.isAnnotationPresent(Column.class))
         {
            columns.put(method.getName(), method);
         }
         else
         {
            columns.remove(method.getName());
         }
      }
   }

}
