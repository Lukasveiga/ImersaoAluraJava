package com.br.aluraStickers.model;

import com.br.aluraStickers.http.ClientHttp;
import com.br.aluraStickers.model.imdb.ModelIMDB;
import com.br.aluraStickers.model.imdb.ModelIMDBList;
import com.br.aluraStickers.model.nasa.ModelNASA;
import com.google.gson.Gson;

import java.util.ArrayList;

public interface Model {

    // Deserialization api json into a list of objects
    static ArrayList<ModelIMDB> getImagesIMBD(String url) {

        var httpCliente = new ClientHttp();
        String json = httpCliente.buscaDados(url);

        Gson gson = new Gson();

        ModelIMDBList list = gson.fromJson(json, ModelIMDBList.class);

        return (ArrayList<ModelIMDB>) list.getItems();
    }

    static ModelNASA[] getImagesNASA(String url) {
        var httpCliente = new ClientHttp();
        String json = httpCliente.buscaDados(url);

        Gson gson = new Gson();

        return gson.fromJson(json, ModelNASA[].class);
    }



    String image();

    String title();
}
