package com.github.symulakr.gwt.generators.rebind.celltable.validator;

import java.lang.reflect.Constructor;

import com.github.symulakr.gwt.generators.client.celltable.DefaultValue;
import com.github.symulakr.gwt.generators.rebind.Logger;
import com.github.symulakr.gwt.generators.rebind.utils.ReflectUtils;
import com.google.gwt.core.ext.UnableToCompleteException;

public class TableTypeValidator
{

   public static void validate(Logger logger, Class tableType) throws UnableToCompleteException
   {
      if (ReflectUtils.isFinal(tableType))
      {
         logger.die("CellTable class cannot be final");
      }
      Constructor[] constructors = tableType.getDeclaredConstructors();
      boolean hasNeededConstructor = false;
      for (Constructor constructor : constructors)
      {
         Class[] parameters = constructor.getParameterTypes();
         if (parameters.length == 2)
         {
            if ((Integer.TYPE.equals(parameters[0]) || Integer.class.equals(parameters[0])) && DefaultValue.DEFAULT_RESOURCES.isAssignableFrom(parameters[1]))
            {
               if (ReflectUtils.isPublic(constructor) || ReflectUtils.isProtected(constructor))
               {
                  hasNeededConstructor = true;
                  break;
               }
            }
         }
      }
      if (!hasNeededConstructor)
      {
         logger.die("CellTable must have public or protected Constructor with Parameters(%s, %s)", Integer.class, DefaultValue.DEFAULT_RESOURCES);
      }
   }

}
