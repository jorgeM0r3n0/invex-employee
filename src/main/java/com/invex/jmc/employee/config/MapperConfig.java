package com.invex.jmc.employee.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class responsible for defining and customizing the {@link ModelMapper} bean.
 *
 * <p>This configuration sets up a {@link ModelMapper} instance used across the
 * application to convert between DTOs and entities. The mapper is configured to:</p>
 * <ul>
 *   <li>Skip mapping of {@code null} values (`setSkipNullEnabled(true)`), preventing unwanted
 *   overwrites.</li>
 *   <li>Enable direct field matching (`setFieldMatchingEnabled(true)`), allowing ModelMapper to
 *   map fields without requiring getters/setters.</li>
 *   <li>Allow access to private fields (`setFieldAccessLevel(PRIVATE)`), improving mapping
 *   flexibility.</li>
 * </ul>
 *
 * <p>The configured {@code ModelMapper} is registered as a Spring bean and can be injected
 * wherever needed.</p>
 */
@Configuration
public class MapperConfig {

  /**
   * Creates and configures the {@link ModelMapper} bean used for object mapping.
   *
   * @return a fully configured {@code ModelMapper} instance.
   */
  @Bean
  public  ModelMapper modelMapper() {
    ModelMapper mapper = new ModelMapper();
    mapper.getConfiguration()
        .setSkipNullEnabled(true)
        .setFieldMatchingEnabled(true)
        .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
    return mapper;
  }
}