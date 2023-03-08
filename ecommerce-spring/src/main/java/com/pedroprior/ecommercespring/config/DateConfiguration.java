package com.pedroprior.ecommercespring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class DateConfiguration {

    @Bean
    public LocalDate dateNow() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = now.format(formatter);

        return LocalDate.parse(formattedDate, formatter);

    }


    @Bean
    public LocalDate getDateAfterThreeDays() {
        LocalDate initialData = dateNow();
        return initialData.plusDays(3);
    }




}
