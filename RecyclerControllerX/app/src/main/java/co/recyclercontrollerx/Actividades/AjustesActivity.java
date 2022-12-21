package co.recyclercontrollerx.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import co.recyclercontrollerx.Fragments.AjustesFragment;
import co.recyclercontrollerx.R;

import android.os.Bundle;
import android.view.View;

public class AjustesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorAjustes,new AjustesFragment()).commit();
    }
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnIcoAtras:
                finish(); break;
        }
    }
}