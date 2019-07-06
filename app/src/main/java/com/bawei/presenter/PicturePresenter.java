package com.bawei.presenter;

import android.content.Context;

import com.bawei.contact.PictureContact;
import com.bawei.entity.PictureEntity;
import com.bawei.model.PictureModel;
import com.bawei.net.NetCallBack;

import okhttp3.MultipartBody;

/*
 *@Auther:姓名
 *@Date: 时间
 *@Description:功能
 * */public class PicturePresenter implements PictureContact.IPicturePresenter {
    private PictureContact.IPictureModel pictureModel;
    private PictureContact.IPictureView pictureView;

    @Override
    public void attach(PictureContact.IPictureView view) {
        this.pictureView = view;
        pictureModel = new PictureModel();
    }

    @Override
    public void detach() {
        if (pictureView != null) {
            pictureView = null;
        }
        if (pictureModel != null) {
            pictureModel = null;
        }
        System.gc();
    }

    @Override
    public void getPicture(MultipartBody.Part filepart) {
        pictureModel.getPicture(filepart, new NetCallBack() {
            @Override
            public void success(Object object) {
                pictureView.success((PictureEntity) object);
            }

            @Override
            public void failure(String error) {
                pictureView.failure(error);
            }
        });
    }
}
