package com.invex.jmc.employee.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;

/**
 * Utility class that provides helper methods for logging Java objects as
 * formatted JSON using SLF4J.
 *
 * <p>This class centralizes common logging patterns, such as converting
 * objects to pretty-printed JSON and conditionally logging them based on the
 * current log level. This avoids repetitive JSON serialization logic
 * throughout the codebase and ensures consistent log formatting.</p>
 *
 * <p>A private constructor (via {@code @NoArgsConstructor(access = AccessLevel.PRIVATE)})
 * prevents instantiation, as the class is intended solely for static utility use.</p>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoggerUtils {

  /** Shared Gson instance configured to generate pretty-printed JSON output. */
  private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

  /**
   * Logs the given object as a JSON string using the DEBUG log level.
   *
   * <p>The method checks whether DEBUG logging is enabled before performing
   * JSON serialization, thus avoiding unnecessary overhead when DEBUG logging
   * is disabled.</p>
   *
   * <p>The {@code statement} parameter should be an SLF4J-style template
   * containing a single placeholder {@code {}} where the JSON string will be
   * injected.</p>
   *
   * <pre>{@code
   * LoggerUtils.logDebugJson(log, "Payload:\n{}", requestObject);
   * }</pre>
   *
   * @param log       the SLF4J {@link Logger} instance used to write the log entry
   * @param statement the log message template containing one placeholder
   * @param object    the object to be serialized into JSON and logged
   */
  public static void logDebugJson(Logger log, String statement, Object object) {
    if (log.isDebugEnabled()) {
      log.debug(statement, gson.toJson(object));
    }
  }

  /**
   * Logs the given object as a JSON string using the INFO log level.
   *
   * <p>The method checks whether INFO logging is enabled before performing
   * JSON serialization, ensuring efficient execution when INFO logs are
   * disabled.</p>
   *
   * <p>The {@code statement} parameter must contain one SLF4J placeholder
   * {@code {}} to receive the generated JSON string.</p>
   *
   * <pre>{@code
   * LoggerUtils.logInfoJson(log, "Response:\n{}", responseObject);
   * }</pre>
   *
   * @param log       the SLF4J {@link Logger} instance used to write the log entry
   * @param statement the log message template containing one placeholder
   * @param object    the object to be serialized into JSON and logged
   */
  public static void logInfoJson(Logger log, String statement, Object object) {
    if (log.isInfoEnabled()) {
      log.info(statement, gson.toJson(object));
    }
  }
}
