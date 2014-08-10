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

import com.github.symulakr.gwt.generators.client.celltable.annotation.Column;
import com.github.symulakr.gwt.generators.rebind.Logger;
import com.github.symulakr.gwt.generators.rebind.celltable.CellContext;
import com.github.symulakr.gwt.generators.rebind.celltable.CellContext.DefaultReturnType;
import com.github.symulakr.gwt.generators.rebind.celltable.ColumnContext;
import com.github.symulakr.gwt.generators.rebind.utils.ReflectUtils;
import com.github.symulakr.gwt.generators.rebind.utils.StringUtils;
import com.google.gwt.core.ext.UnableToCompleteException;

public class ColumnContextValidator
{

   public static void validate(Logger logger, ColumnContext[] columnContexts) throws UnableToCompleteException
   {
      for (ColumnContext columnContext : columnContexts)
      {
         validate(logger, columnContext);
      }
   }

   public static void validate(Logger logger, ColumnContext columnContext) throws UnableToCompleteException
   {
      validateCellContext(logger, columnContext);
      validateFieldUpdater(logger, columnContext);
   }

   private static void validateCellContext(Logger logger, ColumnContext columnContext) throws UnableToCompleteException
   {
      CellContext cellContext = columnContext.getCell();
      if (cellContext.getReturnType() == null)
      {
         if (cellContext.getCellType() == null)
         {
            logger.die("Can't define CellType for return type '%s' of the method '%s'." +
                  " Specify CellType by '%s' annotation or use one of default return types: {%s} ",
                  cellContext.getRawReturnType(), columnContext.getGetterName(),
                  Column.class.getSimpleName(), StringUtils.toString(DefaultReturnType.values()));
         }
         else
         {
            logger.die("Can't define return type from CellType '%s' of the method '%s'",
                  cellContext.getCellType().getSimpleName(), columnContext.getGetterName());
         }
         if (!ReflectUtils.isItThisType(cellContext.getReturnType(),cellContext.getRawReturnType()))
         {
            logger.warn("Different types");
         }
      }
   }

   private static void validateFieldUpdater(Logger logger, ColumnContext columnContext) throws UnableToCompleteException
   {
      //todo show message in case of using fieldUpdater and nonClickableColumn
      //todo [INFO]       [ERROR] Line 82: The method setFieldUpdater(FieldUpdater<Model,String>) in the type Column<Model,String> is not applicable for the arguments (FieldUpdaterImpl)
   }

}
