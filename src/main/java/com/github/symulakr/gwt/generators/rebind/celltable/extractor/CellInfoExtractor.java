package com.github.symulakr.gwt.generators.rebind.celltable.extractor;

import com.github.symulakr.gwt.generators.client.celltable.annotation.Column;
import com.github.symulakr.gwt.generators.rebind.celltable.CellContext;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;
import com.google.gwt.core.ext.typeinfo.JPrimitiveType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.safehtml.shared.SafeHtml;

import static com.github.symulakr.gwt.generators.client.celltable.DefaultValue.DEFAULT_CELL_TYPE;
import static com.github.symulakr.gwt.generators.client.celltable.DefaultValue.NUMBER_CELL_TYPE;
import static com.github.symulakr.gwt.generators.client.celltable.DefaultValue.SAFE_HTML_CELL_TYPE;
import static com.github.symulakr.gwt.generators.rebind.utils.ReflectUtils.isItThisType;

public class CellInfoExtractor extends AbstractExtractor
{

   public CellInfoExtractor(TypeOracle typeOracle)
   {
      super(typeOracle);
   }

   public CellContext getCellInfo(JMethod annotatedMethod)
   {
      Column column = annotatedMethod.getAnnotation(Column.class);
      if (DEFAULT_CELL_TYPE.equals(column.cellType()))
      {
         return getCellInfo(annotatedMethod.getReturnType());
      }
      CellContext cellInfo = new CellContext(annotatedMethod.getReturnType());
      cellInfo.setCellType(column.cellType());
      cellInfo.setReturnType(decideReturnType(cellInfo.getCellType()));
      return cellInfo;
   }

   private CellContext getCellInfo(JType returnType)
   {
      CellContext cellInfo = new CellContext(returnType);
      if (isString(returnType))
      {
         cellInfo.setReturnType(returnType);
         cellInfo.markAsTextCell(true);
         cellInfo.setCellType(DEFAULT_CELL_TYPE);
      }
      else if (isSafeHtml(returnType))
      {
         cellInfo.setReturnType(returnType);
         cellInfo.setCellType(SAFE_HTML_CELL_TYPE);
      }
      else if (isNumber(returnType))
      {
         cellInfo.setReturnType(findType(Number.class));
         cellInfo.setCellType(NUMBER_CELL_TYPE);
      }
      return cellInfo;
   }

   private boolean isNumber(JType returnType)
   {
      JPrimitiveType primitiveType = returnType.isPrimitive();
      JClassType rt;
      if (primitiveType != null)
      {
         rt = findType(primitiveType.getQualifiedBoxedSourceName());
      }
      else
      {
         rt = findType(returnType.getQualifiedSourceName());
      }
      for (JClassType superType : rt.getFlattenedSupertypeHierarchy())
      {
         if (isItThisType(superType, Number.class))
         {
            return true;
         }
      }
      return false;
   }

   private boolean isSafeHtml(JType returnType)
   {
      return isItThisType(returnType, SafeHtml.class);
   }

   private boolean isString(JType returnType)
   {
      return isItThisType(returnType, String.class);
   }

   private JType decideReturnType(Class cellType)
   {
      String cellClassName = Cell.class.getName();
      JClassType cellJType = findType(cellType);
      for (JClassType superType : cellJType.getFlattenedSupertypeHierarchy())
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
      return null;
   }

}
