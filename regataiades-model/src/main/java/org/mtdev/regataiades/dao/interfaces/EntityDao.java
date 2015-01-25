package org.mtdev.regataiades.dao.interfaces;

import java.util.Collection;

import org.hibernate.Criteria;

public interface EntityDao<T> {

	public boolean create(T pObject);

	public boolean update(T pObject);

	public boolean delete(T pObject);

	public Collection<T> findAll();

	public T findById(int pId);

	public Collection<T> findListByCriteria(Criteria pCriteria);
	
	public T findItemByCriteria(Criteria pCriteria);

}
