package pe.ecouni.ecouniapp.coneccion;

import java.util.List;

import pe.ecouni.ecouniapp.presentation.model.FotoUsuario;
import pe.ecouni.ecouniapp.presentation.model.RankingItem;
import pe.ecouni.ecouniapp.presentation.model.Reciclaje;
import pe.ecouni.ecouniapp.presentation.model.ReciclajeDiaMes;
import pe.ecouni.ecouniapp.presentation.model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("usuario/login")
    Call<Usuario> login(@Body Usuario usuLogin);

    @POST("usuario/edit")
    Call<Usuario> updatePerfil(@Body Usuario usuUpdate);

    @POST("usuario/edit_foto")
    Call<FotoUsuario> updatePerfilFoto(@Body FotoUsuario fotoUsuario);

    @GET("usuario/ranking")
    Call<List<RankingItem>> getTop10();

    @POST("usuario/reciclar")
    Call<Reciclaje> reciclar(@Body Reciclaje reciclaje);

    @GET("usuario/historial")
    Call<List<ReciclajeDiaMes>> recHistorial(@Query("codigo") String codigo, @Query("year") Integer year, @Query("month") Integer month);
}
