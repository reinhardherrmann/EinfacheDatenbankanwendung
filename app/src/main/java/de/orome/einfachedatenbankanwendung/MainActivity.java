package de.orome.einfachedatenbankanwendung;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Datenbank_SQLiteOpenHelper myDbHelper;
    private Button btnSaveData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSaveData = (Button) findViewById(R.id.button_SaveData);
        this.myDbHelper = new Datenbank_SQLiteOpenHelper(this);

        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Textfelder bekanntmachen
                EditText txt_ID = (EditText) findViewById(R.id.editText_ID);
                EditText txt_FirstName = (EditText) findViewById(R.id.editText_FirstName);
                EditText txt_LastName = (EditText) findViewById(R.id.editText_LastName);
                String FirstName =  txt_FirstName.getText().toString();
                String LastName = txt_LastName.getText().toString();

                // Datenbank ansprechen
                SQLiteDatabase db = myDbHelper.getWritableDatabase();
                long newID = Datenbank_SQLiteOpenHelper.insertNewUser(db,FirstName,LastName);
                txt_ID.setText(String.valueOf(newID));
                String a ="a";
            }
        });
    }
}
