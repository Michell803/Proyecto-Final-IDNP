package co.recyclercontrollerx.adapters;

import co.recyclercontrollerx.Clases.Utilidadess;
import co.recyclercontrollerx.Clases.vo.avatarvo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import co.recyclercontrollerx.R;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorAvatar extends RecyclerView.Adapter<AdaptadorAvatar.ViewHolderAvatar> implements View.OnClickListener{

    private View.OnClickListener listener;
    List<avatarvo> listaAvatars;
    View vista;

    int posicionMarcada=0;

    public AdaptadorAvatar(List<avatarvo> listaAvatars  ) {
        this.listaAvatars = listaAvatars;
    }

    @NonNull
    @Override
    public ViewHolderAvatar onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        vista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grid_avatar,viewGroup,false);
        return new ViewHolderAvatar(vista);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolderAvatar viewHolderAvatar, int i) {

        viewHolderAvatar.imgAvatar.setImageResource(listaAvatars.get(i).getAvatarId());
        final int pos=i;

        viewHolderAvatar.cardAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicionMarcada=pos;
                Utilidadess.avatarSeleccion=listaAvatars.get(pos);
                Utilidadess.avatarIdSeleccion=pos+1;
                notifyDataSetChanged();

            }
        });
        if (Utilidadess.avatarIdSeleccion==0)
        {
            if (posicionMarcada==i)
            {
                viewHolderAvatar.barraSeleccion.setBackgroundColor(vista.getResources().getColor(R.color.colorPrimaryDark));
            }
            else
            {
                viewHolderAvatar.barraSeleccion.setBackgroundColor(vista.getResources().getColor(R.color.colorBlanco));

            }
        }
        else
        {
            if (Utilidadess.avatarIdSeleccion -1 ==pos)
            {
                viewHolderAvatar.barraSeleccion.setBackgroundColor(vista.getResources().getColor(R.color.colorPrimaryDark));
            }
            else
            {
                viewHolderAvatar.barraSeleccion.setBackgroundColor(vista.getResources().getColor(R.color.colorBlanco));
            }
        }

    }

    @Override
    public int getItemCount() {
        return listaAvatars.size();
    }

    @Override
    public void onClick(View v) {

    }


    public class ViewHolderAvatar extends RecyclerView.ViewHolder {

        CardView cardAvatar;
        ImageView imgAvatar;
        TextView barraSeleccion;

        public ViewHolderAvatar(@NonNull View itemView) {
            super(itemView);
            cardAvatar=itemView.findViewById(R.id.cardAvatar);
            imgAvatar=itemView.findViewById(R.id.idAvatar);
            barraSeleccion=itemView.findViewById(R.id.barraSeleccionId);

        }

    }
}
