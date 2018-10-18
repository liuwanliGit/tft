package com.tft.framework.user.bean;

import com.tft.framework.common.bean.BasicBean;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/24 22:02
 *
 * @ClassName User
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@Entity
@Table(name="TFT_COMM_USER")
public class User extends BasicBean {

    private String userName;       //用户名

    private String passWord;       //密码

    private String headIcon;       //头像

    private String email;          //邮箱

    private String sex;            //性别

    private String tel;             //手机号

    private Date birthday;         //生日

    private String signature;       //签名

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
