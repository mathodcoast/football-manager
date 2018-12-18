package com.bobocode.footballmanager.model.dto;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class PlayerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String teamName;
}
