package com.cristianregnier;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

import java.io.File;

public class FreemarkerConfigurationFactory {

   private static final String DEFAULT_ENCODING = "UTF-8";

   private final Version version;
   private final String templatesPath;

   public FreemarkerConfigurationFactory(Version version, String templatesPath) {
      this.version = version;
      this.templatesPath = templatesPath;
   }

   public Configuration build() throws Exception {
      //Create your Configuration instance, and specify if up to what FreeMarker
      // version do you want to apply the fixes that are not 100%
      // backward-compatible. See the Configuration JavaDoc for details.
      var configuration = new Configuration(version);

      // Specify the source where the template files come from. Here I set a
      // plain directory for it, but non-file-system sources are possible too:
      var templateDirectoryURL = FreemarkerConfigurationFactory.class.getResource(templatesPath);
      configuration.setDirectoryForTemplateLoading(new File(templateDirectoryURL.toURI()));

      // From here we will set the settings recommended for new projects. These
      // aren't the defaults for backward compatibilty.

      // Set the preferred charset template files are stored in. UTF-8 is
      // a good choice in most applications:
      configuration.setDefaultEncoding(DEFAULT_ENCODING);

      // Sets how errors will appear.
      // During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
      configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

      // Don't log exceptions inside FreeMarker that it will thrown at you anyway:
      configuration.setLogTemplateExceptions(false);

      // Wrap unchecked exceptions thrown during template processing into TemplateException-s:
      configuration.setWrapUncheckedExceptions(true);

      // Do not fall back to higher scopes when reading a null loop variable:
      configuration.setFallbackOnNullLoopVariable(false);

      return configuration;
   }
}
