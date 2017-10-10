package docchange.my;

import java.io.File;
import java.sql.Date;
import java.util.List;

public class Record {
	
	private String name;
	private String theme;
	private String message;
	
	private User author;
	private Date creationDate;
	
	private List<Date> editionDates;
	private List<User> usersEdited;
	private List<User> usersList;
	private List<File> fileList;
	private List<Dep> depAdressList;
	private List<User> usrAdressList;
	private List<User> remainedUsers;
	private List<User> remainedDeps;
	
	private boolean checked;
	private boolean archived;
	
	public Record() {
	}

	public Record(String name, String theme, User author, Date creationDate) {
		this.name = name;
		this.theme = theme;
		this.author = author;
		this.creationDate = creationDate;
	}

	public Record(String name, String theme, String message, User author, Date creationDate, List<Date> editionDates,
			List<User> usersEdited, List<User> usersList, List<File> fileList, List<Dep> depAdressList,
			List<User> usrAdressList, List<User> remainedUsers, List<User> remainedDeps, boolean checked,
			boolean archived) {
		this.name = name;
		this.theme = theme;
		this.message = message;
		this.author = author;
		this.creationDate = creationDate;
		this.editionDates = editionDates;
		this.usersEdited = usersEdited;
		this.usersList = usersList;
		this.fileList = fileList;
		this.depAdressList = depAdressList;
		this.usrAdressList = usrAdressList;
		this.remainedUsers = remainedUsers;
		this.remainedDeps = remainedDeps;
		this.checked = checked;
		this.archived = archived;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public List<Date> getEditionDates() {
		return editionDates;
	}

	public void setEditionDates(List<Date> editionDates) {
		this.editionDates = editionDates;
	}

	public List<User> getUsersEdited() {
		return usersEdited;
	}

	public void setUsersEdited(List<User> usersEdited) {
		this.usersEdited = usersEdited;
	}

	public List<User> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<User> usersList) {
		this.usersList = usersList;
	}

	public List<File> getFileList() {
		return fileList;
	}

	public void setFileList(List<File> fileList) {
		this.fileList = fileList;
	}

	public List<Dep> getDepAdressList() {
		return depAdressList;
	}

	public void setDepAdressList(List<Dep> depAdressList) {
		this.depAdressList = depAdressList;
	}

	public List<User> getUsrAdressList() {
		return usrAdressList;
	}

	public void setUsrAdressList(List<User> usrAdressList) {
		this.usrAdressList = usrAdressList;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}
	
}
