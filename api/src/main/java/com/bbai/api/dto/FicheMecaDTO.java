package com.bbai.api.dto;

import com.bbai.api.model.assemblage.Assemblage;
import com.bbai.api.model.client.ClientModel;
import lombok.Data;

import java.util.List;

@Data
public class FicheMecaDTO {
    private String numeroDossier;
    private ClientModel client;
    private Assemblage assemblage;

    private List<OptionDTO> options;
}
