package br.com.projeto.matchsimulator.domain;

import com.google.gson.annotations.SerializedName;

public class Place {

    @SerializedName("nome")
    private  String name;

    @SerializedName("imagem")
    private String image;

    public Place(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
