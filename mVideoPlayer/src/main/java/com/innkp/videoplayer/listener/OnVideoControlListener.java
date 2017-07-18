package com.innkp.videoplayer.listener;

import com.innkp.videoplayer.view.VideoErrorView;

public interface OnVideoControlListener {

    /**
     * 返回
     */
    void onBack();

    /**
     * 全屏
     */
    void onFullScreen();

    /**
     * 错误后的重试
     *
     * @param errorStatus 当前错误状态
     *                    <ul>
     *                    <li>{@link VideoErrorView#STATUS_NORMAL}
     *                    <li>{@link VideoErrorView#STATUS_VIDEO_DETAIL_ERROR}
     *                    <li>{@link VideoErrorView#STATUS_VIDEO_SRC_ERROR}
     *                    <li>{@link VideoErrorView#STATUS_UN_WIFI_ERROR}
     *                    <li>{@link VideoErrorView#STATUS_NO_NETWORK_ERROR}
     *                    </ul>
     */
    void onRetry(int errorStatus);

}
