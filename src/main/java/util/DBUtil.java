package util;

/**
 * 
 * @author Maxim Kulin
 * CRUD - интерфейс поведения классов, обращающихся к базе данных
 *
 */
public interface DBUtil {

	/** Добавить в БД*/
	Integer add(Object obj);
	/** Изменить объект в БД*/
	void update(Object obj) throws Exception;
	/** Получить объект из БД*/
	Object get(Integer id, Class<?> clazz);
	/** Удалить объект из БД*/
	void delete(Integer id,  Class<?> clazz);
}
