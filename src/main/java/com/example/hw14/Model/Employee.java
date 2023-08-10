package com.example.hw14.Model;

import jakarta.validation.constraints.*;
import jdk.jfr.BooleanFlag;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Year;
import java.util.Date;

@Data
@AllArgsConstructor
public class Employee {
    @NotEmpty(message = "this field should not be empty")
    @Size(min = 2, max = 10)
    private String ID;

    @NotEmpty(message = "this field should not be empty")
    @Size(min = 4, max = 20)
    private String name;

    @NotNull(message = "this field should not be empty")
    @Positive(message = "only positive numbers")
    @Min(26)
    private int age;

    @NotEmpty(message = "this field should not be empty")
    //@Category(value = {"supervisor","coordinator"})
    private String position;


    @AssertFalse(message = "only false")
    private boolean onLeave;

    @NotNull(message = "this field should not be empty")
    @Positive(message = "only numbers")
    @Min(1920)
    @Max(2023)
    private int employmentYear;
//    @PastOrPresent
//    private Date employmentYear;

    @NotNull(message = "this field should not be empty")
    @PositiveOrZero(message = "only numbers")
    private int annualLeave;
}
