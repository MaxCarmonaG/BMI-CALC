package co.edu.unab.BmiCalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import co.edu.unab.BmiCalc.dataStorage.StorageRecords;
import co.edu.unab.BmiCalc.model.Record;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "Logs query firebase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginBtn = (Button) findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
                startActivity(intent);
            }
        });

//        initialSubmitFB();
    }

    private void initialSubmitFB() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        StorageRecords records = new StorageRecords();
        for (Record record : records.getRecords()) {
            Map<String, Object> recordMap = new HashMap<>();
            recordMap.put("date", record.getDate());
            recordMap.put("weight", record.getWeight());
            recordMap.put("height", record.getHeight());
            recordMap.put("bmi", record.getBmi());
            recordMap.put("recommendation", record.getRecommendation());

            db.collection("records")
                    .add(recordMap)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });
        }
    }
}