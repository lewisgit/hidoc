package com.happydoc.lewis.myapplication.utils;

/**
 * Created by Lewis on 2016/7/23.
 */

import android.content.Context;

import com.avos.avoscloud.im.v2.AVIMMessage;
import com.avos.avoscloud.im.v2.AVIMReservedMessageType;
import com.avos.avoscloud.im.v2.AVIMTypedMessage;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;
import com.avoscloud.leanchatlib.controller.EmotionHelper;

import org.ocpsoft.prettytime.PrettyTime;

import java.io.Closeable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lzw on 15/4/27.
 */
public class TimeUtils {

    public static String millisecsToDateString(long timestamp) {
        long gap = System.currentTimeMillis() - timestamp;
        if (gap < 1000 * 60 * 60 * 24) {
            String s = (new PrettyTime()).format(new Date(timestamp));
            return s;
        } else {
            SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
            return format.format(new Date(timestamp));
        }
    }

    public static void closeQuietly(Closeable closeable) {
        try {
            closeable.close();
        } catch (Exception e) {
        }
    }

    public static CharSequence getMessageeShorthand(Context context, AVIMMessage message) {
        if (message instanceof AVIMTypedMessage) {
            AVIMReservedMessageType type = AVIMReservedMessageType.getAVIMReservedMessageType(((AVIMTypedMessage) message).getMessageType());
            switch (type) {
                case TextMessageType:
                    return EmotionHelper.replace(context, ((AVIMTextMessage) message).getText());
                case ImageMessageType:
                    return "[图片]";
                case LocationMessageType:
                    return "[位置]";
                case AudioMessageType:
                    return "[语音]";
                default:
                    return "[未知]";
            }
        } else {
            return message.getContent();
        }
    }
}

