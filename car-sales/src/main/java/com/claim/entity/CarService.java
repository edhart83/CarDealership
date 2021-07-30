package com.claim.entity;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class CarService {
	List<Car> carArray = new ArrayList<Car>();
	
	@PostConstruct
	public void addDummyData() {
		Car car1 = new Car("2017", "Dodge", "Charger R/T", 22420, "Great! Gets you from Point A to Point B just fine.", 32999.00, "dodge-charger-red1.PNG");
		Car car2 = new Car("2013", "Dodge", "Charger R/T", 130007, "Clean interior. Reliable", 18995.00, "dodge-charger-white1.jpg");
		Car car3 = new Car("2018", "Dodge", "Charger Daytona", 46077, "Cool Car. Very reliable", 36499.00, "dodge-charger-redstripe1.jpg");
		Car car4 = new Car("2020", "Ford", "F-150 XLT", 14243, "Awesome work truck!", 49999.00, "ford-f150-grey1.PNG");
		Car car5 = new Car("2018", "Dodge", "RAM 1500 Sport", 14243, "Beautiful and amazing truck.", 49999.00, "dodge-ram-blue1.png");
		carArray.add(car1);
		carArray.add(car2);
		carArray.add(car3);
		carArray.add(car4);
		carArray.add(car5);
	}
	
//	public void saveCar(Car car) {
//		carArray.add(car);
//	}
	
	public List<Car> getCars(){
		return carArray;
	}
	
	public Car findCarByID(String id) {
		for (Car car : carArray) {
			if (car.getId().equals(id)) {
				return car;
			}
		}
		System.out.println("No car find with that ID");
		return null;
	}

	public void saveCar(Car car) {
		carArray.add(car);
		
	}
	
//	public Car searchByKeyword(String keyword) {
//		for (Car car : carArray) {
//			if( car.getYear().equals(keyword) || 
//				car.getMake().equals(keyword) || 
//				car.getModel().equals(keyword)){
//				return car;
//			}
//		}
//		System.out.println("No car find with that ID");
//		return null;
//	}
	
	
}
