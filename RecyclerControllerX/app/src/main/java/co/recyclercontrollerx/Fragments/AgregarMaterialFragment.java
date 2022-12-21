package co.recyclercontrollerx.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.recyclercontrollerx.Interfaces.IComunicaAppPrincipal;
import co.recyclercontrollerx.R;

public class AgregarMaterialFragment extends Fragment {


    View vista;
    Activity actividad;
    CardView plastico, papel,vidrio, organico;
    IComunicaAppPrincipal iComunicaAppPrincipal;
    public AgregarMaterialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_agregar_material,container,false);

        plastico= vista.findViewById(R.id.cardPlastico);
        papel= vista.findViewById(R.id.cardPapel);
        vidrio= vista.findViewById(R.id.cardVidrio);
        organico= vista.findViewById(R.id.cardOrganico);
        eventosMenu();
        return vista;
    }
    public void eventosMenu()
    {
        plastico.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                iComunicaAppPrincipal.crearPlastico();
            }
        });
        papel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                iComunicaAppPrincipal.crearPapel();
            }
        });
        vidrio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                iComunicaAppPrincipal.crearVidrio();
            }
        });
        organico.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                iComunicaAppPrincipal.crearOrganico();
            }
        });
    }
    public void  onAttach (Context context)
    {
        super.onAttach(context);
        if (context instanceof Activity)
        {
            actividad= (Activity) context;
            iComunicaAppPrincipal =(IComunicaAppPrincipal) actividad;
        }
    }
}