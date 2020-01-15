package com.example.homeworkbylesson1calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StartPage extends AppCompatActivity implements View.OnClickListener {
    EditText editText;
    Button clickBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        editText = findViewById(R.id.window);

        clickBtn = (Button) findViewById(R.id.clickBtn);
        clickBtn.setOnClickListener(this);


        final Intent myintent = getIntent();
        String text = myintent.getStringExtra("Ava");
        if (text != null) {
            editText.setText(text);
        }else
        {
            editText.setText("Avadakedavra");
        }

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.clickBtn:
                //Go To Calculator

                Intent intent = new Intent(this, MainActivity.class);
                 startActivity(intent);
                break;
                default:
                    break;
        }

    }
}
