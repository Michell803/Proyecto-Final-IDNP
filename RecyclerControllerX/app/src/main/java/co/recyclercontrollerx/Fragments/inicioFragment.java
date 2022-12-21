package co.recyclercontrollerx.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import co.recyclercontrollerx.Clases.PreferenciasJuego;
import co.recyclercontrollerx.Clases.Utilidadess;
import co.recyclercontrollerx.Interfaces.IComunicaFragments;
import co.recyclercontrollerx.R;

public class inicioFragment extends Fragment
{

    View vista;
    Activity actividad;
    CardView cardJugar, cardRanking, cardAyuda, cardAjustes, cardUser,cardInfo;
    IComunicaFragments interfaceComunicaFragments;
    TextView textNickName;
    ImageView imageAvatar;

    public inicioFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        vista = inflater.inflate(R.layout.fragment_inicio,container,false);

        cardJugar= vista.findViewById(R.id.cardJugar);
        cardAjustes= vista.findViewById(R.id.cardAjustes);
        cardAyuda= vista.findViewById(R.id.cardAyuda);
        cardInfo= vista.findViewById(R.id.cardInfo);
        cardRanking= vista.findViewById(R.id.cardRanking);
        cardUser= vista.findViewById(R.id.cardUser);
        textNickName = vista.findViewById(R.id.textNickName);
        imageAvatar= vista.findViewById(R.id.avatarImage);

        textNickName.setText(PreferenciasJuego.nickName);
        if (PreferenciasJuego.avatarId == 1)
        {
            imageAvatar.setImageResource(R.drawable.recycler);
        }
        else
        {
            imageAvatar.setImageResource(Utilidadess.listaAvatars.get(PreferenciasJuego.avatarId-1).getAvatarId());
        }
        eventosMenu();


        return vista;
    }
    private  void eventosMenu()
    {
        cardJugar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                interfaceComunicaFragments.iniciarJuego();
            }
        });
        cardRanking.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                interfaceComunicaFragments.consultarRanking();
            }
        });
        cardUser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                interfaceComunicaFragments.gestionarUsuario();
            }
        });
        cardInfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                interfaceComunicaFragments.consultarInformacion();
            }
        });
        cardAyuda.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                interfaceComunicaFragments.consultarInstrucciones();
            }
        });
        cardAjustes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                interfaceComunicaFragments.llamarAjustes();
            }
        });
    }
    public void  onAttach (Context context)
    {
        super.onAttach(context);
        if (context instanceof Activity)
        {
            actividad= (Activity) context;
            interfaceComunicaFragments =(IComunicaFragments) actividad;
        }
    }

}