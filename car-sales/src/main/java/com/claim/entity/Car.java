package com.claim.entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;

public class Car {
	private String year;
	private String make;
	private String model;
	private double mileage;
	private String description;
	private double price;
	private LocalDate dateOfPurchase;
	private String imagePath;
	private String id;
	private boolean specialPricing;
	
	public Car() {}

	public Car(String year, String make, String model, double mileage, String description, double price, String imagePath) {
		this.year = year;
		this.make = make;
		this.model = model;
		this.mileage = mileage;
		this.description = description;
		this.price = price;
		this.imagePath = imagePath;
		this.id = generateID();
		this.dateOfPurchase = generatePurchaseDate();
		this.specialPricing = checkSpecialPricing(this.dateOfPurchase);
	};
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getMileage() {
		return mileage;
	}

	public void setMileage(double mileage) {
		this.mileage = mileage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(LocalDate dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public boolean getSpecialPricing() {
		return specialPricing;
	}

	public void setSpecialPricing(boolean specialPricing) {
		this.specialPricing = specialPricing;
	}

	
	// ---------------------------- Start of Methods --------------------------------- //
	public String generateID() {
		Random randomNumber = new Random();
		return id = String.valueOf(randomNumber.nextInt(100000));
	}
	
	public LocalDate generatePurchaseDate() {
		// First try
//		String s = new SimpleDateFormat("MMddyyyy").format(new Date());
//		System.out.println("Purchase Date: " + s);
//		return s;
		
		// Second Try: Got info from here: https://www.logicbig.com/how-to/code-snippets/jcode-java-random-random-dates.html
		LocalDate randomDate = createRandomDate(2020, 2021);
		System.out.println("Date Generated: " + randomDate);
	    return randomDate;
	}
	
	private int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    private LocalDate createRandomDate(int startYear, int endYear) {
        int day = createRandomIntBetween(1, 28);
        int month = createRandomIntBetween(1, 7);
        int year = createRandomIntBetween(startYear, endYear);
        return LocalDate.of(year, month, day);
    }

    public boolean checkSpecialPricing(LocalDate dateOfPurchase) { // Got info from here: https://mkyong.com/java8/java-8-how-to-calculate-days-between-two-dates/
    	boolean special = false;
    	LocalDate today = LocalDate.now();
    	long result = ChronoUnit.DAYS.between(dateOfPurchase, today);
    	if (result > 120) {
    		special = true;
    	}
    	this.price = applyDiscount(special, price);
    	System.out.println("Special Pricing: " + special);
    	return special;
    }

    public double applyDiscount(boolean specialPricing, double price) {
    	double newPrice = this.price;
    	if (specialPricing == true) {
    		newPrice = price - (price * .10);
    	}
    	System.out.println("New Price: " + newPrice);
    	return newPrice;
    }
}
