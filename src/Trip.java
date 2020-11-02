import entity.Application;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Trip {
    private Application passenger;

    public Trip(Application newApplicant) {
        this.passenger = newApplicant;
    }

    public String enterDepart(){
        //Place into Map?  It will have a key.
        //String getDisplayName()	is used to return a name of this time zone suitable
        //for presentation to the user in the default locale.
        Scanner scanDepart = new Scanner(System.in);
        System.out.println("Hi " + passenger.getName() + "! Glad that you chose Drive Time.  Now let's get started.");
        System.out.println("Where are you departing from?");
        System.out.print("America/Indiana/Indianapolis, ");
        System.out.print("America/Los_Angeles, ");
        System.out.print("America/Louisville, ");
        System.out.print("America/New_York, ");
        System.out.print("America/Denver, ");
        System.out.print("America/Detroit, ");
        System.out.print("US/Michigan, ");
        System.out.print("US/Central, ");
        System.out.print("US/Eastern, ");
        System.out.print("US/Arizona");
        return scanDepart.nextLine();
    }

    public String enterArrive(){
        //Place into Map?  It will have a key.
        //String getDisplayName()	is used to return a name of this time zone suitable
        //for presentation to the user in the default locale.
        Scanner scanArrive = new Scanner(System.in);
        System.out.println("Where are you looking to go?");
        System.out.print("America/Indiana/Indianapolis, ");
        System.out.print("America/Los_Angeles, ");
        System.out.print("America/Louisville, ");
        System.out.print("America/New_York, ");
        System.out.print("America/Denver, ");
        System.out.print("America/Detroit, ");
        System.out.print("US/Michigan, ");
        System.out.print("US/Central, ");
        System.out.print("US/Eastern, ");
        System.out.print("US/Arizona");
        return scanArrive.nextLine();
    }

    public String departDate(){
        //void setID(String ID) is used to set the time zone ID
        //boolean before(Date date) tests if current date is before the given date.
        //boolean after(Date date) tests if current date is after the given date.
        Scanner scanDepartDate = new Scanner(System.in);
        System.out.println("When do you want to leave?");
        System.out.print("Format: MM/DD/YYYY ");
        return scanDepartDate.nextLine();
    }

    public String departTime(){
        //Place into Map? It will have a key.
        //void setID(String ID) is used to set the time zone ID
        //String getID() is used to get the ID of this time zone
        Scanner scanDepartTime = new Scanner(System.in);
        System.out.println("What time do you want to leave?");
        System.out.println("1: 6:00 am");
        System.out.println("2: 8:00 am");
        System.out.println("3: 10:00 am");
        System.out.println("4: 12:00 pm");
        System.out.println("5: 2:00 pm");
        System.out.println("6: 4:00 pm");
        System.out.println("7: 6:00 pm");
        System.out.println("8: 8:00 pm");
        System.out.println("9: 10:00 pm");
        return scanDepartTime.nextLine();
    }

    public void updateTrip(int appId){
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Application.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            Application currentApp = session.get(Application.class, appId);
            currentApp.setDestination(enterArrive());
            currentApp.setOrigin(enterDepart());
            currentApp.setDepartDate(departDate());
            currentApp.setDepartTime(departTime());
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }

    public String possibleArrive(){
        //get Depart Date
        //get Depart Time
        //get Depart From
        //get Arrive Place
        //calculate ETA
        //Los Angeles to New York - flight distance = 2,452 miles, 5 hours, 24 minutes
        //Detroit to New York - flight distance = 482 miles, 1 hour, 28 minutes
        return "";
    }
}
