package util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import docchange.my.Dep;
import exception.DbChangeException;

/**
 * 
 * @author Maxim Kulin ����� ������ ��� ���������� �������� � ���� ������
 */
public class HDBUtil implements DBUtil {

	private SessionFactory factory = HibernateUtil.getSessionFactory();;
	private Session session;
	private Transaction tx;

	/**
	 * @see util.DBUtil#add(java.lang.Object) ��������� ������� ���������� �
	 *      ���� ������, �������������� �������� ����� ������� ��� �����������
	 *      ����������� ������� ��
	 * 
	 * @param obj
	 *            - ������ ���������� ����������� � ������ Object
	 * @return id - ������� � ����������
	 * @throws HibernateException
	 *             - ���� �������� �������� � ���������, �������� ���������� ���
	 *             ������� � ���� ������
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
	 * @see util.DBUtil#update(java.lang.Object) �������� ������, ���
	 *      ����������� � ��. ����� ����� id ����������� ������� obj, ����
	 *      ������ � ����� id ���������� � ��, �� �������� ���, ��������������
	 *      �������� ����� ������� ��� ����������� ����������� ������� ��
	 * 
	 * @param obj
	 *            - �������� �������, ���������������� ��� ���������
	 * @throws DbChangeException
	 *             - ���� id ���������� ������� obj �� ��������� � id �������
	 *             ���� ������
	 * @throws HibernateException
	 *             - ���� ������� � ����� id �� ����������
	 * 
	 */
	@Override
	public void update(Object obj) throws Exception {
		if (obj instanceof Dep) {
			Dep d = (Dep) obj;
			int id = d.getId();
			session = factory.openSession();
			try {
				tx = session.beginTransaction();
				Dep tmp = (Dep) session.get(Dep.class, id);
				if (tmp == null)
					throw new HibernateException("No such department in database");
				if (id != tmp.getId())
					throw new DbChangeException("Wrong Dep ID");
				session.update(d);
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
	 * @see util.DBUtil#get(java.lang.Integer, java.lang.Class) ����������
	 *      ������ ����������� ������ � ���������� id �� ��
	 * @param id
	 *            - id ���������� �� �� �������
	 * @param clazz
	 *            - ����� ���������� �� �� �������
	 * @throws HibernateException
	 *             - ��� ������ HibernateFramework ��� ���������� ������ �������
	 *             � ���
	 */
	@Override
	public Object get(Integer id, Class<?> clazz) {
		Object result;
		session = factory.openSession();
		try {
			tx = session.beginTransaction();
			result = (Dep) session.get(clazz, id);
			tx.commit();
		} catch (HibernateException e) {
			Logging.logError(e);
			throw e;
		} finally {
			session.close();
		}
		return clazz.cast(result);
	}

	@Override
	public void delete(Integer id) {
		
	}

}
