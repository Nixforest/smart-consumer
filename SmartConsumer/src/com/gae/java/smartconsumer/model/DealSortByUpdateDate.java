package com.gae.java.smartconsumer.model;

public class DealSortByUpdateDate implements java.util.Comparator<Deal>{
    public int compare(Deal deal1, Deal deal2){
        return deal1.getUpdateDate().compareTo(deal2.getUpdateDate());
    }
}
