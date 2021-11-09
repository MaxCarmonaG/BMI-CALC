package co.edu.unab.BmiCalc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

import co.edu.unab.BmiCalc.dataStorage.StorageRecords;
import co.edu.unab.BmiCalc.model.Record;
import co.edu.unab.BmiCalc.recycler.IClickListener;
import co.edu.unab.BmiCalc.recycler.RecyclerRecords;

public class RecordsActivity extends AppCompatActivity {
    Record record;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        ArrayList<Record> records = new StorageRecords().getRecords();
        ImageButton returnBtn = (ImageButton) findViewById(R.id.returnBtn);
        RecyclerView recordList = (RecyclerView) findViewById(R.id.recordList);


        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RecyclerRecords recyclerRecords = new RecyclerRecords(
                this,
                records,
                R.layout.record,
                new IClickListener() {
                    @Override
                    public void onClick(int position) {
                        record = records.get(position);
                        moveToDetails(record);
                    }
                }
        );

        recordList.setAdapter(recyclerRecords);
        recordList.setHasFixedSize(true);
        recordList.setLayoutManager(new GridLayoutManager(this, 1));

    }

    public void moveToDetails(Record record) {
        Intent intent = new Intent(RecordsActivity.this, RecordDetailsActivity.class);
        intent.putExtra("Record", record);
        startActivity(intent);
    }
}