package com.br.aluraStickers;

import com.br.aluraStickers.assistant.ApiProperties;
import com.br.aluraStickers.assistant.ConsoleColors;
import com.br.aluraStickers.model.Model;
import com.br.aluraStickers.model.imdb.ModelIMDB;
import com.br.aluraStickers.stickers.StickerGenerator;

import java.io.IOException;
import java.util.ArrayList;

public class AppIMDB {

    public static void main(String[] args) {

        // Filmes:
        String urlFilmes = ApiProperties.getApiProperties("urlFilmes");
        ArrayList<ModelIMDB> listaFilmes = Model.getImagesIMBD(urlFilmes);

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
        String urlTvs = ApiProperties.getApiProperties("urlTvs");
        ArrayList<ModelIMDB> listaTvs = Model.getImagesIMBD(urlTvs);

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
}
