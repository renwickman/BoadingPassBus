import entity.Application;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.TimeZone;


public class Trip {
    private Application passenger;
    double minutes;
    double hours;

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
        System.out.println("1. " + "America/New_York");
        System.out.println("2. " + "America/Los_Angeles");
        System.out.println("3. " + "America/Detroit");
        System.out.println("4. " + "America/Phoenix");
        System.out.println("5. " + "America/Louisville");
        System.out.println("6. " + "America/Indiana/Indianapolis");
        return scanDepart.nextLine();
    }

    public String enterArrive(){
        //Place into Map?  It will have a key.
        //String getDisplayName()	is used to return a name of this time zone suitable
        //for presentation to the user in the default locale.
        Scanner scanArrive = new Scanner(System.in);
        System.out.println("Where are you looking to go?");
        System.out.println("1. " + "America/New_York");
        System.out.println("2. " + "America/Los_Angeles");
        System.out.println("3. " + "America/Detroit");
        System.out.println("4. " + "America/Phoenix");
        System.out.println("5. " + "America/Louisville");
        System.out.println("6. " + "America/Indiana/Indianapolis");

        return scanArrive.nextLine();
    }

    public String departDate(){
        //void setID(String ID) is used to set the time zone ID
        //boolean before(Date date) tests if current date is before the given date.
        //boolean after(Date date) tests if current date is after the given date.
        Scanner scanDepartDate = new Scanner(System.in);
        System.out.println("When do you want to leave?");
        System.out.println("Format: MM/DD/YYYY ");
        return scanDepartDate.nextLine();
    }

    public String departTime(){
        //Place into Map? It will have a key.
        //void setID(String ID) is used to set the time zone ID
        //String getID() is used to get the ID of this time zone
        Scanner scanDepartTime = new Scanner(System.in);
        System.out.println("What time do you want to leave? (Select a number to choose a time)");
        System.out.println("1: 6:00 am");
        System.out.println("2: 8:00 am");
        System.out.println("3: 10:00 am");
        System.out.println("4: 12:00 pm");
        System.out.println("5: 2:00 pm");
        System.out.println("6: 4:00 pm");
        System.out.println("7: 6:00 pm");
        System.out.println("8: 8:00 pm");
        System.out.println("9: 10:00 pm");
        //Exceptions or Switch Stmt to make sure they enter the right number.
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
        final String DATE_FORMAT = "MM/dd/yyyy hh:mm a";
        TimeZone endZone = TimeZone.getTimeZone(enterArrive());
        ZoneId endZoneId = ZoneId.of(enterArrive());
        ZonedDateTime endDateTime;
        String leaveDateTime = passenger.getDepartTime() + " " + passenger.getDepartDate();
        LocalDateTime ldt = LocalDateTime.parse(leaveDateTime, DateTimeFormatter.ofPattern(DATE_FORMAT));

        double lat1 = 0;
        double lat2 = 0;
        double lon1 = 0;
        double lon2 = 0;
        double earthRadius = 6371.01 * 0.621;


        if (passenger.getOrigin() == "1"){
            lat1 = Math.toRadians(34.05223);
            lon1 = Math.toRadians(-118.24368);
        }
        else if (passenger.getOrigin() == "2"){
            lat1 = Math.toRadians(40.7648);
            lon1 = Math.toRadians(-73.9808);
        }
        else if (passenger.getOrigin() == "3"){
            lat1 = Math.toRadians(42.331427);
            lon1 = Math.toRadians(-83.045754);
        }

        if (passenger.getDestination() == "1"){
            lat2 = Math.toRadians(34.05223);
            lon2 = Math.toRadians(-118.24368);
        }
        else if (passenger.getDestination() == "2"){
            lat2 = Math.toRadians(40.7648);
            lon2 = Math.toRadians(-73.9808);
        }
        else if (passenger.getDestination() == "3"){
            lat1 = Math.toRadians(42.331427);
            lon1 = Math.toRadians(-83.045754);
        }

        double distance = Math.round(earthRadius * Math.acos(Math.sin(lat1)*Math.sin(lat2)
                + Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon1 - lon2)));

        double distance1 = distance/50;
        hours = Math.floor(distance1);
        minutes = Math.ceil((distance1 - hours) * 60);

        if (enterDepart().equals("1")){
            Calendar newYorkTime = new GregorianCalendar(TimeZone.getTimeZone("America/New_york"));
            TimeZone timezone = TimeZone.getTimeZone("America/New_york");

            ZoneId nyZoneId = ZoneId.of("America/New_york");

            ZonedDateTime nyZonedDateTime = ldt.atZone(nyZoneId);

            newYorkTime.add(Calendar.HOUR, (int) hours);
            newYorkTime.add(Calendar.MINUTE, (int) minutes);

            ZonedDateTime endZoneTime = nyZonedDateTime.withZoneSameInstant(nyZoneId);

            endDateTime = nyZonedDateTime.withZoneSameInstant(nyZoneId);
        }
        else if (enterDepart().equals("2")){
            Calendar laTime = new GregorianCalendar(TimeZone.getTimeZone("America/Los_angeles"));
            TimeZone timezone = TimeZone.getTimeZone("America/Los_angeles");

            ZoneId laZoneId = ZoneId.of("America/Los_angeles");

            ZonedDateTime laZonedDateTime = ldt.atZone(laZoneId);

            //Make String into a Date/Time
            laTime.add(Calendar.HOUR, (int) hours);
            laTime.add(Calendar.MINUTE, (int) minutes);

            ZonedDateTime endZoneTime = laZonedDateTime.withZoneSameInstant(laZoneId);

            endDateTime = laZonedDateTime.withZoneSameInstant(laZoneId);
        }
        else if (enterDepart().equals("3")){
            Calendar detTime = new GregorianCalendar(TimeZone.getTimeZone("America/Detroit"));
            TimeZone timezone = TimeZone.getTimeZone("America/Detroit");
            
            ZoneId detZoneId = ZoneId.of("America/Detroit");

            ZonedDateTime detZonedDateTime = ldt.atZone(detZoneId);

            detTime.add(Calendar.HOUR, (int) hours);
            detTime.add(Calendar.MINUTE, (int) minutes);

            ZonedDateTime endZoneTime = detZonedDateTime.withZoneSameInstant(detZoneId);
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


//            String dateInString = "11-01-2020 05:01 PM";
//            LocalDateTime ldt = LocalDateTime.parse(dateInString, DateTimeFormatter.ofPattern(DATE_FORMAT));
//
//            ZoneId chicagoZoneId = ZoneId.of("America/Chicago");
//            System.out.println("TimeZone : " + chicagoZoneId);
//
//            //LocalDateTime + ZoneId = ZonedDateTime
//            ZonedDateTime chicagoZonedDateTime = ldt.atZone(chicagoZoneId);
//            System.out.println("Date (Chicago) : " + chicagoZonedDateTime);
//
//            ZoneId newYokZoneId = ZoneId.of("America/New_York");
//            System.out.println("TimeZone : " + newYokZoneId);
//
//            ZonedDateTime nyDateTime = chicagoZonedDateTime.withZoneSameInstant(newYokZoneId);
//            System.out.println("Date (New York) : " + nyDateTime);
//
//            DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
//            System.out.println("\n---DateTimeFormatter---");
//            System.out.println("Date (Chicago) : " + format.format(chicagoZonedDateTime));
//            System.out.println("Date (New York) : " + format.format(nyDateTime));