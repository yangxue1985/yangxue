package com.innkp.innovate;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.innkp.videoplayer.BDVideoView;
import com.innkp.videoplayer.listener.SimpleOnVideoControlListener;
import com.innkp.videoplayer.utils.DisplayUtils;
import com.innkp.innovate.dto.VideoDetailInfo;

public class CourseActivity extends BaseActivity {
    private static final String TAG = CourseActivity.class.getName();
    private Toolbar mToolbar;
    private BDVideoView videoView;
    public static void start(Context context, VideoDetailInfo info) {
        Intent intent = new Intent(context, CourseActivity.class);
        intent.putExtra("info", info);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
//        initActionBar();
        VideoDetailInfo info = (VideoDetailInfo) getIntent().getSerializableExtra("info");

        videoView = (BDVideoView) findViewById(R.id.vv);
        videoView.setOnVideoControlListener(new SimpleOnVideoControlListener() {

            @Override
            public void onRetry(int errorStatus) {
                // TODO: 2017/6/20 调用业务接口重新获取数据
                // get info and call method "videoView.startPlayVideo(info);"
            }

            @Override
            public void onBack() {
                onBackPressed();
            }

            @Override
            public void onFullScreen() {
                DisplayUtils.toggleScreenOrientation(CourseActivity.this);
            }
        });
        videoView.startPlayVideo(info);
    }

    private void initActionBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_sub);
        TextView toolbar_name = (TextView) findViewById(R.id.toolbar_name);
        toolbar_name.setText(getString(R.string.title_course));
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_return);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
//        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        videoView.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();

        videoView.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        videoView.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (!DisplayUtils.isPortrait(this)) {
            if(!videoView.isLock()) {
                DisplayUtils.toggleScreenOrientation(this);
            }
        } else {
            super.onBackPressed();
        }
    }
}
