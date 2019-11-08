package pe.ecouni.ecouniapp.presentation.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import pe.ecouni.ecouniapp.R;
import pe.ecouni.ecouniapp.presentation.model.RankingItem;
import pe.ecouni.ecouniapp.presentation.presenter.InfoPresenter;
import pe.ecouni.ecouniapp.presentation.view.ui.info.SectionsPagerAdapter;

public class InfoActivity extends AppCompatActivity implements InfoPresenter.InfoView {

    private InfoPresenter presenter;
    private SectionsPagerAdapter sectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        presenter = new InfoPresenter(this,this);

        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        presenter.getRanking();
    }

    public void back(View view){
        onBackPressed();
    }

    @Override
    public void mostrar(List<RankingItem> items) {
        sectionsPagerAdapter.mostrarRanking(items);
    }
}