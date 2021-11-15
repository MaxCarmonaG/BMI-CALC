package co.edu.unab.BmiCalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

import co.edu.unab.BmiCalc.dataStorage.Callback;
import co.edu.unab.BmiCalc.model.Record;
import co.edu.unab.BmiCalc.recycler.IClickListener;
import co.edu.unab.BmiCalc.recycler.RecyclerRecords;
import co.edu.unab.BmiCalc.repository.RecordRepository;
import co.edu.unab.BmiCalc.repository.RecordRepositoryImpl;

public class RecordsActivity extends AppCompatActivity {
    Record record;
    RecordRepository repository;
    ArrayList<Record> records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        ImageButton returnBtn = (ImageButton) findViewById(R.id.returnBtn);


        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        repository = new RecordRepositoryImpl();
        repository.findAll(new Callback() {
            @Override
            public void onSuccess(Object object) {
                records = ((ArrayList<Record>) object);
                RecyclerRecords recyclerRecords = new RecyclerRecords(
                        RecordsActivity.this,
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
                RecyclerView recordList = (RecyclerView) findViewById(R.id.recordList);
                recordList.setAdapter(recyclerRecords);
                recordList.setHasFixedSize(true);
                recordList.setLayoutManager(new GridLayoutManager(RecordsActivity.this, 1));
                Log.d("msj", "Records loaded");
            }

            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }

    public void moveToDetails(Record record) {
        Intent intent = new Intent(RecordsActivity.this, RecordDetailsActivity.class);
        intent.putExtra("Record", record);
        startActivity(intent);
    }
}