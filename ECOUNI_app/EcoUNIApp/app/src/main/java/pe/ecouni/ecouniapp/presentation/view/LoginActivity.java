package pe.ecouni.ecouniapp.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import pe.ecouni.ecouniapp.R;
import pe.ecouni.ecouniapp.presentation.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.LoginView {

    private TextInputEditText tf_codigo;
    private TextInputEditText tf_password;
    private CheckBox chk_recuerdame;
    private LoginPresenter presenter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this,this);

        tf_codigo = findViewById(R.id.tf_codigo);
        tf_password = findViewById(R.id.tf_password);
        chk_recuerdame = findViewById(R.id.chk_recuerdame);
        progressBar = findViewById(R.id.progressBar);

        presenter.llenar();
    }

    public void ingresar(View view){
        String codigo = tf_codigo.getText().toString();
        String pass = tf_password.getText().toString();
        boolean recordar = chk_recuerdame.isChecked();

        presenter.ingresar(codigo,pass,recordar);
    }

    @Override
    public void goMenu() {
        Intent intent = new Intent(this,MenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void llenar(String codigo) {
        tf_codigo.setText(codigo);
    }

    @Override
    public void errorCod(String error) {
        tf_codigo.setError(error);
    }

    @Override
    public void errorPass(String error) {
        tf_password.setError(error);
    }

    @Override
    public void clearError() {
        tf_codigo.setError(null);
        tf_password.setError(null);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void clear() {
        tf_codigo.setText(null);
        tf_password.setText(null);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
