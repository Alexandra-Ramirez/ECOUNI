package pe.ecouni.ecouniapp.presentation.view;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.system.ErrnoException;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pe.ecouni.ecouniapp.R;
import pe.ecouni.ecouniapp.presentation.model.Usuario;
import pe.ecouni.ecouniapp.presentation.presenter.EditarPerfilPresenter;

public class EditarPerfilActivity extends AppCompatActivity implements EditarPerfilPresenter.EditarPerfilView {

    private static int PICKER_REQ = 200;
    private static int CROP_REQ = 300;

    private static String USUARIO = "usuario";
    private static String MOD_FOTO = "mod_foto";
    private static String ELIMINAR = "eliminar";
    private static String FOTO_CORT = "foto_cort";

    private EditarPerfilPresenter presenter;
    private EditText tf_nombre,tf_facultad,tf_escribe,tf_email,tf_celular,tf_cumple;
    private AlertDialog dialog;
    private ImageView foto;
    private Uri mCropImageUri;
    private boolean modFoto,eliminar;
    private Bitmap fotoCortada;
    private Usuario usuTmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        presenter = new EditarPerfilPresenter(this,this);

        if(savedInstanceState!=null){
            usuTmp = savedInstanceState.getParcelable(USUARIO);
            modFoto = savedInstanceState.getBoolean(MOD_FOTO,false);
            eliminar = savedInstanceState.getBoolean(ELIMINAR,false);
            fotoCortada = savedInstanceState.getParcelable(FOTO_CORT);
        }

        tf_nombre = findViewById(R.id.tf_nombre);
        tf_facultad = findViewById(R.id.tf_facultad);
        tf_escribe = findViewById(R.id.tf_escribe);
        tf_email = findViewById(R.id.tf_email);
        tf_celular = findViewById(R.id.tf_celular);
        tf_cumple = findViewById(R.id.tf_cumple);
        foto = findViewById(R.id.foto);

