package com.bigbang.baseframe.bean.request;

/**
 * Created by Administrator on 2018/2/7.
 */

public class SearchRequestBean {

    /**
     * q : 张艺谋
     * tag : 喜剧
     * start : 0
     * count : 20
     */

    private String q;
    private String tag;
    private Integer start;
    private Integer count;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
