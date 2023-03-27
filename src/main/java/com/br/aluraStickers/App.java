package com.br.aluraStickers;

import com.br.aluraStickers.http.ClientHttp;
import com.br.aluraStickers.model.filmes.Filme;
import com.br.aluraStickers.model.filmes.ListaFilmes;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class App {

    public static void main(String[] args) {

        String url = getApiProperties("url");
        String apiKey = getApiProperties("apiKey");

        var httpCliente = new ClientHttp();
        String json = httpCliente.buscaDados(url);

        Gson gson = new Gson();

        ListaFilmes listaFilmes = gson.fromJson(json, ListaFilmes.class);

        ArrayList<Filme> lista = (ArrayList<Filme>) listaFilmes.getItems();

        for (int i = 0; i <= 4; i++) {
            System.out.println(lista.get(i));
        }

    }

    private static String getApiProperties(String key) {

        String value;

        try (InputStream input = new FileInputStream("src/main/resources/api-properties.properties")) {

            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            value = prop.getProperty(key);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        return value;

    }
}
