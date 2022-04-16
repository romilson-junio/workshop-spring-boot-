package com.romilson.workshopspringboot.dto;

import com.romilson.workshopspringboot.services.validation.ClientInsert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ClientInsert
public class ClientNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Mandatory filling")
    @Length(min = 5, max = 120, message = "The length must be between 5 and 120 characters")
    private String name;

    @NotEmpty(message = "Mandatory filling")
    @Email(message = "Invallid Email")
    private String email;

    @NotEmpty(message = "Mandatory filling")
    private String cpfCnpj;
    private Integer typeClient;

    @NotEmpty(message = "Mandatory filling")
    private String publicPlace;

    @NotEmpty(message = "Mandatory filling")
    private String number;
    private String complement;
    private String district;

    @NotEmpty(message = "Mandatory filling")
    private String zipCode;

    @NotEmpty(message = "Mandatory filling")
    private String phone1;
    private String phone2;
    private String phone3;

    private Integer cityId;

}
