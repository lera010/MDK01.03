package com.example.pz19;

import static android.app.ProgressDialog.show;
import static com.example.pz19.Transform.StringNoNull;
import static com.example.pz19.Transform.Vibrate;
import static com.example.pz19.UserStaticInfo.PASSWORD;
import static com.example.pz19.UserStaticInfo.POSITION;
import static com.example.pz19.UserStaticInfo.PROFILE_ID;
import static com.example.pz19.UserStaticInfo.USERS_SIGN_IN_INFO;
import static com.example.pz19.UserStaticInfo.profileId;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignActivity extends AppCompatActivity {

    private EditText LoginTextView, PasswordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        Init();
    }

    private void Init() {
        LoginTextView = findViewById(R.id.LoginTextView);
        PasswordTextView = findViewById(R.id.PasswordTextView);
    }

    public void SignIn(View view) {
        if ((StringNoNull(getPassword()) && StringNoNull(getLogin()))) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference(USERS_SIGN_IN_INFO);
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String login = LoginTextView.getText().toString();
                    Object value = dataSnapshot.child(PASSWORD).getValue();
                    if (value != null) {
                        if (value.toString().equals(getPassword())) {
                            goNext(dataSnapshot.child(PROFILE_ID).getValue().toString());
                        } else CantSignIn();
                    } else CantSignIn();
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
        }
        else {
            Vibrate(SignActivity.this);

            Toast.makeText(SignActivity.this,
                    getResources().getText(R.string.NullParametersMessage),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private String getLogin() {
        return LoginTextView.getText().toString();
    }

    private void goNext(String profileId) {
        UserStaticInfo.profileId = profileId;
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void CantSignIn() {
        Toast.makeText(SignActivity.this,
                getResources().getText(R.string.CantSignInMessage),
                Toast.LENGTH_SHORT).show();
    }

    private String getPassword() {
        return PasswordTextView.getText().toString();
    }

}
