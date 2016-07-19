package com.happydoc.lewis.myapplication.View;

import android.app.Fragment;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Lewis on 2016/7/18.
 */
public class FragmentView {
    public Fragment fragment;
    public View view;
    public FragmentView(Fragment fragment,View view){
        this.fragment=fragment;
        this.view=view;
    }
    public View getView(int id){
        return view.findViewById(id);
    }
    public void showMsg(int id){
        Toast.makeText(view.getContext(),fragment.getString(id),Toast.LENGTH_LONG).show();
    }
}
