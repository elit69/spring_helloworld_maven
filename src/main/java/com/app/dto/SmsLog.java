package com.app.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "sms_log")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class SmsLog {
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "msgid")
	private String msgid;
	@Column(name = "status")
	private String status;
	@Column(name = "code")
	private String code;
	@Column(name = "phone")
	private String phone;
	@Column(name = "date")
	private String date;

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
