package br.com.miltecti.dto.response;

import lombok.Data;

@Data
public class DARECompletoJSONPDFResponseDTO {

    //arquivo html de impressão do DARE.
    private byte[] pdf;
    private String mensagemErro;
}