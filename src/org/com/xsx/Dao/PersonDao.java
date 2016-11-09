package org.com.xsx.Dao;

import org.com.xsx.Domain.PersonBean;
import org.com.xsx.Tools.HibernateDao;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

public class PersonDao {

	public static PersonBean QueryPerson(Integer id) {
		String hql = "select c from PersonBean as c where c.id=:parm0 ";
		
		if(id == null)
			return null;
		
		return (PersonBean) HibernateDao.GetInstance().queryOne(hql.toString(), new Object[]{id});
	}
}
