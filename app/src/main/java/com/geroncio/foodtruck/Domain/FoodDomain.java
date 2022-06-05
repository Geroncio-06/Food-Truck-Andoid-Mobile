package com.geroncio.foodtruck.Domain;

import java.io.Serializable;

/**
 * Assim como uma ficha de trabalho tem perguntas sobre você, como nome, idade, sexo e outros,
 * onde cada pergunta, vai montar um perfil da pessoa.
 *
 * A classe Domain serve para declarar quais serão as perguntas a ser feita para montar o perfil
 * do objeto java que será anexado a sua lista*/

public class FoodDomain implements Serializable {

    private String title;
    private String pic;
    private String description;
    private Double fee;
    private int numberInCard;

    public FoodDomain(String title, String pic, String description, Double fee) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
    }

    public FoodDomain(String title, String pic, String description, Double fee, int numberInCard) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
        this.numberInCard = numberInCard;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public int getNumberInCard() {
        return numberInCard;
    }

    public void setNumberInCard(int numberInCard) {
        this.numberInCard = numberInCard;
    }
}
