import entity.Application;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.type.descriptor.java.TimeZoneTypeDescriptor;

import javax.xml.stream.Location;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Trip {
    private Application passenger;
    ZonedDateTime endDateTime;
    String strDate;
    double lat1 = 0;
    double lat2 = 0;
    double lon1 = 0;
    double lon2 = 0;
    double distance;
    double minutes;
    double hours;
    Cities cities;
    Locations depart;
    Locations arrive;

    public Trip(Application newApplicant) {
        this.passenger = newApplicant;
        cities = new Cities();
    }

  public String enterDepart() {

        Scanner scanDepart = new Scanner(System.in);
        System.out.println("Hi " + passenger.getName() + "! Glad that you chose Drive Time.  Now let's get started.");
        System.out.println("Where are you departing from?");
      for (Locations location: cities.getCityList()
           ) {
          System.out.println(location.getId() + "." +location.getTimeZoneString());
      }

      try{

          depart = cities.getCityList().get(Integer.parseInt(scanDepart.nextLine())+1);
=======
          depart = cities.getCityList().get(Integer.parseInt(scanDepart.nextLine())-1);
>>>>>>> Stashed changes
      } catch(Exception e){
          System.out.println(e.getMessage());
      }
      return depart.getTimeZoneString();

      /*
        System.out.println("1. America/New_York");
        System.out.println("2. America/Los_Angeles");
        System.out.println("3. America/Detroit");
        System.out.println("4. America/Phoenix");
        System.out.println("5. America/Louisville");
        System.out.println("6. America/Indiana/Indianapolis");*/
/*        switch (scanDepart.nextLine()) {
            case "1":
                passenger.setOrigin("America/New_York");
                lat1 = Math.toRadians(40.7648);
                lon1 = Math.toRadians(-73.9808);
                break;
            case "2":
                passenger.setOrigin("America/Los_Angeles");
                lat1 = Math.toRadians(34.05223);
                lon1 = Math.toRadians(-118.24368);
                break;
            case "3":
                passenger.setOrigin("America/Detroit");
                lat1 = Math.toRadians(42.331427);
                lon1 = Math.toRadians(-83.045754);
                break;
            case "4":
                passenger.setOrigin("America/Phoenix");
                lat1 = Math.toRadians(33.448377);
                lon1 = Math.toRadians(-112.074037);
                break;
            case "5":
                passenger.setOrigin("America/Louisville");
                lat1 = Math.toRadians(38.252665);
                lon1 = Math.toRadians(-85.758456);
                break;
            case "6":
                passenger.setOrigin("America/Indiana/Indianapolis");
                lat1 = Math.toRadians(39.76863);
                lon1 = Math.toRadians(-86.15804);
                break;
            default:
                System.out.printf("%s ... you made an invalid entry", passenger.getName());
        }*/
        //Exceptions or Switch Stmt to make sure they enter the right number.
        //return scanDepart.nextLine();
    }

    public String enterArrive() {
        //Place into Map?  It will have a key.
        //String getDisplayName()	is used to return a name of this time zone suitable
        //for presentation to the user in the default locale.
        Scanner scanArrive = new Scanner(System.in);
        System.out.println("Where are you arriving to?");
        for (Locations location: cities.getCityList()
        ) {
            System.out.println(location.getTimeZoneString());
        }
        try{
            arrive = cities.getCityList().get(Integer.parseInt(scanArrive.nextLine())-1);
        } catch(Exception e){

        /*
        System.out.println("Where are you looking to go?");
        System.out.println("1. America/New_York");
        System.out.println("2. America/Los_Angeles");
        System.out.println("3. America/Detroit");
        System.out.println("4. America/Phoenix");
        System.out.println("5. America/Louisville");
        System.out.println("6. America/Indianapolis");
        switch (scanArrive.nextLine()){
            case "1":
                passenger.setDestination("America/New_York");
                lat2 = Math.toRadians(40.7648);
                lon2 = Math.toRadians(-73.9808);
                break;
            case "2":
                passenger.setDestination("America/Los_Angeles");
                lat2 = Math.toRadians(34.05223);
                lon2 = Math.toRadians(-118.24368);
                break;
            case "3":
                passenger.setDestination("America/Detroit");
                lat2 = Math.toRadians(42.331427);
                lon2 = Math.toRadians(-83.045754);
                break;
            case "4":
                passenger.setDestination("America/Phoenix");
                lat2 = Math.toRadians(33.448377);
                lon2 = Math.toRadians(-112.074037);
                break;
            case "5":
                passenger.setDestination("America/Louisville");
                lat2 = Math.toRadians(38.252665);
                lon2 = Math.toRadians(-85.758456);
                break;
            case "6":
                passenger.setDestination("America/Indiana/Indianapolis");
                lat2 = Math.toRadians(39.76863);
                lon2 = Math.toRadians(-86.15804);
                break;
            default:
                System.out.printf("%s ... you made an invalid entry", passenger.getName());
        }*/

    }
        return arrive.getTimeZoneString();}

    public String departDate(){
        //regex for correct format
        //boolean after(Date date) tests if current date is after the given date.
        Scanner scanDepartDate = new Scanner(System.in);
        System.out.println("When do you want to leave?");
        System.out.println("Format: MM/DD/YYYY ");
        return scanDepartDate.nextLine();
    }

    public String departTime() {

        //Place into Map or class. It will have a key.
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
        switch (scanDepartTime.nextLine()) {
            case "1":
                passenger.setDepartTime("6:00 pm");
                break;
            case "2":
                passenger.setDepartTime("8:00 am");
                break;
            case "3":
                passenger.setDepartTime("10:00 am");
                break;
            case "4":
                passenger.setDepartTime("12:00 pm");
                break;
            case "5":
                passenger.setDepartTime("2:00 pm");
                break;
            case "6":
                passenger.setDepartTime("4:00 pm");
                break;
            case "7":
                passenger.setDepartTime("6:00 pm");
                break;
            case "8":
                passenger.setDepartTime("8:00 pm");
                break;
            case "9":
                passenger.setDepartTime("10:00 pm");
                break;
            default:
                System.out.printf("%s ... you made an invalid entry", passenger.getName());
        }
        //Exceptions or Switch Stmt to make sure they enter the right number.
        return scanDepartTime.nextLine();
    }

    public String estTimeArrive() {
        final String DATE_FORMAT = "MM/dd/yyyy hh:mm a";
/*            TimeZone endZone = TimeZone.getTimeZone(enterArrive());
            ZoneId endZoneId = ZoneId.of(enterArrive());*/

        String leaveDateTime = passenger.getDepartTime() + " " + passenger.getDepartDate();
        LocalDateTime ldt = LocalDateTime.parse(leaveDateTime, DateTimeFormatter.ofPattern(DATE_FORMAT));

        double earthRadius = 6371.01 * 0.621;

        distance = Math.round(earthRadius * Math.acos(Math.sin(depart.getLat()) * Math.sin(arrive.getLat())
                + Math.cos(depart.getLat()) * Math.cos(arrive.getLat()) * Math.cos(depart.getLon() - arrive.getLon())));

        double distance1 = distance / 50;
        hours = Math.floor(distance1);
        minutes = Math.ceil((distance1 - hours) * 60);
        Calendar endTime = new GregorianCalendar(TimeZone.getTimeZone(arrive.getTimeZoneString()));
        //TimeZone timezone = TimeZone.getTimeZone("America/New_york");

        ZoneId zoneId = ZoneId.of(arrive.getTimeZoneString());

        ZonedDateTime dateTime = ldt.atZone(zoneId);

        endTime.add(Calendar.HOUR, (int) hours);
        endTime.add(Calendar.MINUTE, (int) minutes);

        //ZonedDateTime endZoneTime = nyZonedDateTime.withZoneSameInstant(nyZoneId);

        endDateTime = dateTime.withZoneSameInstant(zoneId);
        DateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy hh:mm");
        return dateFormat.format(endDateTime);
        /*if (enterDepart().equals("1")) {
            Calendar newYorkTime = new GregorianCalendar(TimeZone.getTimeZone("America/New_york"));
//            TimeZone timezone = TimeZone.getTimeZone("America/New_york");

            ZoneId nyZoneId = ZoneId.of("America/New_york");

            ZonedDateTime nyZonedDateTime = ldt.atZone(nyZoneId);

            newYorkTime.add(Calendar.HOUR, (int) hours);
            newYorkTime.add(Calendar.MINUTE, (int) minutes);

            ZonedDateTime endZoneTime = nyZonedDateTime.withZoneSameInstant(nyZoneId);

            endDateTime = nyZonedDateTime.withZoneSameInstant(nyZoneId);
            DateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy hh:mm");
            strDate = dateFormat.format(endDateTime);

        } else if (enterDepart().equals("2")) {
            //Calendar into Los Angeles TimeZone
            Calendar laTime = new GregorianCalendar(TimeZone.getTimeZone("America/Los_angeles"));
//            TimeZone timezone = TimeZone.getTimeZone("America/Los_angeles");

            ZoneId laZoneId = ZoneId.of("America/Los_angeles");

            ZonedDateTime laZonedDateTime = ldt.atZone(laZoneId);

            //Make String into a Date/Time
            laTime.add(Calendar.HOUR, (int) hours);
            laTime.add(Calendar.MINUTE, (int) minutes);

            ZonedDateTime endZoneTime = laZonedDateTime.withZoneSameInstant(laZoneId);

            endDateTime = laZonedDateTime.withZoneSameInstant(laZoneId);
            DateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy hh:mm");
            strDate = dateFormat.format(endDateTime);
        }  else if (enterDepart().equals("3")) {
            Calendar detTime = new GregorianCalendar(TimeZone.getTimeZone("America/Detroit"));
//            TimeZone timezone = TimeZone.getTimeZone("America/Detroit");

            ZoneId detZoneId = ZoneId.of("America/Detroit");

            ZonedDateTime detZonedDateTime = ldt.atZone(detZoneId);

            detTime.add(Calendar.HOUR, (int) hours);
            detTime.add(Calendar.MINUTE, (int) minutes);

            ZonedDateTime endZoneTime = detZonedDateTime.withZoneSameInstant(detZoneId);
            //Make String into a Date/Time
            endDateTime = detZonedDateTime.withZoneSameInstant(detZoneId);
            DateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy hh:mm");
            strDate = dateFormat.format(endDateTime);
        } else if (enterDepart().equals("4")) {
            Calendar phoenixTime = new GregorianCalendar(TimeZone.getTimeZone("America/Detroit"));
//            TimeZone timezone = TimeZone.getTimeZone("America/Phoenix");

            ZoneId detZoneId = ZoneId.of("America/Phoenix");

            ZonedDateTime phoenixZonedDateTime = ldt.atZone(detZoneId);

            phoenixTime.add(Calendar.HOUR, (int) hours);
            phoenixTime.add(Calendar.MINUTE, (int) minutes);

            ZonedDateTime endZoneTime = phoenixZonedDateTime.withZoneSameInstant(detZoneId);
            //Make String into a Date/Time
            endDateTime = phoenixZonedDateTime.withZoneSameInstant(detZoneId);
            DateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy hh:mm");
            strDate = dateFormat.format(endDateTime);
        } else if (enterDepart().equals("5")) {
            Calendar louTime = new GregorianCalendar(TimeZone.getTimeZone("America/Louisville"));
//            TimeZone timezone = TimeZone.getTimeZone("America/Louisville");

            ZoneId louZoneId = ZoneId.of("America/Louisville");

            ZonedDateTime louZonedDateTime = ldt.atZone(louZoneId);

            louTime.add(Calendar.HOUR, (int) hours);
            louTime.add(Calendar.MINUTE, (int) minutes);

            ZonedDateTime endZoneTime = louZonedDateTime.withZoneSameInstant(louZoneId);
            //Make String into a Date/Time
            endDateTime = louZonedDateTime.withZoneSameInstant(louZoneId);
            DateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy hh:mm");
            strDate = dateFormat.format(endDateTime);
        } else if (enterDepart().equals("6")) {
            Calendar indTime = new GregorianCalendar(TimeZone.getTimeZone("America/Indiana/Indianapolis"));
//            TimeZone timezone = TimeZone.getTimeZone("America/Indiana/Indianapolis");

            ZoneId indZoneId = ZoneId.of("America/Indiana/Indianapolis");

            ZonedDateTime indZonedDateTime = ldt.atZone(indZoneId);

            indTime.add(Calendar.HOUR, (int) hours);
            indTime.add(Calendar.MINUTE, (int) minutes);

            ZonedDateTime endZoneTime = indZonedDateTime.withZoneSameInstant(indZoneId);
            //Make String into a Date/Time
            endDateTime = indZonedDateTime.withZoneSameInstant(indZoneId);
            DateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy hh:mm");
            strDate = dateFormat.format(endDateTime);
                    return strDate;
        }*/
    }

    public void updateTrip(int appId) {
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
            currentApp.setEta(estTimeArrive());
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}