package com.dinhthehoang.ecommerceapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinhthehoang.ecommerceapp.Adapter.PopularListAdapter;
import com.dinhthehoang.ecommerceapp.Domain.PopularDomain;
import com.dinhthehoang.ecommerceapp.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterPopular;
    private RecyclerView recyclerViewPopular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initRecylerView();
        bottom_navigation();

    }
    private void bottom_navigation(){
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.CartBtn);

        homeBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MainActivity.class)));
        cartBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CartActivity.class)));

    }
    private void initRecylerView(){
        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("Asus Vivo Book 15","Whether at work or play, ASUS VivoBook 15 is the compact laptop that immerses you in whatever you set out to do.\n " +
                "Its new frameless four-sided NanoEdge display boasts an ultraslim 5.7mm bezel, giving an amazing 88% screen-to-body ratio for supremely immersive visuals." +
                "\n The new ErgoLift hinge design also tilts the keyboard up for more comfortable typing.\n VivoBook 15 is powered by up to the latest Intel® Core™ i7 processor with discrete \n" +
                "NVIDIA® graphics and dual storage drives to help you get things done with the least amount of fuss." +
                "\n What's more, it's available in four unique finishes to suit your style.", "pic1", 15, 4, 1,1100));
        items.add(new PopularDomain("PS-5 Digital Edition","None", "pic2", 10, 4, 2,500));
        items.add(new PopularDomain("Iphone 14","None", "pic3", 10, 5, 3,1000));

        recyclerViewPopular=findViewById(R.id.view1);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterPopular = new PopularListAdapter(items);
        recyclerViewPopular.setAdapter(adapterPopular);
    }
}