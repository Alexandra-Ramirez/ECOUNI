package pe.ecouni.ecouniapp.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class RankingItem implements Parcelable {

    @SerializedName("path_foto")
    private String urlFoto;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("facultad")
    private String facultad;
    @SerializedName("cantidad")
    private String cantidad;
    @SerializedName("rank_pos")
    private String ranking;

    public RankingItem() {
    }

    public RankingItem(String urlFoto, String nombre, String facultad, String cantidad, String ranking) {
        this.urlFoto = urlFoto;
        this.nombre = nombre;
        this.facultad = facultad;
        this.cantidad = cantidad;
        this.ranking = ranking;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
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

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public static RankingItem ITEMS[] = {
            new RankingItem(null,"Luis Gomez","FC","1247","1"),
            new RankingItem(null,"Luis Gomez","FC","1247","2"),
            new RankingItem(null,"Luis Gomez","FC","1247","3"),
            new RankingItem(null,"Luis Gomez","FC","1247","4"),
            new RankingItem(null,"Luis Gomez","FC","1247","5"),
            new RankingItem(null,"Luis Gomez","FC","1247","6"),
            new RankingItem(null,"Luis Gomez","FC","1247","7"),
            new RankingItem(null,"Luis Gomez","FC","1247","8"),
            new RankingItem(null,"Luis Gomez","FC","1247","9"),
            new RankingItem(null,"Luis Gomez","FC","1247","10")
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.urlFoto);
        dest.writeString(this.nombre);
        dest.writeString(this.facultad);
        dest.writeString(this.cantidad);
        dest.writeString(this.ranking);
    }

    protected RankingItem(Parcel in) {
        this.urlFoto = in.readString();
        this.nombre = in.readString();
        this.facultad = in.readString();
        this.cantidad = in.readString();
        this.ranking = in.readString();
    }

    public static final Parcelable.Creator<RankingItem> CREATOR = new Parcelable.Creator<RankingItem>() {
        @Override
        public RankingItem createFromParcel(Parcel source) {
            return new RankingItem(source);
        }

        @Override
        public RankingItem[] newArray(int size) {
            return new RankingItem[size];
        }
    };
}
