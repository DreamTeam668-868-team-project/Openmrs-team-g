/**
 * The contents of this file are subject to the OpenMRS Public License Version
 * 1.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 *
 * Copyright (C) OpenMRS, LLC. All Rights Reserved.
 */
package org.openmrs.module.csc668spring18;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import org.openmrs.BaseOpenmrsObject;
import org.openmrs.User;

/**
 * @author Travis
 */
public class AccessRecord extends BaseOpenmrsObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private User accessingUser;
	
	private Locale accessLocale;
	
	private Date accessedOn;
	
	private Integer recordId;
	
	private String recordType;
	
	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setId(Integer id) {
		
		this.id = id;
	}
	
	@Override
	public String getUuid() {
		return super.getUuid();
	}
	
	@Override
	public void setUuid(String uuid) {
		super.setUuid(uuid);
	}
	
	public User getAccessingUser() {
		return this.accessingUser;
	}
	
	public void setAccessingUser(User user) {
		this.accessingUser = user;
	}
	
	public Locale getAccessLocale() {
		return this.accessLocale;
	}
	
	public void setAccessLocale(Locale location) {
		this.accessLocale = location;
	}
	
	public void setAccessedOn(Date date) {
		this.accessedOn = date;
	}
	
	public Date getAccessedOn() {
		return this.accessedOn;
	}
	
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	
	public Integer getRecordId() {
		return this.recordId;
	}
	
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	
	public String getRecordType() {
		return this.recordType;
	}
}
