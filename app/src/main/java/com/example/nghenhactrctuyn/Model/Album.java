package com.example.nghenhactrctuyn.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Album implements Serializable {

@SerializedName("IdAlbum")
@Expose
private String idAlbum;
@SerializedName("Tenalbum")
@Expose
private String tenalbum;
@SerializedName("Tencasialbum")
@Expose
private String tencasialbum;
@SerializedName("Hinhalbum")
@Expose
private String hinhalbum;

public String getIdAlbum() {
return idAlbum;
}

public void setIdAlbum(String idAlbum) {
this.idAlbum = idAlbum;
}

public String getTenalbum() {
return tenalbum;
}

public void setTenalbum(String tenalbum) {
this.tenalbum = tenalbum;
}

public String getTencasialbum() {
return tencasialbum;
}

public void setTencasialbum(String tencasialbum) {
this.tencasialbum = tencasialbum;
}

public String getHinhalbum() {
return hinhalbum;
}

public void setHinhalbum(String hinhalbum) {
this.hinhalbum = hinhalbum;
}

}