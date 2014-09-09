package com.github.symulakr.gwt.generators.rebind;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.inject.Singleton;

import org.apache.velocity.app.VelocityEngine;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class RebindModule extends AbstractModule
{

   private final TreeLogger treeLogger;
   private final GeneratorContext generatorContext;

   public RebindModule(TreeLogger treeLogger, GeneratorContext generatorContext)
   {
      super();
      this.treeLogger = treeLogger;
      this.generatorContext = generatorContext;
   }

   @Provides
   public TreeLogger provideTreeLogger()
   {
      return treeLogger;
   }

   @Provides
   public TypeOracle provideTypeOracle()
   {
      return generatorContext.getTypeOracle();
   }

   @Override
   protected void configure()
   {
      bindConstant().annotatedWith(VelocityProperties.class)
            .to("com/github/symulakr/gwt/generators/rebind/velocity.properties");
   }

   @Provides
   @Singleton
   public VelocityEngine provideVelocityEngine(@VelocityProperties String velocityProperties, TreeLogger logger) throws UnableToCompleteException
   {
      VelocityEngine engine = null;
      InputStream inputStream = this.getClass()
            .getClassLoader()
            .getResourceAsStream(velocityProperties);
      Properties properties = new Properties();
      try
      {
         properties.load(inputStream);
         engine = new VelocityEngine(properties);
      }
      catch (IOException e)
      {
         logger.log(TreeLogger.ERROR, "Cannot load velocity properties from " + velocityProperties);
      }
      return engine;
   }

}
