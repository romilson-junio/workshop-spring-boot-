package com.romilson.workshopspringboot.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandarError {
    private Integer status;
    private String message;
    private Long timestamp;
}
