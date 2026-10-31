package br.com.miltecti.client;

import br.com.miltecti.dto.request.DARECompletoRequestDTO;
import br.com.miltecti.dto.request.DAREPixRequestDTO;
import br.com.miltecti.dto.response.*;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import br.com.miltecti.config.*;
import java.util.List;

@FeignClient(value = "ambiental-boleto-sefin-ro-service", url = "${ambiental.url.sefin-ro}",
        configuration = FeignConfig.class, dismiss404 = true)
public interface SEFINROClient {

    @PostMapping(value = "/avulso/processar/documento/externo", consumes = "application/json")
    Response criarDARECompletoHtml(@RequestBody DARECompletoRequestDTO request);

    @PostMapping(value = "/avulso/processar/documento/externo.json", consumes = "application/json")
    DARECompletoJSONResponseDTO criarDARECompletoJson(@RequestBody DARECompletoRequestDTO dareCompletoRequestDTO);

    @PostMapping(value = "/api/avulso/pix.json", consumes = "application/json")
    DAREPixResponseDTO criarDAREPix(@RequestBody DAREPixRequestDTO darePixRequestDTO);

    @GetMapping("/api/info")
    InfoResponseDTO info();

    @GetMapping("/api/receitas")
    List<ReceitaResponseDTO> receitas();

    @GetMapping("/api/orgaos")
    List<OrgaoResponseDTO> orgaos();

    @PostMapping(value = "/situacao-dare/api/consulta_pgto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ConsultaPagtoResponseDTO consultaPagto(@RequestHeader("API-Token") String apiToken,
                                           @RequestParam("numero_guia_cbarras") String numeroGuiaCbarras,
                                           @RequestParam("numero_parcela") String numeroParcela);

}
