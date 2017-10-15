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
 * ����� ������ ��� ���������� �������� ������������� �������
 * ���������� �� ���� ������ ����� HibernateFramework
 * 
 * @author Maxim Kulin
 * @see util.HibernateUtil
 * @see util.DBUtil
 */
public class Connection extends DBUtil {

	/**
	 * �������������� ������ ��� ��������� ���������� ���������� �������������
	 * @param obj ��������� ������� docchange.my.User ��� �������� ���������������� �����
	 */
	public static User getLoginsList(User obj) {
		session = factory.openSession();
		try {
			tx = session.beginTransaction();
			obj = (User) session.get(User.class, obj.getId());
			Hibernate.initialize(obj.getLogins());
			tx.commit();
		} catch (HibernateException e) {
			Logging.logError(e);
			throw e;
		} finally {
			session.close();
		}
		return obj;
	}

	/**
	 * �������������� ������ ��������� ������� ���������� ���������� �������������
	 * @param obj ��������� ������� docchange.my.User ��� �������� ���������������� �����
	 */
	public static User getRCreatedList(User obj) {
		session = factory.openSession();
		try {
			tx = session.beginTransaction();
			obj = (User) session.get(User.class, obj.getId());
			Hibernate.initialize(obj.getrCreated());
			tx.commit();
		} catch (HibernateException e) {
			Logging.logError(e);
			throw e;
		} finally {
			session.close();
		}
		return obj;
	}
	
	/**
	 * �������������� ������ ���������� ������� ���������� ���������� �������������
	 * @param obj ��������� ������� docchange.my.User ��� �������� ���������������� �����
	 */
	public static User getRChangedList(User obj) {
		session = factory.openSession();
		try {
			tx = session.beginTransaction();
			obj = (User) session.get(User.class, obj.getId());
			Hibernate.initialize(obj.getrChanged());
			tx.commit();
		} catch (HibernateException e) {
			Logging.logError(e);
			throw e;
		} finally {
			session.close();
		}
		return obj;
	}

	/**
	 * �������������� ������ ������������� ������� ���������� ���������� �������������
	 * @param obj ��������� ������� docchange.my.User ��� �������� ���������������� �����
	 */
	public static User getRListenedList(User obj) {
		session = factory.openSession();
		try {
			tx = session.beginTransaction();
			obj = (User) session.get(User.class, obj.getId());
			Hibernate.initialize(obj.getrListened());
			tx.commit();
		} catch (HibernateException e) {
			Logging.logError(e);
			throw e;
		} finally {
			session.close();
		}
		return obj;
	}

	/**
	 * �������������� ������ ��������� ������� ���������� ���������� �������������
	 * @param obj ��������� ������� docchange.my.User ��� �������� ���������������� �����
	 */
	public static User getRDeletedList(User obj) {
		session = factory.openSession();
		try {
			tx = session.beginTransaction();
			obj = (User) session.get(User.class, obj.getId());
			Hibernate.initialize(obj.getrDeleted());
			tx.commit();
		} catch (HibernateException e) {
			Logging.logError(e);
			throw e;
		} finally {
			session.close();
		}
		return obj;
	}

	/**
	 * �������������� ������ ����������� ������� docchange.my.User 
	 * ��� ����������� ���������� ������� docchange.my.Dep
	 * @param obj ��������� ������� docchange.my.Dep ��� �������� ���������������� �����
	 */
	public static Dep getUserList(Dep obj) {
		session = factory.openSession();
		try {
			tx = session.beginTransaction();
			obj = (Dep) session.get(Dep.class, obj.getId());
			Hibernate.initialize(obj.getUserList());
		} catch (HibernateException e) {
			Logging.logError(e);
			throw e;
		} finally {
			session.close();
		}
		return obj;
	}

	/**
	 * �������������� ������ ����������� ������� docchange.my.Record 
	 * ��� ����������� ���������� ������� docchange.my.Dep
	 * @param obj ��������� ������� docchange.my.Dep ��� �������� ���������������� �����
	 */
	public static Dep getRecordList(Dep obj) {
		session = factory.openSession();
		try {
			tx = session.beginTransaction();
			obj = (Dep) session.get(Dep.class, obj.getId());
			Hibernate.initialize(obj.getReminedRecs());
			tx.commit();
		} catch (HibernateException e) {
			Logging.logError(e);
			throw e;
		} finally {
			session.close();
		}
		return obj;
	}
}
