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

package com.github.symulakr.gwt.generators.rebind.celltable.extractor;

import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

public abstract class AbstractExtractor
{

   protected TypeOracle typeOracle;

   protected AbstractExtractor(TypeOracle typeOracle)
   {
      this.typeOracle = typeOracle;
   }

   protected JClassType findType(Class<?> clazz)
   {
      return findType(clazz.getCanonicalName());
   }

   protected JClassType findType(String classCanonicalName)
   {
      return typeOracle.findType(classCanonicalName);
   }

}
