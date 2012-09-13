package com.gae.java.smartconsumer.model;

public class DealSortById implements java.util.Comparator<Deal>{
    public int compare(Deal deal1, Deal deal2){
        return deal1.getId().compareTo(deal2.getId());
    }
}
