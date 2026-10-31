package br.com.miltecti.dto.response;

import lombok.Data;
import java.io.Serializable;

@Data
public class ErroResponseDTO {

    private Integer codigo;
    private String descricao;
}
