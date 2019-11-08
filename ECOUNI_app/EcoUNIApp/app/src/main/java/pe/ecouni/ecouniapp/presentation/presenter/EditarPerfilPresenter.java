package pe.ecouni.ecouniapp.presentation.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

import pe.ecouni.ecouniapp.coneccion.RestApi;
import pe.ecouni.ecouniapp.coneccion.RestApiImpl;
import pe.ecouni.ecouniapp.presentation.model.FotoUsuario;
import pe.ecouni.ecouniapp.presentation.model.Usuario;

public class EditarPerfilPresenter {
    private Context context;
    private EditarPerfilView view;

    public EditarPerfilPresenter(Context context, EditarPerfilView view){
        this.context = context;
        this.view = view;
    }

    public void mostrar(Usuario usuario){
        if (usuario==null)
            usuario = Usuario.cargar(context);
        view.mostrar(usuario);
    }

    public void guardar(Usuario usuario,boolean modFoto,boolean eliminar,Bitmap bitmap){
        UpdatePerfilTask task = new UpdatePerfilTask(usuario,modFoto,eliminar,bitmap);
        task.execute();
    }

    private String codificar(Bitmap foto){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        foto.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] b = stream.toByteArray();
        return Base64.encodeToString(b,Base64.DEFAULT);
    }



    public class UpdatePerfilTask extends AsyncTask<Void,Usuario,Usuario>{
        private Bitmap bitmap;
        private Usuario usuario;
        private RestApi api;
        private boolean eliminar;
        private boolean modFoto;

        public UpdatePerfilTask(Usuario usuario,boolean modFoto,boolean eliminar,Bitmap bitmap){
            this.usuario = usuario;
            this.modFoto = modFoto;
            this.eliminar = eliminar;
            this.bitmap = bitmap;
            api  = new RestApiImpl(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            view.showProgress();
        }

        @Override
        protected Usuario doInBackground(Void... voids) {
            if(modFoto){
                FotoUsuario fotoUsuario = new FotoUsuario();
                fotoUsuario.setCodigo(usuario.getCodigo());
                if(!eliminar){
                    fotoUsuario.setBase64Thumbnail(codificar(bitmap));
                }
                FotoUsuario res = null;
                try{
                    res = api.updatePerfilFoto(fotoUsuario);
                }catch (Exception e){
                    Log.e("GUARDAR","error en subir foto",e);
                    e.printStackTrace();
                    cancel(true);
                }
                if(res==null) {
                    Log.e("GUARDAR","error res nulo");
                    cancel(true);
                }
            }
            Usuario usu = null;
            try {
                usu = api.updatePerfil(usuario);
            } catch (Exception e) {
                Log.e("GUARDAR","error en guardar perfil",e);
                e.printStackTrace();
                cancel(true);
            }
            if(usu==null) {
                Log.e("GUARDAR","error nulo res");
                cancel(true);
            }
            return usu;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            view.hideProgress();
            view.showToast("Error en guardar");
            view.salir();
        }

        @Override
        protected void onPostExecute(Usuario usu) {
            super.onPostExecute(usu);
            Usuario.guardar(context,usu);
            view.hideProgress();
            view.salirExito();
        }
    }

    public interface EditarPerfilView {
        void mostrar(Usuario usuario);
        void showProgress();
        void hideProgress();
        void showToast(String msg);
        void salir();
        void salirExito();
    }
}
