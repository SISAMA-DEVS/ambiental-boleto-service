package br.com.miltecti.dto.response;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class DARECompletoJSONResponseDTO {

    //código de barras com os dígitos verificadores
    private List<String> cb_c_dv;

    //código de barras sem os dígitos verificadores
    private List<String> cb_s_dv;

    //arquivo html de impressão do DARE.
    private String dare_base64;

    private String nome_contribuinte;
    private String endereco;
    private String municipio;
    private String cep;
    private String uf;
    private String telefone;
    private String matricula;
    private String numero_guia;
    private String identificacao;
    private String complemento_identificacao;
    private String mesano_referencia;
    private String data_vencimento;
    private String data_pagamento;
    private String numero_parcela;
    private String codigo_receita;
    private String codigo_municipio;
    private String valor_principal;
    private String valor_multa;
    private String valor_juros;
    private String valor_acrescimos;
    private String valor_total;
    private String infor_complementares;
    private String infor_notafiscal;
    private String infor_destinatario;
    private String infor_origem;
    private String infor_destino;
    private String infor_produto;
    private String infor_basecalculo;
    private String codigo_orgao;
    private String infor_complementares_usuario;
    private String pix_qrcode;

    private String mensagemErro;
}