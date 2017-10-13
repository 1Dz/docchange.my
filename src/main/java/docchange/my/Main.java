package docchange.my;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.HDBUtil;
import util.Util;

public class Main {
	
	private static HDBUtil db = new HDBUtil();

	public static void main(String[] args) {
		
		Dep dep = new Dep();
		dep.setName("OGE");
		
		List<Record> recs = new ArrayList<>();
		
		List<String> dates = new ArrayList<>();
		dates.add(Util.getDateStringNow());
		List<User> list = new ArrayList<>();
		
		User usr = new User("First", new Date());
		usr.setKey("123");
		usr.setProfession("newbee");
		list.add(usr);
		
		usr = new User("Second", new Date());
		usr.setKey("123");
		usr.setProfession("newbee");
		Record r = new Record("new rec", "test theme", usr, new Date());
		recs.add(r);
		usr.setrCreated(recs);
		list.add(usr);
		
		usr = new User("Third", new Date());
		usr.setKey("123");
		usr.setProfession("newbee");
		usr.setLogins(dates);
		list.add(usr);
		
		dep.setUserList(list);
		db.add(dep);
		User u = (User)db.get(3, User.class);
		u = db.getLoginsList(u);
		System.out.println(u.getName() + " " + u.getProfession() + " " + u.getLogins());
	}

}
