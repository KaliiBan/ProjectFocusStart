package com.example.mystudyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ConvertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);
        Intent intent = getIntent();
       intent.getStringExtra("IntentCharCode");
        intent.getStringExtra("IntentName");
        intent.getStringExtra("IntentValue");
    }
}