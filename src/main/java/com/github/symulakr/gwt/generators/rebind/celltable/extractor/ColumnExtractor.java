package com.github.symulakr.gwt.generators.rebind.celltable.extractor;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

import com.github.symulakr.gwt.generators.client.celltable.DefaultValue;
import com.github.symulakr.gwt.generators.client.celltable.annotation.Column;
import com.github.symulakr.gwt.generators.client.celltable.annotation.ColumnActions;
import com.github.symulakr.gwt.generators.client.celltable.annotation.NonColumn;
import com.github.symulakr.gwt.generators.rebind.celltable.ColumnContext;
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
            putColumn(columns, method);
         }
         else if (method.isAnnotationPresent(NonColumn.class))
         {
            columns.remove(method.getName());
         }
      }
   }

   private void putColumn(Map<String, JMethod> columns, JMethod method)
   {
      if (method.isAnnotationPresent(ColumnActions.class))
      {
         columns.remove(method.getName());
      }
      columns.put(method.getName(), method);
   }

   public ColumnContext[] extractColumns(TypeOracle typeOracle, JClassType modelType) throws NotFoundException
   {
      Map<String, JMethod> methods = new LinkedHashMap<String, JMethod>();
      extractMethods(methods, modelType);
      Stack<ColumnContext> columns = new Stack<ColumnContext>();
      for (JMethod method : methods.values())
      {
         columns.push(new ColumnContext(typeOracle, method));
      }
      return sortColumns(columns);
   }

   private ColumnContext[] sortColumns(Stack<ColumnContext> columns)
   {
      ColumnContext[] sortedColumns = new ColumnContext[columns.size()];
      Stack<ColumnContext> tempStack = new Stack<ColumnContext>();
      while (!columns.isEmpty())
      {
         ColumnContext columnContext = columns.pop();
         int index = columnContext.getIndex();
         if (index > DefaultValue.UNSPECIFIED && index < sortedColumns.length && sortedColumns[index] == null)
         {
            sortedColumns[index] = columnContext;
         }
         else
         {
            tempStack.push(columnContext);
         }
      }

      for (int i = 0; i < sortedColumns.length; i++)
      {
         if (sortedColumns[i] == null)
         {
            sortedColumns[i] = tempStack.pop();
         }
      }
      return sortedColumns;
   }

   private void extractMethods(Map<String, JMethod> methods, JClassType type)
   {
      if (type != null)
      {
         extractMethods(methods, type.getSuperclass());
         extractMethods(methods, type.getMethods());
      }
   }

}
