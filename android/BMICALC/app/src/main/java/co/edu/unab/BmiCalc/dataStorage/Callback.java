package co.edu.unab.BmiCalc.dataStorage;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.DocumentReference;

import co.edu.unab.BmiCalc.model.Record;

public interface Callback {
    public void onSuccess(Object object);
    public void onFailure(@NonNull Exception e);
}