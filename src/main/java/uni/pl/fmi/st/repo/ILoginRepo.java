package uni.pl.fmi.st.repo;

import java.util.List;

import uni.pl.fmi.st.models.User;

public interface ILoginRepo {

	List<User> findAll();

}
