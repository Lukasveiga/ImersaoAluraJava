package com.br.aluraStickers.model.nasa;

import com.br.aluraStickers.model.Model;

public record ModelNASA(String date, String title, String url, String media_type) implements Model {

    @Override
    public String image() {
        return this.url;
    }
}
