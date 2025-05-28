package br.com.eplano.commons.dto;

import lombok.Data;

@Data
public class EmpresaDTO {
    private Long id;
    private String cnpj;
    private String nomeEmpresarial;
    private String nomeFantasia;
    private String criadoPor;
    private EnumNameValueDTO segmentoEmpresa;
    private EnumNameValueDTO regimeTributario;
    private String descricaoNegocio;
    private Float imposto;
    private Float valorImposto;
    private SegmentoNegocioDTO segmentoNegocio;
    private FaixaSimplesNacionalDTO FaixaSimplesNacional;
    private EnumNameValueDTO naturezaJuridica;
    private EnumNameValueDTO porteEmpresa;

}
