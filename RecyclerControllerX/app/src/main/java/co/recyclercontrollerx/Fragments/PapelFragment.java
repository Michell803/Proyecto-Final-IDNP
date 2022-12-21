package co.recyclercontrollerx.Fragments;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.recyclercontrollerx.Clases.ConexionSQLiteHelper;
import co.recyclercontrollerx.Clases.Utilidadess;
import co.recyclercontrollerx.Interfaces.IComunicaAppPrincipal;
import co.recyclercontrollerx.R;



public class PapelFragment extends Fragment {

    View vista;
    IComunicaAppPrincipal iComunicaAppPrincipal;
    Activity actividad;
    EditText cantidadPapel,nombrePapel,tipoPapel;
    Button btnAgregarPapel;
    ConexionSQLiteHelper conexion;

    public PapelFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_papel, container, false);
        btnAgregarPapel=vista.findViewById(R.id.btnRegistroPapel);
        nombrePapel= vista.findViewById(R.id.campoNombrePapel);
        tipoPapel= vista.findViewById(R.id.tipoPapel);
        cantidadPapel= vista.findViewById(R.id.cantidadPapel);
        btnAgregarPapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnRegistroPapel:registrarPapel();
                }
            }
        });


        return vista;
    }
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.actividad= (Activity) context;
            iComunicaAppPrincipal = (IComunicaAppPrincipal) context;
        }

    }
    private void registrarPapel() {

        conexion =new ConexionSQLiteHelper(actividad, Utilidadess.NOMBRE_BD,null,1);

        SQLiteDatabase db=conexion.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Utilidadess.CAMPO_NOMBRE_MATERIAL,nombrePapel.getText().toString());
        values.put(Utilidadess.CAMPO_TIPO_MATERIAL,tipoPapel.getText().toString());
        values.put(Utilidadess.CAMPO_CANTIDAD_MATERIAL,cantidadPapel.getText().toString());

        Long idResultante=db.insert(Utilidadess.TABLA_MATERIAL,Utilidadess.CAMPO_ID_MATERIAL,values);

        Toast.makeText(actividad,"El material a sido Registrado con Exito!",Toast.LENGTH_SHORT).show();
        iComunicaAppPrincipal.mostrarCategorias();

        db.close();

    }
}