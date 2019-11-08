package pe.ecouni.ecouniapp.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Reciclaje implements Parcelable {
    @SerializedName("codigo")
    private String codigo;
    @SerializedName("cantidad")
    private Integer cantidad;
    @SerializedName("puntos")
    private Integer puntos;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.codigo);
        dest.writeValue(this.cantidad);
        dest.writeValue(this.puntos);
    }

    public Reciclaje() {
    }

    protected Reciclaje(Parcel in) {
        this.codigo = in.readString();
        this.cantidad = (Integer) in.readValue(Integer.class.getClassLoader());
        this.puntos = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Reciclaje> CREATOR = new Parcelable.Creator<Reciclaje>() {
        @Override
        public Reciclaje createFromParcel(Parcel source) {
            return new Reciclaje(source);
        }

        @Override
        public Reciclaje[] newArray(int size) {
            return new Reciclaje[size];
        }
    };
}
