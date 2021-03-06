package com.happydoc.lewis.myapplication.fragmentinfo;

import android.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Lewis on 2016/7/18.
 */
public class FragmentInfo {
    public Fragment fragment;
    public String fragmentId;
    public String fragmentTitle;
    public int lightBtn;
    public int pressRes;
    public int releaseRes;
    public ImageView btnImg;
    public TextView btnText;
    public boolean isShowReload;
    public boolean showTopBar;
    boolean goBack;
    public int titleStringId;
    public boolean backBtnEnable;
    public boolean searchBtnEnable;
    public boolean getIsShowReload(){
        return this.isShowReload;
    }

    public void setIsShowReload(boolean isShowReload) {
        this.isShowReload = isShowReload;
    }

    public void setGoBack(boolean goBack) {
        this.goBack = goBack;
    }
    public boolean getGoBack() {
        return this.goBack;
    }

    public void setShowTopBar(boolean showTopBar) {
        this.showTopBar = showTopBar;
    }
    public boolean getShowTopBar(){
        return this.showTopBar;
    }
}
