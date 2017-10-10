package util;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * ����������� �����-�������� ��� �������� ������� SessionFactory HibernateFramework
 * @author Maxim Kulin
 * @see org.hibernate.SessionFactory
 * @see org.hibernate.cfg.Configuration
 */
public class HibernateUtil {

	private static final SessionFactory SESSION_FACTORY = null;

	/**
	 * ���� ����������� ���������� SESSION_FACTORY ����� null, ������� ��������� ������
	 * SessionFactory HibernateFramework � ���������� ���. ����� ���������� ��� ��������� ���������
	 * @return ��������� ������ SessionFactory HibernateFramework
	 */
	public static SessionFactory getSessionFactory() {
		if (SESSION_FACTORY == null) {
			try {
				return new Configuration().configure().buildSessionFactory();
			} catch (ExceptionInInitializerError e) {
				Logger logger = Logger.getAnonymousLogger();
				logger.log(Level.WARNING, "Session factory creation failed: " + e, e);
				throw e;
			}
		}
		else return SESSION_FACTORY;
	}
}
