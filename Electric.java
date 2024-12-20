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
	private final double conversionRateR; // Conversion Rate for Residential
	private final double conversionRateC; // Conversion Rate for Commercial
	private final double conversionRatePeakHours; // Conversion Rate for PeakHours
	private final double conversionRateOffPeakHours; // Conversion Rate for OffPeakHours

	// Constructor initializes variables to default values
	public Electric() 
	{
		billType = new String(""); // Initialize bill type to an empty string
		hours = 0.0; // Initialize hours to zero for Residential and Commercial
		peakHours = 0.0; // Initialize peak hours to zero for Industrial
		offPeakHours = 0.0; // Initialize off-peak hours to zero for Industrial
		cost = 0.0; // Initialize cost to zero
		conversionRateR = 0.095;  // Initialize conversionRateR to 0.095
		conversionRateC = 0.083;  // Initialize conversionRateC to 0.083
		conversionRatePeakHours = 0.109; // Initialize conversionRatePeakHours to 0.109
		conversionRateOffPeakHours = 0.047; // Initialize conversionRateOffPeakHours to 0.047
	}

	// Main method to start the program
	public static void main(String[] args) 
	{
		Electric electric = new Electric(); // Create an instance of Electric
		electric.computeIt(); // Start the computation process
	}	

	// Method to control the flow of data collection and cost calculation
	public void computeIt() 
	{
		getData(); // Gather input data from the user
		calculateCost(); // Calculate the cost based on input
		// Check if hours, peakHours, or offPeakHours are negative
		printInfo(); // Display the calculated bill information
	}

	// Method to collect user input for bill type and hours
	public void getData() 
	{
		Scanner in = new Scanner(System.in); // Create a Scanner object for input

		// Get bill type from user input
		System.out.printf("\n\n\nEnter the type of electric bill, R for residential, C for commercial, I for industrial -> ");
		billType = in.nextLine(); // Read the bill type input

		// Get hours based on bill type
		if (billType.equals("R")) 
		{
			// Prompt for hours if Residential
			System.out.printf("%-87s%s","Enter the number of kilowatt hours of electricity used","-> ");
			hours = in.nextDouble(); // Read hours for Residential
		} 
		else if (billType.equals("C")) 
		{
			// Prompt for hours if Commercial
			System.out.printf("%-87s%s","Enter the number of kilowatt hours of electricity used","-> ");
			hours = in.nextDouble(); // Read hours for Commercial
		} 
		else if (billType.equals("I")) 
		{
			// Prompt for peak and off-peak hours if Industrial
			System.out.printf("%-87s%s","Enter the number of kilowatt hours of electricity used during peak hours","-> ");
			peakHours = in.nextDouble(); // Read peak hours for Industrial
			System.out.printf("%-87s%s","Enter the number of kilowatt hours of electricity used during off-peak hours","-> ");
			offPeakHours = in.nextDouble(); // Read off-peak hours for Industrial
		}


		in.close(); // Close the scanner to prevent resource leaks
	}

	// Method to calculate the cost based on bill type and hours
	public void calculateCost() 
	{
		// Calculate the cost based on the type of bill
		if (billType.equals("R")) 
		{
			// Calculate cost for Residential
			cost = 12.00 + (conversionRateR * hours); // Base fee plus variable cost per hour
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
				cost = 120.00 + (conversionRateC * (hours - 1000)); // Additional cost for excess hours
			}

		} 
		else if (billType.equals("I")) 
		{
			// Calculate cost for Industrial
			double peakCost = 0.0;
			if (peakHours <= 1000 && peakHours > 0) 
			{
				peakCost = 152.00; // Base fee for peak hours
			} 
			else
			{
				peakCost = 152.00 + (conversionRatePeakHours * (peakHours - 1000)); // Additional cost for excess peak hours
			}

			double offPeakCost = 0.0;
			if (offPeakHours <= 1000 && offPeakHours > 0) 
			{
				offPeakCost = 108.00; // Base fee for off-peak hours
			} 
			else
			{
				offPeakCost = 108.00 + (conversionRateOffPeakHours * (offPeakHours - 1000)); // Additional cost for excess off-peak hours
			}
			cost = peakCost + offPeakCost; // Total cost for Industrial
		}
	}

	// Method to print the calculated information based on bill type
	public void printInfo() 
	{
		if (hours < 0 || peakHours < 0 || offPeakHours < 0)
		{
			System.out.println("\n\nPlease enter a positive number of hours.\n\n\n"); // Prompt error message
		}
		else
		{
			// Print the calculated information based on bill type
			if (billType.equals("R")) 
			{
				// Display information for Residential Bill
				System.out.printf("\n\n\nResidential Bill\n\n%-20s%,.2f \n%-20s%s%,.2f\n\n\n\n", "Peak Hours", hours,"Cost", "$", cost);
			} 
			else if (billType.equals("C")) 
			{
				// Display information for Commercial Bill
				System.out.printf("\n\n\nCommercial Bill\n\n%-20s%,.2f \n%-20s%s%,.2f\n\n\n\n", "Peak Hours", hours,"Cost", "$", cost);
			}
			else if (billType.equals("I")) 
			{
				// Display information for Industrial Bill
				System.out.printf("\n\n\nIndustrial Bill\n\n%-20s%,.2f\n%-20s%,.2f\n%-20s%s%,.2f\n\n\n\n","Peak Hours", peakHours, "Off-Peak Hours", offPeakHours, "Cost", "$", cost);
			}
			else
			{
				// Handle invalid bill type input
				System.out.println("\n\n\nError, please try again.\n\n\n"); // Error message for invalid input
			}
		}	
	}
}
