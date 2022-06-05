package com.geroncio.foodtruck;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.geroncio.foodtruck.Domain.FoodDomain;
import com.geroncio.foodtruck.Helper.ManagementCard;

public class ShowDetailsActivity extends AppCompatActivity {

    private TextView addToCardBtn;
    private TextView titleText, feeTxt, descriptionTxt, numberOrderTxt;
    private ImageView plusBrn, minusBtn, picFood;
    private FoodDomain object;
    private int numberOrder = 1;
    private ManagementCard managementCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        managementCard = new ManagementCard(this);

        initView();
        getBundle();
    }

    private void getBundle() {
        object = (FoodDomain) getIntent().getSerializableExtra("object");

        int drawableResourcesId = getApplicationContext()
                .getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());

        Glide.with(this)
                .load(drawableResourcesId)
                .into(picFood);

        titleText.setText(object.getTitle());
        feeTxt.setText("R$ "+object.getFee());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));

        plusBrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder+1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder-1;
                if (numberOrder > 1){
                    numberOrderTxt.setText(String.valueOf(numberOrder));
                }else {
                    numberOrder = 1;
                    numberOrderTxt.setText(String.valueOf(numberOrder));
                }

            }
        });

        addToCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCard(numberOrder);
                managementCard.insertFood(object);
            }
        });
    }

    private void initView() {

        addToCardBtn = findViewById(R.id.addToCardBtn);
        titleText = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.priceTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberOrderTxt);
        plusBrn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        picFood = findViewById(R.id.foodPic);

    }
}