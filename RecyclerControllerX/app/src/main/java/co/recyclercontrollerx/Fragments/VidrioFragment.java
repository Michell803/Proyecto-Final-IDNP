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


public class VidrioFragment extends Fragment {

    View vista;
    IComunicaAppPrincipal iComunicaAppPrincipal;
    Activity actividad;
    EditText cantidadVidrio,nombreVidrio,tipoVidrio;
    Button btnAgregarVidrio;
    ConexionSQLiteHelper conexion;


    public VidrioFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_vidrio, container, false);
        btnAgregarVidrio=vista.findViewById(R.id.btnRegistroVidrio);
        nombreVidrio= vista.findViewById(R.id.campoNombreVidrio);
        tipoVidrio= vista.findViewById(R.id.tipoVidrio);
        cantidadVidrio= vista.findViewById(R.id.cantidadVidrio);

        btnAgregarVidrio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnRegistroVidrio:registrarVidrio();
                }
            }
        });


        return vista; }
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.actividad= (Activity) context;
            iComunicaAppPrincipal = (IComunicaAppPrincipal) context;
        }

    }
    private void registrarVidrio() {

        conexion =new ConexionSQLiteHelper(actividad, Utilidadess.NOMBRE_BD,null,1);

        SQLiteDatabase db=conexion.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Utilidadess.CAMPO_NOMBRE_MATERIAL,nombreVidrio.getText().toString());
        values.put(Utilidadess.CAMPO_TIPO_MATERIAL,tipoVidrio.getText().toString());
        values.put(Utilidadess.CAMPO_CANTIDAD_MATERIAL,cantidadVidrio.getText().toString());

        Long idResultante=db.insert(Utilidadess.TABLA_MATERIAL,Utilidadess.CAMPO_ID_MATERIAL,values);

        Toast.makeText(actividad,"El material a sido Registrado con Exito!",Toast.LENGTH_SHORT).show();
        iComunicaAppPrincipal.mostrarCategorias();

        db.close();

    }
}