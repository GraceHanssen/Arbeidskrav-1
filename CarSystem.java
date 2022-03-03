import java.util.ArrayList;
import java.util.Scanner;
import java.util.Calendar;

public class CarSystem {

    Scanner scanner = new Scanner(System.in);

    ArrayList<Person> persons = new ArrayList<>();
    ArrayList<Car> cars = new ArrayList<>();

    void run() {
        doMainMenu();
    }

    void doMainMenu() {
        boolean quit = false;
        int choice;

        do {

            System.out.println(
                    "\nMain Menu\n\n"+
                            "1. Register\n"+
                            "2. Search\n"+
                            "3. Calculate\n"+
                            "4. Print\n"+
                            "0. Quit\n"
            );

            do {
                System.out.print("Enter choice: ");
                choice = scanner.nextInt(); scanner.nextLine();
                if(choice<0 || choice>4) System.out.println("Invalid choice.");
            } while(choice<0 || choice>4);

            switch(choice) {
                case 0: quit = true; break;
                case 1: doRegisterMenu(); break;
                case 2: doSearchMenu(); break;
                case 3: doCalculateMenu(); break;
                case 4: doPrintMenu(); break;
            }

        } while(!quit);
    }


    void doRegisterMenu() {
        boolean quit = false;
        int choice;

        do {

            System.out.println(
                    "\nRegister Menu\n\n"+
                            "1. Register Person\n"+
                            "2. Register Car\n"+
                            "0. Back\n"
            );

            do {
                System.out.print("Enter choice: ");
                choice = scanner.nextInt(); scanner.nextLine();
                if(choice<0 || choice>2) System.out.println("Invalid choice.");
            } while(choice<0 || choice>2);

            switch(choice) {
                case 0: quit = true; break;
                case 1: doRegisterPerson(); break;
                case 2: doRegisterCar(null); break;
            }

        } while(!quit);
    }

    void doSearchMenu() {
        boolean quit = false;
        int choice;

        do {

            System.out.println(
                    "\nSearch Menu\n\n"+
                            "1. Search cars of a person.\n"+
                            "2. Search owner of car.\n"+
                            "0. Back\n"
            );

            do {
                System.out.print("Enter choice: ");
                choice = scanner.nextInt(); scanner.nextLine();
                if(choice<0 || choice>2) System.out.println("Invalid choice.");
            } while(choice<0 || choice>2);

            switch(choice) {
                case 0: quit = true; break;
                case 1: doSearchCarsOfAPerson(); break;
                case 2: doSearchOwnerOfACar(); break;
            }

        } while(!quit);

    }

    void doCalculateMenu() {
        boolean quit = false;
        int choice;

        do {

            System.out.println(
                    "\nCalculate Menu\n\n"+
                            "1. Average age of all persons.\n"+
                            "2. Average age of all cars.\n"+
                            "3. Average mileage of all cars.\n"+
                            "0. Back\n"
            );

            do {
                System.out.print("Enter choice: ");
                choice = scanner.nextInt(); scanner.nextLine();
                if(choice<0 || choice>3) System.out.println("Invalid choice.");
            } while(choice<0 || choice>3);

            switch(choice) {
                case 0: quit = true; break;
                case 1: showAverageAgeOfAllPersons(); break;
                case 2: showAverageAgeOfAllCars(); break;
                case 3: showAverageMileageOfAllCars(); break;
            }

        } while(!quit);

    }

    void doPrintMenu() {
        boolean quit = false;
        int choice;

        do {

            System.out.println(
                    "\nPrint Menu\n\n"+
                            "1. Complete overview of people and their cars.\n"+
                            "2. Average value of cars age and mileage.\n"+
                            "3. Average persons age.\n"+
                            "0. Back\n"
            );

            do {
                System.out.print("Enter choice: ");
                choice = scanner.nextInt(); scanner.nextLine();
                if(choice<0 || choice>3) System.out.println("Invalid choice.");
            } while(choice<0 || choice>3);

            switch(choice) {
                case 0: quit = true; break;
                case 1: showCompleteOverview(); break;
                case 2: showAverageValuesOfCarsAgeAndMileage(); break;
                case 3: showAveragePersonAge(); break;
            }

        } while(!quit);
    }

    void doRegisterPerson() {

        Person person = new Person();

        System.out.println("\nRegister Person\n");
        System.out.print("id: "); person.id=scanner.nextInt(); scanner.nextLine();
        System.out.print("Name: "); person.name =scanner.nextLine();
        System.out.print("Year of birth: "); person.yearOfBirth=scanner.nextInt(); scanner.nextLine();

        System.out.print("Number of cars owned? "); int numCars=scanner.nextInt(); scanner.nextLine();

        for(int i=0;i<numCars;i++) {
            doRegisterCar(person.id);
        }

        persons.add(person);

    }

