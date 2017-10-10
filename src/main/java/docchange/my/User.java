package docchange.my;

import java.util.Date;
import java.util.List;

public class User {
	
	private String name;
	private Date usrCreated;
	private Integer id;
	private String key;
	private String profession;
	
	private List<Record> rCreated;
	private List<Record> rChanged;
	private List<Record> rListened;
	private List<Record> rDeleted;
	private List<Date> logins;
	
	public User() {
	}

	public User(String name, Date usrCreated) {
		this.name = name;
		this.usrCreated = usrCreated;
	}
	
	public User(String name, Date usrCreated, Integer id, String key, String profession, List<Record> rCreated,
			List<Record> rChanged, List<Record> rListened, List<Record> rDeleted, List<Date> logins) {
		this.name = name;
		this.usrCreated = usrCreated;
		this.id = id;
		this.key = key;
		this.profession = profession;
		this.rCreated = rCreated;
		this.rChanged = rChanged;
		this.rListened = rListened;
		this.rDeleted = rDeleted;
		this.logins = logins;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getUsrCreated() {
		return usrCreated;
	}

	public void setUsrCreated(Date usrCreated) {
		this.usrCreated = usrCreated;
	}

	public List<Record> getrCreated() {
		return rCreated;
	}

	public void setrCreated(List<Record> rCreated) {
		this.rCreated = rCreated;
	}

	public List<Record> getrChanged() {
		return rChanged;
	}

	public void setrChanged(List<Record> rChanged) {
		this.rChanged = rChanged;
	}

	public List<Record> getrListened() {
		return rListened;
	}

	public void setrListened(List<Record> rListened) {
		this.rListened = rListened;
	}

	public List<Record> getrDeleted() {
		return rDeleted;
	}

	public void setrDeleted(List<Record> rDeleted) {
		this.rDeleted = rDeleted;
	}

	public List<Date> getLogins() {
		return logins;
	}

	public void setLogins(List<Date> logins) {
		this.logins = logins;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}
	
}
