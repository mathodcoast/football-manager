package com.bobocode.footballmanager.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TeamDto {
    private Long id;
    private String name;
    private String city;
    private String captainName;
}
