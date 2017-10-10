package util;

/**
 * 
 * @author Maxim Kulin
 * CRUD - ��������� ��������� �������, ������������ � ���� ������
 *
 */
public interface DBUtil {

	/** �������� � ��*/
	Integer add(Object obj);
	/** �������� ������ � ��*/
	void update(Object obj) throws Exception;
	/** �������� ������ �� ��*/
	Object get(Integer id, Class<?> clazz);
	/** ������� ������ �� ��*/
	void delete(Integer id,  Class<?> clazz);
}
