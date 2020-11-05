import clojure.lang.ExceptionInfo;
import jdk.jshell.spi.ExecutionControlProvider;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello! Welcome to Drive Time!");
        boolean play = true;
        while (play) {
            getMenu();
            play = choice();
        }
    }

    public static void newPass() {
        Passenger pass = new Passenger();
        new Trip(pass.getNewApplicant());
        new Price(pass.getNewApplicant());
        new Itinerary(pass.getNewApplicant());
    }

    public static void getMenu() {
        System.out.println("1. Create new pass");
        System.out.println("2. Exit");
    }

    public static Boolean choice() {
        Scanner scan = new Scanner(System.in);
        try {
            switch (scan.nextLine()) {
                case "1":
                    newPass();
                    return true;
                case "2":
                    System.out.println("Thanks for using Drive Time. Good bye!");
                    return false;
                default:
                    throw new Exception("Invalid Choice! Try again.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
    }
}