package pe.ecouni.ecouniapp.presentation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import pe.ecouni.ecouniapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goLogin(View view){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
