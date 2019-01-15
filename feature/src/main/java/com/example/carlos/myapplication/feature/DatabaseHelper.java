package com.example.carlos.myapplication.feature;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="LocalDB.db";
    public static final String TABLE_NAME="Accounts_table";
    public static final String col1="Id";
    public static final String col2="Nick";
    public static final String col3="Password";

    //crea la base de datos
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,1);
    }

    //cuando se crea la bd crea la tabla
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" (Id INTEGER PRIMARY KEY AUTOINCREMENT,Nick TEXT ,Password TEXT)");
    }

    //Actualiza la bd borrando la tabla y creandola denuevo
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Accounts_table ");
        onCreate(db);
    }
    //Inserta una cuenta, el id es autoincrementable asi que no se agrega al contentValues
    public boolean insertData(String Nick,String Password){
        //se crea una instancia de la base de datos
        SQLiteDatabase db = this.getWritableDatabase();
        //se llena un "list" con el campo de la bd y su valor
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2,Nick);
        contentValues.put(col3,Password);
        long respuesta=db.insert(TABLE_NAME,null,contentValues);
        if (respuesta ==-1){
            return false;
        }else {
            return true;
        }
    }
    //Retorna todas las cuentas de la bd
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from Accounts_table ",null);
        return res;
    }

    public boolean updateData(String id, String nick,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1,id);
        contentValues.put(col2,nick);
        contentValues.put(col3,password);
        //"id=?" es donde se pregunta y se entrega el valor id como new string
        db.update(TABLE_NAME,contentValues,"id=?",new String[]{id});
        return true;
    }
    //el metodo es integer porque devuelve la cantidad de filas eliminadas
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"Id=?",new String[]{id});
    }

}
