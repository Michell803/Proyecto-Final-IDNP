package co.recyclercontrollerx.Actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import co.recyclercontrollerx.Fragments.AgregarMaterialFragment;
import co.recyclercontrollerx.Fragments.EstadisticaMaterialFragment;
import co.recyclercontrollerx.Fragments.OrganicoFragment;
import co.recyclercontrollerx.Fragments.PapelFragment;
import co.recyclercontrollerx.Fragments.PlasticoFragment;
import co.recyclercontrollerx.Fragments.RegistroMaterialFragment;
import co.recyclercontrollerx.Fragments.VidrioFragment;
import co.recyclercontrollerx.Interfaces.IComunicaAppPrincipal;
import co.recyclercontrollerx.R;

public class PrincipalAppActivity extends AppCompatActivity implements IComunicaAppPrincipal {
     //nivel1
     private BottomNavigationView bottomNavigationView1;
     FragmentTransaction transaction;
     Fragment  fragmentAgregar, fragmentRegistro, fragmentEstadistica, fragmentPlastico, fragmentPapel
             ,fragmentVidrio, fragmentOrganico;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_app);

        bottomNavigationView1=findViewById(R.id.bottomNavView);
        fragmentAgregar= new AgregarMaterialFragment();
        fragmentRegistro= new RegistroMaterialFragment();
        fragmentEstadistica= new EstadisticaMaterialFragment();
        /*fragmentVidrio= new VidrioFragment();
        fragmentPapel= new PapelFragment();
        fragmentOrganico= new OrganicoFragment();
        fragmentPlastico= new PlasticoFragment(); */


        getSupportFragmentManager().beginTransaction().add(R.id.frameContenedor,fragmentRegistro).commit();

        bottomNavigationView1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.menuFragmento1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameContenedor,new AgregarMaterialFragment()).commit();
                        return true;
                    case R.id.menuFragmento2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameContenedor,new RegistroMaterialFragment()).commit();
                        return true;

                    case R.id.menuFragmento3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameContenedor,new EstadisticaMaterialFragment()).commit();
                        return true;

                }
                return false;
            }
        });



    }

    @Override
    public void mostrarCategorias()
    {
        getSupportFragmentManager().beginTransaction().add(R.id.frameContenedor,fragmentRegistro).commit();

        bottomNavigationView1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.menuFragmento1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameContenedor,new AgregarMaterialFragment()).commit();
                        return true;
                    case R.id.menuFragmento2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameContenedor,new RegistroMaterialFragment()).commit();
                        return true;

                    case R.id.menuFragmento3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameContenedor,new EstadisticaMaterialFragment()).commit();
                        return true;

                }
                return false;
            }
        });
    }

    @Override
    public void crearPlastico()
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContenedor,new PlasticoFragment()).commit();

    }

    @Override
    public void crearVidrio() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContenedor,new VidrioFragment()).commit();

    }

    @Override
    public void crearPapel() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContenedor,new PapelFragment()).commit();

    }

    @Override
    public void crearOrganico() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContenedor,new OrganicoFragment()).commit();

    }

    public void MostrarTodo()
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContenedor,new RegistroMaterialFragment()).commit();
    }
    public void rellenarMaterial()
    {

    }
}