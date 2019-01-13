package com.example.carlos.myapplication.feature;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="LocalDB.db";
    public static final String TABLE_NAME="Accounts.db";
    public static final String col1="Id";
    public static final String col2="Nick";
    public static final String col3="Password";

    //crea la base de datos
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,1);
       // SQLiteDatabase db = this.getReadableDatabase();
    }

    //cuando se crea la bd crea la tabla
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Accounts.db (Id INTEGER PRIMARY KEY AUTOINCREMENT,Nick TEXT ,Password TEXT)");
    }

    //Actualiza la bd borrando la tabla y creandola denuevo
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Accounts.db ");
        onCreate(db);
    }
}
