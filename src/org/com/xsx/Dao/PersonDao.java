package org.com.xsx.Dao;

import org.com.xsx.Domain.PersonBean;
import org.com.xsx.Tools.HibernateDao;

public class PersonDao {

	public static PersonBean QueryPerson(int id) {
		String hql = "select c from PersonBean as c where c.id=:parm0 ";
		
		return (PersonBean) HibernateDao.GetInstance().queryOne(hql.toString(), new Object[]{id});
	}
}
