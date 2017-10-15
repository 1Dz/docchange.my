package util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import docchange.my.Dep;
import docchange.my.User;
import exception.DbChangeException;

/**
 * 
 * @author Maxim Kulin CRUD - интерфейс поведения классов, обращающихся к базе
 *         данных
 *
 */
public abstract class DBUtil {

	protected static SessionFactory factory = HibernateUtil.getSessionFactory();
	protected static Session session;
	protected static Transaction tx;

	/**
	 * Добавляет объекты приложения в базу данных, предварительно проверив класс
	 * объекта для корректного определения таблицы БД
	 * 
	 * @param obj
	 *            - объект приложения приведенный к классу Object
	 * @return id - объекта в базеданных
	 * @throws HibernateException
	 *             - если возникли проблемы с открытием, коммитом транзакции или
	 *             записью в базу данных
	 * @see util.DBUtil#add(java.lang.Object)
	 */
	public static Integer add(Object obj) {
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
	 * Изменяет объект, уже находящийся в БД. Метод берет id переданного объекта
	 * obj, если объект с таким id существует в БД, то получает его,
	 * предварительно проверив класс объекта для корректного определения таблицы
	 * БД
	 * 
	 * @param obj
	 *            - эземпляр объекта, предназначенного для изменения
	 * @throws DbChangeException
	 *             - если id переанного объекта obj не совпадает с id объекта
	 *             базы данных
	 * @throws HibernateException
	 *             - если объекта с таким id не существует
	 * @see util.DBUtil#update(java.lang.Object)
	 */
	public static void update(Object obj) throws Exception {
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
	 * Возвращает объект переданного класса с переданным id из БД
	 * 
	 * @param id
	 *            - id требуемого из БД объекта
	 * @param clazz
	 *            - класс требуемого из БД объекта
	 * @throws HibernateException
	 *             - при ошибке HibernateFramework или отсутствии такого объекта
	 *             в БДы
	 * @see util.DBUtil#get(java.lang.Integer, java.lang.Class)
	 */
	public static Object get(Integer id, Class<?> clazz) {
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
		return result;
	}

	/**
	 * Возвращает объект переданного класса с переданным id из БД Удаляет объект
	 * из БД по переданному id, предварительно приведя его типу переданного
	 * класса
	 * 
	 * @param id
	 *            - id объекта в БД
	 * @param clazz
	 *            - класс удаляемого объекта
	 * @throws HibernateException
	 *             - - при ошибке HibernateFramework или отсутствии такого
	 *             объекта в БД
	 * @see util.DBUtil#delete(java.lang.Integer, java.lang.Class)
	 */
	public static void delete(Integer id, Class<?> clazz) {
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
	
	private static void swapParams(Object res, Object dest) {
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
