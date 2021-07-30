package com.claim.entity;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	private List<User> userArray = new ArrayList<User>();

	@PostConstruct
	public void addDummyData() {
		User user1 = new User("Test", "123", "test123@gmail.com", "601234589", "a");
		userArray.add(user1);
	}

	public void saveUser(User user) {
		userArray.add(user);
	}

	public List<User> getUserArray() {
		return userArray;
	}

	
	// Some mistakes I was making, that took me a long time / hours to figure out:
	// I didn't wrap my if statement in brackets{}
	// For some reason I had user.getEmail().equals(password)
	// I was trying to print out an user.toString() with a null value; Dead Code warning
	public User findUser(String email, String password) {
//		System.out.println("Email: " + email + " Pass: " + password);
		for (User user : userArray) {
//			System.out.println("List of All Users: " + user.toString());
			if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
//				System.out.println("User That Was Found: " + user.toString());
				return user;
			}
		}
		System.out.println("User does not exist");
		return null;
	}

}
