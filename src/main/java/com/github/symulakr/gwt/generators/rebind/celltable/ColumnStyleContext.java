package com.github.symulakr.gwt.generators.rebind.celltable;

import com.github.symulakr.gwt.generators.annotation.celltable.ColumnStyle;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

public class ColumnStyleContext extends AbstractContext
{

   private String horizontalAlignment;
   private String verticalAlignment;

   public ColumnStyleContext(TypeOracle typeOracle, JMethod method)
   {
      super(typeOracle);
      ColumnStyle columnStyle = method.getAnnotation(ColumnStyle.class);
      setHorizontalAlignment(columnStyle.horizontalAlignment());
      setVerticalAlignment(columnStyle.verticalAlignment());
   }

   private void setVerticalAlignment(VerticalAlign align)
   {
      verticalAlignment = HasVerticalAlignment.class.getName();
      switch (align)
      {
         case TOP:
            verticalAlignment += ".ALIGN_TOP";
            break;
         case BOTTOM:
            verticalAlignment += ".ALIGN_BOTTOM";
            break;
         case MIDDLE:
         default:
            verticalAlignment += ".ALIGN_MIDDLE";
            break;
      }
   }

   private void setHorizontalAlignment(TextAlign align)
   {
      horizontalAlignment = HasHorizontalAlignment.class.getName();
      switch (align)
      {
         case CENTER:
            horizontalAlignment += ".ALIGN_CENTER";
            break;
         case JUSTIFY:
            horizontalAlignment += ".ALIGN_JUSTIFY";
            break;
         case LEFT:
            horizontalAlignment += ".ALIGN_LEFT";
            break;
         case RIGHT:
            horizontalAlignment += ".ALIGN_RIGHT";
            break;
      }
   }

   public String getHorizontalAlignment()
   {
      return horizontalAlignment;
   }

   public String getVerticalAlignment()
   {
      return verticalAlignment;
   }

}
