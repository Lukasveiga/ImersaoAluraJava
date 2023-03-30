package com.br.aluraStickers;

import com.br.aluraStickers.assistant.ApiProperties;
import com.br.aluraStickers.model.Model;
import com.br.aluraStickers.model.lang.ModelLang;
import com.br.aluraStickers.stickers.StickerGenerator;

import java.io.IOException;

public class AppLang {

    public static void main(String[] args) {

        String urlLang = ApiProperties.getApiProperties("urlLang");
        ModelLang[] list = Model.getImagesLang(urlLang);

        try {
            for (int i = 0; i < 3; i++) {
                StickerGenerator.createStickerLang(list[i]);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
