package com.example.contactmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.contactmanager.adapter.RecyclerViewAdapter;
import com.example.contactmanager.data.DatabaseHandler;
import com.example.contactmanager.model.Contact;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Contact> contactArrayList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contactArrayList = new ArrayList<>();
        DatabaseHandler db = new DatabaseHandler(MainActivity.this);

//        db.addContact(new Contact("James","213986"));
//        db.addContact(new Contact("Greg","098765"));
//        db.addContact(new Contact("Helena","40678765"));
//        db.addContact(new Contact("Carimo","768345"));
//
//        db.addContact(new Contact("Silo","3445"));
//        db.addContact(new Contact("Santos","6665"));
//        db.addContact(new Contact("Litos","5344"));
//        db.addContact(new Contact("Karate","96534"));
//        db.addContact(new Contact("Guerra","158285"));
//        db.addContact(new Contact("Gema","78130"));


        List<Contact> contactList = db.getAllContacts();
        for (Contact contact : contactList) {
            Log.d("MainActivity", "onCreate: " + contact.getName());
            contactArrayList.add(contact);
        }

        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this,contactArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
