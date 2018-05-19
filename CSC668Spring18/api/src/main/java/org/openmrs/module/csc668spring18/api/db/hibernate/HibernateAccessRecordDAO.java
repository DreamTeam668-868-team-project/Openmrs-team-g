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
package org.openmrs.module.csc668spring18.api.db.hibernate;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.csc668spring18.AccessRecord;
import org.openmrs.module.csc668spring18.api.db.AccessRecordDAO;

/**
 * It is a default implementation of {@link AccessRecordDAO}.
 */
public class HibernateAccessRecordDAO implements AccessRecordDAO {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public AccessRecord getRecordByUuid(String uuid) {
		return (AccessRecord) getSession().createCriteria(AccessRecord.class).add(Restrictions.eq("uuid", uuid))
		        .uniqueResult();
	}
	
	public AccessRecord getRecord(Integer id) {
		return (AccessRecord) getSession().createCriteria(AccessRecord.class).add(Restrictions.eq("record_id", id))
		        .uniqueResult();
	}
	
	public List<AccessRecord> getAllRecords() {
		return (List<AccessRecord>) getSession().createCriteria(AccessRecord.class).list();
	}
	
	public List<AccessRecord> getRecordsByDate(Date date) {
		Criteria criteria = getSession().createCriteria(AccessRecord.class);
		criteria.add(Restrictions.eq("access_date", date));
		return (List<AccessRecord>) criteria.list();
	}
	
	public List<AccessRecord> getRecordsByUser(Integer userId) {
		Criteria criteria = getSession().createCriteria(AccessRecord.class);
		criteria.add(Restrictions.eq("accessing_user", userId));
		return (List<AccessRecord>) criteria.list();
	}
	
	public List<AccessRecord> getRecordsByTimeframe(Date start, Date end) {
		Criteria criteria = getSession().createCriteria(AccessRecord.class);
		criteria.add(Restrictions.ge("access_date", start));
		criteria.add(Restrictions.le("access_date", end));
		return (List<AccessRecord>) criteria.list();
	}
	
	public List<AccessRecord> getRecordsByUserandDate(Integer userId, Date date) {
		Criteria criteria = getSession().createCriteria(AccessRecord.class);
		criteria.add(Restrictions.eq("accessing_user", userId));
		criteria.add(Restrictions.eq("access_date", date));
		return (List<AccessRecord>) criteria.list();
	}
	
	public List<AccessRecord> getRecordsByUserandTimeframe(Integer userId, Date start, Date end) {
		Criteria criteria = getSession().createCriteria(AccessRecord.class);
		criteria.add(Restrictions.eq("accessing_user", userId));
		criteria.add(Restrictions.ge("access_date", start));
		criteria.add(Restrictions.le("access_date", end));
		return (List<AccessRecord>) criteria.list();
	}
	
	public AccessRecord saveRecord(AccessRecord record) {
		getSession().saveOrUpdate(record);
		return record;
	}
	
}