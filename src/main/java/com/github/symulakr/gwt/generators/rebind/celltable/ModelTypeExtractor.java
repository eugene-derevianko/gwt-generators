package com.github.symulakr.gwt.generators.rebind.celltable;

import com.github.symulakr.gwt.generators.client.celltable.CellTableModel;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

public class ModelTypeExtractor extends AbstractExtractor
{

   private JClassType modelInterface = findType(CellTableModel.class);

   public ModelTypeExtractor(TypeOracle typeOracle)
   {
      super(typeOracle);
   }

   public JClassType extractModelType(JClassType modelType)
   {
      JClassType[] implementedInterfaces = modelType.getImplementedInterfaces();
      for (JClassType implementedInterface : implementedInterfaces)
      {
         if (modelInterface.equals(implementedInterface))
         {
            return modelType;
         }
      }
      return extractModelType(modelType.getSuperclass());
   }

}
