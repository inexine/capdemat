package fr.cg95.cvq.dao.hibernate;

import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.type.Type;
import fr.cg95.cvq.dao.IGenericDAO;
import fr.cg95.cvq.exception.CvqObjectNotFoundException;
import fr.cg95.cvq.permission.CvqPermissionException;
import fr.cg95.cvq.permission.PrivilegeDescriptor;
import fr.cg95.cvq.security.IPolicyDelegate;

import java.util.Map;
import java.util.List;

/**
 *
 * @author bor@zenexity.fr
 */
public class GenericDAO implements IGenericDAO {

    protected IPolicyDelegate cvqPolicy;
    	
    public GenericDAO() {
    }

    public Object findById(final Class clazz, final Long id) 
        throws CvqObjectNotFoundException {

        // FIXME : temp catch block while waiting for permission migrations
        try {
            return findById(clazz, id, null);
        } catch (CvqPermissionException e) {
            return null;
        }
    }

    public Object findById(final Class clazz, final Long id, 
            final PrivilegeDescriptor privilegeDescriptor) 
        throws CvqObjectNotFoundException, CvqPermissionException {

        Object object = null;
        try {
            object = HibernateUtil.getSession().load(clazz, id);
        } catch (ObjectNotFoundException onfe) {
            throw new CvqObjectNotFoundException("Object of class " + clazz.getName()
                    + " with id " + id + " not found");
        } catch (ObjectDeletedException ode) {
            // happens during unit tests
            throw new CvqObjectNotFoundException("Object of class " + clazz.getName()
                    + " with id " + id + " has been deleted");
        }
        if (privilegeDescriptor != null)
            cvqPolicy.check(object, privilegeDescriptor);
        
        return object;
    }

    public Long create(final Object object) throws CvqPermissionException {
        Long generatedId = (Long) HibernateUtil.getSession().save(object);
        return generatedId;
    }
    
    public <T> T saveOrUpdate(final T object) {
        HibernateUtil.getSession().saveOrUpdate(object);
        return object;
    }

    public void update(final Object object) throws CvqPermissionException {
        HibernateUtil.getSession().update(object);
    }

    public void delete(final Object object) throws CvqPermissionException {
        HibernateUtil.getSession().delete(object);
    }

    public void setCvqPolicy(IPolicyDelegate policyDelegate) {
        this.cvqPolicy = policyDelegate;
    }

    /**
     * Builds sort hql statement, must be called after main(select) statement created
     * 
     * @param sortParams Parameters map where key is fileld name (ex: [Alias].[FiledName]) and value is order direction (DESC/ASC), default ASC
     * @param sb Buffer that contains first part of statement
     */
    protected void buildSort(Map<String,String> sortParams, StringBuffer sb) {
        String query = "";
        if(sortParams == null) return;
        for(String key : sortParams.keySet()) {
            query += String.format(" %1$s %2$s ,",key,
                sortParams.get(key)!=null ? sortParams.get(key) : "asc");
        }
        if(query.length()>0) { 
            query = "order by "+query;
            sb.append(query.substring(0,query.length()-2));
        }
    }

    protected <T extends List> T execute(String hql, List<Type> typeList, List<Object> valueList, 
                                         Integer max, Integer offset) {
        Type[] types = typeList.toArray(new Type[typeList.size()]);
        Object[] values = valueList.toArray(new Object[valueList.size()]);
        
        Query query = HibernateUtil.getSession().createQuery(hql);
        if(max != null) {
            query.setMaxResults(max);
            query.setFirstResult(offset != null ? offset : 0);
        }
        
        //noinspection unchecked
        return (T) query.setParameters(values, types).list();
    }

    protected <T extends Number> T execute(String hql, List<Type> typeList, List<Object> valueList) {
        Type[] types = typeList.toArray(new Type[typeList.size()]);
        Object[] values = valueList.toArray(new Object[valueList.size()]);
        
        Query query = HibernateUtil.getSession().createQuery(hql).setParameters(values, types);
        
        //noinspection unchecked
        return (T)query.iterate().next();
    }
}
