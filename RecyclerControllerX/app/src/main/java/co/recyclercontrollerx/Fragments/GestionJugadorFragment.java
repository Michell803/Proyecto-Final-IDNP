package co.recyclercontrollerx.Fragments;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import co.recyclercontrollerx.Clases.ConexionSQLiteHelper;
import co.recyclercontrollerx.Clases.PreferenciasJuego;
import co.recyclercontrollerx.Clases.Utilidadess;
import co.recyclercontrollerx.Clases.vo.JugadorVo;
import co.recyclercontrollerx.Interfaces.IComunicaFragments;
import co.recyclercontrollerx.R;
import co.recyclercontrollerx.adapters.AdaptadorAvatar;
import co.recyclercontrollerx.adapters.AdaptadorJugador;

public class GestionJugadorFragment extends Fragment {



    int eventoEliminar = 0;
    Activity actividad;
    View vista;
    IComunicaFragments iComunicaFragments;
    FloatingActionsMenu grupoBotones;
    FloatingActionButton fabConfirmar,fabActualizar,fabEliminar;

    RecyclerView recycleAvatars, recycleJugadores;

    ImageButton btnAtras, btnAyuda;
    TextView barraSeleccion;
    EditText campoNick;
    RadioButton radioM, radioF;

    JugadorVo jugadorSeleccionado;


