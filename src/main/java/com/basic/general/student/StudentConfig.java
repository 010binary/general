package com.basic.general.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import static java.time.Month.AUGUST;

public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository
    ) {
        return args -> {
            Student augustine = new Student(
                    "Augustine",
                    "augustine12@gmail.com",
                    LocalDate.of(2000, AUGUST, 15),
                    25
            );

            Student emeka = new Student(
                    "Emeka",
                    "chukwuemeka@gmail.com",
                    LocalDate.of(2000, AUGUST, 15),
                    25
            );
        };
    }
}
