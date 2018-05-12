import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Warren Smith.
 *
 */
public class Main {

  /**
   * Main Method.
   * 
   * @param args
   *          Not used.
   */
  public static void main(String[] args) {

    doTwoDimensionalArrayStuff();

    goToTheStore();

    stringProcessing();
  }

  /**
   * Everything encompassing 2D Arrays.
   */
  private static void doTwoDimensionalArrayStuff() {
    Scanner scan;
    final int sizeX = 3;
    final int sizeY = 3;
    int[][] arr = new int[sizeY][sizeX];

    // Explain to User
    System.out.println(
        "We will now calculate the sum of an hourglass within a 3x3 2D Array: \n");
    System.out.println("Enter 9 numbers (one at a time) between -9 and 9: \n");

    boolean doneWithInput = false;
    do {
      scan = new Scanner(System.in);
      int index = sizeY * sizeX;
      doneWithInput = true;

      // Loop through 2D Array
      for (int i = 0; i < sizeX; i++) {
        for (int j = 0; j < sizeY; j++) {

          // Exception Handling
          try {
            arr[i][j] = scan.nextInt();
            if (arr[i][j] > 9 || arr[i][j] < -9) {
              throw new InputMismatchException();
            }
            System.out.println(arr[i][j] + " ... " + --index + " left to go!");
          } catch (InputMismatchException e) {
            System.out.println(
                "That's not an integer from -9 to 9. Try (all over) again.");
            // break out of loops
            i += sizeX;
            j += sizeY;
            doneWithInput = false;
          } catch (Exception e) {
            System.out.println(e);
            // break out of loops
            i += sizeX;
            j += sizeY;
            doneWithInput = false;
          }
        }
      }
    } while (!doneWithInput);

    System.out.println("Here's what you input: \n");
    for (int i = 0; i < sizeX; i++) {
      for (int j = 0; j < sizeY; j++) {
        System.out.print(" " + arr[i][j]);
      }
      System.out.println("");
    }

    System.out.println("\n");

    ArrayList<Integer> possibleValues = new ArrayList<Integer>();
    for (int i = 0; i < sizeX; i++) {
      for (int j = 0; j < sizeY; j++) {
        // If we have room to the right and below then find Hourglass value
        if (i + 2 < 3 && j + 2 < 3) {
          possibleValues.add(findValueOfHourglass(i, j, arr));
        }
      }
    }

    // Sum of Hourglass
    System.out.print("And here's the sum of your hourglass: "
        + Collections.max(possibleValues));

    // Sleep Thread so User can Read
    goToSleep(3000);

    // Search a Two-Dimensional Array and Identify the Coordinates Where a Value
    // was Found!
    System.out.println("\n\n"
        + "Now we will find the coordinates of a number within this 2D array! \n");
    System.out
        .print("Enter a number that you used for one of your 9 numbers: ");

    int num = 0;
    // Input Handling
    do {
      scan = new Scanner(System.in);
      doneWithInput = true;
      // Exception Handling
      try {
        num = scan.nextInt();
        if (num > 9 || num < -9) {
          scan.close();
          throw new InputMismatchException();
        }
      } catch (InputMismatchException e) {
        System.out.println("WTF NO");
        doneWithInput = false;
      } catch (Exception e) {
        System.out.println(e);
        doneWithInput = false;
      }
    } while (!doneWithInput);

    findCoordinatesOfHourglassNumber(arr, num, sizeX, sizeY);

    // close Scanner
    // scan.close(); -> closing scanner causes infinite loop later
  }

  /**
   * Method used to find the total value of the "I" (hourglass).
   * 
   * @param startingX
   *          this is the x coordinate passed.
   * @param startingY
   *          this is the y coordinate passed.
   * @param paramValues
   *          these are the numbers accumulated for the final total.
   * @return
   *          returns accumulation of values.
   */
  private static int findValueOfHourglass(int startingX, int startingY,
      int[][] paramValues) {
    int accumulator = 0;
    for (int i = startingX; i < startingX + 3; i++) {
      for (int j = startingY; j < startingY + 3; j++) {
        if ((i == startingX || i == startingX + 2) && j == startingY + 1) {
          continue;
        }
        accumulator += paramValues[j][i];
      }
    }
    return accumulator;
  }

