package burner;

import java.util.Scanner;

public class Burner {

   public static void main(String[] args) {

       String name;
       int age;
       Scanner in = new Scanner(System.in);

       // Reads a single line from the console 
       // and stores into name variable
       name = in.nextLine();

       // Reads a integer from the console
       // and stores into age variable
       age=in.nextInt();
       in.close();            
       
       // Prints name and age to the console
       System.out.println("Name :"+name);
       System.out.println("Age :"+age);

    }
}
