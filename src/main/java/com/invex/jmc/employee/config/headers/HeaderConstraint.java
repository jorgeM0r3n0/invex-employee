package com.invex.jmc.employee.config.headers;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Annotation used to validate HTTP request headers for a specific API.
 *
 * <p>This constraint is applied at the method parameter level, typically on
 * parameters annotated with {@link org.springframework.web.bind.annotation.RequestHeader}
 * or directly on {@link org.springframework.http.HttpHeaders} objects.</p>
 *
 * <p>Validation logic is delegated to {@link HeaderValidator}, which checks
 * that the incoming request contains all headers required by the API identified
 * through the {@link #api()} attribute. The actual list of required headers is
 * loaded from the configuration source mapped by {@code ConfigHeaders}.</p>
 *
 * <p>This allows server-side enforcement of header rules such as:
 * <ul>
 *   <li>Required headers that must be present</li>
 *   <li>Prohibited headers (when prefixed with "-")</li>
 *   <li>API-specific header validation driven by configuration</li>
 * </ul>
 * </p>
 *
 * <p>The annotation integrates with Jakarta Bean Validation (JSR-380) so that
 * validation errors are automatically propagated as constraint violations and
 * may be returned to the client as part of standard validation handling.</p>
 */
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HeaderValidator.class)
@Documented
public @interface HeaderConstraint {

  /**
   * Default validation error message emitted when one or more headers are
   * missing or invalid according to the configured API rule.
   *
   * @return the default validation message
   */
  String message() default "Header inválido o ausente";

  /**
   * The name of the API whose header rules must be validated.
   *
   * <p>This value is used by {@link HeaderValidator} to look up the
   * configuration entry inside {@code ConfigHeaders}. Each API name
   * corresponds to a list of required or prohibited headers.</p>
   *
   * @return the API identifier to validate against
   */
  String api();

  /**
   * Standard Bean Validation grouping mechanism.
   *
   * @return the validation groups
   */
  Class<?>[] groups() default {};

  /**
   * Additional metadata for constraint payloads.
   *
   * @return the validation payload classes
   */
  Class<? extends Payload>[] payload() default {};
}
