package com.example.nghenhactrctuyn.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Chude implements Serializable {

@SerializedName("IdChuDe")
@Expose
private String idChuDe;
@SerializedName("TenChuDe")
@Expose
private String TenChuDe;
@SerializedName("HinhChuDe")
@Expose
private String hinhChuDe;

public String getIdChuDe() {
return idChuDe;
}

public void setIdChuDe(String idChuDe) {
this.idChuDe = idChuDe;
}

public String getTenChuDe() {
return TenChuDe;
}

public void setTenChuDe(String TenChuDe) {
this.TenChuDe = TenChuDe;
}

public String getHinhChuDe() {
return hinhChuDe;
}

public void setHinhChuDe(String hinhChuDe) {
this.hinhChuDe = hinhChuDe;
}

}