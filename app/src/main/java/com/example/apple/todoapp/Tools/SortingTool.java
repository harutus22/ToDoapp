package com.example.apple.todoapp.Tools;

import com.example.apple.todoapp.fragments.MainFragment;
import com.example.apple.todoapp.viewType.Info;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
            return o1.getDate().compareTo(o2.getDate());
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
}
