package br.com.miltecti.dto.response;

import lombok.Data;

@Data
public class ReceitaResponseDTO {

    private String sigla;
    private String nome_orgao;
    private Integer codigo_receita;
    private String descricao_receita;

    private String mensagemErro;
}
