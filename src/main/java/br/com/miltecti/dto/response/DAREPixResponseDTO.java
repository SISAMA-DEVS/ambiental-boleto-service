package br.com.miltecti.dto.response;

import lombok.Data;

@Data
public class DAREPixResponseDTO {

    //Data
    private String timestampCriacaoSolicitacao;
    //Mostra o status daquele pix ou situação.
    private String estadoSolicitacao;
    private String codigoConciliacaoSolicitante;
    private String numeroVersaoSolicitacaoPagamento;
    private String linkQrCode;
    //Objeto que deverá ser incluido no DARE.
    private String qrCode;

    private String mensagemErro;
}
