package co.edu.unab.BmiCalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import co.edu.unab.BmiCalc.dataStorage.Callback;
import co.edu.unab.BmiCalc.model.User;
import co.edu.unab.BmiCalc.repository.UserRepository;
import co.edu.unab.BmiCalc.repository.UserRepositoryImpl;

public class FormActivity extends AppCompatActivity {
    private final String TAG = "Logs query firebase";
    private FirebaseAuth mAuth;
    UserRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        mAuth = FirebaseAuth.getInstance();

        EditText firstNameInput = (EditText) findViewById(R.id.userFirstName);
        EditText lastNameInput = (EditText) findViewById(R.id.userLastName);
        EditText phoneInput = (EditText) findViewById(R.id.userPhone);
        EditText emailInput = (EditText) findViewById(R.id.userEmail);
        EditText passwordInput = (EditText) findViewById(R.id.userPassword);
        CheckBox isAdminInput = (CheckBox) findViewById(R.id.checkBox);
        Button submitButton = (Button) findViewById(R.id.submitBtn);
        Button cancelButton = (Button) findViewById(R.id.cancelBtn);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameInput.getText().toString();
                String lastName = lastNameInput.getText().toString();
                String phone = phoneInput.getText().toString();
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                Boolean isAdmin = isAdminInput.isChecked();

                User user = new User(firstName, lastName, phone, email, password, isAdmin);

                createAuthUser(user);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void createAuthUser(User user){
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            createFirebaseUser(user);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(FormActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
                    }
                });
    }

    private void createFirebaseUser(User user) {
        repository = new UserRepositoryImpl();
        repository.create(user, new Callback() {
            @Override
            public void onSuccess(Object object) {
                Log.d("msj", "User created");
            }

            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("msj", "User not created");
                Toast.makeText(FormActivity.this, e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}