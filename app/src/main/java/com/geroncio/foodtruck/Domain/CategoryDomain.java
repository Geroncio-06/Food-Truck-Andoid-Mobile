package com.geroncio.foodtruck.Domain;

/**
 * Assim como uma ficha de trabalho tem perguntas sobre você, como nome, idade, sexo e outros,
 * onde cada pergunta, vai montar um perfil da pessoa.
 *
 * A classe Domain serve para declarar quais serão as perguntas a ser feita para montar o perfil
 * do objeto java que será anexado a sua lista*/

public class CategoryDomain {
    private String title;
    private String pic;



    public CategoryDomain(String title, String pic) {
        this.title = title;
        this.pic = pic;
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
}
