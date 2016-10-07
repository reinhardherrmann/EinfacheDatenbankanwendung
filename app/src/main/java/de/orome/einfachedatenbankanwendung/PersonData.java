package de.orome.einfachedatenbankanwendung;

import android.provider.BaseColumns;

/**
 * Created by rherrmann on 02.10.2016.
 * Hier werden die Namen unserer Tabellenstruktur hinterlegt.
 */

public final class PersonData {
    public PersonData(){}
    public static abstract class TabUsers implements BaseColumns{
        public static final String TABLE_NAME = "tbl_Users";
        public static final String COL_USERID = "UserID";
        public static final String COL_FIRSTNAMEVAL = "Vorname";
        public static final String COL_LASTNAMEVAL = "Nachname";
    }
}
