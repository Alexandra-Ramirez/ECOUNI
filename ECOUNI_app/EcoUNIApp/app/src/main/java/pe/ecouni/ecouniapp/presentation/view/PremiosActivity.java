package pe.ecouni.ecouniapp.presentation.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import pe.ecouni.ecouniapp.R;
import pe.ecouni.ecouniapp.presentation.model.Usuario;
import pe.ecouni.ecouniapp.presentation.view.ui.premios.SectionsPagerAdapter;

public class PremiosActivity extends AppCompatActivity {

    private TextView puntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premios);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        puntos = findViewById(R.id.points);

        Usuario usuario = Usuario.cargar(this);
        String punt = usuario.getPuntos().toString();
        puntos.setText(punt);
    }

    public void back(View view){
        onBackPressed();
    }
}