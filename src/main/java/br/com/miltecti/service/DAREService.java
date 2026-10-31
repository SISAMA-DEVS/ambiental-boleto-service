package br.com.miltecti.service;

import java.util.Base64;
import br.com.miltecti.client.SEFINROClient;
import br.com.miltecti.dto.request.DARECompletoRequestDTO;
import br.com.miltecti.dto.request.DAREPixRequestDTO;
import br.com.miltecti.dto.response.*;
import br.com.miltecti.exception.BadRequestException;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import feign.FeignException;
import feign.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.jsoup.nodes.Element;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;

@Service
public class DAREService {

    private final SEFINROClient sefinROClient;
    private final HttpClient httpClient;

    @Value("${ambiental.url.sefin-ro-documento}")
    private String sefinRODocumento;

    @Value("${ambiental.token.API-Token}")
    private String apiToken;

    public DAREService(SEFINROClient sefinROClient, HttpClient httpClient) {
        this.sefinROClient = sefinROClient;
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(30))
                .followRedirects(HttpClient.Redirect.NEVER)
                .build();
    }

    public InfoResponseDTO info() {
        InfoResponseDTO response = new InfoResponseDTO();
        try {
            response = this.sefinROClient.info();

            if (response == null) {
                throw new RuntimeException("A SEFIN retornou null.");
            }

            return response;

        } catch (Exception e) {
            if (e instanceof FeignException feignException) {
                response.setMensagemErro(feignException.status() + " - " + feignException.getMessage());
            } else {
                response.setMensagemErro(e.getMessage());
            }
            return response;
        }
    }


    public List<ReceitaResponseDTO> receitas() {
        List<ReceitaResponseDTO> response =  new ArrayList<>();
        try {
             response = this.sefinROClient.receitas();

            if (response == null) {
                throw new RuntimeException("A SEFIN retornou null.");
            }

            return response;

        } catch (Exception e) {
            if (e instanceof FeignException feignException) {
                response.get(0).setMensagemErro(feignException.status() + " - " + feignException.getMessage());
            } else {
                response.get(0).setMensagemErro(e.getMessage());
            }
            return response;
        }
    }

    public List<ReceitaResponseDTO> receitasSedam() {
        List<ReceitaResponseDTO> response =  new ArrayList<>();
        try {
            response = this.sefinROClient.receitas();

            if (response == null) {
                throw new RuntimeException("A SEFIN retornou null.");
            }

            response.removeIf(receita ->
                    receita.getSigla() == null ||
                            !"SEDAM".equalsIgnoreCase(receita.getSigla().trim())
            );

            return response;

        } catch (Exception e) {
            if (e instanceof FeignException feignException) {
                response.get(0).setMensagemErro(feignException.status() + " - " + feignException.getMessage());
            } else {
                response.get(0).setMensagemErro(e.getMessage());
            }
            return response;
        }
    }


    public List<OrgaoResponseDTO> orgaos() {
        List<OrgaoResponseDTO> response =  new ArrayList<>();
        try {
             response = this.sefinROClient.orgaos();

            if (response == null) {
                throw new RuntimeException("A SEFIN retornou null.");
            }

            return response;

        } catch (Exception e) {
            if (e instanceof FeignException feignException) {
                response.get(0).setMensagemErro(feignException.status() + " - " + feignException.getMessage());
            } else {
                response.get(0).setMensagemErro(e.getMessage());
            }
            return response;
        }
    }

    public List<OrgaoResponseDTO> orgaosSedam() {
        List<OrgaoResponseDTO> response =  new ArrayList<>();
        try {
            response = this.sefinROClient.orgaos();

            if (response == null) {
                throw new RuntimeException("A SEFIN retornou null.");
            }

            response.removeIf(receita ->
                    receita.getSigla_orgao() == null ||
                            !"SEDAM".equalsIgnoreCase(receita.getSigla_orgao().trim())
            );

            return response;

        } catch (Exception e) {
            if (e instanceof FeignException feignException) {
                response.get(0).setMensagemErro(feignException.status() + " - " + feignException.getMessage());
            } else {
                response.get(0).setMensagemErro(e.getMessage());
            }
            return response;
        }
    }


    public ConsultaPagtoResponseDTO consultaPagto(String numeroGuiaCbarras, String numeroParcela) {
        ConsultaPagtoResponseDTO response =  new ConsultaPagtoResponseDTO();
        try {
            response = this.sefinROClient.consultaPagto(apiToken, numeroGuiaCbarras, numeroParcela);

            if (response == null) {
                throw new RuntimeException("A SEFIN retornou null.");
            }

            return response;

        } catch (Exception e) {
            if (e instanceof FeignException feignException) {
                response.setMensagemErro(feignException.status() + " - " + feignException.getMessage());
            } else {
                response.setMensagemErro(e.getMessage());
            }
            return response;
        }
    }


    public DAREPixResponseDTO criarDAREPix(DAREPixRequestDTO darePixRequestDTO) {
        DAREPixResponseDTO response = new DAREPixResponseDTO();
        try {
             response = this.sefinROClient.criarDAREPix(darePixRequestDTO);

            if (response == null) {
                throw new RuntimeException("A SEFIN retornou null.");
            }

            return response;

        } catch (Exception e) {
            if (e instanceof FeignException feignException) {
                response.setMensagemErro(feignException.status() + " - " + feignException.getMessage());
            } else {
                response.setMensagemErro(e.getMessage());
            }
            return response;
        }
    }


    public DARECompletoJSONResponseDTO criarDARECompletoJSON(DARECompletoRequestDTO dareCompletoRequestDTO) {
        DARECompletoJSONResponseDTO response = new DARECompletoJSONResponseDTO();
        try {
            validarRequest(dareCompletoRequestDTO);

            response = this.sefinROClient.criarDARECompletoJson(dareCompletoRequestDTO);

            if (response == null) {
                throw new RuntimeException("A SEFIN retornou null.");
            }

            return response;

        } catch (Exception e) {
            if (e instanceof FeignException feignException) {
                response.setMensagemErro(feignException.status() + " - " + feignException.getMessage());
            } else {
                response.setMensagemErro(e.getMessage());
            }
            return response;
        }
    }


    public DARECompletoResponseDTO criarDARECompletoHtml(DARECompletoRequestDTO dareCompletoRequestDTO) {
        DARECompletoResponseDTO response = new DARECompletoResponseDTO();
        try {
            validarRequest(dareCompletoRequestDTO);

            Response responseCriacao = this.sefinROClient.criarDARECompletoHtml(dareCompletoRequestDTO);

            if (responseCriacao.status() != 302) {
                String body = lerBody(responseCriacao);
                throw new RuntimeException("Resposta inesperada da SEFIN. " + "HTTP " + responseCriacao.status() + ". Body: " + body);
            }

            String cookies = extrairCookies(responseCriacao);
            if (cookies == null || cookies.isBlank()) {
                throw new RuntimeException("A SEFIN retornou 302, " + "mas não retornou cookies de sessão.");
            }

            String documento = buscarDocumentoHtml(cookies);

            if (documento == null || documento.isBlank()) {
                throw new RuntimeException("A SEFIN retornou o documento sem conteúdo.");
            }

            response.setHtml(documento);
            return response;

        } catch (Exception e) {
            if (e instanceof FeignException feignException) {
                response.setMensagemErro(feignException.status() + " - " + feignException.getMessage());
            } else {
                response.setMensagemErro(e.getMessage());
            }
            return response;
        }
    }



    private void validarRequest(DARECompletoRequestDTO request) {
        if (request == null) {
            throw new BadRequestException("Erro: O parâmetro " + "\"dareRequestDTO\" é obrigatório.");
        }

        if (request.getCodigo_municipio() == null) {
            throw new BadRequestException("Erro: O campo \"codigo_municipio\" é obrigatório.");
        }
        if (request.getCodigo_receita() == null) {
            throw new BadRequestException("Erro: O campo \"codigo_receita\" é obrigatório.");
        }
        if (request.getIdentificacao() == null) {
            throw new BadRequestException("Erro: O campo \"identificacao\" é obrigatório.");
        }
        if (request.getData_vencimento() == null) {
            throw new BadRequestException("Erro: O campo \"data_vencimento\" é obrigatório.");
        }
        if (request.getNumero_parcela() == null) {
            throw new BadRequestException("Erro: O campo \"numero_parcela\" é obrigatório.");
        }
        if (request.getValor_total() == null) {
            throw new BadRequestException("Erro: O campo \"valor_total\" é obrigatório.");
        }
        if (request.getC_o() == null) {
            throw new BadRequestException("Erro: O campo \"c_o\" é obrigatório.");
        }
    }


    private String lerBody(Response response) throws IOException {
        if (response == null || response.body() == null) {
            return "";
        }

        return new String(response.body()
                .asInputStream()
                .readAllBytes(),
                StandardCharsets.UTF_8
        );
    }



    private String extrairCookies(Response response) {
        Collection<String> setCookies =
                response.headers()
                        .entrySet()
                        .stream()
                        .filter(entry ->
                                entry.getKey()
                                        .equalsIgnoreCase("set-cookie"))
                        .flatMap(entry ->
                                entry.getValue().stream())
                        .toList();

        if (setCookies.isEmpty()) {
            return null;
        }

        return setCookies.stream()
                .map(this::extrairNomeEValorCookie)
                .filter(cookie ->
                        cookie != null && !cookie.isBlank()
                )
                .collect(Collectors.joining("; "));
    }


    private String extrairNomeEValorCookie(String setCookie) {
        if (setCookie == null || setCookie.isBlank()) {
            return null;
        }

        int index = setCookie.indexOf(';');
        if (index >= 0) {
            return setCookie.substring(0, index).trim();
        }
        return setCookie.trim();
    }



    private String buscarDocumentoHtml(String cookies) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(sefinRODocumento))
                .timeout(Duration.ofSeconds(60))
                .header("Cookie", cookies)
                .header("Accept", "*/*")
                .header("User-Agent", "curl/8.21.0")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8)
        );

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new RuntimeException(
                    "Erro ao obter documento. HTTP "
                            + response.statusCode()
                            + ". Body: "
                            + response.body()
            );
        }

        return response.body();
    }


    public byte[] htmlToPdf(String html) throws IOException {
        try {
            if (html == null || html.isBlank()) {
                throw new IOException("HTML vazio ou nulo.");
            }

            Document document = Jsoup.parse(html);

            document.outputSettings()
                    .syntax(Document.OutputSettings.Syntax.xml)
                    .escapeMode(Entities.EscapeMode.xhtml)
                    .charset(StandardCharsets.UTF_8)
                    .prettyPrint(false);

            String xhtml = document.html();

            ByteArrayOutputStream output = new ByteArrayOutputStream();

            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(xhtml, null);
            builder.toStream(output);
            builder.run();

            return output.toByteArray();

        } catch (Exception e) {
            throw new IOException("Erro ao gerar PDF", e);
        }
    }


    public byte[] base64HtmlToPdf(String htmlBase64) throws IOException {
        try {

            if (htmlBase64 == null || htmlBase64.trim().isEmpty()) {
                throw new IOException("HTML Base64 vazio ou nulo.");
            }

            // ============================================================
            // 1. Remove o prefixo caso seja um Data URI
            // ============================================================

            String base64 = htmlBase64.trim();

            if (base64.startsWith("data:")) {
                int virgula = base64.indexOf(",");

                if (virgula < 0) {
                    throw new IOException(
                            "Base64 inválido: separador ',' não encontrado."
                    );
                }

                base64 = base64.substring(virgula + 1);
            }

            // ============================================================
            // 2. Remove espaços/quebras de linha do Base64
            // ============================================================

            base64 = base64
                    .replace("\r", "")
                    .replace("\n", "")
                    .replace(" ", "")
                    .trim();

            // ============================================================
            // 3. Decodifica Base64
            // ============================================================

            byte[] htmlBytes;

            try {
                htmlBytes = Base64.getDecoder().decode(base64);
            } catch (IllegalArgumentException e) {
                throw new IOException(
                        "Conteúdo Base64 inválido.",
                        e
                );
            }

            // ============================================================
            // 4. Converte para UTF-8
            // ============================================================

            String html = new String(
                    htmlBytes,
                    StandardCharsets.UTF_8
            );

            if (html.trim().isEmpty()) {
                throw new IOException(
                        "O Base64 foi decodificado, mas o HTML está vazio."
                );
            }

            // ============================================================
            // 5. Converte HTML para XHTML
            // ============================================================

            Document document = Jsoup.parse(html);

            document.outputSettings()
                    .syntax(Document.OutputSettings.Syntax.xml)
                    .escapeMode(Entities.EscapeMode.xhtml)
                    .charset(StandardCharsets.UTF_8)
                    .prettyPrint(false);

            // ============================================================
            // 6. Adiciona CSS para PDF
            // ============================================================

            Element style = document.head().appendElement("style");

            style.appendText(
                    "@page {" +
                            "    size: A4 portrait;" +
                            "    margin: 10mm;" +
                            "}" +

                            "html, body {" +
                            "    margin: 0;" +
                            "    padding: 0;" +
                            "    width: 100%;" +
                            "}" +

                            "* {" +
                            "    box-sizing: border-box;" +
                            "}" +

                            "table {" +
                            "    width: 100%;" +
                            "    max-width: 100%;" +
                            "    border-collapse: collapse;" +
                            "}" +

                            "img {" +
                            "    max-width: 100%;" +
                            "    height: auto;" +
                            "}"
            );

            String xhtml = document.html();

            // ============================================================
            // 7. GERA O PDF
            // ============================================================

            ByteArrayOutputStream output =
                    new ByteArrayOutputStream();

            PdfRendererBuilder builder =
                    new PdfRendererBuilder();

            builder.useFastMode();

            builder.withHtmlContent(
                    xhtml,
                    null
            );

            builder.toStream(output);

            builder.run();

            // ============================================================
            // 8. Retorna o PDF
            // ============================================================

            byte[] pdf = output.toByteArray();

            if (pdf.length == 0) {
                throw new IOException(
                        "O PDF foi gerado vazio."
                );
            }

            return pdf;

        } catch (IOException e) {
            throw e;

        } catch (Exception e) {
            throw new IOException(
                    "Erro ao converter HTML Base64 para PDF.",
                    e
            );
        }
    }


}
