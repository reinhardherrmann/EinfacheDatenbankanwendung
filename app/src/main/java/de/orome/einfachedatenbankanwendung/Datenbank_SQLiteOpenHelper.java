package de.orome.einfachedatenbankanwendung;

/**
 * Created by rherrmann on 02.10.2016.
 * SQLiteOpenHelper ist im Wesentlichen dazu gedacht, Datenbanken zu erzeugen und zu managen
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Datenbank_SQLiteOpenHelper extends SQLiteOpenHelper {

    // Konstantendeklaration
    public static final String DATABASE_NAME = "de.orome.User.db";
    public static int DATABASE_VERSION = 1;
    // SQL-Statement zum Erstellen der Tabelle
    public static final String SQL_CREATE_USERTABLE =
            "CREATE TABLE " + PersonData.TabUsers.TABLE_NAME + "( " +
                    PersonData.TabUsers.COL_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    PersonData.TabUsers.COL_FIRSTNAMEVAL + " TEXT, " +
                    PersonData.TabUsers.COL_LASTNAMEVAL + " TEXT)";
    // Variablendeklaration

    public Datenbank_SQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // wenn Datenbank angelegt wird, Tabellen erstellen
        db.execSQL(SQL_CREATE_USERTABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // wenn Datenbank eine neue Version erhält
        // --> momentan kein upgrade nötig
    }

    // Routine zum Einfügen neuer Daten (ein Datensatz) und Rückgabe der neuen ID
    public static int insertNewUser(SQLiteDatabase db,String FirstName,String LastName){
        // Werte aus Anwendung in Feld einfügen
        ContentValues values = new ContentValues();
        values.put(PersonData.TabUsers.COL_FIRSTNAMEVAL,FirstName);
        values.put(PersonData.TabUsers.COL_LASTNAMEVAL,LastName);

        // Feld als insert ausführen und neue ID zurückgeben
        int NewRowID = (int) db.insert(
                PersonData.TabUsers.TABLE_NAME,null,values);
        // neue ID zurückgeben
        return NewRowID;

    }

    // Namen und Vornamen aus Datenbank auslesen
    public static String[] getUserDataByID(SQLiteDatabase db, int userID){
        String FirstName =null;
        String LastName = null;
        String[] Datensatz = new String[2];

        final String GET_USER_DATA =
                "SELECT " + PersonData.TabUsers.COL_FIRSTNAMEVAL +
                        ", " + PersonData.TabUsers.COL_LASTNAMEVAL +
                        " FROM " +PersonData.TabUsers.TABLE_NAME +
                        "WHERE " + PersonData.TabUsers.COL_USERID + " = ?";
        String[] SelectionArgs = new String[] {String.valueOf(userID)};
        Cursor cursor = db.rawQuery(GET_USER_DATA,SelectionArgs);
        if (cursor!=null){
            if(cursor.moveToFirst()) {
                do {
                    // Daten konkret auslesen und
                    FirstName = cursor.getString(0);
                    LastName = cursor.getString(1);
                    Datensatz[0] = FirstName;
                    Datensatz[1] = LastName;

                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return Datensatz;
    }

}

