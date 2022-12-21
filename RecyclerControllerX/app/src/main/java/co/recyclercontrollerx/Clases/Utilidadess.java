package co.recyclercontrollerx.Clases;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import co.recyclercontrollerx.Clases.vo.JugadorVo;
import co.recyclercontrollerx.Clases.vo.avatarvo;
import co.recyclercontrollerx.R;

public class Utilidadess {

    public static ArrayList<JugadorVo> listaJugadores=null;
    public static ArrayList<avatarvo> listaAvatars = null;
     public  static  avatarvo avatarSeleccion=null;
     public static int avatarIdSeleccion=0;


    public static final String NOMBRE_BD="recycler_bd";

    public static final String TABLA_JUGADOR="jugador";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_GENERO="genero";
    public static final String CAMPO_AVATAR="avatar";
    public static final String CREAR_TABLA_JUGADOR="CREATE TABLE "+TABLA_JUGADOR+" ("+CAMPO_ID+" INTEGER PRIMARY KEY, "+CAMPO_NOMBRE+" TEXT,"+CAMPO_GENERO+" TEXT,"+CAMPO_AVATAR+" INTEGER)";

    public static final String TABLA_MATERIAL="material";
    public static final String CAMPO_ID_MATERIAL="id_material";
    public static final String CAMPO_NOMBRE_MATERIAL="nombre_material";
    public static final String CAMPO_TIPO_MATERIAL="tipo_material";
    public static final String CAMPO_CANTIDAD_MATERIAL="cantidad_material";
    public static final String CREAR_TABLA_MATERIAL="CREATE TABLE "+TABLA_MATERIAL+" ("+CAMPO_ID_MATERIAL+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_NOMBRE_MATERIAL+" TEXT,"+CAMPO_TIPO_MATERIAL+" TEXT,"+CAMPO_CANTIDAD_MATERIAL+ "TEXT)";

    public static void obtenerListaAvatars()
     {
         listaAvatars = new ArrayList<avatarvo>();


         listaAvatars.add(new avatarvo(1,R.drawable.avatar1,"Avatar1"));
         listaAvatars.add(new avatarvo(2,R.drawable.avatar2,"Avatar2"));
         listaAvatars.add(new avatarvo(3,R.drawable.avatar3,"Avatar3"));
         listaAvatars.add(new avatarvo(4,R.drawable.avatar4,"Avatar4"));
         listaAvatars.add(new avatarvo(5,R.drawable.avatar5,"Avatar5"));
         listaAvatars.add(new avatarvo(6,R.drawable.avatar6,"Avatar6"));
         listaAvatars.add(new avatarvo(7,R.drawable.avatar7,"Avatar7"));
         listaAvatars.add(new avatarvo(8,R.drawable.avatar8,"Avatar8"));
         listaAvatars.add(new avatarvo(9,R.drawable.avatar9,"Avatar9"));
         listaAvatars.add(new avatarvo(10,R.drawable.avatar10,"Avatar10"));
         listaAvatars.add(new avatarvo(11,R.drawable.avatar11,"Avatar11"));
         listaAvatars.add(new avatarvo(12,R.drawable.avatar12,"Avatar12"));
         listaAvatars.add(new avatarvo(13,R.drawable.avatar13,"Avatar13"));
         listaAvatars.add(new avatarvo(14,R.drawable.avatar14,"Avatar14"));
         listaAvatars.add(new avatarvo(15,R.drawable.avatar15,"Avatar15"));
         listaAvatars.add(new avatarvo(16,R.drawable.avatar16,"Avatar16"));
         listaAvatars.add(new avatarvo(17,R.drawable.avatar17,"Avatar17"));
         listaAvatars.add(new avatarvo(18,R.drawable.avatar18,"Avatar18"));
         listaAvatars.add(new avatarvo(19,R.drawable.avatar19,"Avatar19"));
         listaAvatars.add(new avatarvo(20,R.drawable.avatar20,"Avatar20"));
         listaAvatars.add(new avatarvo(21,R.drawable.avatar21,"Avatar21"));
         listaAvatars.add(new avatarvo(22,R.drawable.avatar22,"Avatar22"));
         listaAvatars.add(new avatarvo(23,R.drawable.avatar23,"Avatar23"));
         listaAvatars.add(new avatarvo(24,R.drawable.avatar24,"Avatar24"));
         listaAvatars.add(new avatarvo(25,R.drawable.avatar25,"Avatar25"));
         listaAvatars.add(new avatarvo(26,R.drawable.avatar26,"Avatar26"));
         listaAvatars.add(new avatarvo(27,R.drawable.avatar27,"Avatar27"));

         avatarSeleccion= listaAvatars.get(0);
     }
    public static void consultarListaJugadores(Activity actividad) {
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(actividad,NOMBRE_BD,null,1);
        SQLiteDatabase db=conn.getReadableDatabase();

        JugadorVo jugador=null;
        listaJugadores=new ArrayList<JugadorVo>();

        Cursor cursor=db.rawQuery("SELECT * FROM "+Utilidadess.TABLA_JUGADOR,null);

        while (cursor.moveToNext()){
            jugador=new JugadorVo();
            jugador.setId(cursor.getInt(0));
            jugador.setNombre(cursor.getString(1));
            jugador.setGenero(cursor.getString(2));
            jugador.setAvatar(cursor.getInt(3));

            listaJugadores.add(jugador);
        }

        db.close();
    }
}
