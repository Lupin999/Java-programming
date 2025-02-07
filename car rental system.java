import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RentalSystem {


    public static class Car {
        private String make;
        private String model;
        private String registrationNumber;
        private boolean isRented;

        public Car(String make, String model, String registrationNumber) {
            this.make = make;
            this.model = model;
            this.registrationNumber = registrationNumber;
            this.isRented = false; // Initially, the car is not rented
        }

        public String getMake() {
            return make;
        }

        public String getModel() {
            return model;
        }

        public String getRegistrationNumber() {
            return registrationNumber;
        }

        public boolean isAvailable() {
            return !isRented;
        }

        public void rent() {
            isRented = true;
        }

        public void returnCar() {
            isRented = false;
        }

        @Override
        public String toString() {
            return "Car{" +
                    "make='" + make + '\'' +
                    ", model='" + model + '\'' +
                    ", registrationNumber='" + registrationNumber + '\'' +
                    ", isRented=" + isRented +
                    '}';
        }
    }

    // Customer Class
    public static class Customer {
        private String name;
        private String customerId;
        private List<Car> rentedCars; // List of rented cars

        public Customer(String name, String customerId) {
            this.name = name;
            this.customerId = customerId;
            this.rentedCars = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public String getCustomerId() {
            return customerId;
        }

        public List<Car> getRentedCars() {
            return rentedCars;
        }

        public void rentCar(Car car) {
            rentedCars.add(car);
        }

        public void returnCar(Car car) {
            rentedCars.remove(car);
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "name='" + name + '\'' +
                    ", customerId='" + customerId + '\'' +
                    '}';
        }
    }

    // RentalAgency Class
    public static class RentalAgency {
        private List<Car> cars;
        private List<Customer> customers;

        public RentalAgency() {
            this.cars = new ArrayList<>();
            this.customers = new ArrayList<>();
        }

        public void addCar(Car car) {
            cars.add(car);
        }

        public void addCustomer(Customer customer) {
            customers.add(customer);
        }

        public Car findAvailableCar(String make, String model) {
            for (Car car : cars) {
                if (car.isAvailable() && car.getMake().equals(make) && car.getModel().equals(model)) {
                    return car;
                }
            }
            return null; // No matching car available
        }

        public void rentCar(Customer customer, Car car) {
            if (car != null && customer != null && car.isAvailable()) {
                car.rent();
                customer.rentCar(car);
                System.out.println(customer.getName() + " rented " + car.getMake() + " " + car.getModel());
            } else {
                System.out.println("Car rental failed. Please check availability or customer details.");
            }
        }

        public void returnCar(Customer customer, Car car) {
            if (car != null && customer != null) {
                car.returnCar();
                customer.returnCar(car);
                System.out.println(car.getMake() + " " + car.getModel() + " returned.");
            }
        }

        public void displayAvailableCars() {
            System.out.println("Available cars:");
            for (Car car : cars) {
                if (car.isAvailable()) {