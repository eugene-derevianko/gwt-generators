package com.github.symulakr.gwt.generators.rebind.celltable;

import com.github.symulakr.gwt.generators.rebind.Logger;
import com.google.gwt.core.ext.UnableToCompleteException;

public abstract class AbstractContext
{

   public abstract void validate(Logger logger) throws UnableToCompleteException;

}
