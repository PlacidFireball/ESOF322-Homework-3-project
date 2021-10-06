import java.util.*;

public class Main {

    abstract class Sort {
        void sort(Inventory module) {
            System.out.println("Default sorting method");
        }
    }
    class BubbleSort extends Sort {
        @Override
        void sort(Inventory module) {
            System.out.println("Using Bubble Sort");
        }
    }
    class InsertionSort extends Sort {
        @Override
        void sort(Inventory module) {
            System.out.println("Using Insertion Sort");
        }
    }
    class QuickSort extends Sort {
        @Override
        void sort(Inventory module) {
            System.out.println("Using Quick Sort");
        }
    }
    class MergeSort extends Sort {
        @Override
        void sort(Inventory module) {
            System.out.println("Using Merge Sort");
        }
    }

    abstract class Inventory {
        /* whatever needs to go in the inventory (i.e. A list of InventoryObjects?) */
        Sort defaultSort = new BubbleSort();
        Sort strategy = defaultSort;
    }

    class InventoryModule extends Inventory {
        void setStrategy(Sort newStrategy) {
            strategy = newStrategy;
        }
    }

    Main() {
        final String help = "ESOF 322 Homework 3 Commands\n" +
                "inventory <inventory number:int> - create a new inventory module\n" +
                "sort <inventory number: int> - sort inventory module <inventory number>\n" +
                "set <inventory number: int> <bubble, insertion, quick, merge> - set the default\n\t\tsorting method on inventory module <inventory number>\n" +
                "help - display this prompt\n" +
                "exit - quit the program";
        System.out.println(help);
        Scanner scanner = new Scanner(System.in);
        String userInput;
        String[] tokens;
        LinkedList<InventoryModule> inventoryList = new LinkedList<>();
        boolean exit = false;
        while(!exit) {
            userInput = scanner.nextLine();
            tokens = userInput.split(" ");
            if (tokens[0].equalsIgnoreCase("help")) {
                System.out.println(help);
            }
            else if (tokens[0].equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                exit = true;
            }
            else if (tokens[0].equalsIgnoreCase("set")) {
                if (tokens.length != 3) System.out.println("Usage:\n\tset <inventory number: int> <bubble, insertion, quick, merge>");
                else {
                    try {
                        int inventoryNumber = Integer.parseInt(tokens[1]);
                        if (inventoryList.get(inventoryNumber) == null || inventoryNumber >= inventoryList.size()) {
                            throw new NumberFormatException();
                        }
                        String sortType = null;
                        if (tokens[2].equals("bubble") || tokens[2].equals("insertion") || tokens[2].equals("quick") || tokens[2].equals("merge")) {
                            sortType = tokens[2];
                        }
                        if (sortType == null) {
                            System.out.println("You did not select a valid sort type.");
                            System.out.println("Usage:\n\tset <inventory number: int> <bubble, insertion, quick, merge>");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("You did not select a valid inventory number.");
                        System.out.println("Usage:\n\tset <inventory number: int> <bubble, insertion, quick, merge>");
                    }
                }
            }
            else if (tokens[0].equalsIgnoreCase("inventory")) {
                if (tokens.length != 2) System.out.println("Usage:\n\tinventory <inventory number: int>");
                else {
                    try {
                        int inventoryNumber = Integer.parseInt(tokens[1]);
                        inventoryList.add(inventoryNumber, new InventoryModule());
                    } catch (NumberFormatException e) {
                        System.out.println("You did not select a valid inventory number.");
                        System.out.println("Usage:\n\tinventory <inventory number: int>");
                    }
                }
            }
            else if (tokens[0].equalsIgnoreCase("sort")) {
                if (tokens.length != 2) System.out.println("Usage:\n\tsort <inventory number: int>");
                else {
                    try {
                        int inventoryNumber = Integer.parseInt(tokens[1]);
                    } catch (NumberFormatException e) {
                        System.out.println("You did not select a valid inventory number.");
                        System.out.println("Usage:\n\tsort <inventory number: int>");
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        new Main();
    }
}
