package pe.ecouni.ecouniapp.presentation.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Usuario implements Parcelable {
    @SerializedName("codigo")
    private String codigo;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("facultad")
    private String facultad;

    @SerializedName("sobre_mi")
    private String sobre_mi;

    @SerializedName("email")
    private String email;

    @SerializedName("cumple")
    private String cumple;

    @SerializedName("path_foto")
    private String path_foto;

    @SerializedName("cantidad")
    private Integer cantidad;

    @SerializedName("puntos")
    private Integer puntos;

    @SerializedName("pass")
    private String pass;

    @SerializedName("celular")
    private String celular;

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getSobre_mi() {
        return sobre_mi;
    }

    public void setSobre_mi(String sobre_mi) {
        this.sobre_mi = sobre_mi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCumple() {
        return cumple;
    }

    public void setCumple(String  cumple) {
        this.cumple = cumple;
    }

    public String getPath_foto() {
        return path_foto;
    }

    public void setPath_foto(String path_foto) {
        this.path_foto = path_foto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Usuario() {
    }

    public static void guardar(Context context,Usuario usuario){
        SharedPreferences pref = context.getSharedPreferences("usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("codigo",usuario.getCodigo());
        edit.putString("nombre",usuario.getNombre());
        edit.putString("email",usuario.getEmail());
        edit.putString("sobre_mi",usuario.getSobre_mi());
        edit.putString("celular",usuario.getCelular());
        edit.putString("cumple",usuario.getCumple());
        edit.putInt("cantidad",usuario.getCantidad());
        edit.putString("facultad",usuario.getFacultad());
        edit.putInt("puntos",usuario.getPuntos());
        edit.putString("path_foto",usuario.getPath_foto());
        edit.apply();
    }

    public static void recordar(Context context,boolean recordar){
        SharedPreferences pref = context.getSharedPreferences("usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putBoolean("recordar",recordar);
        edit.apply();
    }

    public static boolean isRecordar(Context context){
        SharedPreferences pref = context.getSharedPreferences("usuario",Context.MODE_PRIVATE);
        return pref.getBoolean("recordar",false);
    }

    public static Usuario cargar(Context context){
        SharedPreferences pref = context.getSharedPreferences("usuario", Context.MODE_PRIVATE);
        Usuario usuario = new Usuario();
        usuario.setCodigo(pref.getString("codigo",null));
        usuario.setNombre(pref.getString("nombre",null));
        usuario.setEmail(pref.getString("email",null));
        usuario.setSobre_mi(pref.getString("sobre_mi",null));
        usuario.setCumple(pref.getString("cumple",null));
        usuario.setCantidad(pref.getInt("cantidad",0));
        usuario.setPuntos(pref.getInt("puntos",0));
        usuario.setFacultad(pref.getString("facultad",null));
        usuario.setCelular(pref.getString("celular",null));
        usuario.setPath_foto(pref.getString("path_foto",null));
        return usuario;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.codigo);
        dest.writeString(this.nombre);
        dest.writeString(this.facultad);
        dest.writeString(this.sobre_mi);
        dest.writeString(this.email);
        dest.writeString(this.cumple);
        dest.writeString(this.path_foto);
        dest.writeValue(this.cantidad);
        dest.writeValue(this.puntos);
        dest.writeString(this.pass);
        dest.writeString(this.celular);
    }

    protected Usuario(Parcel in) {
        this.codigo = in.readString();
        this.nombre = in.readString();
        this.facultad = in.readString();
        this.sobre_mi = in.readString();
        this.email = in.readString();
        this.cumple = in.readString();
        this.path_foto = in.readString();
        this.cantidad = (Integer) in.readValue(Integer.class.getClassLoader());
        this.puntos = (Integer) in.readValue(Integer.class.getClassLoader());
        this.pass = in.readString();
        this.celular = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel source) {
            return new Usuario(source);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };
}
