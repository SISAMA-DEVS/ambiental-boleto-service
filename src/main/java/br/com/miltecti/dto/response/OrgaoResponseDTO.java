package br.com.miltecti.dto.response;

import lombok.Data;

@Data
public class OrgaoResponseDTO {

    private Integer c_o;
    private String nome_orgao;
    private String sigla_orgao;

    private String mensagemErro;
}
