package com.txt.javatest.bean;

/**
 * Created by huangyuansheng on 17-4-20.
 */

public class NewsResponseBean {
    private String cp;
    private NewsBean news;
    private int index;

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public NewsBean getNews() {
        return news;
    }

    public void setNews(NewsBean news) {
        this.news = news;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
