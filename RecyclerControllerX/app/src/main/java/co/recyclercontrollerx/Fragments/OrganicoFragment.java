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


public class OrganicoFragment extends Fragment {

    View vista;
    IComunicaAppPrincipal iComunicaAppPrincipal;
    Activity actividad;
    EditText cantidadOrganico,nombreOrganico,tipoOrganico;
    Button btnAgregarOrganico;
    ConexionSQLiteHelper conexion;
    public OrganicoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_organico, container, false);
        btnAgregarOrganico=vista.findViewById(R.id.btnRegistroOrganico);
        nombreOrganico= vista.findViewById(R.id.campoNombreOrganico);
        tipoOrganico= vista.findViewById(R.id.tipoOrganico);
        cantidadOrganico= vista.findViewById(R.id.cantidadOrganico);

        btnAgregarOrganico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnRegistroOrganico:registrarOrganico();
                }
            }
        });


        return vista;}
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.actividad= (Activity) context;
            iComunicaAppPrincipal = (IComunicaAppPrincipal) context;
        }

    }
    private void registrarOrganico() {

        conexion =new ConexionSQLiteHelper(actividad, Utilidadess.NOMBRE_BD,null,1);

        SQLiteDatabase db=conexion.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Utilidadess.CAMPO_NOMBRE_MATERIAL,nombreOrganico.getText().toString());
        values.put(Utilidadess.CAMPO_TIPO_MATERIAL,tipoOrganico.getText().toString());
        values.put(Utilidadess.CAMPO_CANTIDAD_MATERIAL,cantidadOrganico.getText().toString());

        Long idResultante=db.insert(Utilidadess.TABLA_MATERIAL,Utilidadess.CAMPO_ID_MATERIAL,values);

        Toast.makeText(actividad,"El material a sido Registrado con Exito!",Toast.LENGTH_SHORT).show();
        iComunicaAppPrincipal.mostrarCategorias();

        db.close();

    }
}