package ruiz.ssh.action;

import java.util.Map;
import ruiz.ssh.model.User;
import ruiz.ssh.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;
	private UserService userService;
	private Map<String, Object> request;
	private Map<String, Object> session;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String list() {
		request = (Map) ActionContext.getContext().get("request");
		request.put("users", userService.queryAll());
		return "success";
	}

	public String checkUser() {
		boolean flag = userService.checkUser(user);
		
		if (flag) {
			session = ActionContext.getContext().getSession();
			session.put("user1", user);
			return list();
		}
		return "error";
	}

	public String deleteUser() {
		userService.deleteUser(user.getId());
		return list();
	}

	public String addUser() {
		userService.addUser(user);
		return list();
	}

	public String updateUser() {
		user = userService.queryById(user.getId());
		return "update";
	}

	public String updateUserImpl() {
		userService.updateUser(user);
		return list();
	}

	public String queryUser() {
		request = (Map) ActionContext.getContext().get("request");
		request.put("users2", userService.queryUser(user));
		request.put("tag", 2);
		return list();
	}

}
