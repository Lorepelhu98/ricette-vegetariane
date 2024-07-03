package com.vegan.ricette_vegetariane.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private String text;
    private String author;
    // Metodo setter per il campo recipe
    // Metodo getter per il campo recipe
    @Setter
    @Getter
    private RecipeDTO recipe;


}
