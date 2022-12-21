package co.recyclercontrollerx.Clases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {
    public ConexionSQLiteHelper(Context context, String name,  SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(Utilidadess.CREAR_TABLA_JUGADOR); //Tabla Usuario
        db.execSQL(Utilidadess.CREAR_TABLA_MATERIAL); //Tabla Material

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+Utilidadess.TABLA_JUGADOR);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidadess.TABLA_MATERIAL);

        onCreate(db);

    }
}
