package co.recyclercontrollerx.Fragments;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import co.recyclercontrollerx.Clases.vo.materialVo2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.recyclercontrollerx.Clases.ConexionSQLiteHelper;
import co.recyclercontrollerx.Clases.Utilidadess;
import co.recyclercontrollerx.Clases.vo.materialVo;
import co.recyclercontrollerx.Interfaces.IComunicaAppPrincipal;

import co.recyclercontrollerx.R;
import co.recyclercontrollerx.adapters.AdaptadorMaterial;


public class RegistroMaterialFragment extends Fragment {

    View vista;
    Activity actividad;
    IComunicaAppPrincipal iComunicaFragments;
    ImageButton btnAgregar;
    ListView lv1,lv2,lv3;
    List<materialVo> material;
    ArrayAdapter<String> adapter1,adapter2,adapter3;
    ArrayList <materialVo>  listaMateriales;
    ConexionSQLiteHelper conexion;
    Button anadirMaterialDia, anadirMaterialTarde, anadirMaterialNoche;
    static TextView plasticosDia;
    static TextView vidriosDia;
    static TextView papelesDia;
    static TextView plasticosTarde;
    static TextView vidriosTarde;
    static TextView papelesTarde;
    static TextView plasticosNoche;
    static TextView vidriosNoche;
    static TextView papelesNoche;

    TextView campoPlastico;
    TextView campoPapel;

    TextView vidrioTarde;
    TextView plasticoTarde;

    TextView vidrioNoche;
    TextView plasticoNoche;

    public RegistroMaterialFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_registro_material, container, false);


        String[] data = {"Botella de plastico 3lts","Hojas bond"};
        String[] dataTarde = {"Vidrio laminado ","Bolsa de basura"};
        String[] dataNoche = {"Envase de leche ","Botella de agua mineral 1L"};
        lv1=(ListView) vista.findViewById(R.id.recyclerManana);
        adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,data);
        lv1.setAdapter(adapter1);

        lv2=(ListView) vista.findViewById(R.id.recyclerTarde);
        adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,dataTarde);
        lv2.setAdapter(adapter2);

        lv3=(ListView) vista.findViewById(R.id.recyclerNoche);
        adapter3 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,dataNoche);
        lv3.setAdapter(adapter3);

        //Espacios de materiales que agrega el usuario
        campoPlastico = vista.findViewById(R.id.campoPlastico);
        campoPapel = vista.findViewById(R.id.campoPapel);

        vidrioTarde = vista.findViewById(R.id.vidrioTarde);
        plasticoTarde = vista.findViewById(R.id.plasticoTarde);

        vidrioNoche = vista.findViewById(R.id.vidrioNoche);
        plasticoNoche = vista.findViewById(R.id.plasticoNoche);

        //Botones
        anadirMaterialDia = vista.findViewById(R.id.anadirMaterialDia); // Botones
        anadirMaterialTarde = vista.findViewById(R.id.anadirMaterialTarde);
        anadirMaterialNoche = vista.findViewById(R.id.anadirMaterialNoche);

        plasticosDia = vista.findViewById(R.id.cantidadPlasticosDia); // Materiales
        vidriosDia = vista.findViewById(R.id.cantidadVidriosDia);
        papelesDia = vista.findViewById(R.id.cantidadPapelesDia);
        plasticosTarde = vista.findViewById(R.id.cantidadPlasticosTarde);
        vidriosTarde = vista.findViewById(R.id.cantidadVidriosTarde);
        papelesTarde = vista.findViewById(R.id.cantidadPapelesTarde);
        plasticosNoche = vista.findViewById(R.id.cantidadPlasticosNoche);
        vidriosNoche = vista.findViewById(R.id.cantidadVidriosNoche);
        papelesNoche = vista.findViewById(R.id.cantidadPapelesNoche);

        anadirMaterialDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aMaterialDia();
            }
        });
        anadirMaterialTarde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aMaterialTarde();
            }
        });
        anadirMaterialNoche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aMaterialNoche();
            }
        });

        return vista;
    }

   private void aMaterialDia() {
       //plasticosDia.setText("" + 5);
       //plasticosTarde.setText("" + 5);
       //plasticosNoche.setText("" + 5);

       int cP = Integer.parseInt(campoPlastico.getText().toString());
       int cV = Integer.parseInt(campoPapel.getText().toString());
       //int suma = cP + cV;
       plasticosDia.setText(""+cP);
       papelesDia.setText(""+cV);

    }

    private void aMaterialTarde() {
        int vT = Integer.parseInt(vidrioTarde.getText().toString());
        int pT = Integer.parseInt(plasticoTarde.getText().toString());
        plasticosTarde.setText(""+pT);
        vidriosTarde.setText(""+vT);
    }

    private void aMaterialNoche() {
        int vN = Integer.parseInt(vidrioNoche.getText().toString());
        int pN = Integer.parseInt(plasticoNoche.getText().toString());
        vidriosNoche.setText(""+vN);
        plasticosNoche.setText(""+pN);
    }
    private void consultarListaMateriales()
    {
        SQLiteDatabase db = conexion.getReadableDatabase();
        materialVo material = null;
        Cursor cursor =db.rawQuery("SELECT * FROM "+ Utilidadess.TABLA_MATERIAL,null);
        while (cursor.moveToNext())
        {
            material = new materialVo();
            material.setNombreMaterial(cursor.getString(0));
            material.setTipoMaterial(cursor.getString(1));
            material.setCantidadMaterial(cursor.getInt(2));

            listaMateriales.add(material);
        }
    }
    private void llenarMateriales(){

        listaMateriales.add(new materialVo(1,1,"plastico",2,"plastico"));
        listaMateriales.add(new materialVo(2,2,"vidro",3,"vidro"));
        listaMateriales.add(new materialVo(3,3,"papel",1,"papel"));

    }

}