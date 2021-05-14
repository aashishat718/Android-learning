package com.example.contactmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.contactmanager.R;
import com.example.contactmanager.model.Contact;
import com.example.contactmanager.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME ,null, Util.DATABASE_VERSION);
    }

    //create table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_CONTACT_TABLE = "CREATE TABLE "+ Util.DATABASE_TABLE + "(" +
                Util.KEY_ID + " INTEGER PRIMARY KEY," +Util.KEY_NAME + " TEXT," + Util.KEY_PHONE_NUMBER
                + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String DROP_TABLE = String.valueOf(R.string.drop_table);
        sqLiteDatabase.execSQL(DROP_TABLE, new String[]{Util.DATABASE_NAME});

        //create a table again
        onCreate(sqLiteDatabase);
    }

    /*
    CRUD
     */
    //add contact
    public void addContact(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(Util.KEY_NAME,contact.getName());
        values.put(Util.KEY_PHONE_NUMBER,contact.getPhoneNumber());

        db.insert(Util.DATABASE_TABLE,null,values);
        db.close();
    }

    //get contact
    public Contact getContact(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor= db.query(Util.DATABASE_TABLE,new String[]{Util.KEY_ID,Util.KEY_NAME,Util.KEY_PHONE_NUMBER},
                Util.KEY_ID + "=?", new String[]{String.valueOf(id)},
                null,null,null);

        if(cursor != null)
            cursor.moveToFirst();

        Contact contact =new Contact();
        contact.setId(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(1));
        contact.setPhoneNumber(cursor.getString(2));

        return contact;
    }

    //get all contacts
    public List<Contact> getAllContacts(){
        List<Contact> contactList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        //Select all contacts
        String selectAll = "SELECT * FROM " + Util.DATABASE_TABLE;
        Cursor cursor= db.rawQuery(selectAll,null);

        //Loop through our data
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact("James", "213986");
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));

                //add contact objects to our list
                contactList.add(contact);
            }while (cursor.moveToNext());
        }

        return contactList;
    }

    //update contact
    public int updateContact(Contact contact){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        //update the row
        return db.update(Util.DATABASE_TABLE , values , Util.KEY_ID + "=?" ,
                new String[]{String.valueOf(contact.getId())});

    }

    //delete contact
    public void deleteContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util.DATABASE_TABLE, Util.KEY_ID + "=?" ,
                new String[]{String.valueOf(contact.getId())});
        db.close();
    }

    //get count
    public int getCount(){
        String countQuery = "SELECT * FROM " + Util.DATABASE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }
}
