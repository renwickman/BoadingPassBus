import entity.Application;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jboss.logging.Param;

import java.util.Scanner;

public class Passenger {
    private Application newApplicant;

    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
            .addAnnotatedClass(Application.class)
            .buildSessionFactory();
    Session session = factory.getCurrentSession();
    Scanner scan = new Scanner(System.in);

    //constructor will create a new entity and save it to DB
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

    //user will enter their name
    public String enterName() {
        return getInput("What is your name?", ".*\\S+.*","Please enter a valid name!" );
    }
    //user will enter their age
    public int enterAge() {
        return Integer.parseInt(getInput("How old are you?", "[0-9]{1,3}+","Please enter a valid number for your age!" ));
    }
    //user will enter their gender
    public String enterGender() {
        return getInput("What is your Gender?\nM) for male\nF) for female", "[FMfm]","Please enter M for male or F for female" );
    }
    //user will enter their phone number
    public long enterPhone() {
        return Long.parseLong(getInput("In case of emergency, what is your phone number?", "[0-9]{10}+","Please enter a valid phone number" ));
    }
    //user will enter their email address
    public String enterEmail() {
        return getInput("What is your email address?", "^[A-Za-z0-9+_.-]+@(.+)$","Email address invalid format. Try again..." );
    }
    //accessor for the entity
    public Application getNewApplicant() {
        return newApplicant;
    }
    //used to get input takes in a prompting message, regex string to compare against, and an error message.
    public String getInput(String message, String regex, String errorMessage){
        while (true) {
            String name = readInput(message);
            if (name.matches(regex))
                return name;
            System.out.println(errorMessage);
        }
    }
    //where we ask for input
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