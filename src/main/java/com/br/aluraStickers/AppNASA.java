package com.br.aluraStickers;

import com.br.aluraStickers.assistant.ApiProperties;
import com.br.aluraStickers.model.Model;
import com.br.aluraStickers.model.nasa.ModelNASA;
import com.br.aluraStickers.stickers.StickerGenerator;

import java.io.IOException;

public class AppNASA {

    public static void main(String[] args) {

        String urlNASA = ApiProperties.getApiProperties("urlNASA");
        ModelNASA[] list = Model.getImagesNASA(urlNASA);

        try {
            for (ModelNASA nasa : list) {
                if (nasa.media_type().equalsIgnoreCase("image")) {
                    StickerGenerator.createStickerNASA(nasa);
                }

            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
