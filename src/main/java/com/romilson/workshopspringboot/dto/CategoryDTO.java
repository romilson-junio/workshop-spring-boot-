package com.romilson.workshopspringboot.dto;

import com.romilson.workshopspringboot.domain.Category;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include()

    private Integer id;

    @NotEmpty(message = "Mandatory filling")
    @Length(min = 5, max = 80, message = "The length must be between 5 and 80 characters")
    private String name;

    public CategoryDTO(Category category){
        this.id = category.getId();
        this.name = category.getName();
    }
}
