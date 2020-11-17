package atm;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ATM atm = new ATM();
        boolean wasQuit = false;

        System.out.println("ATM started, write your command:");

        while (!wasQuit) {
            String command = in.next();
            try {
                switch (command) {
                    case "put":
                        System.out.println(atm.put(in.nextInt(), in.nextInt()));
                        break;
                    case "get":
                        System.out.println(atm.get(in.nextInt()));
                        break;
                    case "dump":
                        System.out.println(atm.dump());
                        break;
                    case "state":
                        System.out.println(atm.state());
                        break;
                    case "quit":
                        wasQuit = true;
                        break;
                    default:
                        System.out.println("Invalid command");
                        in.nextLine();
                }
            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
