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

package com.github.symulakr.gwt.generators.rebind.utils;

import com.google.gwt.core.ext.typeinfo.JType;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

public class ReflectUtils
{

   public static boolean isFinal(Class clazz)
   {
      return Modifier.isFinal(clazz.getModifiers());
   }

   public static boolean isFinal(Member member)
   {
      return Modifier.isFinal(member.getModifiers());
   }

   public static boolean isPublic(Member member)
   {
      return Modifier.isPublic(member.getModifiers());
   }

   public static boolean isProtected(Member member)
   {
      return Modifier.isProtected(member.getModifiers());
   }

   public static boolean hasDefaultConstructor(Class clazz)
   {
      Constructor[] constructors = clazz.getDeclaredConstructors();
      for (Constructor constructor : constructors)
      {
         if (isPublic(constructor) && constructor.getParameterTypes().length == 0)
         {
            return true;
         }
      }
      return false;
   }

   public static boolean isItThisType(JType it, Class thisType)
   {
      return StringUtils.equals(thisType.getName(), it.getQualifiedSourceName());
   }

   public static boolean isItThisType(JType it, JType thisType)
   {
      return StringUtils.equals(thisType.getQualifiedSourceName(), it.getQualifiedSourceName());
   }

}
