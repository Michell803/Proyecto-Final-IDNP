package co.recyclercontrollerx.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.recyclercontrollerx.Clases.Utilidadess;
import co.recyclercontrollerx.Clases.vo.JugadorVo;
import co.recyclercontrollerx.Clases.vo.avatarvo;
import co.recyclercontrollerx.R;

public class AdaptadorJugador extends RecyclerView.Adapter<AdaptadorJugador.ViewHolderJugador> implements View.OnClickListener{

    private View.OnClickListener listener;
    List<JugadorVo> listaJugadores;
    View vista;

    int posicionMarcada=0;

    public AdaptadorJugador(List<JugadorVo> listaJugadores )
    {

        this.listaJugadores = listaJugadores;
    }

    @NonNull
    @Override
    public ViewHolderJugador onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        vista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_jugador,viewGroup,false);
        vista.setOnClickListener(this);
        return new ViewHolderJugador(vista);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolderJugador viewHolderJugador, int i) {

        viewHolderJugador.imgAvatar.setImageResource(Utilidadess.listaAvatars.get(listaJugadores.get(i).getAvatar()-1).getAvatarId());
        viewHolderJugador.txtNombre.setText(listaJugadores.get(i).getNombre());

        if (listaJugadores.get(i).getGenero().equals("M"))
        {
            viewHolderJugador.txtGenero.setText("Masculino");
        }
        else
        {
            viewHolderJugador.txtGenero.setText("Femenino");

        }


    }

    @Override
    public int getItemCount() {
        return listaJugadores.size();
    }
    public void setOnClickListener(View.OnClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onClick(View view)
    {
        if(listener!=null){
            listener.onClick(view);
        }
    }


    public class ViewHolderJugador extends RecyclerView.ViewHolder {

        ImageView imgAvatar;
        TextView txtNombre,txtGenero;

        public ViewHolderJugador(@NonNull View itemView) {
            super(itemView);
            imgAvatar=itemView.findViewById(R.id.idAvatar);
            txtNombre=itemView.findViewById(R.id.idNombre);
            txtGenero=itemView.findViewById(R.id.idGenero);


        }

    }
}
