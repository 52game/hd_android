package com.movebeans.hd_android.utils;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.movebeans.hd_android.R;
import com.movebeans.hd_android.base.App;
import com.movebeans.hd_android.constants.HttpConstants;
import com.movebeans.lib.common.tool.StringUtils;


/**
 * Glide
 */
public class GlideHelper {

    public static void showAvatar(String avatar, ImageView ivHead) {
        ImageView imageView = ivHead;
        String url = avatar;
        Object tabObj = imageView.getTag(R.id.image_url);
        String tag = (tabObj == null ? null : (String) tabObj);
        if (url == null || !url.equals(tag)) {
            imageView.setImageResource(R.mipmap.ic_launcher);
        }
        String head = StringUtils.formatNullString(url).contains("http") ? url : HttpConstants.SERVICE_URL_IMG + url;
        Glide.with(App.mContext)
                .load(head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .dontAnimate()
                .centerCrop()
                .crossFade()
                .into(ivHead);
        imageView.setTag(R.id.image_url, url);
    }

    public static void showImage(Context context, String imageUrl, ImageView iv) {
        String url = StringUtils.formatNullString(imageUrl).contains("http") ? imageUrl : HttpConstants.SERVICE_URL_IMG + imageUrl;
        Glide.with(context)
                .load(imageUrl)
                .animate(android.R.anim.fade_in)
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .centerCrop()
                .into(iv);
    }
}
