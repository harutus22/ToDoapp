package com.example.apple.todoapp.Tools;

import android.text.TextUtils;
import android.util.Log;

import com.example.apple.todoapp.fragments.MainFragment;
import com.example.apple.todoapp.viewType.Info;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class SortingTool {

    private static final Comparator<Info> byTitle = new Comparator<Info>() {
        @Override
        public int compare(Info o1, Info o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };

    private static final Comparator<Info> byDate = new Comparator<Info>() {
        @Override
        public int compare(Info o1, Info o2) {
            String a = splitDate(o1);
            String b = splitDate(o2);
            return a.compareTo(b);
        }
    };

//TODO remade date sorting method

    private static final Comparator<Info> byPriority = new Comparator<Info>() {
        @Override
        public int compare(Info o1, Info o2) {
            return o1.getPriority().compareTo(o2.getPriority());
        }
    };

    public static List<Info> sort(List<Info> list, String sortType){
        switch(sortType) {
            case MainFragment.BY_TITLE:
                Collections.sort(list, byTitle);
                    return list;
            case MainFragment.BY_DATE:
                Collections.sort(list, byDate);
                    return list;
            case MainFragment.BY_PRIORITY:
                Collections.sort(list, byPriority);
                return list;
                default:
                    return list;
        }
    }

    private static String splitDate(Info info){
        String string = TextUtils.substring(info.getDate(), 0, 10);
        StringTokenizer tokenizer = new StringTokenizer(string, "/");
        String day = tokenizer.nextToken();
        String month = tokenizer.nextToken();
        String year = tokenizer.nextToken();
        String hour = TextUtils.substring(info.getDate(), 10, info.getDate().length());
        Log.println(6, hour, hour);
        return year + "/" + month + "/" + day + "/" + hour;
    }
}
