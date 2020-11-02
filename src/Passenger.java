import entity.Application;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.Scanner;

public class Passenger {
    private Application newApplicant;
    private Date date;
    public Flight flight;

    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
            .addAnnotatedClass(Application.class)
            .buildSessionFactory();

    Session session = factory.getCurrentSession();

    public String enterName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! Welcome to Direct Airlines");
        System.out.println("What is your name?");
        return scanner.nextLine();
    }

    void createApplicant() {
        try {
            newApplicant = new Application();
            session.beginTransaction();
            newApplicant.setName(enterName());
            session.save(newApplicant);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            factory.close();
        }
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

    public void updateInfo(int appId){
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Application.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            Application currentApp = session.get(Application.class, appId);
            currentApp.setAge(enterAge());
            currentApp.setGender(enterGender());
            currentApp.setPhone(enterPhone());
            currentApp.setEmail(enterEmail());
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }

    public Passenger(){
        createApplicant();
        updateInfo(newApplicant.getId());
//        Flight flight = new Flight(newApplicant);
    }

    public static void main(String[] args) {
        new Passenger();
    }


}
