package com.bbai.api.dto;

import com.bbai.api.model.Client.ClientModel;
import com.bbai.api.model.assemblage.Assemblage;
import lombok.Data;

@Data
public class FicheMecaDTO {
    private ClientModel client;
    private Assemblage assemblage;
}
