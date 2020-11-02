import entity.Application;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Price {

    private int priceCheck(){
        float price = (float) 300.00;

//        if (Passenger.getGender == F){
//            price = price * .25;
//        }
//        else if (Passenger.getAge <= 12){
//            price = price * .50;
//        }
//        else if (Passenger.getAge >= 60){
//            price = price * .25;
//        }

        return Integer.parseInt(String.valueOf(price));
    }

    void priceUpdate(int appId){
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Application.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            Application currentApp = session.get(Application.class, appId);
            currentApp.setTotal_price(priceCheck());
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }

}
