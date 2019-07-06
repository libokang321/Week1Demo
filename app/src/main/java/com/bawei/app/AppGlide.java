package com.bawei.app;

import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

import java.io.File;

/*
 *@Auther:姓名
 *@Date: 时间
 *@Description:功能
 * */
@GlideModule
public class AppGlide extends AppGlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        super.applyOptions(context, builder);
        int saveSize = 1024 * 1024 * 20;
        builder.setMemoryCache(new LruResourceCache(saveSize));
        File file = new File(Environment.getExternalStorageDirectory(), "sa_ve");
        builder.setDiskCache(new DiskLruCacheFactory(file.getAbsolutePath(), saveSize));
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
