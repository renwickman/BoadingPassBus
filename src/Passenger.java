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
        }
    }

    public String enterName() {
        System.out.println("Hello! Welcome to Trip Time!");
        while (true) {
            try {
                System.out.println("What is your name?");
                return scan.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
            }
        }
    }

    public int enterAge() {
        String age = "";
        while (true) {
            try {
                scan.reset();
                System.out.println("How old are you?");
                age = scan.nextLine();
                if (age.matches("[0-9]+") && Integer.parseInt(age) < 200)
                    return Integer.parseInt(age);
                throw new Exception("Please enter a valid number for your age!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public String enterGender() {
        //Make into Boolean?
        System.out.println("What is your gender");
        System.out.println("Enter M for Male");
        System.out.println("Enter F for Female");
        return scan.nextLine();
    }

    public long enterPhone() {
        String phoneNumber = "";
        while (true) {
            try {
                scan.reset();
                System.out.println("In case of emergency, what is your phone number?");
                phoneNumber = scan.nextLine();
                if (phoneNumber.matches("[0-9]{10}+"))
                    return Long.parseLong(phoneNumber);
                throw new Exception("Please enter a valid phone number");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public String enterEmail() {
        String email = "";
        while (true) {
            scan.reset();
            System.out.println("What is your email?");
            email = scan.nextLine();
            if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$"))
                return email;
            System.out.println("Email address invalid format. Try again...");
        }

    }


    public Application getNewApplicant() {
        return newApplicant;
    }

}