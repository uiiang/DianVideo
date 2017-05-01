package com.kilingzhang.dianvideo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kilingzhang.dianvideo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Bind(R.id.video_url)
    EditText videoUrl;
    @Bind(R.id.load_video)
    ImageButton loadVideo;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initEvents();

    }

    private void initEvents() {
        loadVideo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.load_video:
                Intent intent = new Intent(this, VideoPlayer.class);
                String url = videoUrl.getText().toString().trim();
                if (url.equals("")) {
                    Toasty.warning(getApplicationContext(), "宝宝,不输入地址可不行哦~", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.putExtra("url", url);
                startActivity(intent);
                break;
        }
    }
}
