package mfw.index.client.model;

import java.time.LocalDateTime;

/**
 *
 * create by wanjianyu
 */

public class SRegion {

    private String id;
    /**
     * 区域名称
     */
    private String name;
    /**
     * 所属大区
     */
    private String largeWard;
    /**
     * 区域序号
     */
    private int sortOrder;
    /**
     * 状态
     */
    private int status;
    /**
     * 操作人
     */
    private int createUser;
    /**
     * 操作时间
     */
    private LocalDateTime createTime;
    /**
     * 修改人
     */
    private int updateUser;


    public SRegion() {
        super();
    }


    public int getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public int getCreateUser() {
        return createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getLargeWard() {
        return largeWard;
    }

    public void setLargeWard(String largeWard) {
        this.largeWard = largeWard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
