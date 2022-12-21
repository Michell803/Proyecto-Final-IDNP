package co.recyclercontrollerx.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import co.recyclercontrollerx.Clases.vo.materialVo;
//import res.layout.*;
import co.recyclercontrollerx.R;

import java.util.ArrayList;

public class AdaptadorMaterial extends RecyclerView.Adapter<AdaptadorMaterial.ViewHolderMaterial>{

    ArrayList<materialVo> listaMateriales;

    public AdaptadorMaterial(ArrayList<materialVo> listaMateriales) {
        this.listaMateriales = listaMateriales;
    }

    @NonNull
    @Override
    public ViewHolderMaterial onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_material,null,false);
        return new ViewHolderMaterial(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMaterial holder, int position) {
        holder.etiNombreDeMaterial.setText(listaMateriales.get(position).getNombreMaterial());
        holder.etiTipoDeMaterial.setText(listaMateriales.get(position).getTipoMaterial());
        holder.etiCantidadDeMaterial.setText(listaMateriales.get(position).getCantidadMaterial());
    }

    @Override
    public int getItemCount() {
        return listaMateriales.size();
    }

    public class ViewHolderMaterial extends RecyclerView.ViewHolder {

        TextView etiNombreDeMaterial;
        TextView etiTipoDeMaterial;
        TextView etiCantidadDeMaterial;

        public ViewHolderMaterial(@NonNull View itemView) {
            super(itemView);
            etiNombreDeMaterial = itemView.findViewById(R.id.textDocumento);
            etiTipoDeMaterial = itemView.findViewById(R.id.textNombre);
            etiCantidadDeMaterial = itemView.findViewById(R.id.textTelefono);
        }
    }
}
