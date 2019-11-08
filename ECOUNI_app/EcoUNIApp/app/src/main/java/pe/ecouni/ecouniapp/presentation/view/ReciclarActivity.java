package pe.ecouni.ecouniapp.presentation.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import pe.ecouni.ecouniapp.R;
import pe.ecouni.ecouniapp.presentation.model.ReciclajeDiaMes;
import pe.ecouni.ecouniapp.presentation.presenter.ReciclarPresenter;

public class ReciclarActivity extends AppCompatActivity implements
        ReciclarPresenter.ReciclarView {

    private CalendarAdapter adapter;
    private TextView tv_botellas,tv_total;
    private ReciclarPresenter presenter;
    private Spinner spinner;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciclar);

        presenter = new ReciclarPresenter(this,this);

        spinner = findViewById(R.id.spinner);
        tv_botellas = findViewById(R.id.botellas);
        tv_total = findViewById(R.id.total);

        GridView gridView = findViewById(R.id.gridview);
        adapter = new CalendarAdapter(this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(gridListener);
        spinner.setOnItemSelectedListener(spinnerListener);

        presenter.iniciar();
    }

    public void back(View view){
        onBackPressed();
    }

    AdapterView.OnItemClickListener gridListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int cant = adapter.select(position);
            if (cant>-1){
                String cant_t = cant+"";
                tv_botellas.setText(cant_t);
            }
        }
    };

    AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            presenter.mesSelected(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    public void goPremios(View view){
        Intent intent = new Intent(this,PremiosActivity.class);
        startActivity(intent);
    }

    @Override
    public void showProgress() {
        if(dialog==null){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(R.layout.progress_dialog);
            dialog = builder.create();
        }
        dialog.show();
    }

    @Override
    public void hideProgress() {
        if(dialog!=null){
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setTotal(int total) {
        String total_t = total+"";
        tv_total.setText(total_t);
    }

    @Override
    public void actualizar(int month,List<ReciclajeDiaMes> lista) {
        adapter.setCantidad(month,lista);
        Log.e("MES","mes: "+month);
        tv_botellas.setText("0");
    }

    @Override
    public void setMes(int month) {
        spinner.setSelection(month);
    }
}
