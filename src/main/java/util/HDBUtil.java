package util;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import docchange.my.Dep;
import docchange.my.User;
import exception.DbChangeException;

/**
 * ����� ������ ��� ���������� �������� � ���� ������ ����� HibernateFramework
 * 
 * @author Maxim Kulin
 * @see util.HibernateUtil
 */
public class HDBUtil implements DBUtil {

	private SessionFactory factory = HibernateUtil.getSessionFactory();
	private Session session;
	private Transaction tx;

	/**
	 * ��������� ������� ���������� � ���� ������, �������������� �������� �����
	 * ������� ��� ����������� ����������� ������� ��
	 * 
	 * @param obj
	 *            - ������ ���������� ����������� � ������ Object
	 * @return id - ������� � ����������
	 * @throws HibernateException
	 *             - ���� �������� �������� � ���������, �������� ���������� ���
	 *             ������� � ���� ������
	 * @see util.DBUtil#add(java.lang.Object)
	 */
	@Override
	public Integer add(Object obj) {
		Integer id = null;
		if (obj instanceof Dep) {
			Dep d = (Dep) obj;
			session = factory.openSession();
			try {
				tx = session.beginTransaction();
				id = (Integer) session.save(d);
				tx.commit();
				Logging.logEvent("Added new Department to data base. " + d.toString());
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				Logging.logError(e);
				throw e;
			} finally {
				session.close();
			}
		}
		return id;
	}

	/**
	 * �������� ������, ��� ����������� � ��. ����� ����� id ����������� �������
	 * obj, ���� ������ � ����� id ���������� � ��, �� �������� ���,
	 * �������������� �������� ����� ������� ��� ����������� ����������� �������
	 * ��
	 * 
	 * @param obj
	 *            - �������� �������, ���������������� ��� ���������
	 * @throws DbChangeException
	 *             - ���� id ���������� ������� obj �� ��������� � id �������
	 *             ���� ������
	 * @throws HibernateException
	 *             - ���� ������� � ����� id �� ����������
	 * @see util.DBUtil#update(java.lang.Object)
	 */
	@Override
	public void update(Object obj) throws Exception {
		if (obj instanceof Dep) {
			Dep res = (Dep) obj;
			int id = res.getId();
			session = factory.openSession();
			try {
				tx = session.beginTransaction();
				Dep dest = (Dep) session.get(Dep.class, id);
				if (dest == null)
					throw new HibernateException("No such department in database");
				if (id != dest.getId())
					throw new DbChangeException("Wrong Dep ID");
				swapParams(res, dest);
				session.update(dest);
				tx.commit();
			} catch (HibernateException | DbChangeException e) {
				Logging.logError(e);
				throw e;
			} finally {
				session.close();
			}
		}
		if (obj instanceof User) {
			User res = (User) obj;
			int id = res.getId();
			session = factory.openSession();
			try {
				tx = session.beginTransaction();
				User dest = (User) session.get(User.class, id);
				if (dest == null)
					throw new HibernateException("No such department in database");
				if (id != dest.getId())
					throw new DbChangeException("Wrong Dep ID");
				swapParams(res, dest);
				session.update(dest);
				tx.commit();
			} catch (HibernateException | DbChangeException e) {
				Logging.logError(e);
				throw e;
			} finally {
				session.close();
			}
		}
	}

	/**
	 * ���������� ������ ����������� ������ � ���������� id �� ��
	 * 
	 * @param id
	 *            - id ���������� �� �� �������
	 * @param clazz
	 *            - ����� ���������� �� �� �������
	 * @throws HibernateException
	 *             - ��� ������ HibernateFramework ��� ���������� ������ �������
	 *             � ���
	 * @see util.DBUtil#get(java.lang.Integer, java.lang.Class)
	 */
	@Override
	public Object get(Integer id, Class<?> clazz) {
		Object result;
		session = factory.openSession();
		try {
			tx = session.beginTransaction();
			result = session.get(clazz, id);
			tx.commit();
		} catch (HibernateException e) {
			Logging.logError(e);
			throw e;
		} finally {
			session.close();
		}
		return clazz.cast(result);
	}

	/**
	 * ���������� ������ ����������� ������ � ���������� id �� �� ������� ������
	 * �� �� �� ����������� id, �������������� ������� ��� ���� �����������
	 * ������
	 * 
	 * @param id
	 *            - id ������� � ��
	 * @param clazz
	 *            - ����� ���������� �������
	 * @throws HibernateException
	 *             - - ��� ������ HibernateFramework ��� ���������� ������
	 *             ������� � ��
	 * @see util.DBUtil#delete(java.lang.Integer, java.lang.Class)
	 */
	@Override
	public void delete(Integer id, Class<?> clazz) {
		session = factory.openSession();
		try {
			tx = session.beginTransaction();
			Object result = session.get(clazz, id);
			result = clazz.cast(result);
			session.delete(result);
			tx.commit();
		} catch (HibernateException e) {
			Logging.logError(e);
			throw e;
		} finally {
			session.close();
		}
	}

	public User getLoginsList(User user) {
		session = factory.openSession();
		try {
			tx = session.beginTransaction();
			user = (User) session.get(User.class, user.getId());
			Hibernate.initialize(user.getLogins());
			tx.commit();
		} catch (HibernateException e) {
			Logging.logError(e);
			throw e;
		} finally {
			session.close();
		}
		return user;
	}

	private void swapParams(Object res, Object dest) {
		if (res instanceof Dep && dest instanceof Dep) {
			Dep dres = (Dep) res;
			Dep ddest = (Dep) dest;
			ddest.setName(dres.getName());
			ddest.setReminedRecs(dres.getReminedRecs());
			ddest.setUserList(dres.getUserList());
		}
		if (res instanceof User && dest instanceof User) {
			User dres = (User) res;
			User ddest = (User) dest;
			ddest.setKey(dres.getKey());
			ddest.setLogins(dres.getLogins());
			ddest.setName(dres.getName());
			ddest.setProfession(dres.getProfession());
			ddest.setrChanged(dres.getrChanged());
			ddest.setrCreated(dres.getrCreated());
			ddest.setrDeleted(dres.getrDeleted());
			ddest.setrListened(dres.getrListened());
			ddest.setUsrCreated(dres.getUsrCreated());
		}
	}

}
