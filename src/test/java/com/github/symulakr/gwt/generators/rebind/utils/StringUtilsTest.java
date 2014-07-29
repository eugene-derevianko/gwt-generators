package com.github.symulakr.gwt.generators.rebind.utils;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;

public class StringUtilsTest
{

   private String nullString = null;
   private String emptyString = "";

   @Test
   public void testIsEmpty() throws Exception
   {
      String string = "string";
      assertTrue(StringUtils.isEmpty(nullString));
      assertTrue(StringUtils.isEmpty(emptyString));
      assertFalse(StringUtils.isEmpty(string));
   }

   @Test
   public void testIsNotEmpty() throws Exception
   {
      String string = "string";
      assertFalse(StringUtils.isNotEmpty(nullString));
      assertFalse(StringUtils.isNotEmpty(emptyString));
      assertTrue(StringUtils.isNotEmpty(string));
   }
   
}
