/*
 * Aarav Goyal
 * 10/30/24
 * Electric.java
 * 
 * This program prompts the user to enter which plan a user wants to take a 
 * residential bill, commercial bill, or an industrial bill.
 * 
 * Working on: if-else statements for conditional logic.
 * 
 * Testing: 
 * Residential, hours = 1000, fee = $107.00
 * Commercial, hours = 1000, fee = $203.00
 * Industrial, peakhours = 100, off-peakhours = 150, fee = $260.00
 * Residential, hours = 1, fee = $12.095
 * Commercial, hours = 1001, fee = $120.083
 * Industrial, peakhours = 1000, off-peakhours = 1000, fee = $260.00;
 * 
 * Pseudocode:
 * 
 * main: Initializes the program by creating an instance of the Electric class.
 * 
 * getData: 
 * - Prompt user for bill type and validate input.
 * - Prompt user for kilowatt hours and validate input.
 * 
 * calculateCost:
 * - Use if-else statements to determine the cost based on bill type and hours.
 * 
 * printInfo:
 * - Print the bill type, hours, and calculated cost.
 */

import java.util.Scanner; // Import the Scanner class for user input

public class Electric 
{
    private String billType; // Type of electric bill (Residential, Commercial, Industrial)
    private double hours; // Total hours for Residential and Commercial
    private double peakHours; // Peak hours for Industrial
    private double offPeakHours; // Off-peak hours for Industrial
    private double cost; // Total cost of the electric bill

    public Electric() 
    {
        // Constructor initializes the Electric object
    }

    public static void main(String[] args) 
    {
        Electric electric = new Electric(); // Create an instance of Electric
        electric.computeIt(); // Start the computation process
    }

    public void computeIt() 
    {
        getData(); // Gather input data from the user
        calculateCost(); // Calculate the cost based on input
        printInfo(); // Display the calculated bill information
    }

    public void getData() 
    {
        Scanner in = new Scanner(System.in); // Create a Scanner object for input

        // Get bill type
        System.out.print("Enter the type of electric bill, R for residential, C for commercial, I for industrial: ");
        billType = in.nextLine(); // Read the bill type input

        // Get hours based on bill type
        if (billType.equals("R")) 
        {
            // Prompt for hours if Residential
            System.out.print("Enter the number of kilowatt hours of electricity used: ");
            hours = in.nextDouble();
        } 
        else if (billType.equals("C")) 
        {
            // Prompt for hours if Commercial
            System.out.print("Enter the number of kilowatt hours of electricity used: ");
            hours = in.nextDouble();
        } 
        else if (billType.equals("I")) 
        {
            // Prompt for peak and off-peak hours if Industrial
            System.out.print("Enter the number of kilowatt hours of electricity used during peak hours: ");
            peakHours = in.nextDouble();
            System.out.print("Enter the number of kilowatt hours of electricity used during off-peak hours: ");
            offPeakHours = in.nextDouble();
        }
        else
        {
            // Handle invalid bill type input
            System.out.println("Error, please try again.");
        }

        in.close(); // Close the scanner to prevent resource leaks
    }

    public void calculateCost() 
    {
        // Calculate the cost based on the type of bill
        if (billType.equals("R")) 
        {
            // Calculate cost for Residential
            cost = 12.00 + (0.095 * hours);
        } 
        else if (billType.equals("C")) 
        {
            // Calculate cost for Commercial
            if (hours <= 1000) 
            {
                cost = 120.00; // Base fee for 1000 hours or less
            } 
            else 
            {
                cost = 120.00 + (0.083 * (hours - 1000)); // Additional cost for excess hours
            }
        } 
        else if (billType.equals("I")) 
        {
            // Calculate cost for Industrial
            double peakCost = 0;
            if (peakHours <= 1000) 
            {
                peakCost = 152.00; // Base fee for peak hours
            } 
            else 
            {
                peakCost = 152.00 + (0.109 * (peakHours - 1000)); // Additional cost for excess peak hours
            }
            double offPeakCost = 0;
            if (offPeakHours <= 1000) 
            {
                offPeakCost = 108.00; // Base fee for off-peak hours
            } 
            else 
            {
                offPeakCost = 108.00 + (0.047 * (offPeakHours - 1000)); // Additional cost for excess off-peak hours
            }
            cost = peakCost + offPeakCost; // Total cost for Industrial
        }
    }

    public void printInfo() 
    {
        // Print the calculated information based on bill type
        if (billType.equals("R")) 
        {
            System.out.printf("Residential Bill\nHours: %.2f Cost $%.2f\n", hours, cost);
        } 
        else if (billType.equals("C")) 
        {
            System.out.printf("Commercial Bill\nHours: %.2f Cost $%.2f\n", hours, cost);
        } 
        else if (billType.equals("I")) 
        {
            System.out.printf("Industrial Bill\nPeak Hours: %.2f Off-Peak Hours: %.2f Cost: $%.2f\n", peakHours, offPeakHours, cost);
        }
    }
}
