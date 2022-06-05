package com.geroncio.foodtruck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.geroncio.foodtruck.Adapter.CategoryAdapter;
import com.geroncio.foodtruck.Adapter.PopularAdapter;
import com.geroncio.foodtruck.Domain.CategoryDomain;
import com.geroncio.foodtruck.Domain.FoodDomain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerViewCategory();

        recyclerViewPopular();

        bottomNavigation();
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.cardBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, CardListActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, HomeActivity.class));
            }
        });
    }

    /**
     * Tarefa criada para adaptar e construir a minha  lista de itens populares
     * na minha loja imaginaria*/
    private void recyclerViewPopular() {

        /**
         * LinearLayoutManager permite você especificar uma orientação em sua RecyclerView,
         * da mesma maneira que LinearLayout*/

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);

        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        /**
         * Criando uma arraylista, que vai receber os atributos listados em FoodDomain*/
        ArrayList<FoodDomain> foodlist = new ArrayList<>();

        /**
         * Adicionando objetos java a arraylist que criamos, observando os atributos requeridos segundo os
         * parametros de FoodDomain*/
        foodlist.add(new FoodDomain("Pepperoni pizza","pizza1",
                "Tomate, Queijo, Presunto, Azeitona, Champígnon, Pimentão, Oregano", 9.76));
        foodlist.add(new FoodDomain("Cheese Burger","burger","" +
                "Pão de hamburguer, Carne, Queijo, Cebola, Alface, Tomate, picles com ketchup e mostarda.", 8.79));
        foodlist.add(new FoodDomain("Vegetable pizza","pizza2",
                "Mussarela vegetal, Rodelas de cebolas e de tomate, Abrobinha, Azeitona e Oregano", 8.50));

        /**
         * É o adapter que vai pegar os objetos da nossa lista e apresentar eles da maneira customizado
         * que deixamos pré programado no layout xml*/
        adapter2 = new PopularAdapter(foodlist);
        recyclerViewPopularList.setAdapter(adapter2);
    }

    /**
     * Tarefa criada para adaptar e construir a minha  lista de itens categoria
     * na minha loja imaginaria*/
    private void recyclerViewCategory() {

        /**
         * LinearLayoutManager permite você especificar uma orientação em sua RecyclerView,
         * da mesma maneira que LinearLayout*/

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);

        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        /**
         * Criando uma arraylista, que vai receber os atributos listados em CategoryDomain*/
        ArrayList<CategoryDomain> categoryList = new ArrayList<>();

        /**
         * Adicionando objetos java a arraylist que criamos, observando os atributos requeridos segundo os
         * parametros de CategoryDomain*/
        categoryList.add(new CategoryDomain("Pizza", "cat_1"));
        categoryList.add(new CategoryDomain("Burguer", "cat_2"));
        categoryList.add(new CategoryDomain("Hotdog", "cat_3"));
        categoryList.add(new CategoryDomain("Drink", "cat_4"));
        categoryList.add(new CategoryDomain("Donut", "cat_5"));

        /**
         * É o adapter que vai pegar os objetos da nossa lista e apresentar eles da maneira customizado
         * que deixamos pré programado no layout xml*/
        adapter = new CategoryAdapter(categoryList);
        recyclerViewCategoryList.setAdapter(adapter);
    }
}