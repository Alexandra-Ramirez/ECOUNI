package pe.ecouni.ecouniapp.presentation.presenter;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import pe.ecouni.ecouniapp.coneccion.RestApi;
import pe.ecouni.ecouniapp.coneccion.RestApiImpl;
import pe.ecouni.ecouniapp.presentation.model.RankingItem;

public class InfoPresenter {

    private Context context;
    private InfoView view;

    public InfoPresenter(Context context, InfoView view){
        this.context = context;
        this.view = view;
    }

    public void getRanking(){
        RankingTask task = new RankingTask();
        task.execute();
    }

    public class RankingTask extends AsyncTask<Void,List<RankingItem>,List<RankingItem>>{

        private RestApi api;

        public RankingTask(){
            api = new RestApiImpl(context);
        }

        @Override
        protected List<RankingItem> doInBackground(Void... voids) {

            List<RankingItem> rankingItems = new ArrayList<>();

            try {
                rankingItems = api.getTop10();
            } catch (Exception e) {
                e.printStackTrace();
                cancel(true);
            }
            if(rankingItems==null)
                cancel(true);

            return rankingItems;

        }

        @Override
        protected void onPostExecute(List<RankingItem> rankingItems) {
            super.onPostExecute(rankingItems);
            view.mostrar(rankingItems);
        }
    }

    public interface InfoView {
        void mostrar(List<RankingItem> items);
    }
}
