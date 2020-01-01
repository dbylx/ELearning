package com.elearing.catchPackage.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Course implements Serializable {
	@PrimaryKey
	@NonNull
	private String id;
	private String name;
	private String code;
	private String categoryId;
	private String description;
	private String price;
	private String status;
	private String openDate;
	private String lastUpdateOn;
	private String level;
	private String shared;
	private String sharedUrl;
	private String avatar;
	private String bigAvatar;
	private String certification;
	private String certificationDuration;

	private int showType = 1;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getLastUpdateOn() {
		return lastUpdateOn;
	}

	public void setLastUpdateOn(String lastUpdateOn) {
		this.lastUpdateOn = lastUpdateOn;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getShared() {
		return shared;
	}

	public void setShared(String shared) {
		this.shared = shared;
	}

	public String getSharedUrl() {
		return sharedUrl;
	}

	public void setSharedUrl(String sharedUrl) {
		this.sharedUrl = sharedUrl;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getBigAvatar() {
		return bigAvatar;
	}

	public void setBigAvatar(String bigAvatar) {
		this.bigAvatar = bigAvatar;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public String getCertificationDuration() {
		return certificationDuration;
	}

	public void setCertificationDuration(String certificationDuration) {
		this.certificationDuration = certificationDuration;
	}

	public int getShowType() {
		return showType;
	}

	public void setShowType(int showType) {
		this.showType = showType;
	}

}