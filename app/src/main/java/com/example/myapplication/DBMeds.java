package com.example.myapplication;

//jak odpowiednio zapisać do bazy godzinę, zeby nadawała się do powiadomień?
//trzeba dodać wrzucanie i wyciąganie z bazy godziny itp, na razie jest tylko nazwa

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Agata on 2016-11-12.
 */
public class DBMeds extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "medicaments.db";
    private static final String TABLE_NAME = "medicaments";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DOSE = "dawka";
    private static final String COLUMN_HOUR = "hour";
    private static final String COLUMN_MEAL = "meal";

    SQLiteDatabase db;

    public DBMeds(Context context){ //nazwa klasy taka sama jak wyżej
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + TABLE_NAME + "(" +
                        COLUMN_ID + " integer primary key autoincrement," +
                        COLUMN_NAME + " text," +
                        COLUMN_HOUR + " text," +
                        COLUMN_MEAL + " text);" +
                        "");
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXIST "+TABLE_NAME;
        db.execSQL(query);
        db.execSQL("VACUUM");
        this.onCreate(db);
    }

    public void insertMed(Medicament med){ // przekazanie do metody całego obiektu klasy Medicament o nazwie med
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, med.getName()); // metoda put("nazwa kolumny", parametr);
        values.put(COLUMN_HOUR, med.getHour());
        values.put(COLUMN_MEAL, med.getMeal());
        db.insertOrThrow(TABLE_NAME, null, values);
        db.execSQL("VACUUM");
        db.close();
    }

    // metoda getAllMeds() - pobieranie danych z bazy i zwracanie jako listę obiektów klasy LinkedList
    public List<Medicament> getAllMeds(){
        LinkedList meds = new LinkedList<>();
        String[] columns={COLUMN_ID,COLUMN_NAME,COLUMN_HOUR,COLUMN_MEAL};
        SQLiteDatabase db = getReadableDatabase(); // uchwyt do bazy pozwalający na odczyt danych z bazy
        Cursor cursor =db.query(TABLE_NAME,columns,null,null,null,null,null);
        while(cursor.moveToNext()){
            Medicament med = new Medicament(); // tworzenie pustego obiektu klasy Medicament o nazwie med
            med.setId(cursor.getLong(0)); // uzupełnianie pól obiektu lek
            med.setName(cursor.getString(1));
            med.setHour(cursor.getString(2));
            med.setMeal(cursor.getString(3));
            meds.add(med);// dodwanie obiektu med do listy meds
        }
        return meds; // zwracanie listy leków
    }
/*
    // metoda dajMed - pozwala na wyświetlanie konkretnego rekordu z bazy danych
    public Medicament getMed(int nr){
        Medicament med = new Medicament();
        SQLiteDatabase db = getReadableDatabase();
        String[] columns={COLUMN_NAME,COLUMN_HOUR,COLUMN_MEAL};
        String args[]={nr+""}; // podstawienie wartości która trafi pod znak „?” w warunku where

        Cursor cursor=db.query(TABLE_NAME,columns,"id=?",args,null,null,null,null); // wykonanie zapytania
        if(cursor!=null){ //sprawdzenie, czy zwrócony zostanie przynajmniej jeden wiersz
            // czytanie danych z pól wiersza i uzupełniania obiektu
            cursor.moveToFirst(); // przejście do pierwszego wiersza
            med.setId(cursor.getLong(0)); // uzupełnianie pól obiektu med
            med.setName(cursor.getString(1));
            med.setHour(cursor.getString(2));
            med.setMeal(cursor.getString(3));
        }
        return med; // zwracanie uzupełnionego obiektu
    } */


    // Niestety to co wyżej getMed coś nie chce działać, więc są osobne metody do pobierania konretnej wartości
    public String getMedName(int i){
        db=this.getReadableDatabase();
        String query = "select "+ COLUMN_NAME + " from "+TABLE_NAME + " where id=" + i;
        Cursor cursor = db.rawQuery(query, null);
        String medName="not found";
        if (cursor.moveToFirst()){
            do{
                medName=cursor.getString(0);
            }while(cursor.moveToNext());
        }
        return medName;
    }

    public String getMedHour(int i){
        db=this.getReadableDatabase();
        String query = "select "+ COLUMN_HOUR + " from "+TABLE_NAME + " where id=" + i;
        Cursor cursor = db.rawQuery(query, null);
        String medHour="not found";
        if (cursor.moveToFirst()){
            do{
                medHour=cursor.getString(0);
            }while(cursor.moveToNext());
        }
        return medHour;
    }

    public String getMedMeal(int i){
        db=this.getReadableDatabase();
        String query = "select "+ COLUMN_MEAL + " from "+TABLE_NAME + " where id=" + i;
        Cursor cursor = db.rawQuery(query, null);
        String medMeal="not found";
        if (cursor.moveToFirst()){
            do{
                medMeal=cursor.getString(0);
            }while(cursor.moveToNext());
        }
        return medMeal;
    }

    //usuwanie leku
    public void deleteMed(int id){
        SQLiteDatabase db = getWritableDatabase();
        String[] args={""+id};
        db.delete(TABLE_NAME,"id=?", args);
        db.execSQL("VACUUM");
    }

    //edycja leku - Niby działa, ale nie działą :(
    public void updateMed(Medicament med){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, med.getName());
        values.put(COLUMN_HOUR, med.getHour());
        //values.put(COLUMN_MEAL, med.getMeal());

        String args[]={""+med.getId()};
        db.update(TABLE_NAME, values,"id=?", args);
        db.execSQL("VACUUM");
    }
}
