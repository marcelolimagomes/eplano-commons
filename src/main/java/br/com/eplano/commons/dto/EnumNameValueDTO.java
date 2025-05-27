package br.com.eplano.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor // <-- Adicionado para garantir construtor padrão
public class EnumNameValueDTO {
    private String label;
    private String id;

    public EnumNameValueDTO(String id) {
        this.id = id;
        this.label = id;
    }

}