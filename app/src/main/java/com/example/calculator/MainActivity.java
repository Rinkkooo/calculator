package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    boolean isOperation;
    float firstNumber, secondNumber;
    TextView resultTv;
    Button resultBtnClick;
    int lastOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.resultTv);
        resultBtnClick = findViewById(R.id.resultBtnClick);
        resultBtnClick.setVisibility(View.INVISIBLE);
    }

    public void onButtonClick(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("RESULT_TEXT", resultTv.getText().toString());
        startActivity(intent);

    }

    public void numberClick(View view) {
        if (view instanceof MaterialButton) {
            String text = ((MaterialButton) view).getText().toString();
            if (isOperation) {
                resultTv.setText("");
            }
            resultTv.append(text);
        }
        isOperation = false;
    }

    public void operationClick(View view) {
        if (view.getId() == R.id.clearBtn) {
            resultTv.setText("");
        } else if (view.getId() == R.id.plusBtn || view.getId() == R.id.minusBtn || view.getId() == R.id.multiplyBtn || view.getId() == R.id.divisionBtn) {
            resultBtnClick.setVisibility(View.INVISIBLE);
            firstNumber = Float.valueOf(resultTv.getText().toString());
            isOperation = true;
        } else if (view.getId() == R.id.equalBtn) {
            resultBtnClick.setVisibility(View.VISIBLE);
            secondNumber = Float.valueOf(resultTv.getText().toString());
            float result = calculateResult();
            String finalResult;
            if (result % 1 == 0) finalResult = String.valueOf((int) result);
            else finalResult = String.valueOf(result);
            resultTv.setText(finalResult);
        }
        lastOperations = view.getId();
        isOperation = true;
    }

    private float calculateResult() {
        float result = 0;
        if (lastOperations == R.id.plusBtn) {
            result = firstNumber + secondNumber;
        } else if (lastOperations == R.id.minusBtn) {
            result = firstNumber - secondNumber;
        } else if (lastOperations == R.id.multiplyBtn) {
            result = firstNumber * secondNumber;
        } else if (lastOperations == R.id.divisionBtn) {
            if (secondNumber != 0) {
                result = (firstNumber / secondNumber);
            } else {
                Toast.makeText(MainActivity.this, "Делить на ноль нельзя", Toast.LENGTH_SHORT).show();
                resultTv.setText("");
            }
        }
        return result;
    }

    public void plusMinusBtn(View view) {
        String currentResult = resultTv.getText().toString();
        if (!currentResult.equals("0")) {
            float currentValue = Float.valueOf(currentResult);
            currentValue *= -1;
            if (currentValue % 1 == 0) {
                resultTv.setText(String.valueOf((int) currentValue));
            } else {
                resultTv.setText(String.valueOf(currentValue));
            }
        }
    }


    public void percentBtn(View view) {
        String currentResult = resultTv.getText().toString();
        float currentValue = Float.valueOf(currentResult);
        currentValue /= 100;
        if (currentValue % 1 == 0){
            resultTv.setText(String.valueOf((int) currentValue));
        }else{
            resultTv.setText(String.valueOf(currentValue));
        }
    }


}
