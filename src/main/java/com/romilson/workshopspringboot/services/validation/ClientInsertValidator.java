package com.romilson.workshopspringboot.services.validation;

import com.romilson.workshopspringboot.domain.Client;
import com.romilson.workshopspringboot.domain.enums.TypeClient;
import com.romilson.workshopspringboot.dto.ClientNewDTO;
import com.romilson.workshopspringboot.repositories.ClientRepository;
import com.romilson.workshopspringboot.resources.exceptions.FieldMessage;
import com.romilson.workshopspringboot.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void initialize(ClientInsert ann) {
    }

    @Override
    public boolean isValid(ClientNewDTO o, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if(o.getTypeClient().equals(TypeClient.PESSOA_FISICA.getCode()) && !BR.isValidCPF(o.getCpfCnpj())){
            list.add(new FieldMessage("cpfCnpj", "CPF Invallid"));
        }

        if(o.getTypeClient().equals(TypeClient.PESSOA_JURIDICA.getCode()) && !BR.isValidCNPJ(o.getCpfCnpj())){
            list.add(new FieldMessage("cpfCnpj", "CNPJ Invallid"));
        }

        Client client = clientRepository.findByEmail(o.getEmail());
        if(Objects.nonNull(client)){
            list.add(new FieldMessage("email", "Email already exists"));
        }

        for (FieldMessage e: list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
