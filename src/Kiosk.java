import java.util.Scanner;

/**
 * This class is the user interface class that handles the input and output.
 * This class includes the run() method along with private helper methods to take in user inputs and display
 * the corresponding messages to the console.
 * @author Isha Vora, Kathleen Eife
 */
public class Kiosk {

    private int serialNum = 10000;
    public static final String QUIT = "Q";
    public static final String[] VALID_COMMANDS = {"A", "R", "O", "I", "PA", "PD", "PN", "Q"};

    /**
     * This helper method checks whether the user-inputted command is equal to one of the valid commands
     * in the constant, validCommands string array.
     * @param service a string that represents the user's inputted command
     * @return true if the user's command is valid, and false if it is invalid
     */
    private boolean isCommandValid(String service) {
        for (int i = 0; i < VALID_COMMANDS.length; i++) {
            if (service.equals(VALID_COMMANDS[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method handles the user input, calls the appropriate methods, and displays the
     * corresponding messages to the console.
     */
    public void run() {

        System.out.println("Library Kiosk running.");
        Library myLibrary = new Library();
        Scanner scanInput = new Scanner(System.in);  // Create a Scanner object
        String userInput = scanInput.nextLine();  // Read user input
        while (!(userInput.equals(QUIT))) {
            String[] inputParts = userInput.split(",");
            int inputLength = inputParts.length;
            String service = null;
            String bookSerialNum = null;
            String name = null;
            String datePublished = null;

            if (inputLength == 1) {
                service = inputParts[0].trim();
            }
            if (inputLength == 2) {
                service = inputParts[0].trim();
                bookSerialNum = inputParts[1].trim();
            }
            if (inputLength == 3) {
                service = inputParts[0].trim();
                name = inputParts[1].trim();
                datePublished = inputParts[2].trim();
            }
            if (inputLength > 3) {
                System.out.println("Invalid command!");
            }
            else if (!isCommandValid(service)  && service != null && !service.equals("")) {
                System.out.println("Invalid command!");
            }
            else if ((inputLength == 1) && (service.equals("A") || service.equals("R") || service.equals("O")
                      || service.equals("I"))) {
                System.out.println("Invalid command!");
            }
            else if (service.equals("A")) { //add a new Book object to the library
                Date inputDate = new Date(datePublished);
                if (!(inputDate.isValid())) {
                    System.out.println("Invalid Date!");
                }
                else {
                    serialNum++;
                    String serialNumString = Integer.toString(serialNum);
                    Book newBook = new Book(serialNumString, name, false, inputDate);
                    myLibrary.add(newBook);
                    System.out.println(name + " added to the library.");
                }
            }
            else if (service.equals("R")) { //remove a Book object from the library
                Book bookToRemove = new Book(bookSerialNum, null, false, null);
                if (myLibrary.remove(bookToRemove)) {
                    System.out.println("Book# " + bookSerialNum + " removed.");
                }
                else {
                    System.out.println("Unable to remove, the library does not have this book.");
                }
            }
            else if (service.equals("O")) { //check out a Book object from the library
                Book bookToCheckOut = new Book(bookSerialNum, null, false, null);
                if (myLibrary.checkOut(bookToCheckOut)) {
                    System.out.println("You’ve checked out Book#" + bookSerialNum + ". Enjoy!");
                }
                else {
                    System.out.println("Book#" + bookSerialNum + " is not available.");
                }
            }
            else if (service.equals("I")) { //return a Book object to the library
                Book bookToReturn = new Book(bookSerialNum, null, false, null);
                if (myLibrary.returns(bookToReturn)) {
                    System.out.println("Book#" + bookSerialNum + " return has completed. Thanks!");
                }
                else {
                    System.out.println("Unable to return Book#" + bookSerialNum + ".");
                }
            }
            else if (service.equals("PA")) { //print the list of books in their current sequence
                myLibrary.print();
            }
            else if (service.equals("PD")) { //print the list of books by date published
                myLibrary.printByDate();
            }
            else if (service.equals("PN")) { //print the list of books by serial number
                myLibrary.printByNumber();
            }
            userInput = scanInput.nextLine();  // Read user input
        }

        System.out.println("Kiosk session ended.");
        scanInput.close();
    }
}
