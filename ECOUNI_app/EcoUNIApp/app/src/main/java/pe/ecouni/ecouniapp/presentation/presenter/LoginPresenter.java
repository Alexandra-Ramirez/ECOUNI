package pe.ecouni.ecouniapp.presentation.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pe.ecouni.ecouniapp.coneccion.RestApi;
import pe.ecouni.ecouniapp.coneccion.RestApiImpl;
import pe.ecouni.ecouniapp.presentation.model.Usuario;

public class LoginPresenter {

    private Context context;
    private LoginView view;
    private static String CODIGO_PATTERN = "[0-9]{8}[a-zA-Z]";

    public LoginPresenter(Context context,LoginView view){
        this.context = context;
        this.view = view;
    }

    public void ingresar(String codigo,String pass,boolean recordar){
        if (isValidLogin(codigo,pass)){
            try {
                LoginTask loginTask = new LoginTask(codigo,pass,recordar);
                loginTask.execute();
            } catch (Exception e) {
                view.showToast(e.getMessage());
            }
        }
    }

    public void llenar(){
        if(Usuario.isRecordar(context)){
            Usuario usu = Usuario.cargar(context);
            view.llenar(usu.getCodigo());
        }
    }

    private boolean isValidLogin(String codigo,String pass){
        view.clearError();
        boolean valido = true;
        if (codigo.isEmpty()){
            view.errorCod("Ingrese su código");
            valido = false;
        }else if(!isValidCodigo(codigo)){
            view.errorCod("Codigo inválido");
            valido = false;
        }
        if(pass.isEmpty()){
            view.errorPass("Ingrese su contraseña");
            valido = false;
        }

        return valido;
    }

    private boolean isValidCodigo(String codigo){
        Pattern pattern = Pattern.compile(CODIGO_PATTERN);
        Matcher matcher = pattern.matcher(codigo);
        return matcher.matches();
    }

    public String computeHash(String input) {
        //return org.apache.commons.codec.digest.DigestUtils.sha256Hex(input);
        return new String(Hex.encodeHex(DigestUtils.sha256(input)));
    }


    private class LoginTask extends AsyncTask<Void,Usuario,Usuario>{

        private RestApi api;
        private Usuario usuLogin;
        private boolean recordar;
        public LoginTask(String codigo,String pass,boolean recordar) throws Exception{
            api = new RestApiImpl(context);
            this.recordar = recordar;

            usuLogin = new Usuario();
            usuLogin.setCodigo(codigo);
            usuLogin.setPass(computeHash(pass));
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            view.showProgress();
        }

        @Override
        protected Usuario doInBackground(Void... voids) {
            Usuario usuario = null;
            try {
                usuario = api.login(usuLogin);

            } catch (Exception e) {
                Log.e("LoginError",e.getMessage(),e);
                cancel(true);
            }
            if(usuario==null) cancel(true);
            return usuario;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            view.hideProgress();
            view.showToast("Codigo o contraseña inválida");
            view.clear();
        }

        @Override
        protected void onPostExecute(Usuario usuario) {
            super.onPostExecute(usuario);
            view.hideProgress();
            Usuario.guardar(context,usuario);
            Usuario.recordar(context,recordar);
            view.goMenu();
        }
    }

    public interface LoginView{
        void goMenu();
        void llenar(String codigo);
        void errorCod(String error);
        void errorPass(String error);
        void clearError();
        void showProgress();
        void hideProgress();
        void clear();
        void showToast(String msg);
    }
}
