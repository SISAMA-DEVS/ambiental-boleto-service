package br.com.miltecti.dto.request;

import lombok.Data;

@Data
public class DAREPixRequestDTO {

    //Codigo de barras sem os dígitos verificadores e sem espaço entre dígitos.
    private String codigo_barras_44;
    //Divisão entre decimais obrgatoriamente (.) – Exemplo: “10000.55” dez mil reais e cinquenta e cinco centavos.
    private String valor_total;
    //Deverá ser enviado no padrão DD/MM/ANO. Exemplo: “18/04/2022”
    private String vencimento;
    //Geralmente aqui se inclui o nome do tributo ou serviço a ser pago. Exemplo: “Taxa de abate animal.”
    private String descricao;

}
