package com.example.homeworkbylesson1calculator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.IntArrayEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {

    TextView resultField;
    EditText numberField;
    TextView operationField;
    Double operand = null;
    String lastOperation = "=";
    Button reveerse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultField =(TextView) findViewById(R.id.resultField);
        numberField = (EditText) findViewById(R.id.numberField);
        operationField = (TextView) findViewById(R.id.operationField);
        reveerse = (Button) findViewById(R.id.reveerse);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("OPERATION", lastOperation);
        if(operand!=null)
            outState.putDouble("OPERAND", operand);
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastOperation = savedInstanceState.getString("OPERATION");
        operand= savedInstanceState.getDouble("OPERAND");
        resultField.setText(operand.toString());
        operationField.setText(lastOperation);
    }

    public void operationReverse (View view) {
    switch (view.getId()){
        case R.id.reveerse:
            Intent intent = new Intent(this, StartPage.class);
            startActivity(intent);

            break;
    }
    }
    public void onNumberClick(View view){

        Button button = (Button)view;
        numberField.append(button.getText());

        if (lastOperation.equals("=")){
                switch (view.getId()){
                    case R.id.rev:
                        Intent intent = new Intent(this, StartPage.class);
                        startActivity(intent);
                }
        }
        if(lastOperation.equals("=") && operand!=null){
            operand = null;

        }
    }
    public void onOperationClick(View view){

        Button button = (Button)view;
        String op = button.getText().toString();
        String number = numberField.getText().toString();
        if(number.length()>0){
            number = number.replace(',', '.');
            try{

                performOperation(Double.valueOf(number), op);
            }catch (NumberFormatException ex){
                numberField.setText("");
            }
        }

        lastOperation = op;
        operationField.setText(lastOperation);
        if (op.equals("=")) {

            Intent intent = new Intent(this, StartPage.class);
            intent.putExtra("Ava",operand.toString() );
            startActivity(intent);
        }
    }

    private void performOperation(Double number, String operation){
        if(operand ==null){
            operand = number;
        }
        else{
            if(lastOperation.equals("=")){
                lastOperation = operation;

            }
            switch(lastOperation){
                case "=":

                    operand =number;


                break;
                case "/": if(number==0){
                                operand =0.0;
                    }
                    else{
                        operand /=number;
                    }
                    break;
                case "*": operand *=number; break;
                case "+": operand +=number; break;
                case "-": operand -=number; break;
            }
        }
        resultField.setText(operand.toString().replace('.', ','));
        numberField.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }
}
