package com.happydoc.lewis.myapplication.circle.mvp.contract;

import com.happydoc.lewis.myapplication.circle.bean.CircleItem;
import com.happydoc.lewis.myapplication.circle.bean.CommentConfig;
import com.happydoc.lewis.myapplication.circle.bean.CommentItem;
import com.happydoc.lewis.myapplication.circle.bean.FavortItem;

import java.util.List;

/**
 * Created by suneee on 2016/7/15.
 */
public interface CircleContract {

    interface View extends BaseView{
        void update2DeleteCircle(String circleId);
        void update2AddFavorite(int circlePosition, FavortItem addItem);
        void update2DeleteFavort(int circlePosition, String favortId);
        void update2AddComment(int circlePosition, CommentItem addItem);
        void update2DeleteComment(int circlePosition, String commentId);
        void updateEditTextBodyVisible(int visibility, CommentConfig commentConfig);
        void update2loadData(int loadType, List<CircleItem> datas);
    }

    interface Presenter extends BasePresenter{
        void loadData(int loadType,int skipNum);
        void deleteCircle(final String circleId,CircleItem circleItem);
        void addFavort(final int circlePosition,CircleItem item);
        void deleteFavort(final int circlePosition, final String favortId,CircleItem item);
        void deleteComment(final int circlePosition, final String commentId,CommentItem commentItem,CircleItem circleItem);

    }
}
