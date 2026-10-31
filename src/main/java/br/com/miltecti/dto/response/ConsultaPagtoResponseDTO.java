package br.com.miltecti.dto.response;

import lombok.Data;

@Data
public class ConsultaPagtoResponseDTO {

    private DadosArrecadacaoResponseDTO dados_arrecadacao;
    private String situacao_dare;

    private String mensagemErro;
}
