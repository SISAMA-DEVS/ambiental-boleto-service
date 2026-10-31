package br.com.miltecti.dto.request;

import lombok.Data;

@Data
public class DARECompletoRequestDTO {

    private String nome_contribuinte;
    private String endereco;
    private String municipio;
    private String cep;
    private String uf;
    private String telefone;

    //identificar o servidor quando for o caso.
    private String matricula;

    //descrição do código de receita.
    private String infor_complementares;

    private String infor_complementares_usuario;

    //inscrição estadual, cpf ou cnpj, campo que se correlaciona com o campo "indicador"
    private String identificacao;

    private String complemento_identificacao;
    private String mesano_referencia;
    private String data_vencimento;
    private String numero_parcela;
    private String codigo_receita;
    private String codigo_municipio;
    private String valor_principal;
    private String valor_multa;
    private String valor_juros;
    private String valor_acrescimos;
    private String valor_total;
    private String indicador;
    private String c_o;
}
