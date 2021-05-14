package com.example.introfirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaDrm;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Main Activity";
    private EditText title;
    private EditText thoughts;
    private Button saveButton,showButton,updateButton,deleteButton;
    private TextView titleText,thoughtText;

    //connection to firestore
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference journalRef = db.collection("Journal")
            .document("First Thought");
    //private DocumentReference journalRef = db.document("Journal/First Path");
    private CollectionReference collectionReference = db.collection("Journal");

    //keys
    public static final String KEY_TITLE = "title";
    public static final String KEY_THOUGHT = "thought";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title=findViewById(R.id.title_id);
        thoughts=findViewById(R.id.thoughts_id);
        saveButton=findViewById(R.id.save_id);
        showButton=findViewById(R.id.show_id);
        updateButton=findViewById(R.id.update_id);
        deleteButton=findViewById(R.id.delete_id);
        titleText=findViewById(R.id.titleText);
        thoughtText=findViewById(R.id.thoughtText);

        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addDocument();
//                String t = title.getText().toString().trim();
//                String th = thoughts.getText().toString().trim();
////                Map<String,Object> data = new HashMap<>();
////                data.put(KEY_TITLE,t);
////                data.put(KEY_THOUGHT,th);
//                Journal journal = new Journal();
//                journal.setTitle(t);
//                journal.setThought(th);
//
//                db.collection("Journal")
//                        .document("First Thought")
//                        .set(journal)
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                Toast.makeText(MainActivity.this, R.string.success_text ,Toast.LENGTH_SHORT)
//                                        .show();
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(MainActivity.this, R.string.fail_text ,Toast.LENGTH_SHORT)
//                                        .show();
//                            }
//                        });

            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getThoughts();
//                 journalRef.get()
//                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                            @Override
//                            public void onSuccess(DocumentSnapshot documentSnapshot) {
//
//                                if (documentSnapshot.exists()) {
//
//                                    Journal journal = documentSnapshot.toObject(Journal.class);
////                                    String title = documentSnapshot.getString(KEY_TITLE);
////                                    String thought = documentSnapshot.getString(KEY_THOUGHT);
//
//                                    if (journal != null) {
//                                        titleText.setText(journal.getTitle());
//                                        thoughtText.setText(journal.getThought());
//                                    }
//                                }
//                                else
//                                    Toast.makeText(MainActivity.this,"Not found",Toast.LENGTH_SHORT)
//                                    .show();
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Log.d(TAG, "onFailure: "+ e.toString());
//                            }
//                        });
            }
        });
    }

    private void getThoughts(){

        collectionReference.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for(QueryDocumentSnapshot qs : queryDocumentSnapshots){
                            Log.d(TAG, "onSuccess: "+ qs.get(KEY_TITLE));
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: " + e.toString());
                    }
                });
    }
    private void addDocument() {

        String t = title.getText().toString().trim();
        String th = thoughts.getText().toString().trim();
        Journal journal = new Journal(t,th);

        collectionReference.add(journal)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(MainActivity.this, R.string.success_text ,Toast.LENGTH_SHORT)
                                .show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, R.string.fail_text ,Toast.LENGTH_SHORT)
                                .show();
                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();

        journalRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                if(e!=null){
                    Toast.makeText(MainActivity.this, R.string.sth_went_wrong, Toast.LENGTH_SHORT).show();
                }

                if (documentSnapshot != null && documentSnapshot.exists()) {
                    Journal journal = documentSnapshot.toObject(Journal.class);
//                                    String title = documentSnapshot.getString(KEY_TITLE);
//                                    String thought = documentSnapshot.getString(KEY_THOUGHT);

                    if (journal != null) {
                        titleText.setText(journal.getTitle());
                        thoughtText.setText(journal.getThought());
                    }
                }
                else{
                    titleText.setText(null);
                    thoughtText.setText(null);
                }

            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.update_id:
                //update data
                updateData();
                break;
            case R.id.delete_id:
                deleteData();
                break;
        }
    }

    private void deleteData() {
        //to delete a single field
        //journalRef.update(KEY_THOUGHT, FieldValue.delete());

        //to delete whole data
        journalRef.delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this,"Item deleted successfully", Toast.LENGTH_SHORT)
                                .show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, R.string.sth_went_wrong, Toast.LENGTH_SHORT)
                                .show();
                    }
                });
    }

    private void updateData() {
        String t = title.getText().toString().trim();
        String th = thoughts.getText().toString().trim();
        Map<String,Object> data = new HashMap<>();
        data.put(KEY_TITLE,t);
        data.put(KEY_THOUGHT,th);

        journalRef.update(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this,"Updated" ,Toast.LENGTH_SHORT)
                                .show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, R.string.sth_went_wrong , Toast.LENGTH_SHORT)
                                .show();
                    }
                });

    }
}
