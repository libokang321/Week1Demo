package com.bawei;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.app.App;
import com.bawei.contact.PictureContact;
import com.bawei.entity.PictureEntity;
import com.bawei.presenter.PicturePresenter;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity implements PictureContact.IPictureView {
    private PictureContact.IPicturePresenter presenter;
    private TextView tv1, tv2, tv3;
    private RelativeLayout dialog;
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        img = findViewById(R.id.img);
        tv3 = findViewById(R.id.tv3);
        dialog = findViewById(R.id.log);
        presenter = new PicturePresenter();
        presenter.attach(this);

        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog != null && !dialog.isShown()) {
                    dialog.setVisibility(View.VISIBLE);
                }
            }
        });

        /**
         * 打开相册
         */
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PictureSelector.create(MainActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .selectionMode(PictureConfig.SINGLE)
                        .sizeMultiplier(0.5f)
                        .forResult(PictureConfig.CHOOSE_REQUEST);

                dialog.setVisibility(View.GONE);
            }
        });
        /**
         * 打开相机
         */
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dialog.setVisibility(View.GONE);
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);

                    String compressPath = selectList.get(0).getPath();
                    System.out.println("comparessPath=====" + compressPath);

//                    SharedPreferences file_data = App.context.getSharedPreferences("file_Data", MODE_PRIVATE);
//                    SharedPreferences.Editor edit = file_data.edit();
//                    edit.putString("compressPath", compressPath);

                    File file = new File(compressPath);
                    RequestBody requestBody = MultipartBody.create(MediaType.parse("image/*"), file);
                    MultipartBody.Part part = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
                    System.out.println("part=====" + part);
                    presenter.getPicture(part);

                    break;
            }
        }
    }

    @Override
    public void success(PictureEntity pictureEntity) {

        Glide.with(MainActivity.this).load(pictureEntity.headpath).into(img);
        Toast.makeText(MainActivity.this, "" + pictureEntity.headpath, Toast.LENGTH_LONG).show();
    }

    @Override
    public void failure(String error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }
}
