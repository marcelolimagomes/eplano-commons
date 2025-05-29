package br.com.eplano.commons.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SugerirProdutosServicosRequestDTO {
    @NotBlank(message = "Segmento da empresa é obrigatório")
    private String segmentoEmpresa;

    @NotBlank(message = "Descrição do segmento da empresa é obrigatória")
    private String descricaoSegmentoEmpresa;
}
