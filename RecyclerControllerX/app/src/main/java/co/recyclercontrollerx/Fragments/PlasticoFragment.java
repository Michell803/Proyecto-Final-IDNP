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
import co.recyclercontrollerx.Interfaces.IComunicaFragments;
import co.recyclercontrollerx.R;


public class PlasticoFragment extends Fragment {
    View vista;
    IComunicaAppPrincipal iComunicaAppPrincipal;
    Activity actividad;
    EditText cantidadPlastico,nombrePlastico,tipoPlastico;
    Button btnAgregarPlastico;
    ConexionSQLiteHelper conexion;


    public PlasticoFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_plastico, container, false);
        btnAgregarPlastico=vista.findViewById(R.id.btnRegistroPlastico);
        nombrePlastico= vista.findViewById(R.id.campoNombreMaterial);
        tipoPlastico= vista.findViewById(R.id.tipoPlastico);
        cantidadPlastico= vista.findViewById(R.id.cantidadPLastico);

        btnAgregarPlastico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnRegistroPlastico:registrarPlastico();
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
    private void registrarPlastico() {

        conexion =new ConexionSQLiteHelper(actividad, Utilidadess.NOMBRE_BD,null,1);

        SQLiteDatabase db=conexion.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Utilidadess.CAMPO_NOMBRE_MATERIAL,nombrePlastico.getText().toString());
        values.put(Utilidadess.CAMPO_TIPO_MATERIAL,tipoPlastico.getText().toString());
        values.put(Utilidadess.CAMPO_CANTIDAD_MATERIAL,cantidadPlastico.getText().toString());

        Long idResultante=db.insert(Utilidadess.TABLA_MATERIAL,Utilidadess.CAMPO_ID_MATERIAL,values);

        Toast.makeText(actividad,"El material a sido Registrado con Exito!",Toast.LENGTH_SHORT).show();
        iComunicaAppPrincipal.mostrarCategorias();

        db.close();

    }
    public void onClick(View view)
    {

    }
}