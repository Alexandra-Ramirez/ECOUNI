package pe.ecouni.ecouniapp.presentation.presenter;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import pe.ecouni.ecouniapp.coneccion.RestApi;
import pe.ecouni.ecouniapp.coneccion.RestApiImpl;
import pe.ecouni.ecouniapp.presentation.model.ReciclajeDiaMes;
import pe.ecouni.ecouniapp.presentation.model.Usuario;

public class ReciclarPresenter {
    private Context context;
    private ReciclarView view;

    public ReciclarPresenter(Context context,ReciclarView view){
        this.context = context;
        this.view = view;
    }

    public void iniciar(){
        Usuario usuario = Usuario.cargar(context);
        view.setTotal(usuario.getCantidad());

        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        view.setMes(month);
        descargar(usuario.getCodigo(),year,month);
    }

    public void mesSelected(int month){
        Usuario usuario = Usuario.cargar(context);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        view.setMes(month);
        descargar(usuario.getCodigo(),year,month);
    }

    private void descargar(String codigo, int year,int month){
        ReciclarHistorialTask task = new ReciclarHistorialTask(codigo,year,month);
        task.execute();
    }

    public class ReciclarHistorialTask extends AsyncTask<Void, List<ReciclajeDiaMes>,List<ReciclajeDiaMes>>{

        private RestApi api;
        private String codigo;
        private int year,month;

        public ReciclarHistorialTask(String codigo,int year, int month){
            this.codigo = codigo;
            this.year = year;
            this.month = month;
            api = new RestApiImpl(context);
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            view.showProgress();
        }

        @Override
        protected List<ReciclajeDiaMes> doInBackground(Void... voids) {
            List<ReciclajeDiaMes> lista = new ArrayList<>();

            try {
                lista = api.recHistorial(codigo,year,month+1);
            } catch (Exception e) {
                cancel(true);
                e.printStackTrace();
            }
            if (lista == null )
                cancel(true);
            return lista;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            view.hideProgress();
            view.showToast("Error en obtener historial");
            view.actualizar(month,new ArrayList<ReciclajeDiaMes>());
        }

        @Override
        protected void onPostExecute(List<ReciclajeDiaMes> reciclajeDiaMes) {
            super.onPostExecute(reciclajeDiaMes);
            view.hideProgress();
            view.actualizar(month,reciclajeDiaMes);
        }
    }

    public interface ReciclarView{
        void showProgress();
        void hideProgress();
        void showToast(String msg);
        void setTotal(int total);
        void actualizar(int month,List<ReciclajeDiaMes> lista);
        void setMes(int month);
    }
}
