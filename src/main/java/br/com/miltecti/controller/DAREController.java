package br.com.miltecti.controller;

import br.com.miltecti.dto.request.DARECompletoRequestDTO;
import br.com.miltecti.dto.request.DAREPixRequestDTO;
import br.com.miltecti.dto.response.DARECompletoJSONPDFResponseDTO;
import br.com.miltecti.dto.response.DARECompletoJSONResponseDTO;
import br.com.miltecti.dto.response.DARECompletoResponseDTO;
import br.com.miltecti.service.DAREService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class DAREController {


    @Autowired
    private DAREService dareService;


    @ApiOperation(
            value="Cria DARE - Retorno HTML",
            notes="Cria DARE - Retorno HTML",
            response= ResponseEntity.class)
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna o html com os dados do DARE."
            ),
            @ApiResponse(
                    code=404,
                    message="Atenção!. Endpoint nao encontrado para cria DARE."
            ),
            @ApiResponse(
                    code=500,
                    message ="Ocorreu um erro inesperado. Contate o suporte!"
            )
    })
    @PostMapping(value = "/cria-dare-completo-html")
    public ResponseEntity<?> criarDARECompletoHtml(@RequestBody DARECompletoRequestDTO dareCompletoRequestDTO) {
        return ResponseEntity.ok(this.dareService.criarDARECompletoHtml(dareCompletoRequestDTO));
    }



    @ApiOperation(
            value="Cria DARE - Retorno PDF - Recebe html de RO e converte para PDF",
            notes="Cria DARE - Retorno PDF - Recebe html de RO e converte para PDF",
            response= ResponseEntity.class)
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna o html com os dados do DARE."
            ),
            @ApiResponse(
                    code=404,
                    message="Atenção!. Endpoint nao encontrado para cria DARE."
            ),
            @ApiResponse(
                    code=500,
                    message ="Ocorreu um erro inesperado. Contate o suporte!"
            )
    })
    @PostMapping(value = "/cria-dare-completo-html-pdf")
    public ResponseEntity<?> criarDARECompletoHtmlPdf(@RequestBody DARECompletoRequestDTO dareCompletoRequestDTO) throws IOException {
        DARECompletoResponseDTO dareCompletoResponseDTO = this.dareService.criarDARECompletoHtml(dareCompletoRequestDTO);
        return ResponseEntity.ok(this.dareService.htmlToPdf(dareCompletoResponseDTO.getHtml()));
    }



    @ApiOperation(
            value="Cria DARE - Retorno JSON",
            notes="Cria DARE - Retorno JSON",
            response= ResponseEntity.class)
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna o json com os dados do DARE."
            ),
            @ApiResponse(
                    code=404,
                    message="Atenção!. Endpoint nao encontrado para cria DARE."
            ),
            @ApiResponse(
                    code=500,
                    message ="Ocorreu um erro inesperado. Contate o suporte!"
            )
    })
    @PostMapping(value = "/cria-dare-completo-json")
    public ResponseEntity<?> criarDARECompletoJSON(@RequestBody DARECompletoRequestDTO dareCompletoRequestDTO) {
        return ResponseEntity.ok(this.dareService.criarDARECompletoJSON(dareCompletoRequestDTO));
    }



    @ApiOperation(
            value="Cria DARE - Retorno PDF - Recebe json de RO e converte para PDF o base64",
            notes="Cria DARE - Retorno PDF - Recebe json de RO e converte para PDF o base64",
            response= ResponseEntity.class)
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna o json com os dados do DARE."
            ),
            @ApiResponse(
                    code=404,
                    message="Atenção!. Endpoint nao encontrado para cria DARE."
            ),
            @ApiResponse(
                    code=500,
                    message ="Ocorreu um erro inesperado. Contate o suporte!"
            )
    })
    @PostMapping(value = "/cria-dare-completo-json-pdf")
    public ResponseEntity<?> criarDARECompletoJSONPDF(@RequestBody DARECompletoRequestDTO dareCompletoRequestDTO) throws IOException {
        DARECompletoJSONResponseDTO dareCompletoJSONResponseDTO = this.dareService.criarDARECompletoJSON(dareCompletoRequestDTO);

        DARECompletoJSONPDFResponseDTO dareCompletoJSONPDFResponseDTO =  new DARECompletoJSONPDFResponseDTO();
        dareCompletoJSONPDFResponseDTO.setPdf(this.dareService.base64HtmlToPdf(dareCompletoJSONResponseDTO.getDare_base64()));
        dareCompletoJSONPDFResponseDTO.setMensagemErro(dareCompletoJSONResponseDTO.getMensagemErro());

        return ResponseEntity.ok(dareCompletoJSONPDFResponseDTO);
    }




    @ApiOperation(
            value="Cria DARE - Retorno Pix",
            notes="Cria DARE - Retorno Pix",
            response= ResponseEntity.class)
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna o json com os dados do DARE."
            ),
            @ApiResponse(
                    code=404,
                    message="Atenção!. Endpoint nao encontrado para cria DARE."
            ),
            @ApiResponse(
                    code=500,
                    message ="Ocorreu um erro inesperado. Contate o suporte!"
            )
    })
    @PostMapping(value = "/cria-dare-pix")
    public ResponseEntity<?> criarDAREPix(@RequestBody DAREPixRequestDTO darePixRequestDTO) {
        return ResponseEntity.ok(this.dareService.criarDAREPix(darePixRequestDTO));
    }



    @ApiOperation(
            value="Consulta informações da API",
            notes="Consulta informações da API",
            response= ResponseEntity.class)
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna informações da API."
            ),
            @ApiResponse(
                    code=404,
                    message="Atenção!. Endpoint nao encontrado."
            ),
            @ApiResponse(
                    code=500,
                    message ="Ocorreu um erro inesperado. Contate o suporte!"
            )
    })
    @GetMapping(value = "/info")
    public ResponseEntity<?> info() {
        return ResponseEntity.ok(this.dareService.info());
    }


    @ApiOperation(
            value="Consulta receitas",
            notes="Consulta receitas",
            response= ResponseEntity.class)
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna informações das receitas."
            ),
            @ApiResponse(
                    code=404,
                    message="Atenção!. Endpoint nao encontrado."
            ),
            @ApiResponse(
                    code=500,
                    message ="Ocorreu um erro inesperado. Contate o suporte!"
            )
    })
    @GetMapping(value = "/receitas")
    public ResponseEntity<?> receitas() {
        return ResponseEntity.ok(this.dareService.receitas());
    }


    @ApiOperation(
            value="Consulta receitas SEDAM",
            notes="Consulta receitas SEDAM",
            response= ResponseEntity.class)
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna informações das receitas  SEDAM."
            ),
            @ApiResponse(
                    code=404,
                    message="Atenção!. Endpoint nao encontrado."
            ),
            @ApiResponse(
                    code=500,
                    message ="Ocorreu um erro inesperado. Contate o suporte!"
            )
    })
    @GetMapping(value = "/receitas-sedam")
    public ResponseEntity<?> receitasSedam() {
        return ResponseEntity.ok(this.dareService.receitasSedam());
    }


    @ApiOperation(
            value="Consulta orgãos",
            notes="Consulta orgãos",
            response= ResponseEntity.class)
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna informações dos orgãos."
            ),
            @ApiResponse(
                    code=404,
                    message="Atenção!. Endpoint nao encontrado."
            ),
            @ApiResponse(
                    code=500,
                    message ="Ocorreu um erro inesperado. Contate o suporte!"
            )
    })
    @GetMapping(value = "/orgaos")
    public ResponseEntity<?> orgaos() {
        return ResponseEntity.ok(this.dareService.orgaos());
    }

    @ApiOperation(
            value="Consulta orgãos SEDAM",
            notes="Consulta orgãos SEDAM",
            response= ResponseEntity.class)
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna informações dos orgãos SEDAM."
            ),
            @ApiResponse(
                    code=404,
                    message="Atenção!. Endpoint nao encontrado."
            ),
            @ApiResponse(
                    code=500,
                    message ="Ocorreu um erro inesperado. Contate o suporte!"
            )
    })
    @GetMapping(value = "/orgaos-sedam")
    public ResponseEntity<?> orgaosSedam() {
        return ResponseEntity.ok(this.dareService.orgaosSedam());
    }


    @ApiOperation(
            value="Consulta Pagto",
            notes="Consulta Pagto",
            response= ResponseEntity.class)
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message="Retorna informações do Pagto."
            ),
            @ApiResponse(
                    code=404,
                    message="Atenção!. Endpoint nao encontrado."
            ),
            @ApiResponse(
                    code=500,
                    message ="Ocorreu um erro inesperado. Contate o suporte!"
            )
    })
    @PostMapping(value = "/consulta-pagto")
    public ResponseEntity<?> consultaPagto(@RequestParam("numero_guia_cbarras") String numeroGuiaCbarras,
                                           @RequestParam("numero_parcela") String numeroParcela) {
        return ResponseEntity.ok(this.dareService.consultaPagto(numeroGuiaCbarras, numeroParcela));
    }

}