        presenter.mostrar(usuTmp);
    }

    public void save(View view){
        readFields();
        presenter.guardar(usuTmp,modFoto,eliminar,fotoCortada);
    }

    private void readFields(){
        if (usuTmp==null)
            usuTmp = Usuario.cargar(this);
        usuTmp.setNombre(tf_nombre.getText().toString());
        usuTmp.setSobre_mi(tf_escribe.getText().toString());
        usuTmp.setEmail(tf_email.getText().toString());
        usuTmp.setCelular(tf_celular.getText().toString());
        usuTmp.setFacultad(tf_facultad.getText().toString());

        try {
            Date dateCumple= new SimpleDateFormat("dd/MM/yyyy").parse(tf_cumple.getText().toString());
            String cumpleFormated = new SimpleDateFormat("yyyy-MM-dd").format(dateCumple);
            usuTmp.setCumple(cumpleFormated);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void cancel(View view){
        salir();
    }

    public void datepick(View view){
        Calendar calendar = Calendar.getInstance();
        try {
            Date dateCumple= new SimpleDateFormat("dd/MM/yyyy").parse(tf_cumple.getText().toString());
            calendar.setTime(dateCumple);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DatePickerDialog dialog = new DatePickerDialog(this,listener,
                calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

            Date dateCumple = calendar.getTime();
            String cumple = new SimpleDateFormat("dd/MM/yyyy").format(dateCumple);
            tf_cumple.setText(cumple);
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        readFields();
        outState.putParcelable(USUARIO,usuTmp);
        outState.putBoolean(MOD_FOTO,modFoto);
        outState.putBoolean(ELIMINAR,eliminar);
        outState.putParcelable(FOTO_CORT,fotoCortada);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void mostrar(Usuario usuario) {
        usuTmp = usuario;
        if(modFoto){
            if(eliminar){
                setFotoBmp(null);
            }else{
                setFotoBmp(fotoCortada);
            }
        }else {
            setFotoUri(usuTmp.getPath_foto());
        }
        tf_nombre.setText(usuTmp.getNombre());
        tf_email.setText(usuTmp.getEmail());
        tf_facultad.setText(usuTmp.getFacultad());
        tf_celular.setText(usuTmp.getCelular());
        tf_escribe.setText(usuTmp.getSobre_mi());

        if(usuTmp.getCumple()!=null && !usuTmp.getCumple().isEmpty() && !usuTmp.getCumple().equals("0000-00-00")){
            try {
                Date dateCumple= new SimpleDateFormat("yyyy-MM-dd").parse(usuTmp.getCumple());
                String cumpleFormated = new SimpleDateFormat("dd/MM/yyyy").format(dateCumple);
                tf_cumple.setText(cumpleFormated);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

    private void setFotoUri(String path){
        if(path!=null && !path.isEmpty()){
            Uri uri = Uri.parse(getString(R.string.server_url)+"public/images/"+path);
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
    }

    private void setFotoBmp(Bitmap bmp){
        if(bmp!=null){
            Glide.with(this)
                    .load(bmp)
                    .apply(RequestOptions.circleCropTransform())
                    .into(foto);
        }else{
            Glide.with(this)
                    .load(R.drawable.default_avatar)
                    .apply(RequestOptions.circleCropTransform())
                    .into(foto);
        }
    }

    private void goCrop(){
        CropImage.activity(mCropImageUri)
                .setAutoZoomEnabled(false)
                .setAspectRatio(1,1)
                .setInitialCropWindowPaddingRatio(0f)
                .setScaleType(CropImageView.ScaleType.FIT_CENTER)
                .setMinCropResultSize(400,400)
                .setCropShape(CropImageView.CropShape.OVAL)
                .setOutputCompressFormat(Bitmap.CompressFormat.JPEG)
                .start(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == PICKER_REQ) {
            if (resultCode == RESULT_OK) {
                Uri imageUri = getPickImageResultUri(data);
                mCropImageUri = imageUri;
                // For API >= 23 we need to check specifically that we have permissions to read external storage,
                // but we don't know if we need to for the URI so the simplest is to try open the stream and see if we get error.
                boolean requirePermissions = false;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                        checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                        isUriRequiresPermissions(imageUri)) {

                    // request permissions and handle the result in onRequestPermissionsResult()
                    requirePermissions = true;

                    Toast.makeText(this, mCropImageUri.toString(), Toast.LENGTH_SHORT).show();
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                }

                if (!requirePermissions) {
                    //mCropImageView.setImageUriAsync(imageUri);
                    goCrop();
                }
            }
        }
        //RESULT FROM CROPING ACTIVITY
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                try {
                    fotoCortada = MediaStore.Images.Media.getBitmap(getContentResolver(), result.getUri());
                    modFoto = true;
                    eliminar = false;
                    setFotoBmp(fotoCortada);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (mCropImageUri != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            goCrop();
        } else {
            Toast.makeText(this, "Required permissions are not granted", Toast.LENGTH_LONG).show();
        }
    }

    public void pickFoto(View view){
        selectMod();
    }

    private void pickSource(){
        startActivityForResult(getPickImageChooserIntent(),PICKER_REQ);
    }

    /**
     * Create a chooser intent to select the source to get image from.<br/>
     * The source can be camera's (ACTION_IMAGE_CAPTURE) or gallery's (ACTION_GET_CONTENT).<br/>
     * All possible sources are added to the intent chooser.
     */
    public Intent getPickImageChooserIntent() {

        // Determine Uri of camera image to save.
        Uri outputFileUri = getCaptureImageOutputUri();

        List<Intent> allIntents = new ArrayList<>();
        PackageManager packageManager = getPackageManager();

        // collect all camera intents
        Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            if (outputFileUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            }
            allIntents.add(intent);
        }

        // collect all gallery intents
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
        for (ResolveInfo res : listGallery) {
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            allIntents.add(intent);
        }

        // the main intent is the last in the list (fucking android) so pickup the useless one
        Intent mainIntent = allIntents.get(allIntents.size() - 1);
        for (Intent intent : allIntents) {
            if (intent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
                mainIntent = intent;
                break;
            }
        }
        allIntents.remove(mainIntent);

        // Create a chooser from the main intent
        Intent chooserIntent = Intent.createChooser(mainIntent, "Seleccionar fuente");

        // Add all other intents
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));

        return chooserIntent;
    }

    /**
     * Get URI to image received from capture by camera.
     */
    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new File(getImage.getPath(), "pickImageResult.jpeg"));
        }
        return outputFileUri;
    }

    /**
     * Get the URI of the selected image from {@link #getPickImageChooserIntent()}.<br/>
     * Will return the correct URI for camera and gallery image.
     *
     * @param data the returned data of the activity result
     */
    public Uri getPickImageResultUri(Intent data) {
        boolean isCamera = true;
        if (data != null && data.getData() != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ? getCaptureImageOutputUri() : data.getData();
    }

    /**
     * Test if we can open the given Android URI to test if permission required error is thrown.<br>
     */
    public boolean isUriRequiresPermissions(Uri uri) {
        try {
            ContentResolver resolver = getContentResolver();
            InputStream stream = resolver.openInputStream(uri);
            stream.close();
            return false;
        } catch (FileNotFoundException e) {
            if (e.getCause() instanceof ErrnoException) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
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
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void salir() {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void salirExito() {
        setResult(RESULT_OK);
        finish();
    }

    private void selectMod(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String items[] = {"Nueva Foto","Eliminar Foto"};

        builder.setTitle("Modificar foto:");
        builder.setItems(items,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    pickSource();
                }else{
                    modFoto = true;
                    eliminar = true;
                    setFotoBmp(null);
                }
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }
}
