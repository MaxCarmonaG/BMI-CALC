package co.edu.unab.BmiCalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import co.edu.unab.BmiCalc.model.Record;

public class RecordDetailsActivity extends AppCompatActivity {

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

        Record record = (Record) getIntent().getSerializableExtra("Record");

        txtDate.setText(record.getDate());
        txtWeight.setText(Integer.toString(record.getWeight()));
        txtHeight.setText(Integer.toString(record.getHeight()));
        txtBmi.setText(String.format("%, .2f", record.getBmi()));
        txtRec.setText(record.getRecommendation());

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}