package co.edu.unab.BmiCalc.repository;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import co.edu.unab.BmiCalc.dataStorage.Callback;
import co.edu.unab.BmiCalc.model.Record;

public class RecordRepositoryImpl implements RecordRepository {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    final String COLLECTION = "records";

    @Override
    public void findAll(Callback callback) {
        db.collection(COLLECTION).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    ArrayList<Record> records = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()){
                        Record record = document.toObject(Record.class);
                        records.add(record);
                    }
                    callback.onSuccess(records);
                } else {
                    callback.onFailure(null);
                }
            }
        });
    }

    @Override
    public void create(Record record, Callback callback) {
        db.collection(COLLECTION).add(record.getMap()).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                callback.onSuccess(documentReference);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onFailure(e);
            }
        });
    }

    @Override
    public void update(Record record, Callback callback) {
        db.collection(COLLECTION).document(record.getId()).update(record.getMap())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        callback.onSuccess(record);
                    }
                });
    }

    @Override
    public void delete(Record record, Callback callback) {
        db.collection(COLLECTION).document(record.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                callback.onSuccess(null);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onFailure(e);
            }
        });
    }
}
