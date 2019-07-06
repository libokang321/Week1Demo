package com.bawei.contact;

import android.content.Context;

import com.bawei.entity.PictureEntity;
import com.bawei.net.NetCallBack;

import java.util.HashMap;

import okhttp3.MultipartBody;

/*
 *@Auther:姓名
 *@Date: 时间
 *@Description:功能
 * */
public interface PictureContact {
    interface IPictureView {
        void success(PictureEntity pictureEntity);

        void failure(String error);
    }

    interface IPictureModel {
        void getPicture(MultipartBody.Part filepart, NetCallBack netCallBack);
    }

    interface IPicturePresenter {
        void attach(IPictureView view);

        void detach();

        void getPicture(MultipartBody.Part filepart);
    }
}
