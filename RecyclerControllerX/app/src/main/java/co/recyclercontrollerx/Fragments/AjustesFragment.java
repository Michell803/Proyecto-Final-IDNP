package co.recyclercontrollerx.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.recyclercontrollerx.R;

public class AjustesFragment extends PreferenceFragmentCompat {


    public AjustesFragment() {
    }

    public void onCreatePreferences( Bundle bundle,  String key)
    {
        setPreferencesFromResource(R.xml.preferencias,key);
    }



}