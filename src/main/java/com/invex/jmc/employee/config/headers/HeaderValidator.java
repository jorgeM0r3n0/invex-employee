package com.invex.jmc.employee.config.headers;

import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * Validator implementation for the {@link HeaderConstraint} annotation.
 *
 * <p>This validator inspects the incoming {@link HttpHeaders} of a controller
 * method and ensures that all required headers configured for a specific API
 * are present. The API name is provided by the {@code api} attribute of the
 * {@link HeaderConstraint} annotation.</p>
 *
 * <h3>How it works:</h3>
 * <ul>
 *   <li>During initialization, the validator stores the API name configured in
 *       the annotation applied to the controller parameter.</li>
 *   <li>In {@link #isValid(HttpHeaders, ConstraintValidatorContext)}, the validator
 *       retrieves the list of required headers for that API through
 *       {@link ConfigHeaders#getRequiredHeaders(String)}.</li>
 *   <li>Each required header is checked against the incoming request headers.</li>
 *   <li>If a required header is missing, the validator produces a customized
 *       validation message and returns {@code false}, causing Spring Validation
 *       to return HTTP 400 automatically.</li>
 * </ul>
 *
 * <h3>Validation behavior:</h3>
 * <ul>
 *   <li><b>Valid</b>: all required headers present → request proceeds normally.</li>
 *   <li><b>Invalid</b>: one or more required headers missing → validation fails,
 *       Spring returns HTTP 400 with the message configured here.</li>
 * </ul>
 *
 * @see HeaderConstraint
 * @see ConfigHeaders
 * @see HttpHeaders
 * @see ConstraintValidator
 */
@Component
@RequiredArgsConstructor
public class HeaderValidator implements ConstraintValidator<HeaderConstraint, HttpHeaders> {

  private final ConfigHeaders configHeaders;
  private String api;

  /**
   * Initializes the validator by capturing the API name declared
   * in the {@link HeaderConstraint} annotation.
   *
   * @param constraintAnnotation the annotation instance used on the parameter
   */
  @Override
  public void initialize(HeaderConstraint constraintAnnotation) {
    this.api = constraintAnnotation.api();
  }

  /**
   * Validates that all headers required for the configured API are present
   * in the incoming request.
   *
   * <p>If a header is missing, a custom validation message is registered and
   * the method returns {@code false}.</p>
   *
   * @param headers the incoming {@link HttpHeaders} from the controller method
   * @param context validation context used to build error messages
   * @return {@code true} if all required headers are present; {@code false} otherwise
   */
  @Override
  public boolean isValid(HttpHeaders headers, ConstraintValidatorContext context) {
    List<String> requiredHeaders = configHeaders.getRequiredHeaders(api);

    for (String required : requiredHeaders) {
      if (!headers.containsKey(required)) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
            "El header requerido '" + required + "' no fue enviado")
            .addConstraintViolation();
        return false;
      }
    }
    return true;
  }
}
