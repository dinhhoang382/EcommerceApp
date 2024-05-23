package com.dinhthehoang.ecommerceapp.Activity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.dinhthehoang.ecommerceapp.Domain.PopularDomain;
import com.dinhthehoang.ecommerceapp.Helper.ManagmentCart;
import com.dinhthehoang.ecommerceapp.R;

public class DetailActivity extends AppCompatActivity {
    private Button addtoCartBtn;
    private TextView titleTxt, feetxt, descriptionTxt, reviewTxt, scoretxt;
    private ImageView picFood, backBtn;
    private PopularDomain object;
    private int numberOrder = 1;
    private ManagmentCart managmentCart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        getBundle();
    }
    private void getBundle()
    {
        object = (PopularDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId = this.getResources().getIdentifier(object.getPicUrl(), "drawable", getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);
        titleTxt.setText(object.getTitle());
        feetxt.setText("$"+object.getPrice());
        descriptionTxt.setText(object.getDescription());
        reviewTxt.setText(object.getReview()+"");
        scoretxt.setText(object.getScore()+"");

        addtoCartBtn.setOnClickListener(v->{
            object.setNumberinCart(numberOrder);
            managmentCart.insertFood(object);
        });
        backBtn.setOnClickListener(v -> finish());
    }
    private void initView()
    {
        addtoCartBtn = findViewById(R.id.addToCartBtn);
        feetxt = findViewById(R.id.price);
        titleTxt = findViewById(R.id.titledTxt);
        descriptionTxt = findViewById(R.id.desciptionTxt);
        reviewTxt = findViewById(R.id.reviewTxt);
        scoretxt = findViewById(R.id.scoredTxt);
        picFood = findViewById(R.id.foodPic);
        backBtn = findViewById(R.id.backBtn);
        managmentCart = new ManagmentCart(this);
    }
}