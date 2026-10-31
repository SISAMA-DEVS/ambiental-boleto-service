package br.com.miltecti.dto.response;

import lombok.Data;

@Data
public class InfoResponseDTO {

    private String versao_da_api;
    private String versao_manual_integracao;
    private String ambiente;

    private String mensagemErro;
}
