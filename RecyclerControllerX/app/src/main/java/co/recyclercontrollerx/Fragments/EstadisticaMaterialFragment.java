package co.recyclercontrollerx.Fragments;

import static co.recyclercontrollerx.Fragments.RegistroMaterialFragment.plasticosDia;
import static co.recyclercontrollerx.Fragments.RegistroMaterialFragment.plasticosTarde;
import static co.recyclercontrollerx.Fragments.RegistroMaterialFragment.plasticosNoche;

import static co.recyclercontrollerx.Fragments.RegistroMaterialFragment.vidriosDia;
import static co.recyclercontrollerx.Fragments.RegistroMaterialFragment.vidriosTarde;
import static co.recyclercontrollerx.Fragments.RegistroMaterialFragment.vidriosNoche;

import static co.recyclercontrollerx.Fragments.RegistroMaterialFragment.papelesDia;
import static co.recyclercontrollerx.Fragments.RegistroMaterialFragment.papelesTarde;
import static co.recyclercontrollerx.Fragments.RegistroMaterialFragment.papelesNoche;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

import co.recyclercontrollerx.R;


public class EstadisticaMaterialFragment extends Fragment {
    PieChart pieChart;

    ArrayList<String> valoresX = new ArrayList<>();
    ArrayList<Entry> valoresY = new ArrayList<>();
    ArrayList<Integer> colores = new ArrayList<>();


    public EstadisticaMaterialFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_estadistica_material, container, false);

        pieChart = view.findViewById(R.id.pcGrafica);

        pieChart.setHoleRadius(40f);
        pieChart.setDrawXValues(true);
        pieChart.setDrawYValues(true);
        pieChart.setRotationEnabled(true);
        pieChart.animateXY(1500, 1500);


        valoresX.add("Plásticos");
        valoresX.add("Vidrios");
        valoresX.add("Papeles");

        RegistroMaterialFragment re;
        int plasticos = Integer.parseInt(plasticosDia.getText().toString()) + Integer.parseInt(plasticosTarde.getText().toString()) + Integer.parseInt(plasticosNoche.getText().toString());
        int vidrios = Integer.parseInt(vidriosDia.getText().toString()) + Integer.parseInt(vidriosTarde.getText().toString()) + Integer.parseInt(vidriosNoche.getText().toString());
        int papeles = Integer.parseInt(papelesDia.getText().toString()) + Integer.parseInt(papelesTarde.getText().toString()) + Integer.parseInt(papelesNoche.getText().toString());



        valoresY.add(new Entry(plasticos, 0));
        valoresY.add(new Entry(vidrios, 1));
        valoresY.add(new Entry(papeles, 2));


        colores.add(getResources().getColor(R.color.colorVinotinto));
        colores.add(getResources().getColor(R.color.colorAzul));
        colores.add(getResources().getColor(R.color.colorAmarillo));


        PieDataSet set = new PieDataSet(valoresY, "Resultados");
        set.setSliceSpace(5f);
        set.setColors(colores);

        PieData data = new PieData(valoresX, set);
        pieChart.setData(data);
        pieChart.highlightValues(null);
        pieChart.invalidate();


        pieChart.setDescription("Registro de compras y ventas");


        pieChart.setDrawLegend(true);
        return view;
    }
}