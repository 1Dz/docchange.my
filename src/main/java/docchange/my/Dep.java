package docchange.my;

import java.util.List;

public class Dep {
	
	private String name;
	private Integer id;
	
	private List<User> userList;
	private List<Record> reminedRecs;
	
	public Dep() {
	}

	public String getName() {
		return name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<Record> getReminedRecs() {
		return reminedRecs;
	}

	public void setReminedRecs(List<Record> reminedRecs) {
		this.reminedRecs = reminedRecs;
	}
	
}
