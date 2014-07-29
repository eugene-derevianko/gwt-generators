package com.github.symulakr.gwt.generators.rebind.utils;

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

}
