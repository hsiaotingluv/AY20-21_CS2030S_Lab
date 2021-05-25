import java.util.Scanner;

/**
 * The main class for CS2030S Lab 1.
 *
 * @author Chen Hsiao Ting
 * @version CS2030S AY20/21 Semester 2
 */
class Lab1 {
  public static void main(String[] args) {

    // Create a scanner to read from standard input.
    Scanner sc = new Scanner(System.in);

    // Create a simulation.  The ShopSimulation 
    // constructor will read the simulation parameters 
    // and initial events using the scanner.
    Simulation simulation = new Shop(sc);

    // Create a new simulator and run the simulation
    new Simulator(simulation).run();

    // Clean up the scanner.
    sc.close();
  }
}
