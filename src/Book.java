/**
 * This class defines the abstract data type Book, which encapsulates the data fields and methods of a book.
 * This class includes two book constructors to create either a book object with multiple parameters or an
 * empty book object. There is also equals and toString methods along with various getters and setters to
 * access instance variables.
 * @author Isha Vora, Kathleen Eife
 */

public class Book {

    private String number; //a 5-digit serial number unique to the book
    private String name;
    private boolean checkedOut;
    private Date datePublished;

    /**
     * This constructor takes in a book number, name, boolean checkedOut, and datePublished and creates a book object.
     * @param number an automatically generated serial number that is a string
     * @param name the name of the book as a string
     * @param checkedOut a boolean that is true if a book is checked out and false if a book is not checked out
     * @param datePublished a date object that holds the date a book was published
     */
    public Book(String number, String name, boolean checkedOut, Date datePublished) {
        this.number = number;
        this.name = name;
        this.checkedOut = checkedOut;
        this.datePublished = datePublished;
    }

    /**
     * This constructor creates an empty book object.
     */
    public Book() {
        this.number = "";
        this.name = "";
        this.checkedOut = false;
        this.datePublished = null;
    }

    /**
     * This getter method returns the serial number of a book.
     * @return number the serial number of the book
     */
    public String getNumber() {
        return number;
    }

    /**
     * This getter method returns the date that a book was published.
     * @return datePublished the date that a book was published
     */
    public Date getDatePublished() {
        return datePublished;
    }

    /**
     * This getter method returns the boolean checkedOut indicator of a book.
     * @return checkedOut the boolean checkedOut indicator of a book that is true if a book
     * is checked out and false if a book is not checked out
     */
    public boolean getCheckedOut() {
        return checkedOut;
    }

    /**
     * This setter method sets the boolean checkedOut indicator of a book.
     * @param checkedOut a boolean variable that is true if a book is checked out and false if a book
     * is not checked out
     */
    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    /**
     * This method determines if the serial numbers of two Book objects are equal.
     * @param obj Book object to be compared for equality
     * @return true if the serial numbers of the two Book objects are the same, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (this.getClass() != obj.getClass() || obj == null) {
            return false;
        }
        Book objBook = (Book)obj; //obj is a Book object, so typecast it
        int objSerialNum = Integer.parseInt(objBook.number);
        int thisSerialNum = Integer.parseInt(this.number);

        if (objSerialNum != thisSerialNum) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * This method returns a textual representation of a book object, which includes the book number, book name,
     * the date the book was published, and whether the book is available or not.
     * @return bookString a textual representation of a book object
     */
    @Override
    public String toString() {
        String availability;
        String bookString;
        if (checkedOut) {
            availability = "is checked out.";
        }
        else {
            availability = "is available.";
        }
        bookString = ("Book#" + number + "::" + name + "::" + datePublished.getDate() + "::" + availability);
        return bookString;
    }
}
