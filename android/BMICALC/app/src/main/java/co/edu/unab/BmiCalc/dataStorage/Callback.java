package co.edu.unab.BmiCalc.dataStorage;

import androidx.annotation.NonNull;

public interface Callback {
    public void onSuccess(Object object);
    public void onFailure(@NonNull Exception e);
}