package com.basic.general.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.AUGUST;

@Configuration
public class StudentConfig {

        @Bean
        CommandLineRunner commandLineRunner(
                        StudentRepository repository) {
                return args -> {
                        Student augustine = new Student(
                                        "Augustine",
                                        "augustine12@gmail.com",
                                        LocalDate.of(2000, AUGUST, 15));

                        Student emeka = new Student(
                                        "Emeka",
                                        "chukwuemeka@gmail.com",
                                        LocalDate.of(2004, AUGUST, 15));

                        repository.saveAll(
                                        List.of(augustine, emeka));
                };
        }
}
