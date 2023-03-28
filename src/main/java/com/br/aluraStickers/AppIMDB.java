package com.br.aluraStickers;

import com.br.aluraStickers.assistant.ConsoleColors;
import com.br.aluraStickers.http.ClientHttp;
import com.br.aluraStickers.model.imdb.ModelIMDBList;
import com.br.aluraStickers.model.imdb.ModelIMDB;
import com.br.aluraStickers.stickers.StickerGenerator;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class AppIMDB {

    public static void main(String[] args) {

        // Filmes:
        String urlFilmes = getApiProperties("urlFilmes");
        ArrayList<ModelIMDB> listaFilmes = getFilmes(urlFilmes);

        System.out.println(ConsoleColors.RED_BOLD + "Filmes: " + ConsoleColors.RESET);
        for (int i = 0; i < 3; i++) {
            System.out.println(listaFilmes.get(i));
        }

        // Catch a movie from each rating category
        try {
            for (ModelIMDB f : listaFilmes) {
                if (f.imDbRating() <= 8) {
                    StickerGenerator.createStickerIMDB(f);
                    break;
                }
            }

            for (ModelIMDB f : listaFilmes) {
                if (f.imDbRating() > 8 & f.imDbRating() <= 8.5) {
                    StickerGenerator.createStickerIMDB(f);
                    break;
                }
            }

            for (ModelIMDB f : listaFilmes) {
                if (f.imDbRating() > 8.5) {
                    StickerGenerator.createStickerIMDB(f);
                    break;
                }
            }

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }


        // Tvs Shows:
        String urlTvs = getApiProperties("urlTvs");
        ArrayList<ModelIMDB> listaTvs = getTvs(urlTvs);

        System.out.println(ConsoleColors.RED_BOLD + "Tv's Shows: " + ConsoleColors.RESET);
        for (int i = 0; i < 3; i++) {
            try {
                // Generating stickers
                StickerGenerator.createStickerIMDB(listaTvs.get(i));
                // Printing Tv's shows information
                System.out.println(listaTvs.get(i));
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

    // Getting api properties by Key
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

    // Deserialization api json into a list of objects(Filme)
    private static ArrayList<ModelIMDB> getFilmes(String url) {
        var httpCliente = new ClientHttp();
        String json = httpCliente.buscaDados(url);

        Gson gson = new Gson();

        ModelIMDBList listaFilmes = gson.fromJson(json, ModelIMDBList.class);

        return (ArrayList<ModelIMDB>) listaFilmes.getItems();
    }

    // Deserialization api json into a list of objects(Tv)
    private static ArrayList<ModelIMDB> getTvs(String url) {
        var httpCliente = new ClientHttp();
        String json = httpCliente.buscaDados(url);

        Gson gson = new Gson();

        ModelIMDBList listaTvs = gson.fromJson(json, ModelIMDBList.class);

        return (ArrayList<ModelIMDB>) listaTvs.getItems();
    }
}
