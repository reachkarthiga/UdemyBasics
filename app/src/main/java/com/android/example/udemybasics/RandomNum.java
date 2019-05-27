package com.android.example.udemybasics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Random;




public class Random extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random_number);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Random rand = new Random();
        int num = rand.nextInt(50) + 1;

    }
}
