package com.kilingzhang.dianvideo.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kilingzhang.dianvideo.ApiURL;
import com.kilingzhang.dianvideo.Bean.RealVideoUrlApiBean;
import com.kilingzhang.dianvideo.R;
import com.kilingzhang.dianvideo.utils.HttpUtil;

import java.io.IOException;

import es.dmoral.toasty.Toasty;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class VideoPlayer extends AppCompatActivity {
    private PlayerView player;
    private String videoUrl;
    private String realVideoUrl;
    private RealVideoUrlApiBean realVideo;
    private static final String TAG = "VideoPlayer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_player_view_player);
        Intent intent = getIntent();
        videoUrl = intent.getStringExtra("url").toString().trim();
        if (videoUrl.equals("")) {
            finish();
        }
        loadVideo(videoUrl);


    }

    public void loadVideo(String videoUrl) {
        HttpUtil.sendGetOkhttpRequest(ApiURL.getGetVideoUrlApi() + "?url=" + videoUrl, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Toasty.error(getApplicationContext(), "宝宝,请求失败了~重试下,还不行私发我~", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.d(TAG, "onResponse: " + json);
                Gson gson = new Gson();
                realVideo = gson.fromJson(json, RealVideoUrlApiBean.class);
                if (realVideo.getMsg().equals("200")) {
                    realVideoUrl = realVideo.getUrl();
                }
                if (realVideoUrl.equals("")) {
                    Toasty.warning(getApplicationContext(), "宝宝视频地址有错误哦~重试下,还不行私发我~", Toast.LENGTH_SHORT).show();
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            player = new PlayerView(VideoPlayer.this)
                                    .setTitle("视频")
                                    .setScaleType(PlayStateParams.fitparent)
                                    .hideMenu(true)
                                    .forbidTouch(false)
                                    .showThumbnail(new OnShowThumbnailListener() {
                                        @Override
                                        public void onShowThumbnail(ImageView ivThumbnail) {
                                            Glide.with(getApplicationContext())
                                                    .load("http://markdown-1252847423.file.myqcloud.com/20170303012356665.jpeg")
                                                    .placeholder(R.color.cl_default)
                                                    .error(R.color.cl_error)
                                                    .into(ivThumbnail);
                                        }
                                    })
                                    .setPlaySource(realVideoUrl)
                                    .startPlay();
                        }
                    });
                }


            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
        /**demo的内容，恢复系统其它媒体的状态*/
        //MediaUtils.muteAudioFocus(mContext, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
        /**demo的内容，暂停系统其它媒体的状态*/
        //MediaUtils.muteAudioFocus(getApplicationContext(), false);
        /**demo的内容，激活设备常亮状态*/
        //if (wakeLock != null) {
        //    wakeLock.acquire();
        //}
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        }
        super.onBackPressed();
        /**demo的内容，恢复设备亮度状态*/
        //if (wakeLock != null) {
        //    wakeLock.release();
        //}
    }


}
