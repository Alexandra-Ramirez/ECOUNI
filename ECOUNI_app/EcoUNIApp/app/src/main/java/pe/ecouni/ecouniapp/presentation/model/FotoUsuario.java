package pe.ecouni.ecouniapp.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class FotoUsuario implements Parcelable {
    @SerializedName("codigo")
    private String codigo;

    @SerializedName("foto")
    private String base64Thumbnail;

    @SerializedName("path_foto")
    private String path_foto;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getBase64Thumbnail() {
        return base64Thumbnail;
    }

    public void setBase64Thumbnail(String base64Thumbnail) {
        this.base64Thumbnail = base64Thumbnail;
    }

    public String getPath_foto() {
        return path_foto;
    }

    public void setPath_foto(String path_foto) {
        this.path_foto = path_foto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.codigo);
        dest.writeString(this.base64Thumbnail);
        dest.writeString(this.path_foto);
    }

    public FotoUsuario() {
    }

    protected FotoUsuario(Parcel in) {
        this.codigo = in.readString();
        this.base64Thumbnail = in.readString();
        this.path_foto = in.readString();
    }

    public static final Parcelable.Creator<FotoUsuario> CREATOR = new Parcelable.Creator<FotoUsuario>() {
        @Override
        public FotoUsuario createFromParcel(Parcel source) {
            return new FotoUsuario(source);
        }

        @Override
        public FotoUsuario[] newArray(int size) {
            return new FotoUsuario[size];
        }
    };
}
