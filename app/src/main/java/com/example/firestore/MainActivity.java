package com.example.firestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.firestore.Note.Note;
import com.example.firestore.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Phaser;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private static final String TAG = "MainActivity";
    private static final String USER_NAME = "name";
    private static final String GMAIL_ID = "gmail";
    private static final String USER_PASSWORD = "password";
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private CollectionReference noteBookRef=db.collection("users");


    /* private DocumentReference noteReference = db.collection("users").document("My First Note");*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

       /* Map<String,Object> user=new HashMap<>();
        user.put("first Name","ishwar");
        user.put("Last Name","parihar");
        user.put("bday","26-10-2000");*/
        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                saveNot();
                addNote();

            }
        });
        binding.loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNote();

            }
        });
       /* binding.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });*/
      /*  binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.userEdtLayout.setVisibility(View.VISIBLE);
                binding.userInfoLayout.setVisibility(View.GONE);

            }
        });*/
       /* binding.deleteBtn.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     delete();
                                                 }
                                             }
        );*/
    }

    private void addNote() {
        String gmail = binding.gmailEdt.getText().toString();
        String userName = binding.usernameEdt.getText().toString();
        String password = binding.passwordEdt.getText().toString();
        String priorityEdt=binding.priorityEdt.getText().toString();

        if (binding.priorityEdt.length()==0){
            binding.priorityEdt.setText("0");
        }
    int priority= Integer.parseInt(binding.priorityEdt.getText().toString());
        Note note=new Note(userName,gmail,password,priority);
        noteBookRef.add(note);
    }


   /* public void saveNot() {
        String gmail = binding.gmailEdt.getText().toString();
        String userName = binding.usernameEdt.getText().toString();
        String password = binding.passwordEdt.getText().toString();
        Note note=new Note(userName,gmail,password);

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

    }*/


    public void loadNote() {
        noteBookRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                String data="";
            for (QueryDocumentSnapshot documentSnapshots:queryDocumentSnapshots){
                Note note=documentSnapshots.toObject(Note.class);
                note.setId(documentSnapshots.getId());

                String documentId=note.getId();
                String Gmail=note.getGmail();
                String username=note.getName();
                String passWord=note.getPassword();
                int priority=note.getPriority();
                data+="Id: "+documentId +"\nGmail is: "+Gmail+"\nUsername is: "+username+"\nPassword is: "+passWord+"\nPriority is: "+ priority + "\n"+"\n";


            }
            binding.gmailTv.setText(data);
            }
        });
       /* db.collection("users").document("My First Note").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                   *//* String gmailId = documentSnapshot.getString(GMAIL_ID);
                    String userName = documentSnapshot.getString(USER_NAME);
                    String password = documentSnapshot.getString(USER_PASSWORD);*//*

                    Note note=documentSnapshot.toObject(Note.class);

                    binding.gmailTv.setText("Gmail Is:" + note.getGmail());
                    binding.usernameTv.setText("Username is:" + note.getUserName());
                    binding.passwordTv.setText("password is:" + note.getPassWord());

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
        });*/
    }

    public void updateData() {
        String gmail = binding.gmailEdt.getText().toString();
        Map<String, Object> note = new HashMap<>();
        note.put(GMAIL_ID, gmail);

        db.collection("users").document("My First Note").set(note, SetOptions.merge());
    }

    public void delete() {
        db.collection("users").document("My First Note").update(GMAIL_ID, FieldValue.delete());
    }
}