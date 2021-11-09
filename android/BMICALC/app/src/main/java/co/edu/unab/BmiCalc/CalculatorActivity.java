package co.edu.unab.BmiCalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        EditText weightInput = (EditText) findViewById(R.id.weightInput);
        EditText heightInput = (EditText) findViewById(R.id.heightInput);
        TextView bmiResult = (TextView) findViewById(R.id.bmiResult);
        Button calcBtn = (Button) findViewById(R.id.calcBtn);
        Button clearBtn = (Button) findViewById(R.id.clearBtn);
        ImageButton returnBtn = (ImageButton) findViewById(R.id.returnBtn);

        weightInput.requestFocus();

        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float weight = Float.parseFloat(weightInput.getText().toString());
                float height = Float.parseFloat(heightInput.getText().toString()) / 100;

                float bmi = weight / (height * height);

                bmiResult.setText(String.format("%, .2f", bmi));
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightInput.setText("");
                heightInput.setText("");
                bmiResult.setText("");
                weightInput.requestFocus();
            }
        });

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button recordsBtn = (Button) findViewById(R.id.recordsBtn);

        recordsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculatorActivity.this, RecordsActivity.class);
                startActivity(intent);
            }
        });

    }
}