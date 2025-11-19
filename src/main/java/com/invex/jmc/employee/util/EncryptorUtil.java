package com.invex.jmc.employee.util;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.util.text.AES256TextEncryptor;

/**
 * Utility class for encrypting text values using AES-256 encryption.
 *
 * <p>This class demonstrates how to generate an encrypted value using
 * {@link org.jasypt.util.text.AES256TextEncryptor}. It is typically used
 * to produce secure values that can later be placed inside configuration
 * files (e.g., {@code application.yml}) when working with encrypted
 * properties.</p>
 *
 * <p>The {@code main} method serves as a standalone tool: it encrypts a
 * hard-coded plaintext string and prints the result to the application
 * logs in the format {@code ENC(...)}, matching Spring Boot's convention
 * for encrypted properties when Jasypt is enabled.</p>
 *
 * <p><b>Note:</b> The secret key (password) used by the encryptor is
 * hardcoded here only for demonstration purposes and should not be stored
 * in source code in production environments.</p>
 */
@Slf4j
public class EncryptorUtil {

  /**
   * Command-line entry point that encrypts a predefined plaintext string
   * using AES-256 and logs the encrypted output.
   *
   * <p>The method performs the following steps:
   * <ol>
   *   <li>Creates an instance of {@code AES256TextEncryptor}.</li>
   *   <li>Sets the encryption password through {@code setPassword}.</li>
   *   <li>Encrypts the plaintext value.</li>
   *   <li>Logs the result if INFO logging is enabled.</li>
   * </ol>
   * </p>
   *
   * @param args CLI arguments (not used)
   */
  public static void main(String[] args) {
    AES256TextEncryptor encryptor = new AES256TextEncryptor();
    encryptor.setPassword("MI_LLAVE_SECRETA");

    String plaintext = "eurekaJMC";
    String encrypted = encryptor.encrypt(plaintext);

    if (log.isInfoEnabled()) {
      log.info("ENC(" + encrypted + ")");
    }
  }
}
