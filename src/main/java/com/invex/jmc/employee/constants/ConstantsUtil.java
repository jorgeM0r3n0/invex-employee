package com.invex.jmc.employee.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utility class that centralizes constant values used across the application.
 *
 * <p>This class is declared as {@code final} and has a private constructor
 * (generated via {@code @NoArgsConstructor(access = AccessLevel.PRIVATE)})
 * to prevent instantiation, as it is intended solely for holding constant
 * values.</p>
 *
 * <p>These constants are typically used for header validation, default
 * configurations, and shared identifiers across multiple components.</p>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConstantsUtil {

  /**
   * Name of the HTTP header used to determine the client's language preference.
   *
   * <p>This header is frequently validated or propagated to ensure
   * consistent localization behavior.</p>
   */
  public static final String ACCEPT_LANGUAGE = "Accept-language";

  /**
   * Default language/modality applied when no {@code Accept-language}
   * header is provided by the client.
   */
  public static final String DEF_MODALITY = "es";

  /**
   * Identifier for the header validation rule named
   * {@code invex-employee}, used to retrieve required header
   * definitions from {@code ConfigHeaders}.
   *
   * <p>This value is typically referenced inside the
   * {@code @HeaderConstraint(api = ...)} annotation.</p>
   */
  public static final String HEADERS_CONSTRAINT = "invex-employee";

  /**
   * Media type constant representing the value {@code application/json}.
   *
   * <p>Commonly used in HTTP requests and responses to indicate that the body
   * content is formatted as JSON. This value can be applied in headers such as
   * {@code Content-Type} and {@code Accept} to explicitly specify that the
   * client or server expects JSON-formatted data.</p>
   */
  public static final String APPLICATION_JSON = "application/json";
  public static final String ACCEPT = "Accept";
}
