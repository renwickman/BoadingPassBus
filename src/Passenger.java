import entity.Application;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.Scanner;

public class Passenger {
    private Application newApplicant;
    private Date date;

    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
            .addAnnotatedClass(Application.class)
            .buildSessionFactory();
    Session session = factory.getCurrentSession();
    Scanner scan = new Scanner(System.in);


    public Passenger() {
        try {
            System.out.println("Hello! Welcome to Trip Time!");
            newApplicant = new Application();
            newApplicant.setName(enterName());
            newApplicant.setAge(enterAge());
            newApplicant.setGender(enterGender());
            newApplicant.setPhone(enterPhone());
            newApplicant.setEmail(enterEmail());
            session.beginTransaction();
            session.save(newApplicant);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
            factory.close();
            scan.close();
        }
    }

    public String enterName() {
        while (true) {
            String name = readInput("What is your name?");
            if (!name.isEmpty())
                return name;
            System.out.println("Please enter a valid name!");
        }
    }

    public int enterAge() {
        while (true) {
            String age = readInput("How old are you?");
            if (age.matches("[0-9]+") && Integer.parseInt(age) < 200)
                return Integer.parseInt(age);
            System.out.println("Please enter a valid number for your age!");
        }

    }

    public String enterGender() {
        //Make into Boolean?
        while (true) {
            String gender = readInput("What is your Gender?\n M for male\n F for female");
            if(gender.toLowerCase().matches("[FMfm]"))
                return gender;
            System.out.println("Please enter M for male or F for female");
        }
    }

    public long enterPhone() {
        while (true) {
            String phoneNumber = readInput("In case of emergency, what is your phone number?");
            if (phoneNumber.matches("[0-9]{10}+"))
                return Long.parseLong(phoneNumber);
            System.out.println("Please enter a valid phone number");
        }
    }

    public String enterEmail() {
        while (true) {
            String email = readInput("What is your email?");
            if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$"))
                return email;
            System.out.println("Email address invalid format. Try again...");
        }
    }

    public Application getNewApplicant() {
        return newApplicant;
    }

    public String readInput(String message) {
        try {
            System.out.println(message);
            scan.reset();
            return scan.nextLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
}