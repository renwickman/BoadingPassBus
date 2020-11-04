import entity.Application;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Itinerary {
    private Application passenger;
    Path filePath;
    public Itinerary(Application newApplicant){
        this.passenger = newApplicant;
        filePath = Paths.get(System.getProperty("user.dir") + "/src/itinerary/" + passenger.getName() + ".txt");
    }


    String createBoardPass(){
        Random rand = new Random();
        String pass ="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder res = new StringBuilder();
        for (int i = 0; i <= 12; i++){
            int randIndex = rand.nextInt(pass.length());
                res.append(pass.charAt(randIndex));
            }
            return res.toString();
        }

    void generatePass(int appId){
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Application.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
//            Application currentApp = session.get(Application.class, appId);
            passenger.setBoarding_pass(createBoardPass());
            session.save(passenger);
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }

    public void createFile() {
        try {
            Files.createFile(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeToAFile() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();

        try{
            Files.writeString(filePath, formatter.format(date) + passenger.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}