  /**
   * Method to find Coordinates for the User-defined number in the hourglass.
   * 
   * @param arr
   *          array containing all numbers of hourglass.
   * @param numberToFind
   *          the number to find (user-defined).
   * @param sizeX
   *          size of array in x dimension.
   * @param sizeY
   *          size of array in y dimension.
   */
  private static void findCoordinatesOfHourglassNumber(int[][] arr,
      int numberToFind, int sizeX, int sizeY) {
    LinkedList<Vector2> queue = new LinkedList<Vector2>();
    for (int i = sizeX - 1; i >= 0; i--) {
      for (int j = sizeY - 1; j >= 0; j--) {
        if (numberToFind == arr[j][i]) {
          queue.push(new Vector2(i, j));
        }
      }
    }
    System.out.println();

    switch (queue.size()) {
      case 0:
        System.out.println("Apparently we couldn't find that number.");
        break;
      case 1:
        System.out.println("We found that number at the following location:");
        System.out.println("( X, Y )");
        System.out.println("________");
        queue.pop().print();
        break;
      default:
        System.out
            .println("We found a few instances at the following locations:");
        System.out.println("( X, Y )");
        System.out.println("________");
        while (!queue.isEmpty()) {
          queue.pop().print();
        }
        break;
    }
  }

  /**
   * Method to sleep thread (so user can read text).
   * 
   * @param timeInMilliseconds
   *          number of milliseconds to sleep thread.
   */
  private static void goToSleep(int timeInMilliseconds) {
    // Sleep the current thread for amount passed so user can read
    try {
      Thread.sleep(timeInMilliseconds);
    } catch (InterruptedException e) {
      System.out.println("---- CANNOT SLEEP THREAD -----");
    }
  }

  /**
   * This method shows the usage of Inheritance and Polymorphism.
   */
  @SuppressWarnings("resource") //for scanner not closing see below for why
  private static void goToTheStore() {

    // Inheritance is extending a class from a base class in order to "inherit"
    // Properties from the parent class.
    // Polymorphism is using an object to take on multiple objects. In this
    // case,
    // I'm using Clothes as a parent object and Pants/Shirt/Shoes as child
    // objects that run different variations
    // of the base class's methods. I'm also changing the base properties
    // through each child's constructor.

    Scanner scan = new Scanner(System.in);
    List<Clothes> myClothes = new ArrayList<Clothes>();

    boolean makingBigDecisions = true;
    do {
      System.out.println(
          "So.. I guess you're going to the store now. What will you wear?");
      System.out.println("Pants: 1");
      System.out.println("Shirt: 2");
      System.out.println("Shoes: 3");
      System.out.println("Leave for Store: 0");
      int userInput = 0;
      try {
        scan = new Scanner(System.in);
        userInput = scan.nextInt();
        scan.nextLine();
        // In case they didn't pick a correct choice
        if (userInput < 0 || userInput > 3) {
          // scan.close(); -> if I close the scanner - it will cause an infinite
          // loop
          throw new InputMismatchException();
        }
      } catch (InputMismatchException e) {
        System.out.println("That's not one of the choices! Try again.");
        continue;
      } catch (Exception e) {
        System.out.println("Catch-All Error. You messed something up!");
        System.out.println(e);
        continue;
      }
      switch (userInput) {
        case 0:
          makingBigDecisions = false;
          break;
        case 1:
          myClothes.add(new Pants());
          break;
        case 2:
          myClothes.add(new Shirt());
          break;
        case 3:
          myClothes.add(new Shoes());
          break;
        default:
          break;
      }
    } while (makingBigDecisions);

    // At Store
    System.out.println("\n");
    scan.close();

    goToStore(myClothes);

    goToSleep(2000);
  }

  /**
   * Method used from the main "goToTheStore()" Method Finds which clothes the
   * user obtained.
   * 
   * @param clothes
   *          the list of Clothes from the User.
   */
  private static void goToStore(List<Clothes> clothes) {
    boolean shoes = false;
    boolean shirt = false;
    boolean pants = false;

    for (Clothes c : clothes) {
      if (c.isBodyCovered()) {
        shirt = true;
      } else if (c.areLegsCovered()) {
        pants = true;
      } else if (c.areFeetCovered()) {
        shoes = true;
      }
    }

    if (shoes && shirt && pants) {
      System.out.println("Good job! You can dress yourself!");
    } else {
      System.out.println("You fail at life.");
    }
  }

  /**
   * Random String Stuff.
   */
  private static void stringProcessing() {
    System.out.println("Random String Stuff...\n");
    String s1 = "Mike";
    String s2 = "Ike";
    String s3 = String.join("-n-", s1, s2);
    System.out.println("\t" + s3 + "\n");

    System.out.println("What is the meaning of life?");
    String s = "12425";
    try {
      System.out.println(Integer.parseInt(s.substring(2, s.length() - 1)));
    } catch (StringIndexOutOfBoundsException e) {
      System.out.println("Out of Bounds Exception");
    } catch (Exception ex) {
      System.out.println("Bad String");
      System.out.println(ex);
    }

    System.out.println();

    String s4 = "Application Complete";
    for (int i = 0; i < s4.length(); i++) {
      if (s4.charAt(i) == ' ') {
        System.out.println();
        continue;
      } else {
        System.out.print(s4.charAt(i));
      }
    }
  }
}
