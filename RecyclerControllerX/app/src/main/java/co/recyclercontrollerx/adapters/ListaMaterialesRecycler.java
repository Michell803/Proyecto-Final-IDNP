package co.recyclercontrollerx.adapters;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.recyclercontrollerx.Clases.ConexionSQLiteHelper;
import co.recyclercontrollerx.Clases.Utilidadess;
import co.recyclercontrollerx.R;
import co.recyclercontrollerx.Clases.vo.*;
import co.recyclercontrollerx.adapters.AdaptadorMaterial;

public class ListaMaterialesRecycler extends AppCompatActivity {

    ArrayList<materialVo> listMaterial;
    RecyclerView recyclerViewMateriales;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registro_material);

        conn = new ConexionSQLiteHelper(getApplicationContext(),"recycler_bd",null,1);

        /*listMaterial = new ArrayList<>();
        recyclerViewMateriales = findViewById(R.id.recyclerManana);
        recyclerViewMateriales.setLayoutManager(new LinearLayoutManager(this));

        consultarListaMateriales();

        AdaptadorMaterial adapter = new AdaptadorMaterial(listMaterial);
        recyclerViewMateriales.setAdapter(adapter);
        */




    }

    private void consultarListaMateriales() {
        SQLiteDatabase db = conn.getReadableDatabase();

        materialVo material = null;

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidadess.TABLA_MATERIAL,null);

        while (cursor.moveToNext()) {
            material = new materialVo();
            material.setIdMascota(cursor.getInt(0));
            material.setNombreMaterial(cursor.getString(1));
            material.setTipoMaterial(cursor.getString(2));
            material.setCantidadMaterial(Integer.valueOf(cursor.getString(3)));

            listMaterial.add(material);
        }
    }
}
