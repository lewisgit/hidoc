package com.happydoc.lewis.myapplication.Presenter;

import com.happydoc.lewis.myapplication.Bean.GlobalInfos;
import com.happydoc.lewis.myapplication.Bean.UserInfo;
import com.happydoc.lewis.myapplication.Model.UserInfoModel;
import com.happydoc.lewis.myapplication.View.MeView;
import com.happydoc.lewis.myapplication.event.MyCallBack;

/**
 * Created by Lewis on 2016/7/23.
 */
public class MePresenter implements GenPresenter{
    private MeView view;
    private UserInfoModel model;
    //private UserInfo userInfo;
    public MePresenter(MeView view,UserInfoModel model){
        this.view=view;
        this.model=model;
        initView();
    }

    @Override
    public void refreshView() {
        loadUserInfo();
    }
    @Override
    public void initView(){
        view.setView();
        model.getUserInfo(new MyCallBack<UserInfo>() {
            @Override
            public void done(UserInfo data) {
                GlobalInfos.setUserInfo(data);
                loadUserInfo();
            }
        });
        view.setOnClickListner();

    }
    public void loadUserInfo(){
        view.showInfo(GlobalInfos.getUserInfo());

    }
}
