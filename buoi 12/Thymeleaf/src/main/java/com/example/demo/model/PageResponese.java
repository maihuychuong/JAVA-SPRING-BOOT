package com.example.demo.model;

import java.util.List;

public class PageResponese<T> {
    private List<T> resources;
    private int pageSize;
    private int currentPage;

    public PageResponese(List<T> resources, int pageSize, int currentPage) {
        this.resources = resources;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages(){
        return (int) Math.ceil((double)resources.size()/ pageSize);
    }

    public List<T> getData(){
        int startIndex = (currentPage -1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, resources.size());
        return resources.subList(startIndex, endIndex);
    }
}
