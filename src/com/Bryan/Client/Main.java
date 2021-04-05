package com.Bryan.Client;

import com.Bryan.api.Connector;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Internal Variables
        boolean loginLoop = true;
        boolean accountFunction = true;
        boolean logoutLoop;
        String inputUsername, inputPassword;
        String[] sender = new String[4];
        /*
          sender parameter
          0 Modifier
          1 Element 1
          2 Element 2
          3 Sub Modifier
         */
        Scanner sc = new Scanner(System.in);
        boolean ConnectionLoop = true;


        while (ConnectionLoop) {
            System.out.println("Enter the hostname / ip address of the Server ");
            String ipHost = sc.next();

            System.out.println("Enter the port");
            String port = sc.next();
            try {
                // Connections
                Registry reg = LocateRegistry.getRegistry(ipHost, Integer.parseInt(port));
                Connector connect = (Connector) reg.lookup("server");

                // Rest of the program would be here
                ConnectionLoop = false; // Just to be sure
                System.out.println("Now Connected :D");

                while (loginLoop) {
                    // Telling the server someone is loggin in
                    sender[0] = "login";
                    // connect.process(sender);

                    // Getting email and password
                    System.out.println("Enter Username");
                    inputUsername = sc.next().toLowerCase();
                    System.out.println("Enter Password for " + inputUsername);
                    inputPassword = sc.next();

                    // Sending data via RMI-api for Processing
                    sender[0] = "account";
                    sender[1] = inputUsername;
                    sender[2] = inputPassword;
                    sender[3] = ""; // Initializer for 4th value
                    String inEmail = connect.process(sender)[0];
                    String inPass = connect.process(sender)[1];


                    // Process of checking the account availability and security
                    if (inEmail.equals(inputUsername) && inPass.equals(inputPassword)) {
                        // Loading the user
                        sender[3] = "load";

                        // Getting the User info to User Class
                        User.setUsername(connect.process(sender)[0]);
                        User.setPassword(connect.process(sender)[1]);
                        User.setRegion(connect.process(sender)[2]);
                        User.setType(connect.process(sender)[3]);

                        System.out.println("Welcome " + User.getType() + " " + User.getUsername());

                        // Terminates the loop for login
                        loginLoop = false;
                        // Initializes the loop for what the account can do.
                        accountFunction = true;
                    } else {
                        System.out.println("Invalid Username or Password");
                    }
                }

                // Loop for Account function
                while (accountFunction) {

                    if (User.getType().equals("SM")) { // Separates the function for Senior Manager to Regional Director
                        System.out.println("What do you want to do? \n [1] Generate Region A \n [2] Generate Region B \n [3] Generate Region C \n [4] Generate All Regions  \n [0] Logout \n Enter the number of your choice");
                        String choice = sc.next();

                        switch (Integer.parseInt(choice)) {
                            case 1: { // Generate Region A
                                System.out.println("Set the target number of client");
                                String goal = sc.next();

                                // Sending the signal to generate report and printing
                                sender[0] = "report";
                                sender[1] = "A";
                                System.out.println(connect.process(sender)[0]);

                                if (Integer.parseInt(connect.process(sender)[1]) < Integer.parseInt(goal)) {
                                    System.out.println();
                                    System.out.println("Number of Clients: " + connect.process(sender)[1]);
                                    System.out.println("Region Failed");
                                } else {
                                    System.out.println("Number of Clients: " + connect.process(sender)[1]);
                                    System.out.println("Region Success");
                                }
                                System.out.println();
                                sender[0] = "idle";
                                break;
                            }
                            case 2: { // Generate Region B
                                System.out.println("Set the target number of client");
                                String goal = sc.next();

                                // Sending the signal to generate report and printing
                                sender[0] = "report";
                                sender[1] = "B";
                                System.out.println(connect.process(sender)[0]);

                                if (Integer.parseInt(connect.process(sender)[1]) < Integer.parseInt(goal)) {
                                    System.out.println();
                                    System.out.println("Number of Clients: " + connect.process(sender)[1]);
                                    System.out.println("Region Failed");
                                } else {
                                    System.out.println("Number of Clients: " + connect.process(sender)[1]);
                                    System.out.println("Region Success");
                                }
                                System.out.println();
                                break;
                            }
                            case 3: { // Generate Region C
                                System.out.println("Set the target number of client");
                                String goal = sc.next();

                                // Sending the signal to generate report and printing
                                sender[0] = "report";
                                sender[1] = "C";
                                System.out.println(connect.process(sender)[0]);

                                if (Integer.parseInt(connect.process(sender)[1]) < Integer.parseInt(goal)) {
                                    System.out.println();
                                    System.out.println("Number of Clients: " + connect.process(sender)[1]);
                                    System.out.println("Region Failed");
                                } else {
                                    System.out.println("Number of Clients: " + connect.process(sender)[1]);
                                    System.out.println("Region Success");
                                }
                                System.out.println();
                                break;
                            }
                            case 4: { // Generate All Regions
                                System.out.println("Set the target number of client");
                                String goal = sc.next();

                                // Region A
                                sender[0] = "report";
                                sender[1] = "A";
                                System.out.println(connect.process(sender)[0]);

                                if (Integer.parseInt(connect.process(sender)[1]) < Integer.parseInt(goal)) {
                                    System.out.println();
                                    System.out.println("Number of Clients: " + connect.process(sender)[1]);
                                    System.out.println("Region Failed");
                                } else {
                                    System.out.println("Number of Clients: " + connect.process(sender)[1]);
                                    System.out.println("Region Success");
                                }

                                System.out.println();

                                //Region B
                                sender[1] = "B";
                                System.out.println(connect.process(sender)[0]);

                                if (Integer.parseInt(connect.process(sender)[1]) < Integer.parseInt(goal)) {
                                    System.out.println();
                                    System.out.println("Number of Clients: " + connect.process(sender)[1]);
                                    System.out.println("Region Failed");
                                } else {
                                    System.out.println("Number of Clients: " + connect.process(sender)[1]);
                                    System.out.println("Region Success");
                                }
                                System.out.println();

                                //Region C
                                sender[1] = "C";
                                System.out.println(connect.process(sender)[0]);

                                if (Integer.parseInt(connect.process(sender)[1]) < Integer.parseInt(goal)) {
                                    System.out.println();
                                    System.out.println("Number of Clients: " + connect.process(sender)[1]);
                                    System.out.println("Region Failed");
                                } else {
                                    System.out.println("Number of Clients: " + connect.process(sender)[1]);
                                    System.out.println("Region Success");
                                }
                                break;
                            }
                            case 0: { // Logout
                                logoutLoop = true;
                                while (logoutLoop) {
                                    System.out.println("Are you sure  to logout? \n [1 or Y] Yes \n [2 or N] No");
                                    String choice2 = sc.next();

                                    switch (choice2) {
                                        case "1":
                                        case "Y":
                                        case "y": {
                                            System.out.println("Logged Out");
                                            System.out.println("Thank you for using the program :D");

                                            // Terminates the Account function loop
                                            accountFunction = false;
                                            // Terminates the logout loop;
                                            logoutLoop = false;
                                            break;
                                        }
                                        case "2":
                                        case "N":
                                        case "n": {
                                            System.out.println("Ok then stay");
                                            logoutLoop = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Invalid choice!");
                                            break;
                                        }
                                    }
                                }
                                System.out.println();
                                break;
                            }
                            default: {
                                System.out.println("Invalid choice!");
                                break;
                            }
                        }
                    } else {
                        // Non SM
                        System.out.println("What do you want to do? \n [1] Generate Region " + User.getRegion() + " report \n [0] Logout \n Enter the number of your choice");
                        String choice = sc.next();
                        switch (Integer.parseInt(choice)) {
                            case 1: {
                                System.out.println("Set the target number of client");
                                String goal = sc.next();

                                sender[0] = "report";
                                sender[1] = User.getRegion();
                                System.out.println(connect.process(sender)[0]);

                                if (Integer.parseInt(connect.process(sender)[1]) < Integer.parseInt(goal)) {
                                    System.out.println();
                                    System.out.println("Number of Clients: " + connect.process(sender)[1]);
                                    System.out.println("Region Failed");
                                } else {
                                    System.out.println("Number of Clients: " + connect.process(sender)[1]);
                                    System.out.println("Region Success");
                                }
                                System.out.println();
                                break;
                            }
                            case 0: { // Logout
                                logoutLoop = true;
                                while (logoutLoop) {
                                    System.out.println("Are you sure  to logout? \n [1 or Y] Yes \n [2 or N] No");
                                    String choice2 = sc.next();

                                    switch (choice2) {
                                        case "1":
                                        case "Y":
                                        case "y": {
                                            System.out.println("Logged Out");
                                            System.out.println("Thank you for using the program :D");

                                            // Terminates the Account function loop
                                            accountFunction = false;
                                            // Terminates the logout loop;
                                            logoutLoop = false;
                                            break;
                                        }
                                        case "2":
                                        case "N":
                                        case "n": {
                                            System.out.println("Ok then stay");
                                            logoutLoop = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Invalid choice!");
                                            break;
                                        }
                                    }
                                }
                                System.out.println();
                                break;
                            }
                            default: {
                                System.out.println("Invalid choice!");
                                break;
                            }
                        }
                    }
                }

            } catch (RemoteException | NotBoundException e) {
                System.out.println("Error in hostname " + ipHost);
                ConnectionLoop = true;
            }

            // System.out.println(connect.process(sender)[0] + " " + connect.process(sender)[1]);
        }
    }
}