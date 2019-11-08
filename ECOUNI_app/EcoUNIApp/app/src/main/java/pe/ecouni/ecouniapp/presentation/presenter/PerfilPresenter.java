package pe.ecouni.ecouniapp.presentation.presenter;

import android.content.Context;

import pe.ecouni.ecouniapp.presentation.model.Usuario;

public class PerfilPresenter {
    private Context context;
    private PerfilView view;

    public PerfilPresenter(Context context, PerfilView view){
        this.context = context;
        this.view = view;
    }

    public void mostrar(){
        Usuario usuario = Usuario.cargar(context);
        view.mostrar(usuario);
    }

    public interface PerfilView{
        void mostrar(Usuario usuario);
    }
}
