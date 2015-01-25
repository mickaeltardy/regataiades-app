package org.mtdev.regataiades.dao.impls;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.mtdev.regataiades.dao.interfaces.EntityDao;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class EntityDaoImpl<T> implements EntityDao<T> {

	@Autowired
	protected SessionFactory mSessionFactory;

	private Class<T> entityBeanType;

	@SuppressWarnings("unchecked")
	public EntityDaoImpl() {
		entityBeanType = ((Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	@Override
	public boolean create(T pObject) {
		try {
			Session lSession = mSessionFactory.getCurrentSession();
			lSession.save(pObject);
			return true;
		} catch (Exception lE) {

		}

		return false;
	}

	@Override
	public boolean update(T pObject) {
		try {
			Session lSession = mSessionFactory.getCurrentSession();
			lSession.update(pObject);
			return true;
		} catch (Exception lE) {

		}

		return false;
	}

	@Override
	public boolean delete(T pObject) {
		try {
			Session lSession = mSessionFactory.getCurrentSession();
			lSession.delete(pObject);
			return true;
		} catch (Exception lE) {

		}

		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<T> findAll() {
		try {
			Criteria lCriteria = getGenericCriteria();

			return lCriteria.list();
		} catch (Exception lE) {

		}

		return null;
	}

	@Override
	public T findById(int pId) {

		Criteria lCriteria = getGenericCriteria().add(
				Restrictions.eq("id", pId));

		return this.findItemByCriteria(lCriteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<T> findListByCriteria(Criteria pCriteria) {
		try {
			return pCriteria.list();
		} catch (Exception lE) {

		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findItemByCriteria(Criteria pCriteria) {
		try {

			return (T) pCriteria.uniqueResult();
		} catch (Exception lE) {

		}

		return null;
	}

	public Criteria getGenericCriteria() {
		Session lSession = mSessionFactory.getCurrentSession();

		Criteria lCriteria = lSession.createCriteria(getEntityBeanType()
				.getClass());
		return lCriteria;
	}

	@SuppressWarnings("rawtypes")
	protected Class getEntityBeanType() {
		return entityBeanType;
	}

}
