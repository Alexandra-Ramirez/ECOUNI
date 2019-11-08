package pe.ecouni.ecouniapp.coneccion;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.List;

import pe.ecouni.ecouniapp.R;
import pe.ecouni.ecouniapp.presentation.model.FotoUsuario;
import pe.ecouni.ecouniapp.presentation.model.RankingItem;
import pe.ecouni.ecouniapp.presentation.model.Reciclaje;
import pe.ecouni.ecouniapp.presentation.model.ReciclajeDiaMes;
import pe.ecouni.ecouniapp.presentation.model.Usuario;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiImpl implements RestApi {
    private Context context;
    private ApiService apiService;

    public RestApiImpl(Context context) {
        this.context = context;
        String serverUrl = context.getString(R.string.server_url);
        /* Logging */
        /*HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(serverUrl)
                .addConverterFactory(GsonConverterFactory.create())
                //.client(client) // Logging
                .build();
        this.apiService = retrofit.create(ApiService.class);
    }


    private boolean hayInternet() {
        boolean hayInternet;
        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        hayInternet = (networkInfo != null && networkInfo.isConnectedOrConnecting());
        return hayInternet;
    }


    @Override
    public Usuario login(Usuario usuLogin) throws Exception {
        if (hayInternet()) {
            Call<Usuario> call = apiService.login(usuLogin);
            Response<Usuario> response = call.execute();
            if (response.isSuccessful()) {
                //Log.d("DispositivosActivity","Exito");
                return response.body();
            } else {
                //Log.d("DispositivosActivity","Error en la respuesta");
                throw new Exception("Error en la respuesta "+response.code());
            }
        } else {
            //Log.d("DispositivosActivity","No hay internet");
            throw new Exception("No hay internet");
        }
    }

    @Override
    public Usuario updatePerfil(Usuario usuUpdate) throws Exception {
        if (hayInternet()) {
            Call<Usuario> call = apiService.updatePerfil(usuUpdate);
            Response<Usuario> response = call.execute();
            if (response.isSuccessful()) {
                //Log.d("DispositivosActivity","Exito");
                return response.body();
            } else {
                //Log.d("DispositivosActivity","Error en la respuesta");
                throw new Exception();
            }
        } else {
            //Log.d("DispositivosActivity","No hay internet");
            throw new Exception();
        }
    }

    @Override
    public FotoUsuario updatePerfilFoto(FotoUsuario fotoUsuario) throws Exception {
        if (hayInternet()) {
            Call<FotoUsuario> call = apiService.updatePerfilFoto(fotoUsuario);
            Response<FotoUsuario> response = call.execute();
            if (response.isSuccessful()) {
                //Log.d("DispositivosActivity","Exito");
                return response.body();
            } else {
                //Log.d("DispositivosActivity","Error en la respuesta");
                throw new Exception();
            }
        } else {
            //Log.d("DispositivosActivity","No hay internet");
            throw new Exception();
        }
    }

    @Override
    public List<RankingItem> getTop10() throws Exception {
        if (hayInternet()) {
            Call<List<RankingItem>> call = apiService.getTop10();
            Response<List<RankingItem>> response = call.execute();
            if (response.isSuccessful()) {
                //Log.d("DispositivosActivity","Exito");
                return response.body();
            } else {
                //Log.d("DispositivosActivity","Error en la respuesta");
                throw new Exception();
            }
        } else {
            //Log.d("DispositivosActivity","No hay internet");
            throw new Exception();
        }
    }

    @Override
    public Reciclaje reciclar(Reciclaje reciclaje) throws Exception {
        if (hayInternet()) {
            Call<Reciclaje> call = apiService.reciclar(reciclaje);
            Response<Reciclaje> response = call.execute();
            if (response.isSuccessful()) {
                //Log.d("DispositivosActivity","Exito");
                return response.body();
            } else {
                //Log.d("DispositivosActivity","Error en la respuesta");
                throw new Exception();
            }
        } else {
            //Log.d("DispositivosActivity","No hay internet");
            throw new Exception();
        }
    }

    @Override
    public List<ReciclajeDiaMes> recHistorial(String codigo, Integer year, Integer month) throws Exception {
        if (hayInternet()) {
            Call<List<ReciclajeDiaMes>> call = apiService.recHistorial(codigo,year,month);
            Response<List<ReciclajeDiaMes>> response = call.execute();
            if (response.isSuccessful()) {
                //Log.d("DispositivosActivity","Exito");
                return response.body();
            } else {
                //Log.d("DispositivosActivity","Error en la respuesta");
                throw new Exception();
            }
        } else {
            //Log.d("DispositivosActivity","No hay internet");
            throw new Exception();
        }
    }
}
