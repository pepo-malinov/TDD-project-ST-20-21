/**
 * 
 */
package uni.pl.fmi.st.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uni.pl.fmi.st.models.User;
import uni.pl.fmi.st.repo.ILoginRepo;

/**
 * Test class for {@link LoginService}
 *
 */
public class LoginServiceTest {

	private LoginService loginService;
	private ILoginRepo loginRepo;

	@Before
	public void setup() {
		loginRepo = mock(ILoginRepo.class);
		User user = new User();
		user.setPassword("pass");
		user.setUsername("username");
		List<User> users = Arrays.asList(user);
		doReturn(users).when(loginRepo).findAll();
		loginService = new LoginService(loginRepo);
	}

	/**
	 * test {@link LoginService#login(String, String, String)} with null entries.
	 * Expected message for not valid entries.
	 */
	@Test
	public void testLoginWithNullEntries() {
		final String result = loginService.login(null, null, null);

		assertEquals("Въведете валидни входни аргументи", result);
	}

	/**
	 * test {@link LoginService#login(String, String, String)} with null username
	 * and valid password entries. Expected message for not valid entries.
	 */
	@Test
	public void testLoginWithNullUsername() {
		final String result = loginService.login(null, "pass", "pass");

		assertEquals("Въведете валидни входни аргументи", result);
	}

	/**
	 * test {@link LoginService#login(String, String, String)} with null pass1 and
	 * valid username and pass2 entries. Expected message for not valid entries.
	 */
	@Test
	public void testLoginWithNullpass1() {
		final String result = loginService.login("username", null, "pass");

		assertEquals("Въведете валидни входни аргументи", result);
	}

	/**
	 * test {@link LoginService#login(String, String, String)} with null pass2 and
	 * valid username and pass1 entries. Expected message for not valid entries.
	 */
	@Test
	public void testLoginWithNullpass2() {
		final String result = loginService.login("username", "pass", null);

		assertEquals("Въведете валидни входни аргументи", result);
	}

	/**
	 * test {@link LoginService#login(String, String, String)} with valid and
	 * existing entries. Expected "OK" message.
	 */
	@Test
	public void testLoginWithValidAndExistingEntries() {
		final String result = loginService.login("username", "pass", "pass");

		assertEquals("OK", result);
	}

	/**
	 * test {@link LoginService#login(String, String, String)} with valid and not
	 * existing entries. Expected "Потребител тези данни не съществува!" message.
	 */
	@Test
	public void testLoginWithValidAndNotExistingEntries() {
		final String result = loginService.login("username1", "pass", "pass");

		assertEquals("Потребител тези данни не съществува!", result);
	}

	/**
	 * test {@link LoginService#login(String, String, String)} with wrong password.
	 * Expected "Потребител тези данни не съществува!" message.
	 */
	@Test
	public void testLoginWithValidAndWorngPassword() {
		final String result = loginService.login("username", "pass1", "pass1");

		assertEquals("Потребител тези данни не съществува!", result);
	}
	
	
	/**
	 * test {@link LoginService#login(String, String, String)} with wrong password.
	 * Expected "Въведете еднакви пароли!" message.
	 */
	@Test
	public void testLoginWithNotEqualPasswords() {
		final String result = loginService.login("username", "pass", "pass1");
		
		assertEquals("Въведете еднакви пароли!", result);
	}

}
