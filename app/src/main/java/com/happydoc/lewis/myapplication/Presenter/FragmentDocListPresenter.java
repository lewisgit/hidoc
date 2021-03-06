package com.happydoc.lewis.myapplication.Presenter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.happydoc.lewis.myapplication.Bean.GlobalInfos;
import com.happydoc.lewis.myapplication.Model.FragmentDocListModel;
import com.happydoc.lewis.myapplication.R;
import com.happydoc.lewis.myapplication.View.FragmentDocListView;
import com.happydoc.lewis.myapplication.account.DoctorInfo;
import com.happydoc.lewis.myapplication.event.MainActivityEventBus;
import com.happydoc.lewis.myapplication.event.MyCallBack;
import com.happydoc.lewis.myapplication.event.SearchDocEvent;
import com.happydoc.lewis.myapplication.fragmentinfo.CarouselItem;
import com.happydoc.lewis.myapplication.scrollimage.image.ImageScroll;
import com.happydoc.lewis.myapplication.search.SearchCriteria;

import java.util.List;

/**
 * Created by Lewis on 2016/7/18.
 */
public class FragmentDocListPresenter {
    public FragmentDocListView view;
    public FragmentDocListModel model;
    ImageScroll myPager; // 图片容器
    LinearLayout ovalLayout; // 圆点容器
    public FragmentDocListPresenter(FragmentDocListView view,FragmentDocListModel model){
        this.view=view;
        this.model=model;
        MainActivityEventBus.getEventBus().register(this);
        GlobalInfos.addListRegObj(this);
        initialize();
    }
    public void initialize(){
        view.initView();
        model.getScrollImageData(new MyCallBack<List<CarouselItem>>() {
            @Override
            public void done(List<CarouselItem> data) {
                view.initScrollData(data);
            }
        });
        SearchCriteria criteria=new SearchCriteria();
        criteria.setChildMajor(false);
        model.getDocListData(criteria, new MyCallBack<List<DoctorInfo>>() {
            @Override
            public void done(List<DoctorInfo> data) {view.setOnDoc1Btn();view.setListViewData(data);
            }
        });
        view.setListVIewItemClickListener();
    }

    public void onEvent(SearchDocEvent event){
        if(event.getCriteria().getIsChileMajor()){view.setOnDoc2Btn();}else{view.setOnDoc1Btn();}
        model.getDocListData(event.getCriteria(), new MyCallBack<List<DoctorInfo>>() {
            @Override
            public void done(List<DoctorInfo> data) {
                view.setListViewData(data);
            }
        });
    }
}
