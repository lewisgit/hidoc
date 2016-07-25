package com.happydoc.lewis.myapplication.View;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.happydoc.lewis.myapplication.App;
import com.happydoc.lewis.myapplication.Bean.BtnInfo;
import com.happydoc.lewis.myapplication.MainActivity;
import com.happydoc.lewis.myapplication.R;
import com.happydoc.lewis.myapplication.event.MainActivityEventBus;
import com.happydoc.lewis.myapplication.event.ShowFragmentEvent;
import com.happydoc.lewis.myapplication.event.StartSendCircleEvent;
import com.happydoc.lewis.myapplication.fragment.DocListFragment;
import com.happydoc.lewis.myapplication.fragment.GeneralFragment;
import com.happydoc.lewis.myapplication.fragmentinfo.FragmentInfo;

import de.greenrobot.event.EventBus;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Lewis on 2016/7/15.
 */
public class MainActivityView extends MotherView implements ActivityView{
    public Fragment currentFragment;
    public FragmentInfo currentFragmentInfo;
    public FragmentInfo backFragmentInfo;
    public Fragment backFragment;

    public int fragmentRegionId;
    public EventBus eventBus;
    public TextView titleView;
    //View
    public ImageView sendCircle;

    //currentBtn;
    BtnInfo curBtn;

    public HashMap<String,FragmentInfo> fragmentList=new HashMap<>();
    public MainActivityView(AppCompatActivity activity){
        super(activity);
        //setFragmentList();
    }
    public void setFragmentList(List<FragmentInfo> fragmentInfos){
        for(FragmentInfo fragmentInfo:fragmentInfos){
            fragmentList.put(fragmentInfo.fragmentId, fragmentInfo);
        }
    }
    @Override
    public void showFragment(int id){
        FragmentInfo fragmentInfo=fragmentList.get(activity.getString(id));
        Fragment fragment=fragmentInfo.fragment;
        FragmentTransaction fragmentTransaction=activity.getFragmentManager().beginTransaction();
        if(currentFragment!=fragment && fragment!=null){
            if(currentFragment!=null){
            if(fragment.isAdded()) {
                if(fragmentInfo.getIsShowReload())
                ((GeneralFragment)fragment).getPresenter().refreshView();
                fragmentTransaction.hide(currentFragment).show(fragment).commit();
            }else{
                fragmentTransaction.hide(currentFragment).add(fragmentRegionId,fragment).commit();
            }
                if(fragmentInfo.btnImg!=null){
                    setBtn(fragmentInfo.btnImg,fragmentInfo.pressRes,fragmentInfo.btnText,R.color.doc_blue);

                if(curBtn!=null)
                    if(curBtn.getImg()!=fragmentInfo.btnImg)
                    setBtn(curBtn.getImg(), curBtn.getImgResId(), curBtn.getTxt(), R.color.doc_gray);
                    BtnInfo newBtn=new BtnInfo();
                    newBtn.setImg(fragmentInfo.btnImg);
                    newBtn.setImgResId(fragmentInfo.releaseRes);
                    newBtn.setTxt(fragmentInfo.btnText);
                    curBtn=newBtn;
                }

                //Set Fragment Title

            }else{
                fragmentTransaction.add(fragmentRegionId,fragment).commit();
                if(fragmentInfo.btnImg!=null){
                    setBtn(fragmentInfo.btnImg,fragmentInfo.pressRes,fragmentInfo.btnText,R.color.doc_blue);
                BtnInfo newBtn=new BtnInfo();
                newBtn.setImg(fragmentInfo.btnImg);
                newBtn.setImgResId(fragmentInfo.releaseRes);
                newBtn.setTxt(fragmentInfo.btnText);
                curBtn=newBtn;}
            }
            currentFragment=fragment;
            currentFragmentInfo=fragmentInfo;
            if(titleView!=null) titleView.setText(fragmentInfo.fragmentTitle);
        }
    }
    //set btn view of bottom bar
    public void setBtn(ImageView view, int resId, TextView textView,int color) {
        view.setImageResource(resId);
        textView.setTextColor(ContextCompat.getColor(activity,color));
    }
    public void setFragmentRegionId(int id){
        fragmentRegionId=id;
    }
    public void setEventBus(EventBus eventBus){
        this.eventBus=eventBus;
        MainActivityEventBus.setEventBus(eventBus);
        //MainActivity mainActivity=(MainActivity)activity;
        //mainActivity.setEventBus(eventBus);
    }
    public EventBus getEventBus(){
        if(eventBus==null){
            eventBus=new EventBus();
            return eventBus;
        }else{return eventBus;}
    }

    public void setBtnOnClickListener(){


        sendCircle=(ImageView) getView(R.id.send_circle_btn);
        sendCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventBus.post(new StartSendCircleEvent());
            }
        });

        TextView doclistBtnText=(TextView) activity.findViewById(R.id.doclist_btn_text);
        ImageView doclistBtn=(ImageView) activity.findViewById(R.id.docList_btn);
        View.OnClickListener listener1=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventBus.post(new ShowFragmentEvent(R.string.doclist_script));
                //showMsg("testBtn!");
            }};
        if(doclistBtn!=null && doclistBtnText!=null){
        doclistBtn.setOnClickListener(listener1);
        doclistBtnText.setOnClickListener(listener1);}

        TextView videoBtnText=(TextView) activity.findViewById(R.id.video_btn_text);
        ImageView videoBtn=(ImageView) activity.findViewById(R.id.video_btn);
        View.OnClickListener listener2=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventBus.post(new ShowFragmentEvent(R.string.video_script));
                //showMsg("testBtn!");
            }};
        if(videoBtnText!=null && videoBtn!=null){
        videoBtn.setOnClickListener(listener2);
        videoBtnText.setOnClickListener(listener2);}

        TextView circleBtnText=(TextView) activity.findViewById(R.id.circle_btn_text);
        ImageView circleBtn=(ImageView) activity.findViewById(R.id.circle_btn);
        View.OnClickListener listener3=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventBus.post(new ShowFragmentEvent(R.string.circle_scriptt));
                //showMsg("testBtn!");
                }};
        if(circleBtnText!=null && circleBtn!=null){
        circleBtn.setOnClickListener(listener3);
        circleBtnText.setOnClickListener(listener3);}


        TextView meBtnTxt=(TextView) getView(R.id.me_btn_text);
        ImageView meBtn=(ImageView) getView(R.id.me_btn);
        View.OnClickListener listener4=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventBus.post(new ShowFragmentEvent(R.string.me_script));
            }
        };
        if(meBtn!=null) meBtn.setOnClickListener(listener4);
        meBtnTxt.setOnClickListener(listener4);
    }
    public void setView(){
        titleView=(TextView)getView(R.id.tobbar_script);
    }


}
