package com.example.calculator;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class SecondActivity extends AppCompatActivity {

    TextView resultTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        resultTxt = findViewById(R.id.resultTxt);

        Intent intent = getIntent();
        String resultText = intent.getStringExtra("RESULT_TEXT");
        resultTxt.setText(resultText);
    }

    public void changeHearth(View view) {
        if (view.getId() == R.id.hearthImageBtn) {
            ImageButton hearthButton = (ImageButton) view;
            ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.pink));
            hearthButton.setImageTintList(colorStateList);
        }
    }

    public void onDestroy(View view) {
        finishAffinity();
        Log.e(TAG, "onDestroy");
    }


}

