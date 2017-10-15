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
 *  ласс служит дл€ выполнени€ запросов инициализации списков
 * приложени€ из базы данных через HibernateFramework
 * 
 * @author Maxim Kulin
 * @see util.HibernateUtil
 * @see util.DBUtil
 */
public class Connection extends DBUtil {

	/**
	 * »нициализирует список дат посещени€ приложени€ переданным пользователем
	 * @param obj экземпл€р объекта docchange.my.User дл€ которого инициализируетс€ спиок
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
	 * »нициализирует список созданных записей приложени€ переданным пользователем
	 * @param obj экземпл€р объекта docchange.my.User дл€ которого инициализируетс€ спиок
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
	 * »нициализирует список измененных записей приложени€ переданным пользователем
	 * @param obj экземпл€р объекта docchange.my.User дл€ которого инициализируетс€ спиок
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
	 * »нициализирует список просмотренных записей приложени€ переданным пользователем
	 * @param obj экземпл€р объекта docchange.my.User дл€ которого инициализируетс€ спиок
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
	 * »нициализирует список удаленных записей приложени€ переданным пользователем
	 * @param obj экземпл€р объекта docchange.my.User дл€ которого инициализируетс€ спиок
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
	 * »нициализирует список экземпл€ров объекта docchange.my.User 
	 * дл€ переданного экземпл€ра объекта docchange.my.Dep
	 * @param obj экземпл€р объекта docchange.my.Dep дл€ которого инициализируетс€ спиок
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
	 * »нициализирует список экземпл€ров объекта docchange.my.Record 
	 * дл€ переданного экземпл€ра объекта docchange.my.Dep
	 * @param obj экземпл€р объекта docchange.my.Dep дл€ которого инициализируетс€ спиок
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