    void doRegisterCar(Integer ownerId) {
        Car car = new Car();
        System.out.println("\nRegister Car\n");
        System.out.print("Producer: "); car.producer=scanner.nextLine();
        System.out.print("Name: "); car.name=scanner.nextLine();

        String ans;
        do {
            System.out.print("Car type: (Personal, Van, Truck)  [P/V/T]? ");
            ans=scanner.nextLine().trim().toUpperCase();
            if(ans.isEmpty() || !"PVT".contains(ans)) System.out.println("Invalid answer.");
        } while(ans.isEmpty() || !"PVT".contains(ans));
        switch(ans) {
            case "P": car.type=Car.CarType.PERSONAL; break;
            case "V": car.type=Car.CarType.VAN; break;
            case "T": car.type=Car.CarType.TRUCK; break;
        }

        System.out.print("Production year: "); car.productionYear=scanner.nextInt(); scanner.nextLine();

        System.out.print("Mileage: "); car.mileage=scanner.nextInt(); scanner.nextLine();

        System.out.print("Mileage date: "); car.mileageDate=scanner.nextLine();

        if(ownerId==null) {
            System.out.print("Owner id: "); car.ownerId=scanner.nextInt(); scanner.nextLine();
        } else {
            car.ownerId = ownerId;
        }

        cars.add(car);
    }

    void doSearchCarsOfAPerson() {
        int personId;
        System.out.println("Search Cars of a Person\n");
        System.out.print("Can you provide us the person id: "); personId=scanner.nextInt(); scanner.nextLine();
        for(Car car: cars) {
            if(car.ownerId==personId) {
                System.out.println("Cars that belong to this person: ");
                showCarInfo(car);
                System.out.println();
            }
        }
    }

    void doSearchOwnerOfACar() {
        String carName;
        System.out.println("Search owner of a car\n");
        System.out.print("Can you provide us the car name:  "); carName=scanner.nextLine();
        for(Car car: cars) {
            if(car.name.equals(carName)) {
                if(car.ownerId==0) {
                    System.out.println("Car is unowned.");
                } else {
                    for(Person person: persons) {
                        if(person.id==car.ownerId) {
                            System.out.println("Car is owned by:");
                            showPersonInfo(person);
                            break;
                        }
                    }
                }
                break;
            }
        }
    }

    void showAverageAgeOfAllPersons() {
        if(persons.size()>0) {
            int sum=0;
            int yearNow=Calendar.getInstance().get(Calendar.YEAR);
            for(Person person: persons) {
                sum+=yearNow-person.yearOfBirth;
            }
            int ave=sum/persons.size();
            System.out.println("Average age of persons: "+ave);
        }
    }

    void showAverageAgeOfAllCars() {
        if(cars.size()>0) {
            int sum=0;
            int yearNow=Calendar.getInstance().get(Calendar.YEAR);
            for(Car car: cars) {
                sum+=yearNow-car.productionYear;
            }
            int ave=sum/cars.size();
            System.out.println("Average age of cars: "+ave);
        }
    }

    void showAverageMileageOfAllCars() {
        if(cars.size()>0) {
            int sum=0;
            for(Car car: cars) {
                sum+=car.mileage;
            }
            int ave=sum/cars.size();
            System.out.println("Average mileage of cars: "+ave);
        }
    }

    void showCompleteOverview() {
        for(Person person: persons) {
            System.out.println("Person");
            System.out.println("\tid: "+person.id);
            System.out.println("\tName: "+person.name);
            System.out.println("\tYear of birth: "+person.yearOfBirth);
            System.out.println();

            for(Car car: cars) {
                if(car.ownerId==person.id) {
                    System.out.println("\tCar:");
                    System.out.println("\t\tProducer: "+car.producer);
                    System.out.println("\t\tName: "+car.name);
                    System.out.println("\t\tType: "+car.type);
                    System.out.println("\t\tProduction year: "+car.productionYear);
                    System.out.println("\t\tMileage: "+car.mileage);
                    System.out.println("\t\tMileage date: "+car.mileageDate);
                    System.out.println();
                }
            }
        }
    }

    void showAverageValuesOfCarsAgeAndMileage() {
        showAverageAgeOfAllCars();
        showAverageMileageOfAllCars();
    }

    void showAveragePersonAge() {
        if(persons.size()>0) {
            int sum=0;
            int yearNow=Calendar.getInstance().get(Calendar.YEAR);
            for(Person person: persons) {
                    sum+=yearNow-person.yearOfBirth;
            }
            int ave=sum/persons.size();
            System.out.println("Average age of persons: "+ave);
        }
    }

    void showPersonInfo(Person person) {
        System.out.println("Person:");
        System.out.println("\tid: "+person.id);
        System.out.println("\tName: "+person.name);
        System.out.println("\tYear of birth: "+person.yearOfBirth);
    }

    void showCarInfo(Car car) {
        System.out.println("Car:");
        System.out.println("\tProducer: "+car.producer);
        System.out.println("\tName: "+car.name);
        System.out.println("\tType: "+car.type);
        System.out.println("\tProduction year: "+car.productionYear);
        System.out.println("\tMileage: "+car.mileage);
        System.out.println("\tMileage date: "+car.mileageDate);
    }

}
