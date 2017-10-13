package docchange.my;

import java.io.Serializable;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

public class DateType implements UserType{

	private final int[] dateType = new int[]{Types.DATE};

	@Override
	public int[] sqlTypes() {
		return dateType;
	}

	@Override
	public Class returnedClass() {
		return List.class;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		return x == null ? y == null : x.equals(y);
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return x == null ? 0 : x.hashCode();
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
		if (names != null && names.length > 0 && rs != null && rs.getArray(names[0]) != null) {
            // weirdness causing either hibernate or postgres jdbc driver to cause both short and
            // integer types to return.. no idea. Even odder after changing a smallint array from
            // {0,1,2} to {0,1,2,4,5} it switch from Integer to Short.
            Object array = rs.getArray(names[0]).getArray();
            if (array instanceof Date[])
                return Arrays.asList((Date[]) array);
        }
 
        return null;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		if (value != null && st != null) {
            List<Date> list = (List<Date>) value;
            Date[] castObject = list.toArray(new Date[list.size()]);
            Array array = session.connection().createArrayOf("date", castObject);
            st.setArray(index, array);
        } else {
            st.setNull(index, dateType[0]);
        }
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		if (value == null)
            return null;
 
        List<Date> list = (List<Date>) value;
        ArrayList<Date> clone = new ArrayList<Date>();
        for (Object intOn : list)
            clone.add((Date) intOn);
 
        return clone;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

}
