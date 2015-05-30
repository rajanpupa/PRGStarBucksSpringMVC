package mum.edu.validator;

import java.util.ArrayList;
import java.util.List;

import mum.edu.data.Database;
import mum.edu.domain.User;

import org.springframework.beans.factory.annotation.Autowired;

public class UserValidatorImpl implements UserValidator {

	@Autowired
	Database database;//=new Database();

	@Override
	public List<String> validate(Object object) {
		List<String> errors = new ArrayList<String>();

		User user = (User) object;

		String username = user.getUsername();
		String password = user.getPassword();

		if (password.equals(database.getPassword(username))) {
			// login successful, no error
		} else {
			errors.add("Either the username or the password is incorrect.");
		}

		return errors;
	}

}
