package com.invex.jmc.employee.config.headers.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring Web MVC configuration class responsible for registering
 * application-wide interceptors.
 *
 * <p>This configuration registers the {@link HeaderValidationInterceptor},
 * which performs validation of HTTP headers before controller methods
 * are executed. By implementing {@link WebMvcConfigurer}, the class
 * customizes the MVC pipeline without requiring the use of
 * {@code @EnableWebMvc}.</p>
 *
 * <p>The interceptor is applied to all request paths matching
 * {@code /api/**}, ensuring that only API endpoints enforce the
 * header validation rules.</p>
 *
 * <p>The class uses constructor injection through
 * {@link RequiredArgsConstructor}, ensuring that {@code headerValidationInterceptor}
 * is properly provided by Spring at runtime.</p>
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

  /**
   * Interceptor responsible for validating required or prohibited
   * HTTP headers for incoming API requests.
   *
   * <p>This instance is injected automatically by Spring as part
   * of the application's configuration?</p>
   */
  private final HeaderValidationInterceptor headerValidationInterceptor;

  /**
   * Registers application interceptors.
   *
   * <p>This method adds the {@link HeaderValidationInterceptor} to the
   * Spring MVC interceptor chain and restricts its applicability to
   * URL paths under {@code /api/**}. This prevents header validation
   * from being applied to non-API endpoints (e.g., actuator, static
   * files, documentation paths).</p>
   *
   * @param registry the registry used to manage MVC interceptors
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(headerValidationInterceptor)
        .addPathPatterns("/api/**"); // o tus rutas personalizadas
  }
}
