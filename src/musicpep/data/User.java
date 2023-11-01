package musicpep.data;

public class User {
	private int id;
	private String user_name;
	private String password;
	private UserStatus status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", user_name=" + user_name + ", password=" + password + ", status=" + status + "]";
	}

}
