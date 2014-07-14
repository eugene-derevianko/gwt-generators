package com.github.symulakr.gwt.generators.client.celltable;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.user.cellview.client.CellTable.Resources;

public class DefaultValue
{
   public static final Class FIELD_UPDATER = DefaultFieldUpdater.class;
   public static final Class DEFAULT_CELL_TYPE = TextCell.class;
   public static final int UNSPECIFIED = -1;
   public static final String EMPTY_STRING = "";
   public static final Class DEFAULT_RESOURCES = Resources.class;
   public static final TextAlign DEFAULT_HORIZONTAL_ALIGNMENT = TextAlign.CENTER;
   public static final VerticalAlign DEFAULT_VERTICAL_ALIGNMENT = VerticalAlign.MIDDLE;

}
