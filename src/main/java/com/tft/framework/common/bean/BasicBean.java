package com.tft.framework.common.bean;

import com.tft.framework.common.util.TftWebContext;
import com.tft.framework.user.bean.User;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.common.util.StringHelper;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class BasicBean {
	
	@Id
	@GeneratedValue(generator="tft")
	@GenericGenerator(name="tft",strategy="uuid")
	private String id;

	private String creatorName;//创建者名称
	private String creatorId;//创建者编码
	private Date creatorTime;//创建时间
	private String modifierName;//修改者名称
	private String modifierId;//修改者编码
	private Date modifierTime;//修改时间

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getModifierId() {
		return modifierId;
	}

	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}

	public void setBaseInfo(){
		User user = TftWebContext.getLoginUser();
		if(user==null){
			user = new User();
			user.setUserName("系统创建");
			user.setId("system");

		}
		if(StringHelper.isEmpty(this.id)){
			this.creatorName = user.getUserName();
			this.creatorId = user.getId();
			this.creatorTime = new Date();
		}
		this.modifierName = user.getUserName();
		this.modifierId = user.getId();
		this.modifierTime = new Date();
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Date getCreatorTime() {
		return creatorTime;
	}

	public void setCreatorTime(Date creatorTime) {
		this.creatorTime = creatorTime;
	}

	public String getModifierName() {
		return modifierName;
	}

	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
	}

	public Date getModifierTime() {
		return modifierTime;
	}

	public void setModifierTime(Date modifierTime) {
		this.modifierTime = modifierTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
