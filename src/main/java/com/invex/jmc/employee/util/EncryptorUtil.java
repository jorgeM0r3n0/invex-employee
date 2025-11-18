package com.invex.jmc.employee.util;

import org.jasypt.util.text.AES256TextEncryptor;

public class EncryptorUtil {

  public static void main(String[] args) {
    AES256TextEncryptor encryptor = new AES256TextEncryptor();
    encryptor.setPassword("MI_LLAVE_SECRETA"); // la pasas como parámetro si quieres

    String plaintext = "eurekaJMC";
    String encrypted = encryptor.encrypt(plaintext);

    System.out.println("ENC(" + encrypted + ")");
  }
}