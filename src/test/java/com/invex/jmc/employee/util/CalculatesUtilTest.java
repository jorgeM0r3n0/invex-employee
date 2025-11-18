package com.invex.jmc.employee.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CalculatesUtilTest {

  @BeforeEach
  void setUp() {
  }

  @Test
  void testCalculateAge_ValidDate() {
    LocalDate birthDate = LocalDate.of(1990, 1, 1);
    int expected = LocalDate.now().getYear() - 1990;
    int result = CalculatesUtil.calculateAge(birthDate);
    assertEquals(expected, result);
  }

  @Test
  void testCalculateAge_BirthdayToday() {
    LocalDate birthDate = LocalDate.now();
    int result = CalculatesUtil.calculateAge(birthDate);
    assertEquals(0, result);
  }

  @Test
  void testCalculateAge_FutureDate() {
    LocalDate birthDate = LocalDate.now().plusYears(1);
    int result = CalculatesUtil.calculateAge(birthDate);

    // Period.between() devuelve negativo si la fecha es futura
    assertTrue(result < 0);
  }

  @Test
  void testCalculateAge_NullDateThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> CalculatesUtil.calculateAge(null));
  }
}