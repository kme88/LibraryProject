import java.util.Calendar;

/**
 * This is the container class that defines the abstract data type Library to hold library catalog and its
 * operations.
 * This class includes a library constructor to create a bag structure to hold the book objects, various
 * library services such as grow(), add(), remove(), checkOut(), return(), print(), printByDate(),
 * printByNumber(), and various private helper methods.
 * @author Isha Vora, Kathleen Eife
 */

public class Library {

    private Book[] books; // array-based implementation of the bag data structure
    private int numBooks; // the number of books currently in the bag

    public static final int INITIAL_CAPACITY = 4;
    public static final int INCREASE_CAPACITY = 4;
    public static final int NOT_FOUND_IN_LIBRARY = -1;

    /**
     * This default constructor creates an empty bag structure with an initial capacity of 4.
     */
    public Library() {
        books = new Book[INITIAL_CAPACITY];
    }

    /**
     * This method finds a book in the bag structure.
     * @param book a book object
     * @return i an integer that represents the index of the book object in the Book[] books array,
     * or returns NOT_FOUND_IN_LIBRARY if the book is not in the library
     */
    private int find(Book book) {
        for (int i = 0; i < numBooks; i++) {
            if (books[i].equals(book)) {
                return i;
            }
        }
        return NOT_FOUND_IN_LIBRARY;
    }

    /**
     * This method increases the bag capacity by 4.
     */
    private void grow() {
        Book[] tempBooks = new Book[numBooks + INCREASE_CAPACITY];
        for (int i = 0; i < numBooks; i++) {
            tempBooks[i] = books[i];
        }
        books = tempBooks;
    }

    /**
     * This method adds a book to the library.
     * @param book a book object
     */
    public void add(Book book) {
        if (books.length > numBooks) {
            books[numBooks] = book;
            numBooks++;
        }
        else {
            grow();
            books[numBooks] = book;
            numBooks++;
        }
    }

    /**
     * This method removes a book from the library.
     * @param book a book object
     * @return true if the book is successfully removed, false otherwise
     */
    public boolean remove(Book book) {
        int indexOfBook = find(book);
        if (indexOfBook == NOT_FOUND_IN_LIBRARY) {
            return false;
        }
        for (int i = indexOfBook; i < numBooks-1; i++) {
            books[i] = books[i+1];
        }
        numBooks--; //reduce number of books by one after removing one book
        books[numBooks] = new Book();
        return true;
    }

    /**
     * This checks out a book from the library.
     * @param book a book object
     * @return true if the book is successfully checked out, false otherwise
     */
    public boolean checkOut(Book book) {
        int indexOfBook = find(book);
        if (indexOfBook == NOT_FOUND_IN_LIBRARY) {
            return false;
        }
        if (!(books[indexOfBook].getCheckedOut())) {
            books[indexOfBook].setCheckedOut(true);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * This method returns a book to the library.
     * @param book a book object
     * @return true if the book is successfully returned, false otherwise
     */
    public boolean returns(Book book) {
        int indexOfBook = find(book);
        if (indexOfBook == NOT_FOUND_IN_LIBRARY) {
            return false;
        }
        if (books[indexOfBook].getCheckedOut()) {
            books[indexOfBook].setCheckedOut(false);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * This method prints the list of books in the current sequence.
     */
    public void print() {
        if (numBooks == 0) {
            System.out.println("Library catalog is empty!");
        }
        else {
            System.out.println("**List of books in the library.");
            for (int i = 0; i < numBooks; i++) {
                System.out.println(books[i].toString());
            }
            System.out.println("**End of list");
        }
    }

    /**
     * This method prints the list of books by datePublished (ascending).
     */
    public void printByDate() {
        if (numBooks == 0) {
            System.out.println("Library catalog is empty!");
        }
        else {
            System.out.println("**List of books by the dates published.");
            sortByDate(books);
            for (int i = 0; i < numBooks; i++) {
                System.out.println(books[i].toString());
            }
            System.out.println("**End of list");
        }
    }

    /**
     * This method prints the list of books by number (ascending).
     */
    public void printByNumber() {
        if (numBooks == 0) {
            System.out.println("Library catalog is empty!");
        }
        else {
            System.out.println("**List of books by the book numbers.");
            sortByNum(books);
            for (int i = 0; i < numBooks; i++) {
                System.out.println(books[i].toString());
            }
            System.out.println("**End of list");
        }
    }

    /**
     * This private helper method sorts the list of books by number (ascending).
     * @param books books an array containing all of the book objects of the books in the library
     */
    private void sortByNum(Book[] books) {
        for (int i = 0; i < numBooks; i++) {
            int min = i; // min is the index of the smallest element with an index greater or equal to i
            for (int j = i + 1; j < numBooks; j++) {
                int currBookNum = Integer.parseInt(books[j].getNumber());
                int minBookNum = Integer.parseInt(books[min].getNumber());
                if (currBookNum < minBookNum) {
                    min = j;
                }
            }
            // Swapping i-th and min-th elements
            Book swap = books[i];
            books[i] = books[min];
            books[min] = swap;
        }
    }

    /**
     * This private helper method sorts the list of books by date (ascending).
     * @param books an array containing all of the book objects of the books in the library
     */
    private void sortByDate(Book[] books) {
        for (int i = 0; i < numBooks; i++) {
            int min = i; // min is the index of the smallest element with an index greater or equal to i
            for (int j = i + 1; j < numBooks; j++) {
                Date currBookDate = books[j].getDatePublished();
                Date minBookDate = books[min].getDatePublished();
                Calendar currBookCal = Calendar.getInstance();
                Calendar minBookCal = Calendar.getInstance();
                currBookCal.set(currBookDate.getYear(), currBookDate.getMonth()-1, currBookDate.getDay(), 0, 0, 0);
                minBookCal.set(minBookDate.getYear(), minBookDate.getMonth()-1, minBookDate.getDay(), 0, 0, 0);
                int compareDates = currBookCal.compareTo(minBookCal);
                if (compareDates < 0) {
                    min = j;
                }
            }
            // Swapping i-th and min-th elements
            Book swap = books[i];
            books[i] = books[min];
            books[min] = swap;
        }
    }
}
