package com.example.firestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.firestore.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private static final String TAG = "MainActivity";
    private static final String USER_NAME = "name";
    private static final String GMAIL_ID = "gmail";
    private static final String USER_PASSWORD = "password";
    FirebaseFirestore db;
    /* private DocumentReference noteReference = db.collection("users").document("My First Note");*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        db = FirebaseFirestore.getInstance();
       /* Map<String,Object> user=new HashMap<>();
        user.put("first Name","ishwar");
        user.put("Last Name","parihar");
        user.put("bday","26-10-2000");*/
        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNot();
                binding.userEdtLayout.setVisibility(View.VISIBLE);
                binding.userInfoLayout.setVisibility(View.GONE);

            }
        });
        binding.loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNote();
                binding.userEdtLayout.setVisibility(View.GONE);
                binding.userInfoLayout.setVisibility(View.VISIBLE);

            }
        });

    }

    public void saveNot() {
        String gmail = binding.gmailEdt.getText().toString();
        String userName = binding.usernameEdt.getText().toString();
        String password = binding.passwordEdt.getText().toString();
        Map<String, Object> note = new HashMap<>();
        note.put(USER_NAME, userName);
        note.put(GMAIL_ID, gmail);
        note.put(USER_PASSWORD, password);

        db.collection("users").document("My First Note").set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity.this, "saved", Toast.LENGTH_SHORT).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        Log.d("Error", e.toString());
                    }
                });

    }

    public void loadNote() {
        db.collection("users").document("My First Note").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot)  {
                if (documentSnapshot.exists()) {
                    String gmailId = documentSnapshot.getString(GMAIL_ID);
                    String userName = documentSnapshot.getString(USER_NAME);
                    String password = documentSnapshot.getString(USER_PASSWORD);
                    binding.gmailTv.setText("Gmail Is:" + gmailId);
                    binding.usernameTv.setText("Username is:" + userName);
                    binding.passwordTv.setText("password is:" + password);

                    Toast.makeText(MainActivity.this, "Data Exists", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Document Does not Exist", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }
        });
    }
}