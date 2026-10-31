    package br.com.miltecti.dto.response;

import lombok.Data;

@Data
public class MunicipioResponseDTO {

    private Integer co_municipio;
    private String no_municipio;
    private String uf;

    private String mensagemErro;
}
