package com.invex.jmc.employee.config.headers.interceptor;

import com.invex.jmc.employee.config.headers.ConfigHeaders;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Interceptor responsible for validating incoming HTTP request headers based on
 * the rules defined in {@link ConfigHeaders}.
 *
 * <p>This interceptor enforces required and prohibited headers on a per-API basis.
 * The API name is extracted from the request URI, and matched against the list
 * of configured API header rules.</p>
 *
 * <h3>How the validation works:</h3>
 * <ul>
 *   <li>The API name is extracted from the URL using {@code /{context}/{api}/...}.</li>
 *   <li>Header rules for that API are retrieved from {@link ConfigHeaders}.</li>
 *   <li>Each rule in the {@code required} list is evaluated:</li>
 *   <ul>
 *     <li><b>Required header</b>: if a header is listed normally (e.g. {@code uuid}),
 *         the interceptor ensures the header is present and non-blank.</li>
 *     <li><b>Prohibited header</b>: if a header name begins with {@code -}
 *         (e.g. {@code -Content-Type}), the interceptor ensures that the header
 *         is <em>not</em> present in the request.</li>
 *   </ul>
 *   <li>If a rule is violated, the interceptor returns an HTTP 400 response and
 *       prevents the request from being processed further.</li>
 * </ul>
 *
 * <h3>Example configuration:</h3>
 *
 * <pre>
 * headers:
 *   apis:
 *     - name: employee
 *       required:
 *         - uuid
 *         - Accept
 *         - -Content-Type
 * </pre>
 *
 * <p>In this example:
 * <ul>
 *   <li>{@code uuid} and {@code Accept} are required.</li>
 *   <li>{@code Content-Type} must NOT be sent.</li>
 * </ul></p>
 *
 * <h3>HTTP Errors</h3>
 * <ul>
 *   <li><b>400 BAD REQUEST</b> — Missing required header</li>
 *   <li><b>400 BAD REQUEST</b> — Presence of prohibited header</li>
 * </ul>
 *
 * <p>This interceptor is typically registered through a {@code WebMvcConfigurer}
 * using {@code registry.addInterceptor(...)}.</p>
 *
 * @see ConfigHeaders
 * @see HandlerInterceptor
 */
@Component
@RequiredArgsConstructor
public class HeaderValidationInterceptor implements HandlerInterceptor {

  private final ConfigHeaders configHeaders;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    String requestApiName = extractApiName(request);

    var apiConfig = configHeaders.getApis().stream()
        .filter(api -> api.getName().equalsIgnoreCase(requestApiName))
        .findFirst()
        .orElse(null);

    if (apiConfig == null) {
      return true;
    }

    List<String> requiredHeaders = apiConfig.getRequired();

    for (String header : requiredHeaders) {

      boolean prohibited = header.startsWith("-");
      String cleanHeader = prohibited ? header.substring(1) : header;
      String headerValue = request.getHeader(cleanHeader);

      if (prohibited) {
        if (headerValue != null) {
          response.sendError(HttpServletResponse.SC_BAD_REQUEST,
              "Prohibited header: " + cleanHeader);
          return false;
        }
      } else {
        if (headerValue == null || headerValue.isBlank()) {
          response.sendError(HttpServletResponse.SC_BAD_REQUEST,
              "Missing required header: " + cleanHeader);
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Extracts the API name from the request URI.
   *
   * <p>Assumes the URI format: {@code /{context}/{api}/...}</p>
   *
   * <p>Examples:</p>
   * <ul>
   *   <li>{@code /api/employee/list} → {@code employee}</li>
   *   <li>{@code /service/payroll/run} → {@code payroll}</li>
   * </ul>
   *
   * @param request the incoming HTTP servlet request
   * @return the extracted API name, or an empty string if not present
   */
  private String extractApiName(HttpServletRequest request) {
    String path = request.getRequestURI().toLowerCase(Locale.ROOT);
    String[] parts = path.split("/");
    return parts.length > 2 ? parts[2] : "";
  }
}