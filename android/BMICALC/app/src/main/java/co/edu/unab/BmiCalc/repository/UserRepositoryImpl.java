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
import co.edu.unab.BmiCalc.model.User;

public class UserRepositoryImpl implements UserRepository{
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    final String COLLECTION = "users";

    @Override
    public void create(User user, Callback callback) {
        db.collection(COLLECTION).add(user.getMap()).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                callback.onSuccess((documentReference));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onFailure(e);
            }
        });
    }

    @Override
    public void findByEmail(String email, Callback callback) {
        db.collection(COLLECTION).whereEqualTo("email", email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    ArrayList<User> users = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()){
                        User user = document.toObject(User.class);
                        user.setId(document.getId());
                        users.add(user);
                    }
                    callback.onSuccess(users.get(0));
                } else {
                    callback.onFailure(null);
                }
            }
        });
    }
}
