package com.invex.jmc.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Main entry point for the Invex Employee Management Application.
 *
 * <p>This class bootstraps the Spring Boot application and initializes all
 * configured components, beans, and services within the application context.
 *
 * <p>It uses Spring Boot's auto-configuration mechanism to streamline setup and
 * reduce boilerplate code. Uncomment the {@code @EnableFeignClients} annotation
 * if the application requires Feign-based HTTP clients for inter-service communication.
 *
 * <h2>Execution</h2>
 *
 * <p>The application is launched via the {@link SpringApplication#run(Class, String...)}
 * method, which starts the embedded server and initializes the Spring container.</p>
 *
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 * @see org.springframework.cloud.openfeign.EnableFeignClients
 */
@SpringBootApplication
@EnableFeignClients
public class InvexEmployeeApp {
  /**
   * Starts the Invex Employee application.
   *
   * @param args command-line arguments passed during JVM startup
   */
  public static void main(String[] args) {
    SpringApplication.run(InvexEmployeeApp.class, args);
  }
}
