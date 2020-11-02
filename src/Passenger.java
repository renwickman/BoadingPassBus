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


    public Passenger(){
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
            e.getMessage();
        } finally {
            session.close();
            factory.close();
        }
    }

    public String enterName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! Welcome to Drive Time!");
        System.out.println("What is your name?");
        return scanner.nextLine();
    }

    public String enterEmail(){
        Scanner scanEmail = new Scanner(System.in);
        System.out.println("What is your email?");
        return scanEmail.nextLine();
    }

    public int enterAge(){
        Scanner scanAge = new Scanner(System.in);
        System.out.println("How old are you?");
        return scanAge.nextInt();
    }

    public String enterGender(){
        //Make into Boolean?
        Scanner scanGender = new Scanner(System.in);
        System.out.println("What is your gender");
        System.out.println("Enter M for Male");
        System.out.println("Enter F for Female");
        return scanGender.next();
    }

    public int enterPhone(){
        Scanner scanPhone = new Scanner(System.in);
        System.out.println("In case of emergency, what is your phone number?");
        System.out.println("Correct format: '0123456789'");
        return scanPhone.nextInt();
    }

    public Application getNewApplicant(){
        return newApplicant;
    }

}