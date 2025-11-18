package com.invex.jmc.employee.util;

import java.time.LocalDate;
import java.time.Period;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utility class for performing common calculations.
 *
 * <p>Currently provides methods related to age calculation.
 * </p>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CalculatesUtil {
  /**
   * Calculates the age in years based on the given birth date.
   *
   * <p>The calculation considers the current date and returns the
   * number of full years elapsed since the birth date.</p>
   *
   * @param birthDate the birth date of the person; must not be null
   * @return the age in years
   * @throws IllegalArgumentException if {@code birthDate} is null
   */
  public static int calculateAge(LocalDate birthDate) {
    if (birthDate == null) {
      throw new IllegalArgumentException("birthDate cannot be null");
    }
    LocalDate today = LocalDate.now();
    return Period.between(birthDate, today).getYears();
  }
}
