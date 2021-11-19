package co.edu.unab.BmiCalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import co.edu.unab.BmiCalc.dataStorage.Callback;
import co.edu.unab.BmiCalc.model.User;
import co.edu.unab.BmiCalc.repository.UserRepository;
import co.edu.unab.BmiCalc.repository.UserRepositoryImpl;

public class MenuActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button goToHistoryBtn;
    private User user;
    private final String TAG = "Logs query firebase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mAuth = FirebaseAuth.getInstance();

        Button calculatorBtn = (Button) findViewById(R.id.calculatorBtn);
        goToHistoryBtn = (Button) findViewById(R.id.historiesBtn);
        Button logoutBtn = (Button) findViewById(R.id.logoutBtn);

        calculatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, CalculatorActivity.class);
                intent.putExtra("User", user);
                startActivity(intent);
            }
        });

        goToHistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, RecordsActivity.class);
                intent.putExtra("User", user);
                intent.putExtra("Show all", user.isAdmin());
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        UserRepository repository = new UserRepositoryImpl();
        repository.findByEmail(currentUser.getEmail(), new Callback() {
            @Override
            public void onSuccess(Object object) {
                user = (User) object;
                goToHistoryBtn.setEnabled(user.isAdmin());
            }

            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error adding document", e);
            }
        });
    }
}