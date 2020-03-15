package com.example.nghenhactrctuyn.Service;

import com.example.nghenhactrctuyn.Model.Album;
import com.example.nghenhactrctuyn.Model.Baihat;
import com.example.nghenhactrctuyn.Model.Baihatyeuthich;
import com.example.nghenhactrctuyn.Model.Chude;
import com.example.nghenhactrctuyn.Model.Chudevatheloai;
import com.example.nghenhactrctuyn.Model.Fullalbum;
import com.example.nghenhactrctuyn.Model.Playlist;
import com.example.nghenhactrctuyn.Model.Quangcao;
import com.example.nghenhactrctuyn.Model.Theloai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {
    @GET("songbanner.php")
    Call<List<Quangcao>> GetdataBanner();

    @GET("playlistcuren.php")
    Call<List<Playlist>> Getplaylist();

    @GET ("chudevatheloai.php")
    Call<Chudevatheloai> Getchudevatheloai();

    @GET ("Album.php")
    Call<List<Album>> Getalbum();

    @GET ("baihatduocyeuthich.php")
    Call<List<Baihatyeuthich>> Getbaihatyeuthich();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> Getdanhsachbaihat(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> Getdanhsachbaihattheoplaylist(@Field("idplaylist") String idplaylist);

    @GET("fulldanhsachplaylist.php")
    Call<List<Playlist>> Getfulldanhsach();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> Gettheloai(@Field("idtheloai") String idtheloai);

    @GET("fullchude.php")
    Call<List<Chude>> Getfullchude();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<Theloai>> Gettheloaitheochude(@Field("idchude") String idchude);

   @GET("tatcaalbum.php")
    Call<List<Fullalbum>> Getfullalbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> Getbaihattheoalbum(@Field("idalbum")String idalbum);

    @FormUrlEncoded
    @POST("updataLuotThich.php")
    Call<String> Getupdateluotthich(@Field("luotthich")String luotthich,@Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("timkiemcakhuc.php")
    Call<List<Baihat>> Getbaihattimkiem(@Field("tukhoa")String tukhoa);
}

