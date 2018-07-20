package mfw.index.client.test.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;


public class User implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    
    protected Long id;   //userid
    
    
    protected String username; //用户名
    
    
    protected String nickname; //昵称 
    
    
    protected Integer sex; //性别  1：男 2：女 3：保密
    
    
    protected String mobile; //手机号
    
    
    protected String email;  //邮箱
    
    
    protected Integer userType; //用户类型
    
    
    protected Integer status; //用户状态  1：有效 2：无效
    
    private LocalDateTime birthday;
    
    
    public LocalDateTime getBirthday() {
		return birthday;
	}


	public void setBirthday(LocalDateTime birthday) {
		this.birthday = birthday;
	}
    
    
    
    public User() {
        super();
    }


    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public String getUsername() {
        return "".equals(username) ?null:username;
    }



    public void setUsername(String username) {
        this.username = username;
    }



    public String getNickname() {
        return  "".equals(nickname) ?null:nickname;
    }



    public void setNickname(String nickname) {
        this.nickname = nickname;
    }



    public Integer getSex() {
        return sex;
    }



    public void setSex(Integer sex) {
        this.sex = sex;
    }



    public String getMobile() {
        return  "".equals(mobile) ?null:mobile;
    }



    public void setMobile(String mobile) {
        this.mobile = mobile;
    }



    public String getEmail() {
        return  "".equals(email) ?null:email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public Integer getUserType() {
        return userType;
    }



    public void setUserType(Integer userType) {
        this.userType = userType;
    }



    public Integer getStatus() {
        return status;
    }



    public void setStatus(Integer status) {
        this.status = status;
    }

    
    

    
    
    
    

}
