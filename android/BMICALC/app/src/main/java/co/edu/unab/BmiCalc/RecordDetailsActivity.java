package co.edu.unab.BmiCalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import co.edu.unab.BmiCalc.dataStorage.Callback;
import co.edu.unab.BmiCalc.model.Record;
import co.edu.unab.BmiCalc.repository.RecordRepository;
import co.edu.unab.BmiCalc.repository.RecordRepositoryImpl;

public class RecordDetailsActivity extends AppCompatActivity {
    RecordRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_details);

        ImageButton returnBtn = (ImageButton) findViewById(R.id.returnBtn);
        TextView txtDate = (TextView) findViewById(R.id.recordDate);
        TextView txtWeight = (TextView) findViewById(R.id.recordWeight);
        TextView txtHeight = (TextView) findViewById(R.id.recordHeight);
        TextView txtBmi = (TextView) findViewById(R.id.recordBmi);
        TextView txtRec = (TextView) findViewById(R.id.recordRec);
        Button deleteBtn = (Button) findViewById(R.id.deleteBtn);

        Record record = (Record) getIntent().getSerializableExtra("Record");

        txtDate.setText(record.getDate());
        txtWeight.setText(Float.toString(record.getWeight()));
        txtHeight.setText(Float.toString(record.getHeight()));
        txtBmi.setText(record.getBmi());
        txtRec.setText(record.getRecommendation());

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repository = new RecordRepositoryImpl();
                repository.delete(record, new Callback() {
                    @Override
                    public void onSuccess(Object object) {
                        Log.d("msj", "Record deleted");
                    }

                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("msj", "Record not deleted");
                    }
                });
                finish();
            }
        });


    }
}