    public GestionJugadorFragment()
    {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        vista = inflater.inflate(R.layout.fragment_gestion_jugador, container, false);

        recycleAvatars= vista.findViewById(R.id.recyclerAvatarsId);
        recycleJugadores=vista.findViewById(R.id.recyclerJugadoresId);

        btnAtras=vista.findViewById(R.id.btnIcoAtras);
        campoNick=vista.findViewById(R.id.campoNickName);
        radioM=vista.findViewById(R.id.radioM);
        radioF=vista.findViewById(R.id.radioF);
        barraSeleccion=vista.findViewById(R.id.barraSeleccionId);
        grupoBotones=vista.findViewById(R.id.grupoFab);
        fabConfirmar=(FloatingActionButton)  vista.findViewById(R.id.idFabConfirmar);
        fabActualizar=(FloatingActionButton)  vista.findViewById(R.id.idFabActualizar);
        fabEliminar= (FloatingActionButton)  vista.findViewById(R.id.idFabEliminar);


        recycleJugadores.setLayoutManager(new LinearLayoutManager(this.actividad));
        recycleJugadores.setHasFixedSize(true);

        recycleAvatars.setLayoutManager(new GridLayoutManager(this.actividad,3));
        recycleAvatars.setHasFixedSize(true);

       fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (campoNick.getText().toString()!=null && !campoNick.getText().toString().trim().equals(""))
                {
                    dialogoEliminar().show();
                    grupoBotones.collapse();
                }
                else
                {
                Toast.makeText(actividad,"Debe seleccionar un Jugador para poder eliminarlo",Toast.LENGTH_SHORT).show();
                }

            }


        });
        fabConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (campoNick.getText().toString()!=null && !campoNick.getText().toString().trim().equals("")){
                    PreferenciasJuego.nickName="{ "+campoNick.getText().toString()+" }";
                    PreferenciasJuego.avatarId=Utilidadess.avatarSeleccion.getId();
                    SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(actividad);
                    PreferenciasJuego.asignarPreferenciasJugador(preferences,actividad);
                    grupoBotones.collapse();
                    campoNick.setText("");
                    eventoEliminar=0;
                    iComunicaFragments.mostrarMenu();
                }else{
                    Toast.makeText(actividad,"Verifique los datos para realizar la selección",Toast.LENGTH_SHORT).show();
                }
            }


        });

        fabActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                actulizarJugador();
                llenarAdaptadorJugadores();
                eventoEliminar = 0 ;
                grupoBotones.collapse();
            }


        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(eventoEliminar== 0)
                {
                    iComunicaFragments.mostrarMenu();
                    campoNick.setText("");
                }
                else
                {
                    Toast.makeText(actividad,"Debe seleccionar un jugador para continuar \n ",Toast.LENGTH_SHORT).show();

                }
            }


        });

        llenarAdaptadorJugadores();
        llenarAdaptadorAvatars(0);

        return vista;
    }
    private void actulizarJugador()
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


            ConexionSQLiteHelper conn=new ConexionSQLiteHelper(actividad,Utilidadess.NOMBRE_BD,null,1);
            SQLiteDatabase db=conn.getWritableDatabase();
            ContentValues values=new ContentValues(); //hash table con una clave y un valor y se envia como parametro para hacer el registro
            values.put(Utilidadess.CAMPO_NOMBRE,nickName);
            values.put(Utilidadess.CAMPO_GENERO,genero);
            values.put(Utilidadess.CAMPO_AVATAR,avatarId);

            int idResultante=db.update(Utilidadess.TABLA_JUGADOR, values, Utilidadess.CAMPO_ID+"="+jugadorSeleccionado.getId(), null);

            if (idResultante!=-1)
            {
                Toast.makeText(actividad,"El jugador fue Actualizado con exito! \n ",Toast.LENGTH_SHORT).show();
                recycleJugadores.scrollToPosition(jugadorSeleccionado.getId()-1);
                Utilidadess.consultarListaJugadores(actividad);

            }
            else
            {
                Toast.makeText(actividad,"No se pudo Actualizar el registro! \n ",Toast.LENGTH_SHORT).show();
            }
            db.close();

        }
        else
        {
            Toast.makeText(actividad,"Verifique los datos de registro",Toast.LENGTH_SHORT).show();

        }
    }
    private void llenarAdaptadorAvatars( int avatarId)
    {
        Utilidadess.avatarIdSeleccion=avatarId;

        AdaptadorAvatar miAdaptadorAvatars = new AdaptadorAvatar(Utilidadess.listaAvatars);
        recycleAvatars.scrollToPosition(avatarId-1);
        recycleAvatars.setAdapter(miAdaptadorAvatars);
    }

    private void llenarAdaptadorJugadores()
    {
        final AdaptadorJugador miAdaptadorJugadores = new AdaptadorJugador(Utilidadess.listaJugadores);

        miAdaptadorJugadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                grupoBotones.collapse();
                jugadorSeleccionado=Utilidadess.listaJugadores.get(recycleJugadores.getChildAdapterPosition(view));
                campoNick.setText(jugadorSeleccionado.getNombre());

                if(jugadorSeleccionado.getGenero().equals("M"))
                {
                    radioM.setChecked(true);
                }
                else
                {
                    radioF.setChecked(true);
                }
                Utilidadess.avatarSeleccion=Utilidadess.listaAvatars.get(jugadorSeleccionado.getAvatar()-1);
                llenarAdaptadorAvatars(jugadorSeleccionado.getAvatar());

            }
        });
        recycleJugadores.setAdapter(miAdaptadorJugadores);
    }
    public AlertDialog dialogoEliminar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(actividad);

        builder.setTitle("Advertencia!!!")
                .setMessage("¿Está seguro que desea eliminar a "+jugadorSeleccionado.getNombre().toUpperCase()+" y toda su información?")
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                .setPositiveButton("ACEPTAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                eliminarJugador();
                                llenarAdaptadorJugadores();
                            }


                        });

        return builder.create();
    }
    private void eliminarJugador()
    {
        if (campoNick.getText().toString()!=null && !campoNick.getText().toString().trim().equals("")){
            ConexionSQLiteHelper conn=new ConexionSQLiteHelper(actividad,Utilidadess.NOMBRE_BD,null,1);

            SQLiteDatabase db=conn.getWritableDatabase();


            int idResultante= db.delete(Utilidadess.TABLA_JUGADOR, Utilidadess.CAMPO_ID+"="+jugadorSeleccionado.getId(), null);

            if(idResultante!=-1){
                Toast.makeText(actividad,"El Jugador a sido Eliminado con Exito!",Toast.LENGTH_SHORT).show();
                campoNick.setText("");
                radioF.setChecked(false);
                radioM.setChecked(false);
                recycleJugadores.scrollToPosition(jugadorSeleccionado.getId());
                recycleAvatars.scrollToPosition(0);
                Utilidadess.consultarListaJugadores(actividad);

                PreferenciasJuego.nickName="NA";
                PreferenciasJuego.avatarId=1;
                SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(actividad);
                PreferenciasJuego.asignarPreferenciasJugador(preferences,actividad);

                eventoEliminar=1;
            }else
                Toast.makeText(actividad,"No se pudo Eliminar el Jugador! ",Toast.LENGTH_SHORT).show();


            db.close();
        }else{
            Toast.makeText(actividad,"Debe seleccionar un Jugador para poder eliminarlo",Toast.LENGTH_SHORT).show();
        }

    }
    public  void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.actividad= (Activity) context;
            iComunicaFragments= (IComunicaFragments) this.actividad;
        }

    }
}