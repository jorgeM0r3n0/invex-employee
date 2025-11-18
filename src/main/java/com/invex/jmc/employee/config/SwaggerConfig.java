package com.invex.jmc.employee.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up the OpenAPI (Swagger) documentation
 * using Springdoc.
 *
 * <p>This class defines the metadata that will appear in the generated API
 * documentation, such as the API title, version, and description.</p>
 *
 * @see org.springdoc.core for additional customization options
 */
@Configuration
public class SwaggerConfig {

  /**
   * Defines the OpenAPI metadata for the application.
   *
   * <p>The information provided here is displayed in the Swagger UI and
   * OpenAPI documentation, helping consumers understand the purpose and
   * version of the API.</p>
   *
   * @return an {@link OpenAPI} instance containing the API metadata
   */
  @Bean
  public OpenAPI apiInfo() {
    return new OpenAPI()
      .info(new Info()
        .title("API de Empleados")
        .version("1.0")
        .description("Documentación OpenAPI con Springdoc"));
  }
}
