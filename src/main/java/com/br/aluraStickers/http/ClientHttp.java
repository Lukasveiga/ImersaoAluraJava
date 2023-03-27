package com.br.aluraStickers.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHttp {

    public String buscaDados(String url) {

        try {

            HttpRequest getRequest =HttpRequest.newBuilder().uri(new URI(url)).build();
            var httpClient =HttpClient.newHttpClient();
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
            return getResponse.body();

        }
        catch (IOException | InterruptedException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }

    }


}
