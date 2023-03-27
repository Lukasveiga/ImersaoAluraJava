package com.br.aluraStickers;

import com.br.aluraStickers.assistant.ConsoleColors;
import com.br.aluraStickers.http.ClientHttp;
import com.br.aluraStickers.model.filmes.Filme;
import com.br.aluraStickers.model.filmes.ListaFilmes;
import com.br.aluraStickers.model.tvs.ListaTvs;
import com.br.aluraStickers.model.tvs.Tv;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class App {

    public static void main(String[] args) {

        // Filmes:
        String urlFilmes = getApiProperties("urlFilmes");
        ArrayList<Filme> listaFilmes = getFilmes(urlFilmes);

        System.out.println(ConsoleColors.RED_BOLD + "Filmes: " + ConsoleColors.RESET);
        for (int i = 0; i < 3; i++) {
            System.out.println(listaFilmes.get(i));
        }

        // Tvs Shows:
        String urlTvs = getApiProperties("urlTvs");
        ArrayList<Tv> listaTvs = getTvs(urlTvs);

        System.out.println(ConsoleColors.RED_BOLD + "Tv's Shows: " + ConsoleColors.RESET);
        for (int i = 0; i < 3; i++) {
            System.out.println(listaTvs.get(i));
        }


    }

    private static String getApiProperties(String key) {

        String value;

        try (InputStream input = new FileInputStream("src/main/resources/aplication-properties.properties")) {

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

    private static ArrayList<Filme> getFilmes(String url){
        var httpCliente = new ClientHttp();
        String json = httpCliente.buscaDados(url);

        Gson gson = new Gson();

        ListaFilmes listaFilmes = gson.fromJson(json, ListaFilmes.class);

        return (ArrayList<Filme>) listaFilmes.getItems();
    }

    private static ArrayList<Tv> getTvs(String url){
        var httpCliente = new ClientHttp();
        String json = httpCliente.buscaDados(url);

        Gson gson = new Gson();

        ListaTvs listaFilmes = gson.fromJson(json, ListaTvs.class);

        return (ArrayList<Tv>) listaFilmes.getItems();
    }
}
