package net.simpotech.simpo.modules.vo;

import java.io.Serializable;
import java.sql.Date;


/**
 * UserVo
 * @author chenkun
 * @version 1.0
 * @since 2017Äê8ÔÂ2ÈÕ
 */
public class UserVo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private String agent;
	private String depart;
	private String delFlag;
	private Date addTime;
	private Date updateTime;
	
	/***********************/
	
	
	
	public String getName() {
		return name;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	@Override
	public String toString() {
		return "UserVo [id=" + id + ", name=" + name + ", agent=" + agent + ", depart=" + depart + "]";
	}
	
	
	
	
	
	
	
}
