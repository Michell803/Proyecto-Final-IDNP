package co.recyclercontrollerx.Dialogos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import co.recyclercontrollerx.Actividades.PrincipalAppActivity;
import co.recyclercontrollerx.Clases.PreferenciasJuego;
import co.recyclercontrollerx.Clases.Utilidadess;
import co.recyclercontrollerx.Interfaces.IComunicaFragments;
import co.recyclercontrollerx.R;

public class DialogoTipoJuegoFragment extends DialogFragment {


    Activity actividad;
    IComunicaFragments iComunicaFragments;

    ImageButton btnSalir;
    LinearLayout barraSuperior;
    CardView cardFacil, cardMedio;


    public DialogoTipoJuegoFragment() {
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return crearDialogoGestionarJuego();
    }

    private AlertDialog crearDialogoGestionarJuego() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.fragment_dialogo_tipo_juego, null);
        builder.setView(v);


        barraSuperior = v.findViewById(R.id.barraSuperiorId);
        // barraSuperior.setBackgroundColor(getResources().getColor(PreferenciasJuego.colorTema));

        btnSalir = v.findViewById(R.id.btnSalir);

        cardFacil = v.findViewById(R.id.cardFacil);
        cardMedio = v.findViewById(R.id.cardMedio);

        eventosBotones();
        return builder.create();
    }

    private void eventosBotones() {
        cardFacil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoInstruccionesNivelFacil().show();
            }
        });

        cardMedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogoInstruccionesNivelMedio().show();
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public AlertDialog dialogoInstruccionesNivelFacil() {

        AlertDialog.Builder builder = new AlertDialog.Builder(actividad);

        builder.setTitle("OPCION 1")
                .setMessage("Se presentara el panel de contro, en el podra registrar los materiales de residuos que utiliza durante el dia.")
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                .setPositiveButton("INGRESAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(actividad, "Primera Opcion", Toast.LENGTH_SHORT).show();
                                 //Utilidadess.nivelJuego=1;
                                 Intent intent=new Intent(actividad, PrincipalAppActivity.class);
                                 startActivity(intent);
                                 dismiss();
                            }
                        });

        return builder.create();
    }

    public AlertDialog dialogoInstruccionesNivelMedio() {
        AlertDialog.Builder builder = new AlertDialog.Builder(actividad);

        builder.setTitle("OPCION 2")
                .setMessage("Se presentará el menu que le permitira crear el material que desee. ")
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                .setPositiveButton("INGRESAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(actividad, "Segunda Opcion", Toast.LENGTH_SHORT).show();
                               /* Utilidadess.nivelJuego=2;
                                Intent intent=new Intent(actividad, Nivel2Activity.class);
                                startActivity(intent); */
                                dismiss();
                            }
                        });

        return builder.create();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.actividad = (Activity) context;
            iComunicaFragments = (IComunicaFragments) this.actividad;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

}