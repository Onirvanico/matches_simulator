package br.com.projeto.matchsimulator.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Place implements Parcelable {

    @SerializedName("nome")
    private  String name;

    @SerializedName("imagem")
    private String image;

    public Place(String name, String image) {
        this.name = name;
        this.image = image;
    }

    protected Place(Parcel in) {
        name = in.readString();
        image = in.readString();
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(image);
    }
}
