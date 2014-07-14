package com.github.symulakr.gwt.generators.rebind.celltable;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.github.symulakr.gwt.generators.annotation.celltable.Column;
import com.github.symulakr.gwt.generators.client.celltable.DefaultValue;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

public class ColumnExtractor
{

   private void extractMethods(Map<String, JMethod> columns, JMethod[] methods)
   {
      for (JMethod method : methods)
      {
         if (method.isAnnotationPresent(Column.class))
         {
            columns.put(method.getName(), method);
            System.out.println("put "+method.getName());
         }
         else
         {
            columns.remove(method.getName());
            System.out.println("remove " + method.getName());
         }
      }
   }

   public ColumnContext[] extractColumns(TypeOracle typeOracle, JClassType modelType) throws NotFoundException
   {
      Map<String, JMethod> methods = new LinkedHashMap<String, JMethod>(16, 0.75f, true);
      extractMethods(methods, modelType);
      List<ColumnContext> columns = new ArrayList<ColumnContext>();
      Iterator<JMethod> methodIterator = methods.values().iterator();
      while (methodIterator.hasNext())
      {
         JMethod me = methodIterator.next();
         System.out.println("proc "+me.getName());

         columns.add(new ColumnContext(typeOracle, me));
      }
      return sortColumns(columns);
   }

   private ColumnContext[] sortColumns(List<ColumnContext> columns)
   {
      ColumnContext[] sortedColumns = new ColumnContext[columns.size()];
      Deque<ColumnContext> queue = new LinkedList<ColumnContext>();
      for (ColumnContext columnContext : columns)
      {
         int index = columnContext.getIndex();
         if (index > DefaultValue.UNSPECIFIED && index < sortedColumns.length && sortedColumns[index] == null)
         {
            sortedColumns[index] = columnContext;
         }
         else
         {
            queue.offer(columnContext);
         }
      }

      for (int i = 0; i < columns.size(); i++)
      {
         if (sortedColumns[i] == null)
         {
            sortedColumns[i] = queue.pollFirst();
         }
      }
      return sortedColumns;
   }

   private void extractMethods(Map<String, JMethod> methods, JClassType type)
   {
      if (type != null)
      {
         extractMethods(methods, type.getSuperclass());
         System.out.println("proc "+type.getName());

         extractMethods(methods, type.getMethods());
      }
   }

}
