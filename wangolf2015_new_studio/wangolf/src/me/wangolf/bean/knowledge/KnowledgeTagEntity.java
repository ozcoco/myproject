package me.wangolf.bean.knowledge;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ============================================================

 * ============================================================
 */
public class KnowledgeTagEntity implements Serializable 
{

    private String status;
    private String info;
    private int id;
    private ArrayList<KnowledgeTagEntity> data;
    private String name;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<KnowledgeTagEntity> getData() {
        return data;
    }

    public void setData(ArrayList<KnowledgeTagEntity> data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
