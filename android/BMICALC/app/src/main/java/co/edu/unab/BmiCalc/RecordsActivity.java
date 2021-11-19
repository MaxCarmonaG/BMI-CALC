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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import co.edu.unab.BmiCalc.dataStorage.Callback;
import co.edu.unab.BmiCalc.model.Record;
import co.edu.unab.BmiCalc.model.User;
import co.edu.unab.BmiCalc.recycler.IClickListener;
import co.edu.unab.BmiCalc.recycler.RecyclerRecords;
import co.edu.unab.BmiCalc.repository.RecordRepository;
import co.edu.unab.BmiCalc.repository.RecordRepositoryImpl;

public class RecordsActivity extends AppCompatActivity {
    Record record;
    RecordRepository repository;
    ArrayList<Record> records;
    User user;
    boolean showAll;
    private FirebaseAuth mAuth;
    private final String TAG = "Logs query firebase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        user = (User) getIntent().getSerializableExtra("User");
        showAll = (boolean) getIntent().getSerializableExtra("Show all");

        ImageButton returnBtn = (ImageButton) findViewById(R.id.returnBtn);

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        repository = new RecordRepositoryImpl();
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(showAll){
            repository.findAll(new Callback() {
                @Override
                public void onSuccess(Object object) {
                    setRepository(object);
                }

                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w(TAG, "Error adding document", e);
                }
            });
        } else {
            repository.findByEmail(user.getEmail(), new Callback() {
                @Override
                public void onSuccess(Object object) {
                    setRepository(object);
                }

                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w(TAG, "Error adding document", e);
                }
            });
        }
    }

    private void moveToDetails(Record record) {
        Intent intent = new Intent(RecordsActivity.this, RecordDetailsActivity.class);
        intent.putExtra("Record", record);
        startActivity(intent);
    }

    private void setRepository(Object object) {
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
}