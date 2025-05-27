package com.genshinimpact.crud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CharacterDTO {
    private Integer id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Element is mandatory")
    private String element;

    @NotBlank(message = "Weapon type is mandatory")
    private String weaponType;

    @NotBlank(message = "Rarity is mandatory")
    private String rarity;

    @NotBlank(message = "Region is mandatory")
    private String region;
}