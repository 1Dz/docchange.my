package util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import docchange.my.Dep;
import exception.DbChangeException;

/**
 * 
 * @author Maxim Kulin Класс служит для выполнения запросов к базе данных
 */
public class HDBUtil implements DBUtil {

	private SessionFactory factory = HibernateUtil.getSessionFactory();;
	private Session session;
	private Transaction tx;

	/**
	 * @see util.DBUtil#add(java.lang.Object) Добавляет объекты приложения в
	 *      базу данных, предварительно проверив класс объекта для корректного
	 *      определения таблицы БД
	 * 
	 * @param obj
	 *            - объект приложения приведенный к классу Object
	 * @return id - объекта в базеданных
	 * @throws HibernateException
	 *             - если возникли проблемы с открытием, коммитом транзакции или
	 *             записью в базу данных
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
	 * @see util.DBUtil#update(java.lang.Object) Изменяет объект, уже
	 *      находящийся в БД. Метод берет id переданного объекта obj, если
	 *      объект с таким id существует в БД, то получает его, предварительно
	 *      проверив класс объекта для корректного определения таблицы БД
	 * 
	 * @param obj
	 *            - эземпляр объекта, предназначенного для изменения
	 * @throws DbChangeException
	 *             - если id переанного объекта obj не совпадает с id объекта
	 *             базы данных
	 * @throws HibernateException
	 *             - если объекта с таким id не существует
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
	 * @see util.DBUtil#get(java.lang.Integer, java.lang.Class) Возвращает
	 *      объект переданного класса с переданным id из БД
	 * @param id
	 *            - id требуемого из БД объекта
	 * @param clazz
	 *            - класс требуемого из БД объекта
	 * @throws HibernateException
	 *             - при ошибке HibernateFramework или отсутствии такого объекта
	 *             в БДы
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
