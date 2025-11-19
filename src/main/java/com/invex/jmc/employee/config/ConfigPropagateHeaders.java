package com.invex.jmc.employee.config;

import com.invex.jmc.employee.constants.ConstantsUtil;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

/**
 * Configuration class that defines which HTTP headers should be propagated
 * from incoming requests to outgoing requests in downstream service calls.
 *
 * <p>This class is populated automatically from application properties under
 * the prefix {@code headers}. The property {@code propagateHeaders} contains
 * the list of header names that should always be forwarded.</p>
 *
 * <h3>Example configuration:</h3>
 * <pre>
 * headers:
 *   propagateHeaders:
 *     - uuid
 *     - Accept-Language
 *     - Authorization
 * </pre>
 *
 * <h3>Behavior:</h3>
 * <ul>
 *   <li>The constructor safely clones the provided list to avoid accidental mutation.</li>
 *   <li>{@link #fixHeaders(HttpHeaders)} creates a filtered map of headers that:
 *       <ul>
 *         <li>Includes only the headers listed in {@code propagateHeaders}.</li>
 *         <li>Ensures the {@code Accept-Language} header always has a default value
 *             if it was not provided in the incoming request.</li>
 *       </ul>
 *   </li>
 *   <li>The final result is returned as a single-value header map ready to be used
 *       in outgoing HTTP calls (e.g., via {@code RestTemplate} or {@code WebClient}).</li>
 * </ul>
 *
 * <h3>Accept-Language Injection:</h3>
 *
 * <p>If the incoming request does not include the {@code Accept-Language} header,
 * a default modality value defined in {@link ConstantsUtil#DEF_MODALITY} is added.</p>
 *
 * <h3>Intended Usage:</h3>
 * <ul>
 *   <li>Inter-service communication where correlation or identity headers must propagate.</li>
 *   <li>Gateways or middleware microservices enforcing consistent header forwarding.</li>
 * </ul>
 *
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 * @see org.springframework.http.HttpHeaders
 */
@ConfigurationProperties(prefix = "headers")
@Configuration
@Setter
@Getter
public class ConfigPropagateHeaders {

  private List<String> propagateHeaders;

  /**
   * Constructs a new configuration object and safely clones the list of header
   * names to avoid unintended external modifications.
   *
   * @param propagateHeaders the list of headers to be propagated
   */
  public ConfigPropagateHeaders(List<String> propagateHeaders) {
    this.propagateHeaders = ObjectUtils.cloneIfPossible(propagateHeaders);
  }

  /**
   * Produces a sanitized and filtered map of headers to propagate to downstream services.
   *
   * <p>This method:
   * <ul>
   *   <li>Ensures the {@code Accept-Language} header is present, injecting a default value
   *       if necessary.</li>
   *   <li>Extracts from the incoming request only the headers listed in
   *       {@code propagateHeaders}.</li>
   *   <li>Returns a single-value header map, which is typically required by HTTP clients.</li>
   * </ul>
   * </p>
   *
   * @param httpHeaders the incoming HTTP request headers
   * @return a map of header names and values ready for propagation
   */
  public Map<String, String> fixHeaders(HttpHeaders httpHeaders) {
    var headers = new HttpHeaders();

    if (httpHeaders.entrySet().stream().noneMatch(
        header -> header.getKey().trim().equalsIgnoreCase(ConstantsUtil.ACCEPT_LANGUAGE)
    )) {
      httpHeaders.add(
          ConstantsUtil.ACCEPT_LANGUAGE.toLowerCase(Locale.US),
          ConstantsUtil.DEF_MODALITY
      );
    }

    this.propagateHeaders.forEach(header ->
        headers.add(header, httpHeaders.getFirst(header))
    );

    return headers.toSingleValueMap();
  }
}