/*
* ESOF322 Homework 3
* Completed by: Jared Weiss (no partners) for Professor Faqeer al Rehman
* Implements changing algorithms on the fly. (the Strategy Pattern)
* Compilation instructions:
* I assume you have some jdk installed (I used openjdk-11) so you can build this.
* This source file can be compiled in the command line like so:
* $ javac Main.java
* $ java Main
* (to clean class files)
* $ rm *.class
* */


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
        Sort strategy;
        Inventory() {
            this.strategy = new BubbleSort();
        }
        Sort getSortStrategy() {
            return this.strategy;
        }
        void setSortStrategy(Sort strategy) {
            this.strategy = strategy;
        }
    }

    class InventoryModule extends Inventory {

    }

    Main() {
        final String help = "ESOF 322 Homework 3 Commands\n" +
                "inventory new - create a new inventory module\n" +
                "sort <inventory number: int> - sort inventory module <inventory number>\n" +
                "set <inventory number: int> <bubble, insertion, quick, merge> - set the\n\t\tsorting method on inventory module <inventory number>\n" +
                "help - display this prompt\n" +
                "exit - quit the program";
        final String prompt = "Homework 3> ";
        System.out.println(help);
        Scanner scanner = new Scanner(System.in);
        String userInput;
        String[] tokens;
        LinkedList<InventoryModule> inventoryList = new LinkedList<>();
        boolean exit = false;
        while(!exit) {
            System.out.println(prompt);
            userInput = scanner.nextLine();
            tokens = userInput.split(" ");
            // help
            if (tokens[0].equalsIgnoreCase("help")) { System.out.println(help); }
            // exit
            else if (tokens[0].equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                exit = true;
            }
            // set the strategy for an inventory module
            else if (tokens[0].equalsIgnoreCase("set")) {
                if (tokens.length != 3) System.out.println("Usage:\n\tset <inventory number: int> <bubble, insertion, quick, merge>");
                else {
                    try {
                        int inventoryNumber = Integer.parseInt(tokens[1]); // grab which inventory module we are working with
                        if (inventoryNumber > 0 && inventoryNumber <= inventoryList.size()) { // make sure we have a valid inventory number
                            InventoryModule inventory = inventoryList.get(inventoryNumber-1); // select the inventory module we are working with
                            // set sort method based on user input
                            if (tokens[2].equalsIgnoreCase("bubble")) { inventory.setSortStrategy(new BubbleSort());}
                            else if(tokens[2].equalsIgnoreCase("insertion")) { inventory.setSortStrategy(new InsertionSort()); }
                            else if(tokens[2].equalsIgnoreCase("quick")) { inventory.setSortStrategy(new QuickSort()); }
                            else if(tokens[2].equalsIgnoreCase("merge")) { inventory.setSortStrategy(new MergeSort()); }
                            // otherwise report that an invalid sort method has been selected
                            else { System.out.printf("Invalid sort method: %s\n", tokens[2]); }
                        }
                        else {
                            System.out.printf("Invalid inventory number: %d\n\tValid values between %d and %d.\n",
                                    inventoryNumber, 1, inventoryList.size());
                        }
                    } catch (NumberFormatException e) { // catch invalid number parse
                        System.out.println("You did not select a valid inventory number.");
                        System.out.println("Usage:\n\tset <inventory number: int> <bubble, insertion, quick, merge>");
                    }
                }
            }
            // creation of new inventory module
            else if (tokens[0].equalsIgnoreCase("inventory") && tokens[1].equalsIgnoreCase("new")) {
                inventoryList.push(new InventoryModule());
                System.out.printf("Created new inventory, access by doing: set %d <sort type> or sort %d\n", inventoryList.size(), inventoryList.size());
            }
            // call the sort method of a specific inventory module
            else if (tokens[0].equalsIgnoreCase("sort")) {
                if (tokens.length != 2) System.out.println("Usage:\n\tsort <inventory number: int>");
                else {
                    try {
                        int inventoryNumber = Integer.parseInt(tokens[1]); // select what inventory module we are working with
                        if (inventoryNumber > 0 && inventoryNumber <= inventoryList.size()) {
                            InventoryModule inventory = inventoryList.get(inventoryNumber-1);
                            Sort strategy = inventoryList.get(inventoryNumber-1).getSortStrategy();
                            strategy.sort(inventory);
                        }
                        else {
                            System.out.printf("You did not enter a valid inventory number.\n\tValid values between %d and %d.\n", 1, inventoryList.size());
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("You did not enter a valid inventory number.");
                        System.out.println("Usage:\n\tsort <inventory number: int>");
                    }
                }
            }
            else {
                System.out.printf("Invalid command: %s\n\tType \"help\" for help.", userInput);
            }
        }
    }


    public static void main(String[] args) {
        Main main = new Main();
    }
}
