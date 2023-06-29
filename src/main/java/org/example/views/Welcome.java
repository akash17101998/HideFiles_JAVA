package org.example.views;

import org.example.dao.UserDAO;
import org.example.model.User;
import org.example.service.GenerateOTP;
import org.example.service.SendOTPService;
import org.example.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class Welcome {
    public void welcomeScreen(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the application");
        System.out.println("Press 1 to login");
        System.out.println("Press 2 to signup");
        System.out.println("Press 0 to exit");
        System.out.println("Please enter any option");
        int choice=0;
        try {
            choice = Integer.parseInt(br.readLine());
        }catch (IOException e){
            e.printStackTrace();
        }
        switch (choice){
            case 1-> login();
            case 2-> signUp();
            case 0-> System.exit(0);
        }
    }

    private void signUp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name");
        String name = sc.nextLine();
        System.out.println("Enter email");
        String email = sc.nextLine();
        String genOTP = GenerateOTP.getOTP();
        SendOTPService.sendOTP(email,genOTP);
        System.out.println("Enter the OTP");
        String otp = sc.nextLine();
        if(otp.equals(genOTP)){
            User user = new User(name, email);
            int response = UserService.saveUser(user);
            System.out.println("this is the response--------------------- "+response);
            switch (response){
                case 0-> System.out.println("User already registered...");
                case 1-> System.out.println("User Created...");
            }
        }else {
            System.out.println("Wrong OTP");
        }
    }

    private void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter email");
        String email = sc.nextLine();
        try {
            if (UserDAO.isExist(email)){
                String genOTP = GenerateOTP.getOTP();
                SendOTPService.sendOTP(email,genOTP);
                System.out.println("Enter the OTP");
                String otp = sc.nextLine();
                if(otp.equals(genOTP)){
                    new UserView(email).home();
                    System.out.println("Welcome");
                }else {
                    System.out.println("Wrong OTP");
                }
            }else {
                System.out.println("User not found");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
