package com.claim.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.claim.entity.Car;
import com.claim.entity.CarService;
import com.claim.entity.User;
import com.claim.entity.UserService;

@Controller
public class MainController {
	
	@Autowired
	UserService userService;
	@Autowired
	CarService carService;
	
	@GetMapping({"/", "/index"})
	public String welcome(Model model) {
		return "index";
	}
	
	@GetMapping("/register")
	public ModelAndView register(Model model) {
		//When the user tries to view the sign up page
		//This servlet will handle the request and add a spring ModelAttribute
		//object to map to the sign up form called “user”, which is a User object.
	return new ModelAndView("register", "user", new User()); 
	}
	
	@PostMapping("/register")
	//The Model Attribute will map to the html model attribute in step 19
	public String handleRegistration(Model model, @ModelAttribute("user") User user) {
		userService.saveUser(user);
		model.addAttribute("savedUser", user);
//		session.setAttribute("savedUser", user);
//		System.out.println("Size of User List: " + userService.getUserArray().size());
		model.addAttribute("msg", "Thank you for registering!");
		return "thankyou";
	}
	
	@GetMapping("/login")
	public ModelAndView login(Model model) {
		return new ModelAndView("login", "loginUser", new User());
	}
		
	@PostMapping("/login")
	public String handleLogin(Model model, @ModelAttribute("loginUser") User user) {
		User loggedInUser = userService.findUser(user.getEmail(), user.getPassword());
		if (loggedInUser == null) {
			model.addAttribute("loginError", "Invalid login. Please check your email and password");
			return "login";
		}
//		System.out.println("User: " + loggedInUser.toString());
		model.addAttribute("loggedInStudent", loggedInUser);
		return "index";
	}
	
	@GetMapping("/inventory")
	public String inventory(Model model) {
		List<Car> carList = carService.getCars();
		model.addAttribute("carList", carList);
		return "inventory";
	}
	
	@GetMapping("/details")
	public String handleDetails(Model model, HttpServletRequest request) {
		String CarId = request.getParameter("id");
		Car currentCar = carService.findCarByID(CarId);
		model.addAttribute("car", currentCar);
		return "details";
	}
	
	@GetMapping("/addCar")
	public ModelAndView addACar(Model model) {
	return new ModelAndView("addcar", "carToAdd", new Car());
	}
	
	@PostMapping("/addCar")
	public String handleAddCar(Model model, @ModelAttribute("carToAdd") Car car, HttpServletRequest request) {
		List<Car> carList = carService.getCars();
		// Temporary code since currently not uploading images
		car.setImagePath("no-image-placeholder.png");
		car.setId(car.generateID());
		car.setDateOfPurchase(LocalDate.now()); // Got info form here about LocalDate https://www.javatpoint.com/java-get-current-date
		car.setSpecialPricing(false);
		System.out.println("New Car Special? " + car.getSpecialPricing());
		carList.add(car);
		model.addAttribute("carList", carList);
		System.out.println("Size of inventory: " + carList.size());
		return "/inventory";
	}
	
	@GetMapping("/search")
	public String search(Model model) {
		List<Car> carList = carService.getCars();
		model.addAttribute("carList", carList);
		return "search";
	}
	
	@PostMapping("searchnow") 
	String searchNow(@RequestParam String keyword, Model model, RedirectAttributes redirect) {
//		System.out.println("Error here in searchNow");
//		List<Car> carList = carService.getCars();
//		for (Car c : carList) {
//			System.out.println("Error here in #2. Size of List: " + carList.size());
//			System.out.println("Keyword: " + keyword.toString());
//			System.out.println(c.getMake());
//			if (c.getMake().equals(keyword)) {
//				System.out.println("Error here in #4");
//				carList.remove(c);
//			}
//		}
		
		// --------------- Try #2 Works Best So Far --------------------------//
		List<Car> searchInventory = carService.getCars();
		Iterator<Car> i = searchInventory.iterator();
		while (i.hasNext()) {
			Car c = i.next();
			if (c.getMake().equals(keyword)) {
//				System.out.println("Error here in #4: " + c.getMake());
//				carList.remove(c);
				i.remove();
			}
			
		}
		
//		List<Car> carList = carService.getCars();
//		List<Car> searchInventory = new ArrayList<Car>(carList);
//		Collections.copy(searchInventory, carList);
//		for (Car car : searchInventory) {
//			if (car.getMake().equals(keyword)) {
//				return car;
//			}
//		}
		// --------------- Try #4 - Works the best so far, has some issues, like only removes first instance if multiple found  --------------------------//
//		List<Car> carList = carService.getCars();
//		Car carfound = carService.searchByKeyword(keyword);
//		for (int i = 0; i < carList.size(); i++) {
//			if (carList.)
//		}
		
		model.addAttribute("carList", searchInventory);
		return "redirect:search";
	}
	
	
}
