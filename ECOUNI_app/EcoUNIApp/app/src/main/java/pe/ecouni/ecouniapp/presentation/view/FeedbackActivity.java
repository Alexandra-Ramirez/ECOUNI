package pe.ecouni.ecouniapp.presentation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pe.ecouni.ecouniapp.R;
import pe.ecouni.ecouniapp.coneccion.utils.GMailSender;
import pe.ecouni.ecouniapp.presentation.model.Usuario;

public class FeedbackActivity extends AppCompatActivity {

    private EditText novedades,premios,otros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        novedades = findViewById(R.id.novedades);
        premios = findViewById(R.id.premios);
        otros = findViewById(R.id.otros);
    }

    public void send(View view){
        String t_novedades = novedades.getText().toString();
        String t_premios = premios.getText().toString();
        String t_otros = otros.getText().toString();
        String t_message = "";
        if(!t_novedades.isEmpty()){
            t_message += "P: "+getString(R.string.feeback_1)+"\n\n";
            t_message += "R: "+t_novedades+"\n\n";
        }if(!t_premios.isEmpty()){
            t_message += "P: "+getString(R.string.feedback_2)+"\n\n";
            t_message += "R: "+t_premios+"\n\n";
        }if(!t_otros.isEmpty()){
            t_message += "P: "+getString(R.string.feedback_3)+"\n\n";
            t_message += "R: "+t_otros+"\n\n";
        }

        if(!t_message.isEmpty()){
            Usuario usuario = Usuario.cargar(this);
            String t_sub = "[FeedBack] enviado por "+usuario.getCodigo();
            final String body = t_message;
            final String subject = t_sub;
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        GMailSender sender = new GMailSender("ecouni.fdb@gmail.com",
                                "3c0un1FC");
                        sender.sendMail(subject, body,
                                "ecouni.fdb@gmail.com", "ecouni.fdb@gmail.com");
                    } catch (Exception e) {
                        Log.e("SendMail", e.getMessage(), e);
                    }
                }

            }).start();
            Toast.makeText(this,"Feedback enviado",Toast.LENGTH_SHORT).show();
        }
        finish();

    }

    public void back(View view){
        onBackPressed();
    }
}
