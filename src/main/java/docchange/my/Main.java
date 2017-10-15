package docchange.my;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.Connection;
import util.Util;

public class Main {
	
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
		Connection.add(dep);
		User u = (User)Connection.get(3, User.class);
		u = Connection.getLoginsList(u);
		Dep d = (Dep)Connection.get(1, Dep.class);
		d = Connection.getUserList(d);
		System.out.println(d.getUserList() + " " + u.getName() + " " + u.getProfession() + " " + u.getLogins());
	}

}
