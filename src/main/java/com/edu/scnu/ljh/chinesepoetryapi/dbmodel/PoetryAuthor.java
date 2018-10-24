package com.edu.scnu.ljh.chinesepoetryapi.dbmodel;

public class PoetryAuthor {
    private int id;
    private String name;
    private String intro;
    private String dynasty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    @Override
    public String toString() {
        return "PoetryAuthor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", intro='" + intro + '\'' +
                ", dynasty=" + dynasty +
                '}';
    }
}
