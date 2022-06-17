package com.grupo3.confido.usercase.recomendations;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.grupo3.confido.R;
import com.grupo3.confido.usercase.recomendations.info.FragmentInfo;

public class FragmentRecomendations extends Fragment implements FragmentChange {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_recomendations, container, false);

        Button btninfo = root.findViewById(R.id.btnInformation);
        btninfo.setOnClickListener(view -> goToNewFragment());

        return root;
    }



    @Override
    public void goToNewFragment() {
        //requireActivity() --> Metodo que nos permite hacer referencia a la actividad actual y se asegura que dicha referencia no sea nulo.
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);

        //                  replace(ID del fragmento a reemplazar (Actual), Nombre de la clase del fragmento a mostrar, null)
        fragmentTransaction.replace(R.id.fragment_recomend, FragmentInfo.class, null);

        fragmentTransaction.commit();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}