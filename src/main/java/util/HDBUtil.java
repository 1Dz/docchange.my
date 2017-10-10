package util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import docchange.my.Dep;
import exception.DbChangeException;

/**
 * Класс служит для выполнения запросов к базе данных через HibernateFramework
 * @author Maxim Kulin 
 * @see util.HibernateUtil
 */
public class HDBUtil implements DBUtil {

	private SessionFactory factory = HibernateUtil.getSessionFactory();
	private Session session;
	private Transaction tx;

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

}
