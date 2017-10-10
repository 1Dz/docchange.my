package docchange.my;

import java.io.File;
import java.sql.Date;
import java.util.List;

public class UserInformation {
	
	private User user;
	private Date birthday;
	private File photo;
	private Date hireDate;
	private Date firedDate;
	private List<Record> reminedRecs;
	
	public UserInformation() {
	}

	public UserInformation(User user, Date birthday, File photo, Date hireDate, Date firedDate,
			List<Record> reminedRecs) {
		this.user = user;
		this.birthday = birthday;
		this.photo = photo;
		this.hireDate = hireDate;
		this.firedDate = firedDate;
		this.reminedRecs = reminedRecs;
	}

	public UserInformation(User user, Date birthday, Date hireDate) {
		this.user = user;
		this.birthday = birthday;
		this.hireDate = hireDate;
	}

	public UserInformation(User user, Date birthday, File photo, Date hireDate) {
		super();
		this.user = user;
		this.birthday = birthday;
		this.photo = photo;
		this.hireDate = hireDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Date getFiredDate() {
		return firedDate;
	}

	public void setFiredDate(Date firedDate) {
		this.firedDate = firedDate;
	}

	public List<Record> getReminedRecs() {
		return reminedRecs;
	}

	public void setReminedRecs(List<Record> reminedRecs) {
		this.reminedRecs = reminedRecs;
	}
	
}
