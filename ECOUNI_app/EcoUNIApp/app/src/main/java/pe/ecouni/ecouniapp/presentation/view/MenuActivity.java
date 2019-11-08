package pe.ecouni.ecouniapp.presentation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import pe.ecouni.ecouniapp.R;

public class MenuActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        GridView gridView = findViewById(R.id.gridview);
        MenuAdapter adapter = new MenuAdapter(this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0: //Perfil
                goPerfil();
                break;
            case 1: //Reciclar
                goReciclar();
                break;
            case 2: //Premios
                goPremios();
                break;
            case 3: //Puntos
                goPuntos();
                break;
            case 4: //Feedback
                goFeedback();
                break;
            case 5: //Info
                goInfo();
                break;
            default:
                break;
        }
    }

    private void goPerfil(){
        Intent intent = new Intent(this,PerfilActivity.class);
        startActivity(intent);
    }

    private void goReciclar(){
        Intent intent = new Intent(this,ReciclarActivity.class);
        startActivity(intent);
    }

    private void goPremios(){
        Intent intent = new Intent(this,PremiosActivity.class);
        startActivity(intent);
    }

    private void goInfo(){
        Intent intent = new Intent(this,PuntosActivity.class);
        startActivity(intent);
    }

    private void goFeedback(){
        Intent intent = new Intent(this,FeedbackActivity.class);
        startActivity(intent);
    }

    private void goPuntos(){
        Intent intent = new Intent(this,InfoActivity.class);
        startActivity(intent);
    }
}
