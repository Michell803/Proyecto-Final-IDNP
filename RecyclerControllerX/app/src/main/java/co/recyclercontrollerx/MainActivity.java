package co.recyclercontrollerx;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import co.recyclercontrollerx.Actividades.AjustesActivity;
import co.recyclercontrollerx.Clases.ConexionSQLiteHelper;
import co.recyclercontrollerx.Clases.PreferenciasJuego;
import co.recyclercontrollerx.Clases.Utilidadess;
import co.recyclercontrollerx.Dialogos.DialogoTipoJuegoFragment;
import co.recyclercontrollerx.Fragments.GestionJugadorFragment;
import co.recyclercontrollerx.Fragments.RegistroJugadorFragment;
import co.recyclercontrollerx.Fragments.inicioFragment;
import co.recyclercontrollerx.Interfaces.IComunicaFragments;

public class MainActivity extends AppCompatActivity implements IComunicaFragments {

    FragmentTransaction transaction;
    Fragment fragmentInicio, registroJugadorFragment, gestionJugadorFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferenceManager.setDefaultValues(this,R.xml.preferencias, false);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        PreferenciasJuego.obtenerPreferencias(preferences,this);


        Utilidadess.obtenerListaAvatars();
        Utilidadess.consultarListaJugadores(this);

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,Utilidadess.NOMBRE_BD,null,1);

        registroJugadorFragment = new RegistroJugadorFragment();
        gestionJugadorFragment= new GestionJugadorFragment();
        fragmentInicio= new inicioFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentInicio).commit();
    }


    public AlertDialog dialogoGestionUsuario()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Gestionar Perfiles").setMessage("Indique si desea añadir un nuevo perfil"+
                "o si desea escoger uno ya existente, \n \n"+
                "Tambien podra modificar algun perfil presionando SELECCIONAR")
                .setNegativeButton("REGISTRAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(),"REGISTRAR PERFIL",Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,registroJugadorFragment).commit();

                    }
                }).setPositiveButton("SELECCIONAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,gestionJugadorFragment).commit();


                    }
                });

        return builder.create();
    }
    @Override
    public void mostrarMenu() {
        Utilidadess.obtenerListaAvatars();//llena la lista
        Utilidadess.consultarListaJugadores(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentInicio).commit();
    }

    @Override
    public void iniciarJuego()
    {
        //Toast.makeText(getApplicationContext(),"iniciar uso",Toast.LENGTH_SHORT).show();;


       if (Utilidadess.listaJugadores!=null && Utilidadess.listaJugadores.size()>0)
        {
            DialogoTipoJuegoFragment dialogoTipoJuego=new DialogoTipoJuegoFragment();
            dialogoTipoJuego.show(getSupportFragmentManager(),"DialogoTipoJuego");
        }
       else
       {
            Toast.makeText(getApplicationContext(),"Debe registrar un Perfil primero",Toast.LENGTH_SHORT).show();
       }
    }
    @Override

    public void llamarAjustes() {
        Intent intent = new Intent(this, AjustesActivity.class);
        startActivity(intent);

    }

    @Override
    public void consultarRanking() {
        Toast.makeText(getApplicationContext(),"iniciar ranking",Toast.LENGTH_SHORT).show();;

    }

    @Override
    public void consultarInstrucciones() {
        Toast.makeText(getApplicationContext(),"iniciar instrucciones",Toast.LENGTH_SHORT).show();;

    }

    @Override
    public void gestionarUsuario() {
        dialogoGestionUsuario().show();
    }

    @Override
    public void consultarInformacion() {
        Toast.makeText(getApplicationContext(),"iniciar info",Toast.LENGTH_SHORT).show();;

    }
}