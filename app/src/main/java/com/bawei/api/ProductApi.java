package com.bawei.api;

import com.bawei.entity.PictureEntity;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/*
 *@Auther:姓名
 *@Date: 时间
 *@Description:功能
 * */public interface ProductApi {
    /**
     * 上传头像
     * @param params
     * @param filepart
     * @return
     */
    @POST(Api.PICTURE_URL)
    @Multipart
    Observable<PictureEntity> getPicture(@HeaderMap HashMap<String, String> params, @Part MultipartBody.Part filepart);
}
