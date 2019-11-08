package pe.ecouni.ecouniapp.presentation.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import pe.ecouni.ecouniapp.R;
import pe.ecouni.ecouniapp.presentation.model.Usuario;
import pe.ecouni.ecouniapp.presentation.presenter.PerfilPresenter;

@SuppressLint("SimpleDateFormat")
public class PerfilActivity extends AppCompatActivity implements PerfilPresenter.PerfilView {

    private Button btnEditar;
    private ImageView foto;
    private TextView nombres, codigo, email, celular, cumple;
    private static int REQ_CODE = 6996;
    private PerfilPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        presenter = new PerfilPresenter(this,this);

        btnEditar = findViewById(R.id.editar);
        foto = findViewById(R.id.foto);
        nombres = findViewById(R.id.nombre);
        codigo = findViewById(R.id.codigo);
        email = findViewById(R.id.email);
        celular = findViewById(R.id.celular);
        cumple = findViewById(R.id.cumple);

        presenter.mostrar();

    }

    public void editar(View view){
        Intent intent = new Intent(this,EditarPerfilActivity.class);
        startActivityForResult(intent,REQ_CODE);
    }

    public void back(View view){
        onBackPressed();
    }

    @Override
    public void mostrar(Usuario usuario) {
        if(usuario.getPath_foto()!=null && !usuario.getPath_foto().isEmpty()){
            Uri uri = Uri.parse(getString(R.string.server_url)+"public/images/"+usuario.getPath_foto());
            Glide.with(this)
                    .load(uri)
                    .apply(RequestOptions.circleCropTransform())
                    .into(foto);
        }else{
            Glide.with(this)
                    .load(R.drawable.default_avatar)
                    .apply(RequestOptions.circleCropTransform())
                    .into(foto);
        }
        if(usuario.getNombre()!=null && !usuario.getNombre().isEmpty()){
            nombres.setText(usuario.getNombre());
        }else{
            nombres.setText(usuario.getCodigo());
        }
        codigo.setText(usuario.getCodigo());
        email.setText(usuario.getEmail());
        celular.setText(usuario.getCelular());


        if(usuario.getCumple()!=null && !usuario.getCumple().isEmpty() && !usuario.getCumple().equals("0000-00-00")){
            try {
                Date dateCumple= new SimpleDateFormat("yyyy-MM-dd").parse(usuario.getCumple());
                String cumpleFormated = new SimpleDateFormat("dd/MM/yyyy").format(dateCumple);
                cumple.setText(cumpleFormated);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_CODE){
            if(resultCode == RESULT_OK){
                presenter.mostrar();
            }
        }
    }
}
