package org.com.xsx.Tools;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria; 
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;  
import org.hibernate.Transaction;
import org.jboss.logging.Param;

public class HibernateDao{  
	
	private static HibernateDao S_HibernateDao = null;
	
    protected Session session = null;  
    protected Transaction ts = null;  
    protected Criteria criteria;  
    
    private HibernateDao(){
    	
    }
    
    public static HibernateDao GetInstance(){
    	if(S_HibernateDao == null)
    		S_HibernateDao = new HibernateDao();
    	
    	return S_HibernateDao;
    }
    
    /** 
     * session保存方法 
     * */  
    public boolean save(Object obj){  
    	Session session=null;  
        Transaction tran=null;  
        boolean result=false;  
        try  
        {  
            session = HibernateSessionBean.GetInstance().getSession();
            tran = session.beginTransaction();  
            session.saveOrUpdate(obj);
            tran.commit();  
            result=true;  
        }  
        catch (Exception e)  
        {  
           if(tran!=null)  
           {  
               //事物回滚  
               tran.rollback();  
           }  
        }  
        finally  
        {  
            if(session!=null)  
            {  
                //关闭session  
                session.close();  
            }  
        }  
        return result;
    }  
    /** 
     * @param obj 
     * @return 删除数据 
     */  
    public boolean delete(Object obj)  
      {  
            Session session=null;  
            Transaction tran=null;  
            boolean result=false;  
            try  
            {  
                session = HibernateSessionBean.GetInstance().getSession();  
                tran = session.beginTransaction();  
                session.delete(obj);  
                tran.commit();  
                result=true;  
            }  
            catch (Exception e)  
            {  
               if(tran!=null)  
               {  
                   //事物回滚  
                   tran.rollback();  
               }  
            }  
            finally  
            {  
                if(session!=null)  
                {  
                    //关闭session  
                    session.close();  
                }  
            }  
            return result;  
      }  
    
    /** 
     * @param c 
     * @param obj  查询一条数据根据主键的id号 
     * @return 
     */  
      public Object get(Class c,int obj)  
      {  
            Session session=null;  
            Object object=null;  
            try  
            {  
                session = HibernateSessionBean.GetInstance().getSession();  
                object=session.get(c,obj);  
            }  
            catch (Exception e)  
            {  
            }  
            finally  
            {  
                if(session!=null)  
                {  
                    //关闭session  
                    session.close();  
                }  
            }  
            return object;  
      }  
      
    /** 
     * session修改方法 
     * */  
    public boolean update(Object obj){  
    	Session session=null;  
        Transaction tran=null;  
        boolean result=false;  
        try  
        {  
            session = HibernateSessionBean.GetInstance().getSession();  
            tran=session.beginTransaction();  
            session.update(obj);  
            tran.commit();  
            result=true;  
        }  
        catch (Exception e)  
        {  
           if(tran!=null)  
           {  
               //事物回滚  
               tran.rollback();  
           }  
        }  
        finally  
        {  
            if(session!=null)  
            {  
                //关闭session  
                session.close();  
            }  
        }  
        return result; 
    }  
    
      /** 
     * @param sql 
     * @param param 查询单条记录 
     * @return 
     */  
    public Object queryOne(String sql,Object[] param)  
      {  
          Object object=null;  
          Session session=null;  
           try  
            {  
                session = HibernateSessionBean.GetInstance().getSession();  
                Query query=session.createQuery(sql);  
                if(param!=null)  
                {  
                    for(int i=0;i<param.length;i++)  
                    {
                    	if(param[i] != null){
                    		if(param[i] instanceof List<?>)
                    			query.setParameterList("parm"+i, (List) param[i]);
                    		else
                    			query.setParameter("parm"+i, param[i]); 
                    	}  
                    }  
                    
                }  
                object=query.uniqueResult();  
            }  
            catch (Exception e)  
            { 
            	System.out.println(e);
            }  
            finally  
            {  
                if(session!=null)  
                {  
                    session.close();  
                }  
            }  
          return object;  
      }
    
    public <T> List<T> query(String sql,Object[] param, Integer firstnum, Integer maxnum)  
    {  
        List<T> list=new ArrayList<T>();  
        Session session=null;  
         try  
          {  
              session = HibernateSessionBean.GetInstance().getSession();  
              Query query=session.createQuery(sql); 

              if(param!=null)  
              {  
                  for(int i=0;i<param.length;i++)  
                  {
                	  if(param[i] instanceof List<?>)
                		  query.setParameterList("parm"+i, (List) param[i]);
                	  else
                
                		  query.setParameter("parm"+i, param[i]);    
                  }  
              }
              if((firstnum != null)&&(maxnum != null)){
              	query.setFirstResult(firstnum.intValue());
              	query.setMaxResults(
              			maxnum.intValue());
              }
              list=query.list();
          }  
          catch (Exception e)  
          {
        	 e.printStackTrace();
          }  
          finally  
          {  
              if(session!=null)  
              {  
                  session.close();  
              }  
          }  
        return list;  
    }
    
    public <T> List<T> querysql(String sql, Object[][] c)  
    {  
        List<T> list=new ArrayList<T>();  
        Session session=null;  
         try  
          {  
              session = HibernateSessionBean.GetInstance().getSession();  
              SQLQuery query=session.createSQLQuery(sql);
              
              if(c != null){
            	  for (Object[] t : c) {
      				query.addEntity((String)t[0], (Class)t[1]);
      			}
              }
              
              list=query.list();  
          }  
          catch (Exception e)  
          {
        	  e.printStackTrace();
          }  
          finally  
          {  
              if(session!=null)  
              {  
                  session.close();  
              }  
          }  
        return list;  
    }
 
    public Object queryonesql(String sql)  
    {  
    	Object object=null; 
        Session session=null;  
         try  
          {  
              session = HibernateSessionBean.GetInstance().getSession();  
              SQLQuery query=session.createSQLQuery(sql);  

              object = query.uniqueResult();
          }  
          catch (Exception e)  
          {
        	  e.printStackTrace();
          }  
          finally  
          {  
              if(session!=null)  
              {  
                  session.close();  
              }  
          }  
        return object;  
    }
    
    public int HqlAction(String hql, Object[] param) {
    	int num = 0;
    	Session session=null;  
        try  
         {  
             session = HibernateSessionBean.GetInstance().getSession();  
             Query query=session.createQuery(hql); 

             if(param!=null)  
             {  
                 for(int i=0;i<param.length;i++)  
                 {
               	  if(param[i] instanceof List<?>)
               		  query.setParameterList("parm"+i, (List) param[i]);
               	  else
               
               		  query.setParameter("parm"+i, param[i]);    
                 }  
             }

             num = query.executeUpdate();
         }  
         catch (Exception e)  
         {
       	 e.printStackTrace();
         }  
         finally  
         {  
             if(session!=null)  
             {  
                 session.close();  
             }  
         }  
       return num;  
	}
} 