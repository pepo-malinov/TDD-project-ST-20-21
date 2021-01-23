/**
 * 
 */
package uni.pl.fmi.st.service;

import java.util.List;
import java.util.function.Predicate;

import uni.pl.fmi.st.models.User;
import uni.pl.fmi.st.repo.ILoginRepo;
import uni.pl.fmi.st.repo.LoginRepo;

/**
 *
 */
public class LoginService {

	final ILoginRepo loginRepo;

	public LoginService(ILoginRepo loginRepo) {
		this.loginRepo = loginRepo;
	}

	/**
	 * Provide user log functionality. Expected username and equals user passwords.
	 * 
	 * @param username String representation for username. Valid not {@code NULL} or
	 *                 empty String
	 * @param pass1    represent user password. Valid not {@code NULL} or empty
	 *                 String
	 * @param pass2    represent user password. Valid not {@code NULL} or empty
	 *                 String
	 * @return String information message from user login state.
	 */
	public String login(final String username, final String pass1, final String pass2) {
		String result = null;
		if (username == null || pass1 == null || pass2 == null) {
			result = "Въведете валидни входни аргументи";
		} else if (!pass1.equals(pass2)) {
			result = "Въведете еднакви пароли!";
		} else {
			List<User> users = loginRepo.findAll();
			Predicate<? super User> predicate = user -> user.getUsername().equals(username)
					&& user.getPassword().equals(pass1);
			boolean isUserMatch = users.stream().anyMatch(predicate);
			result = isUserMatch ? "OK" : "Потребител тези данни не съществува!";
		}
		return result;
	}

}
