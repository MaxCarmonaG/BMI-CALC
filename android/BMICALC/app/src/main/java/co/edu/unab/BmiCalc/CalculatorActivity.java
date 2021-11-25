package co.edu.unab.BmiCalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import co.edu.unab.BmiCalc.dataStorage.Callback;
import co.edu.unab.BmiCalc.model.Record;
import co.edu.unab.BmiCalc.model.User;
import co.edu.unab.BmiCalc.network.Bmi;
import co.edu.unab.BmiCalc.network.BmiApiClient;
import co.edu.unab.BmiCalc.repository.GetBmiCategory;
import co.edu.unab.BmiCalc.repository.RecordRepository;
import co.edu.unab.BmiCalc.repository.RecordRepositoryImpl;
import retrofit2.Call;
import retrofit2.Response;

public class CalculatorActivity extends AppCompatActivity {
    RecordRepository repository;
    BmiUtils bmiUtils = new BmiUtils();
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        repository = new RecordRepositoryImpl();
        GetBmiCategory apiBmi = BmiApiClient.getClient().create(GetBmiCategory.class);

        user = (User) getIntent().getSerializableExtra("User");

        EditText weightInput = (EditText) findViewById(R.id.weightInput);
        EditText heightInput = (EditText) findViewById(R.id.heightInput);
        TextView bmiResult = (TextView) findViewById(R.id.bmiResult);
        TextView recText = (TextView) findViewById(R.id.bmiRec);
        Button calcBtn = (Button) findViewById(R.id.calcBtn);
        Button clearBtn = (Button) findViewById(R.id.clearBtn);
        ImageButton returnBtn = (ImageButton) findViewById(R.id.returnBtn);

        weightInput.requestFocus();

        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weightText = weightInput.getText().toString();
                String heightText = heightInput.getText().toString();

                if(weightText.matches("") || heightText.matches("")) {
                    return;
                }

                try {
                    float weight = Float.parseFloat(weightText);
                    float height = Float.parseFloat(heightText) / 100;
                    String bmi = bmiUtils.calcBmi(weight, height);

                    Record record = new Record(user.getEmail(), bmiUtils.dateGenerator(), weight, height, bmi);

                    Call<Bmi> callBmi = apiBmi.getBmiCat(bmi);

                    callBmi.enqueue(new retrofit2.Callback<Bmi>() {
                        @Override
                        public void onResponse(Call<Bmi> call, Response<Bmi> response) {
                            record.setRecommendation(response.body().getWeightCategory());
                            bmiResult.setText(bmi);
                            recText.setText(record.getRecommendation());

                            repository.create(record, new Callback() {
                                @Override
                                public void onSuccess(Object object) {
                                    Log.d("msj", "Record created");
                                }

                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("msj", "Record not created");
                                }
                            });
                            Log.d("Retrofit query", "BMI Category = " + record.getRecommendation() );
                        }

                        @Override
                        public void onFailure(Call<Bmi> call, Throwable t) {
                            Log.d("Retrofit query", "Retrofit Error");
                        }
                    });



                } catch (Exception e) {
                    Log.d("msj", e.getMessage());
                }

            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightInput.setText("");
                heightInput.setText("");
                bmiResult.setText("");
                recText.setText("");
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
                intent.putExtra("User", user);
                intent.putExtra("Show all", false);
                startActivity(intent);
            }
        });
    }
}