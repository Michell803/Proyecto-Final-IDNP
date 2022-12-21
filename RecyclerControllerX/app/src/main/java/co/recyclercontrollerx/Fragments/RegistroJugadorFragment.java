package co.recyclercontrollerx.Fragments;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import co.recyclercontrollerx.Clases.ConexionSQLiteHelper;
import co.recyclercontrollerx.Clases.PreferenciasJuego;
import co.recyclercontrollerx.Clases.Utilidadess;
import co.recyclercontrollerx.Interfaces.IComunicaFragments;
import co.recyclercontrollerx.R;
import co.recyclercontrollerx.adapters.AdaptadorAvatar;


public class RegistroJugadorFragment extends Fragment  {


    View vista;
    Activity actividad;
    IComunicaFragments iComunicaFragments;
    RecyclerView reciclerAvatar;

    ImageButton btnAtras,btnAyuda;
    FloatingActionButton fabRegistro;
    EditText campoNick;
    RadioButton radioM,radioF;



    public RegistroJugadorFragment() {

     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_registro_jugador, container, false);
        reciclerAvatar=vista.findViewById(R.id.recyclerAvatarsId);

        fabRegistro=vista.findViewById(R.id.idFabRegistro);
        campoNick=vista.findViewById(R.id.campoNickName);
        radioM=vista.findViewById(R.id.radioM);
        radioF=vista.findViewById(R.id.radioF);
        btnAtras= vista.findViewById(R.id.btnIcoAtras);

        reciclerAvatar.setLayoutManager(new GridLayoutManager(this.actividad,3));
        reciclerAvatar.setHasFixedSize(true);

        fabRegistro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                registarJugador();
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                iComunicaFragments.mostrarMenu();
                campoNick.setText("");
            }
        });

        final AdaptadorAvatar miAdaptador = new AdaptadorAvatar(Utilidadess.listaAvatars);
        reciclerAvatar.setAdapter(miAdaptador);

        return vista;
    }

    private void registarJugador()
    {
        String genero="";

        if (radioM.isChecked()==true){
            genero="M";
        }
        else if(radioF.isChecked()==true)
        {
            genero="F";
        }
        else
        {
            genero="No seleccionado";
        }

        if (!genero.equals("No seleccionado") && campoNick.getText().toString()!=null && !campoNick.getText().toString().trim().equals(""))
        {
            String nickName=campoNick.getText().toString();
            int avatarId=Utilidadess.avatarSeleccion.getId();

            String registro= "Nombre: "+campoNick.getText().toString()+"\n";
            registro += "Genero: "+genero+"\n";
            registro +="Avatar Id: "+Utilidadess.avatarSeleccion.getId();


            ConexionSQLiteHelper conn=new ConexionSQLiteHelper(actividad,Utilidadess.NOMBRE_BD,null,1);
            SQLiteDatabase db=conn.getWritableDatabase();
            ContentValues values=new ContentValues(); //hash table con una clave y un valor y se envia como parametro para hacer el registro
            values.put(Utilidadess.CAMPO_NOMBRE,nickName);
            values.put(Utilidadess.CAMPO_GENERO,genero);
            values.put(Utilidadess.CAMPO_AVATAR,avatarId);

            Long idResultante=db.insert(Utilidadess.TABLA_JUGADOR,Utilidadess.CAMPO_ID,values);

            if (idResultante!=-1)
            {
                PreferenciasJuego.jugadorId=Integer.parseInt(idResultante+"");
                Toast.makeText(actividad,"El Usuario a sido Registrado con Exito!",Toast.LENGTH_SHORT).show();
                PreferenciasJuego.nickName="{ "+campoNick.getText().toString()+" }";
                PreferenciasJuego.avatarId=Utilidadess.avatarSeleccion.getId();
                SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(actividad);
                PreferenciasJuego.asignarPreferenciasJugador(preferences,actividad);
                campoNick.setText("");
                iComunicaFragments.mostrarMenu();
            }
            else
            {
                Toast.makeText(actividad,"No se pudo registrar el registro! \n "+registro,Toast.LENGTH_SHORT).show();
            }
            db.close();

        }
        else
        {
            Toast.makeText(actividad,"Verifique los datos de registro",Toast.LENGTH_SHORT).show();

        }
    }
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.actividad= (Activity) context;
            iComunicaFragments = (IComunicaFragments) context;
        }

    }
    public void onClick(View view)
    {
    }
}