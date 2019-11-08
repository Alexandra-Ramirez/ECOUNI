package pe.ecouni.ecouniapp.coneccion;

import java.util.List;

import pe.ecouni.ecouniapp.presentation.model.FotoUsuario;
import pe.ecouni.ecouniapp.presentation.model.RankingItem;
import pe.ecouni.ecouniapp.presentation.model.Reciclaje;
import pe.ecouni.ecouniapp.presentation.model.ReciclajeDiaMes;
import pe.ecouni.ecouniapp.presentation.model.Usuario;

public interface RestApi {
    Usuario login(Usuario usuLogin) throws Exception;
    Usuario updatePerfil(Usuario usuUpdate) throws Exception;
    FotoUsuario updatePerfilFoto(FotoUsuario fotoUsuario) throws Exception;
    List<RankingItem> getTop10() throws Exception;
    Reciclaje reciclar(Reciclaje reciclaje) throws Exception;
    List<ReciclajeDiaMes> recHistorial(String codigo, Integer year, Integer month) throws Exception;
}
