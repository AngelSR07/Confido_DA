package com.grupo3.confido.usercase.recomendations.info;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.grupo3.confido.R;
import com.grupo3.confido.usercase.recomendations.FragmentChange;
import com.grupo3.confido.usercase.recomendations.FragmentRecomendations;
import com.grupo3.confido.usercase.recomendations.FragmentViewDate;
import com.grupo3.confido.usercase.recomendations.info.dialog_info_container.FragmentInfoDialogBox;
import com.grupo3.confido.usercase.recomendations.info.dialog_welcome_info.FragmentInfoWelcome;

import java.util.ArrayList;


public class FragmentInfo extends Fragment implements FragmentViewDate, FragmentChange {

    AdapterFragmentInfo adapterFragmentInfo;
    RecyclerView recyclerViewInfo;
    GridLayoutManager gridLayoutManager;
    ArrayList<Info> listInfo;
    ArrayList<String> listaDesc;

    FragmentInfoWelcome dialogWelcome;
    FragmentInfoDialogBox fragInfoDialogInfo;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragInfoDialogInfo = new FragmentInfoDialogBox();
        viewDialogWelcome();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recomendations_info, container, false);

        recyclerViewInfo = view.findViewById(R.id.recyclerView_info);

        listInfo = new ArrayList<>();
        listaDesc = new ArrayList<>();

        //Cargar lista
        loadList();

        //Mostrar datos
        viewList();

        Button btnBack = view.findViewById(R.id.btnBackViewInfo);

        btnBack.setOnClickListener(View -> goToNewFragment());

        return view;
    }



    @Override
    public void goToNewFragment() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.replace(R.id.fragment_recomend_info, FragmentRecomendations.class, null);

        fragmentTransaction.remove(FragmentInfo.this).commit();
    }



    private void viewDialogWelcome(){
        dialogWelcome = new FragmentInfoWelcome();

        dialogWelcome.viewDialogWelcome(getContext(), getLayoutInflater());
    }



    @Override
    public void loadList(){

        loadDesc();

        listInfo.add(new Info("Feminicidio",R.drawable.info_feminicidio,listaDesc.get(0)));
        listInfo.add(new Info("Hostigamiento laboral",R.drawable.info_hostigamiento,listaDesc.get(1)));
        listInfo.add(new Info("Machismo",R.drawable.info_machismo,listaDesc.get(2)));
        listInfo.add(new Info("Tocamientos indebidos",R.drawable.info_tocamiento_indevido,listaDesc.get(3)));
        listInfo.add(new Info("Acoso sexual callejero",R.drawable.info_acoso,listaDesc.get(4)));
        listInfo.add(new Info("Cosificaci??n",R.drawable.info_cosificacion,listaDesc.get(5)));
        listInfo.add(new Info("Violencia psicol??gica",R.drawable.info_psicologico,listaDesc.get(6)));
        listInfo.add(new Info("Violaci??n sexual",R.drawable.info_sexual,listaDesc.get(7)));
        listInfo.add(new Info("Paridad",R.drawable.info_paridad,listaDesc.get(8)));
    }

    private void loadDesc(){
        //Feminicidio
        listaDesc.add("Es el acto de matar a una mujer por el solo hecho de serlo, es decir, por razones vinculadas a su g??nero.\n" +
                "\n" +
                "La persona que comete este tipo de actos tiene la creencia de que la mujer debe de ser: Sumisa, obediente, servicial, pura y delicada. Por lo que, cuando la victima incumple este rol, est?? agrede a dicha persona como forma de castigo.\n" +
                "\n" +
                "Este acto es sancionado en el art??culo 108-B del C??digo Penal.");


        //Hostigamiento laboral
        listaDesc.add("Es una forma de violencia cuya naturaleza parte de una conducta sexual o sexista no deseada, lo que, genera un ambiente laboral hostil o humillante.\n" +
                "\n" +
                "Preguntas como \"??Tu novio no se pondr?? celoso si nos tomamos una foto juntos? ??No?\" evidencia la presencia de este tipo de violencia en su ambiente laboral.\n" +
                "\n" +
                "Este puede ocurrir entre jefe/a hacia un trabajador/a, o entre trabajadores.\n" +
                "\n" +
                "Esta se encuentra penada por la ley en el art??culo 151 A del C??digo Penal.");


        //Machismo
        listaDesc.add("Es la actitud de prepotencia de los varones con respecto a las mujeres.\n" +
                "\n" +
                "Es una forma de organizar las relaciones entre los generos cuya idea es que el hombre es superior a la mujer.\n" +
                "\n" +
                "Se caracteriza por la distribuci??n desigualitaria de los ejercicios de poder sobre otros u otras.");


        //Tocamientos indebidos
        listaDesc.add("Es el acto de tocar las partes intimas o cualquier parte del cuerpo sin el concentimiento de la persona .\n" +
                "\n" +
                "Est?? se encuentra regulado como un delito en el art??culo 176 del C??digo Penal.");


        //Acoso sexual callejero
        listaDesc.add("Es una forma de acoso sexual, la cual, consiste en la conducta verbal o f??sica de connotaci??n sexual realizada por una persona desconocida en espacios p??blicos.\n" +
                "\n" +
                "Este es un tipo de delito tipificado en el art??culo 176-B del C??digo Penal.");


        //Cosificaci??n
        listaDesc.add("Es el acto de representar o tratar a una persona como objeto no pensante que solo sirve para satisfacer los deseos de otros.");


        //Violencia psicol??gica
        listaDesc.add("Es la acci??n o conducta que busca controlar o aislar a la persona contra su voluntad, a humillarla o avergonzarla, la cual, puede ocasionar da??os ps??cologicos.");


        //Violaci??n Sexual
        listaDesc.add("Es el acto sexual que se comete sin el consentimiento, con violencia f??sica, psicol??gcia, grave amenaza o aprovech??ndose de un contexto de coacci??n.\n" +
                "\n" +
                "Basta con que la v??citima diga \"no\" para probar que no hay consentimiento.\n" +
                "\n" +
                "Est?? acci??n se encuentra sancionada en el art??culo 170 del C??digo Penal.");


        //Paridad
        listaDesc.add("Es la estrategia pol??tica que busca garantizar una participaci??n efectiva del 50% de mujeres en todos los ??mbitos de la sociedad, particularmente en los espacios de toma de decisiones.");
    }



    @Override
    public void viewList(){
        recyclerViewInfo.setLayoutManager(new LinearLayoutManager(getContext()));

        adapterFragmentInfo = new AdapterFragmentInfo(getContext(),listInfo);

        //GridLayoutManager(contexto, n??m de columnas del RecyclerView, Horientaci??n, Invertir layout (false | true))
        gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);

        recyclerViewInfo.setLayoutManager(gridLayoutManager);
        recyclerViewInfo.setAdapter(adapterFragmentInfo);

        adapterFragmentInfo.setOnClickListener(view -> setOnClickListenerItem(view));
    }



    @Override
    public void setOnClickListenerItem(View view){
        String nomInfo = listInfo.get(recyclerViewInfo.getChildAdapterPosition(view)).getNomInfo();
        String descripInfo = listInfo.get(recyclerViewInfo.getChildAdapterPosition(view)).getDescription();

        fragInfoDialogInfo.viewDialogInfo(getContext(), getLayoutInflater(), nomInfo, descripInfo);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}