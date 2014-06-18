package com.github.symulakr.gwt.generators.client.celltable;

import com.google.gwt.user.cellview.client.CellTable;

public interface CellTableRes extends CellTable.Resources
{

   public interface CellTableCss extends CellTable.Style
   {

   }

   @Source({CellTable.Style.DEFAULT_CSS, "com/github/symulakr/gwt/generators/rebind/celltable/cell-table.css"})
   CellTableCss cellTableStyle();

}
