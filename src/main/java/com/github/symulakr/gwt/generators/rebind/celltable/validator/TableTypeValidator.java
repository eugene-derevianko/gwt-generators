/*
 * Copyright (c) 2014 Eugene
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.symulakr.gwt.generators.rebind.celltable.validator;

import com.github.symulakr.gwt.generators.client.celltable.DefaultValue;
import com.github.symulakr.gwt.generators.rebind.Logger;
import com.github.symulakr.gwt.generators.rebind.utils.ReflectUtils;
import com.google.gwt.core.ext.UnableToCompleteException;

import java.lang.reflect.Constructor;

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
