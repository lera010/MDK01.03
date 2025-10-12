package com.example.pz6;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewLeft, textViewRight, textViewRes, textViewOperation, textViewRavno;
    private Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonPlus, buttonMin, buttonPr,
            buttonDel, buttonRes;

    private String currentNumber = "";
    private String leftNumber = "";
    private String rightNumber = "";
    private String currentOperation = "";
    private boolean isNewOperation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // убедитесь, что имя файла разметки правильное

        initializeViews();
        setupNumberButtons();
        setupOperationButtons();
        setupClearButton();
        setupEqualsButton();
    }

    private void initializeViews() {
        // Инициализация TextViews
        textViewLeft = findViewById(R.id.textViewLeft);
        textViewRight = findViewById(R.id.textViewRight);
        textViewRes = findViewById(R.id.textViewRes);
        textViewOperation = findViewById(R.id.textViewOperation);
        textViewRavno = findViewById(R.id.textViewRavno);

        // Инициализация кнопок с цифрами
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        // Инициализация кнопок операций
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMin = findViewById(R.id.buttonMin);
        buttonPr = findViewById(R.id.buttonPr);
        buttonDel = findViewById(R.id.buttonDel);
        buttonRes = findViewById(R.id.buttonRes);
    }

    private void setupNumberButtons() {
        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String number = button.getText().toString();

                if (isNewOperation) {
                    currentNumber = number;
                    isNewOperation = false;
                } else {
                    currentNumber += number;
                }

                if (currentOperation.isEmpty()) {
                    leftNumber = currentNumber;
                    updateDisplay();
                } else {
                    rightNumber = currentNumber;
                    updateDisplay();
                }
            }
        };

        // Назначение слушателей для всех цифровых кнопок
        button0.setOnClickListener(numberListener);
        button1.setOnClickListener(numberListener);
        button2.setOnClickListener(numberListener);
        button3.setOnClickListener(numberListener);
        button4.setOnClickListener(numberListener);
        button5.setOnClickListener(numberListener);
        button6.setOnClickListener(numberListener);
        button7.setOnClickListener(numberListener);
        button8.setOnClickListener(numberListener);
        button9.setOnClickListener(numberListener);
    }

    private void setupOperationButtons() {
        View.OnClickListener operationListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String operation = button.getText().toString();

                if (!leftNumber.isEmpty() && !currentOperation.isEmpty() && !rightNumber.isEmpty()) {
                    // Если уже есть операция и оба числа, вычисляем результат
                    calculateResult();
                }

                currentOperation = operation;
                isNewOperation = true;
                updateDisplay();
            }
        };

        buttonPlus.setOnClickListener(operationListener);
        buttonMin.setOnClickListener(operationListener);
        buttonPr.setOnClickListener(operationListener);
    }

    private void setupClearButton() {
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCalculator();
            }
        });
    }

    private void setupEqualsButton() {
        buttonRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!leftNumber.isEmpty() && !currentOperation.isEmpty() && !rightNumber.isEmpty()) {
                    calculateResult();
                }
            }
        });
    }

    private void calculateResult() {
        try {
            double left = Double.parseDouble(leftNumber);
            double right = Double.parseDouble(rightNumber);
            double result = 0;

            switch (currentOperation) {
                case "+":
                    result = left + right;
                    break;
                case "-":
                    result = left - right;
                    break;
                case "*":
                    result = left * right;
                    break;
            }

            // Отображаем результат
            textViewRes.setText(String.valueOf(result));

            // Сбрасываем для следующей операции
            leftNumber = String.valueOf(result);
            rightNumber = "";
            currentOperation = "";
            currentNumber = leftNumber;
            isNewOperation = true;

        } catch (NumberFormatException e) {
            textViewRes.setText("Error");
            clearCalculator();
        }
    }

    private void updateDisplay() {
        textViewLeft.setText(leftNumber);
        textViewOperation.setText(currentOperation);
        textViewRight.setText(rightNumber);

        // Очищаем результат при новом вводе
        if (!isNewOperation) {
            textViewRes.setText("");
        }
    }

    private void clearCalculator() {
        currentNumber = "";
        leftNumber = "";
        rightNumber = "";
        currentOperation = "";
        isNewOperation = true;

        textViewLeft.setText("");
        textViewRight.setText("");
        textViewRes.setText("");
        textViewOperation.setText("");
    }
}