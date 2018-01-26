package com.zhangyingwei.miner.service.date.model;

/**
 * Created by zhangyw on 2017/8/15.
 */
public class Resources {
    public static final Integer FLAG_INIT = 0;
    public static final Integer FLAG_NOMAL = 1;
    public static final Integer FLAG_UNVALID = 2;
    public static final Integer FLAG_DEL = 3;
    private Integer id;
    private String resources;
    private String rgroup;
    private String rtype;
    private String createdate;
    private String updatedate;
    private Integer flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getRgroup() {
        return rgroup;
    }

    public void setRgroup(String rgroup) {
        this.rgroup = rgroup;
    }

    public String getRtype() {
        return rtype;
    }

    public void setRtype(String rtype) {
        this.rtype = rtype;
    }

    @Override
    public String toString() {
        return "Resources{" +
                "id=" + id +
                ", resources='" + resources + '\'' +
                ", group='" + rgroup + '\'' +
                ", type='" + rtype + '\'' +
                ", createdate='" + createdate + '\'' +
                ", updatedate='" + updatedate + '\'' +
                ", flag=" + flag +
                '}';
    }
}
