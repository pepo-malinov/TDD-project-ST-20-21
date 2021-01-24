package uni.pl.fmi.st.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import uni.pl.fmi.st.models.User;
import uni.pl.fmi.st.repo.ILoginRepo;

@RunWith(Parameterized.class)
public class LoginServiceParameterizedTest {

	@Parameters(name = "{index}: with username={0}, pass1={1}, pass2={2} and expected message={3}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][] { //
				{ null, null, null, "Въведете валидни входни аргументи" }, //0
				 { null, "pass", "pass", "Въведете валидни входни аргументи" }, //1
				 { "username", null, "pass",  "Въведете валидни входни аргументи"}, //2
				 {"username", "pass", null,  "Въведете валидни входни аргументи"},//3
				 {"username", "pass", "pass",  "OK"},//4
				 {"username1", "pass", "pass",  "Потребител тези данни не съществува!"},//5
				 {"username", "pass1", "pass1",  "Потребител тези данни не съществува!"},//6
				 {"username", "pass", "pass1",  "Въведете еднакви пароли!"},//7
				 {"username", "pass1", "pass",  "Въведете еднакви пароли!"}//8
		});
	}

	@Parameter(0)
	public String username;
	@Parameter(1)
	public String pass1;
	@Parameter(2)
	public String pass2;
	@Parameter(3)
	public String expectedMessage;

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

	@Test
	public void testLogin() {
		final String result = loginService.login(username, pass1, pass2);
		assertEquals(expectedMessage, result);
	}

}
