package com.bbai.api.dto;

import com.bbai.api.model.assemblage.Assemblage;
import com.bbai.api.model.client.ClientModel;
import lombok.Data;

@Data
public class FicheMecaDTO {
    private ClientModel client;
    private Assemblage assemblage;
}
