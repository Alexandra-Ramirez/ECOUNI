package pe.ecouni.ecouniapp.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ReciclajeDiaMes implements Parcelable {
     @SerializedName("cant_dia")
    private Integer cant_dia;

     @SerializedName("dia")
    private Integer dia;

    public Integer getCant_dia() {
        return cant_dia;
    }

    public void setCant_dia(Integer cant_dia) {
        this.cant_dia = cant_dia;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.cant_dia);
        dest.writeValue(this.dia);
    }

    public ReciclajeDiaMes() {
    }

    protected ReciclajeDiaMes(Parcel in) {
        this.cant_dia = (Integer) in.readValue(Integer.class.getClassLoader());
        this.dia = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<ReciclajeDiaMes> CREATOR = new Parcelable.Creator<ReciclajeDiaMes>() {
        @Override
        public ReciclajeDiaMes createFromParcel(Parcel source) {
            return new ReciclajeDiaMes(source);
        }

        @Override
        public ReciclajeDiaMes[] newArray(int size) {
            return new ReciclajeDiaMes[size];
        }
    };
}
