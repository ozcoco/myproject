package me.wangolf.bean;

import java.io.Serializable;
import java.util.ArrayList;

import com.lidroid.xutils.db.annotation.Table;

@Table(name = "city")
public class CityEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private int pid;
    private String name;
    private int regtion_level;
    private String status;
    private String info;
    private ArrayList<CityEntity> data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRegtion_level() {
        return regtion_level;
    }

    public void setRegtion_level(int regtion_level) {
        this.regtion_level = regtion_level;
    }

    /**
     * 根据名称判断是否相等
     */
    @Override
    public boolean equals(Object o) {
        CityEntity c = (CityEntity) o;
        return name.equals(c.getName());
    }

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

    public ArrayList<CityEntity> getData() {
        return data;
    }

    public void setData(ArrayList<CityEntity> data) {
        this.data = data;
    }

}
