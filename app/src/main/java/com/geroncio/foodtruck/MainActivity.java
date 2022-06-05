package com.geroncio.foodtruck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {

    private ImageView foodtruck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodtruck = findViewById(R.id.logo);

        /**o Handler post delayed serve para atrasar, por um tempo que você determina,
         * a execução de um ou mais comandos*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                /**
                 * YoYo Faz parte do pacote de animação damajia
                 * onde você declara qual vai ser a animação,ex: Tequiniques."Bounce", aplicada sobre o objeto java
                 * a duração e qual é o objeto*/

                YoYo.with(Techniques.Bounce).duration(700).playOn(MainActivity.this.foodtruck);

                chamaroutraatividade();
            }
        },500);



    }

    /**
     * Criei uma tarefa para sair dessa atividade para ir para a pagina home*/

    private void chamaroutraatividade(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        },2000);
    }
}