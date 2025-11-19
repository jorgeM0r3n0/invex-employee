package com.invex.jmc.employee.config.headers;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class that loads and manages header validation rules
 * from the application's configuration files using
 * {@code @ConfigurationProperties(prefix = "headers")}.
 *
 * <p>This class allows defining, per API name, which HTTP headers
 * are required for incoming requests. It is used by the
 * {@code HeaderValidator} to enforce header-based constraints.</p>
 *
 * <p>The expected structure in {@code application.yml} is for example:</p>
 *
 * <pre>{@code
 * headers:
 *   apis:
 *     - name: employees
 *       required:
 *         - X-Request-Id
 *         - X-Client
 * }</pre>
 *
 * <p>Each API entry defines a unique name and the list of mandatory
 * HTTP headers that must be present on the request.</p>
 */
@Configuration
@ConfigurationProperties(prefix = "headers")
@Getter
@Setter
public class ConfigHeaders {

  /**
   * List of API header validation rules.
   *
   * <p>Each entry defines the API name and the set of headers required
   * for that API. This list is populated automatically from the
   * application configuration.</p>
   */
  private List<ApiHeaderRule> apis;

  /**
   * Represents a single header validation rule for a given API.
   *
   * <p>Each rule contains the API identifier ({@code name}) and the
   * list of required HTTP headers that must be included in requests
   * for that specific API.</p>
   */
  @Getter
  @Setter
  public static class ApiHeaderRule {

    /** Name of the API to which this rule applies. */
    private String name;

    /** List of required HTTP headers for the API. */
    private List<String> required;
  }

  /**
   * Returns the list of required HTTP headers for the given API name.
   *
   * <p>If the API name does not exist or no rules were configured,
   * an empty list is returned.</p>
   *
   * @param apiName the name of the API to retrieve required headers from
   * @return a list of required header names, never {@code null}
   */
  public List<String> getRequiredHeaders(String apiName) {
    if (apis == null) {
      return List.of();
    }

    return apis.stream()
      .filter(api -> api.getName().equalsIgnoreCase(apiName))
      .findFirst()
      .map(ApiHeaderRule::getRequired)
      .orElse(List.of());
  }
}
