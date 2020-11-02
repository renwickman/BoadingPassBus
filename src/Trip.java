import entity.Application;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.TimeZone;

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
        double lat1 = 0;
        double lat2 = 0;
        double lon1 = 0;
        double lon2 = 0;
        double earthRadius = 6371.01 * 0.621;

        if (passenger.getOrigin() == "America/Los_Angeles"){
            lat1 = Math.toRadians(34.05223);
            lon1 = Math.toRadians(-118.24368);
        }
        else if (passenger.getOrigin() == "America/New_york"){
            lat1 = Math.toRadians(40.7648);
            lon1 = Math.toRadians(-73.9808);
        }
        else if (passenger.getOrigin() == "America/Detroit"){
            lat1 = Math.toRadians(42.331427);
            lon1 = Math.toRadians(-83.045754);
        }

        if (passenger.getDestination() == "America/Los_Angeles"){
            lat2 = Math.toRadians(34.05223);
            lon2 = Math.toRadians(-118.24368);
        }
        else if (passenger.getDestination() == "America/New_york"){
            lat2 = Math.toRadians(40.7648);
            lon2 = Math.toRadians(-73.9808);
        }
        else if (passenger.getDestination() == "America/Detroit"){
            lat1 = Math.toRadians(42.331427);
            lon1 = Math.toRadians(-83.045754);
        }

        double distance = Math.round(earthRadius * Math.acos(Math.sin(lat1)*Math.sin(lat2)
                + Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon1 - lon2)));

        double distance1 = distance/50;
        double hours = Math.floor(distance1);
        double minutes = Math.ceil((distance1 - hours) * 60);

        if (enterDepart().equals("America/New_york")){
            TimeZone timezone = TimeZone.getTimeZone("America/New_york");
            //Make String into a Date/Time
        }
        else if (enterDepart().equals("America/Los_angeles")){
            TimeZone timezone = TimeZone.getTimeZone("America/Los_angeles");
            //Make String into a Date/Time
        }
        else if (enterDepart().equals("America/Detroit")){
            TimeZone timezone = TimeZone.getTimeZone("America/Detroit");
            //Make String into a Date/Time
        }


//        Calendar newYorkTime = new GregorianCalendar(TimeZone.getTimeZone("America/New_york"));
//        newYorkTime.setTimeInMillis(newYorkTime.getDepartTime);
//
//        passenger.getDepartTime();
//
//        newYorkTime.add(Calendar.HOUR, 3);
//
//        Calendar denverTime = new GregorianCalendar(TimeZone.getTimeZone("America/Denver"));
//        denverTime.setTimeInMillis(localTime.getTimeInMillis());
//        hour = denverTime.get(Calendar.HOUR);
//        minute = denverTime.get(Calendar.MINUTE);

        //get Depart Time
        //get Depart From
        //get Arrive Place
        //calculate ETA
        //Los Angeles to New York - flight distance = 2,452 miles, 5 hours, 24 minutes
        //Detroit to New York - flight distance = 482 miles, 1 hour, 28 minutes
        return "";
    }
}
