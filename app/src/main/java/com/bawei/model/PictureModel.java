package com.bawei.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.bawei.api.Api;
import com.bawei.api.ProductApi;
import com.bawei.app.App;
import com.bawei.contact.PictureContact;
import com.bawei.entity.PictureEntity;
import com.bawei.net.NetCallBack;
import com.bawei.net.RetrfitUtils;

import java.io.File;
import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.content.Context.MODE_PRIVATE;

/*
 *@Auther:姓名
 *@Date: 时间
 *@Description:功能
 * */public class PictureModel implements PictureContact.IPictureModel {
    @Override
    public void getPicture(MultipartBody.Part filepart, final NetCallBack netCallBack) {


        HashMap<String, String> params = new HashMap<>();
        params.put("sessionId", "15623967482086719");
        params.put("userId", "6719");
        RetrfitUtils.getUtils().createService(ProductApi.class).getPicture(params, filepart).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PictureEntity>() {
                    @Override
                    public void accept(PictureEntity pictureEntity) throws Exception {
                        netCallBack.success(pictureEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        netCallBack.failure("网络不稳定");
                    }
                });
    }
}
