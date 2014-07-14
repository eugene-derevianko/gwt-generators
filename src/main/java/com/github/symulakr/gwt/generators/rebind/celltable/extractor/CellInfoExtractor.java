package com.github.symulakr.gwt.generators.rebind.celltable.extractor;

import com.github.symulakr.gwt.generators.annotation.celltable.Column;
import com.github.symulakr.gwt.generators.client.celltable.DefaultValue;
import com.github.symulakr.gwt.generators.rebind.celltable.CellContext;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

public class CellInfoExtractor extends AbstractExtractor
{

   public CellInfoExtractor(TypeOracle typeOracle)
   {
      super(typeOracle);
   }

   public CellContext getCellInfo(JMethod annotatedMethod)
   {
      Column column = annotatedMethod.getAnnotation(Column.class);
      CellContext cellInfo = new CellContext();
      cellInfo.setCellType(findType(column.cellType()));
      if (DefaultValue.DEFAULT_CELL_TYPE.equals(column.cellType()))
      {
         cellInfo.setDefaultCell(true);
         cellInfo.setReturnType(findType(String.class));
      }
      cellInfo.setReturnType(decideReturnType(cellInfo.getCellType(), annotatedMethod.getReturnType()));
      return cellInfo;
   }

   private JType decideReturnType(JClassType cellType, JType returnType)
   {
      String cellClassName = Cell.class.getName();
      for (JClassType superType : cellType.getFlattenedSupertypeHierarchy())
      {
         if (cellClassName.equals(superType.getQualifiedSourceName()))
         {
            JParameterizedType parameterizedType = superType.isParameterized();
            if (parameterizedType != null)
            {
               return parameterizedType.getTypeArgs()[0];
            }
         }
      }
      return returnType;
   }

}
