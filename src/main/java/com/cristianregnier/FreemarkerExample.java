package com.cristianregnier;

import freemarker.template.Configuration;

import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class FreemarkerExample {

   public static void main(String args[]) throws Exception {
      // Create the configuration instance
      var configuration =
              new FreemarkerConfigurationFactory(Configuration.VERSION_2_3_21, "/templates").build();

      // Create the data model
      HashMap<Object, Object> dataModel = getDataModel();

      // Get  the template
      var template = configuration.getTemplate("test.ftlh");

      // Write to the standard output
      template.process(dataModel, new OutputStreamWriter(System.out));
   }

   private static HashMap<Object, Object> getDataModel() {
      // Create the root hash. We use a Map here, but it could be a JavaBean too.
      var root = new HashMap<>();

      // Put string "user" into the root
      root.put("user", "Big Joe");

      // Create the "latestProduct" hash.
      var latestProduct = Map.of(
              "url", "products/greenmouse.html",
              "name", "green mouse");

      // and put it into the root
      root.put("latestProduct", latestProduct);
      return root;
   }


}
