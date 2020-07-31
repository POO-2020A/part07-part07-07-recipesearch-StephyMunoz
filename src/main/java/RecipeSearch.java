
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class RecipeSearch {

    public static void main(String[] args) {
        ArrayList<String> recipes = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("File to read: ");
        String file = scanner.nextLine();
        String line;
        try (Scanner scan = new Scanner(Paths.get(file))) {
            while (scan.hasNextLine()) {
                line = scan.nextLine();
                recipes.add(line);
            }

            while (true) {
                System.out.println("");
                System.out.println("Commands:");
                System.out.println("list - list the recipes");
                System.out.println("stop - stop the program");
                System.out.println("find name - searched recipes by name");
                System.out.println("find cooking time - searches recipes by cooking time");
                System.out.println("find ingredient - searches recipes by ingredient");
                System.out.println("");
                System.out.print("Enter command: ");
                String command = scanner.nextLine();
                if (command.equals("stop")) {
                    break;
                } else if (command.equals("list")) {
                    System.out.println("Recipes:");
                    System.out.print(recipes.get(0) + ", cooking time: " + recipes.get(1));
                    System.out.println("");
                    for (int i = 0; i < recipes.size(); i++) {

                        if (recipes.get(i).equals("")) {
                            System.out.print(recipes.get(i + 1) + ", cooking time: " + recipes.get(i + 2));
                            System.out.println("");
                        }
                    }

                } else if (command.equals("find name")) {
                    System.out.print("Searched word: ");
                    String searchedWord = scanner.nextLine();
                    System.out.println();
                    System.out.println("Recipes:");
                    if (recipes.get(0).contains(searchedWord)) {
                        System.out.print(recipes.get(0) + ", cooking time: " + recipes.get(1));
                        System.out.println("");
                    }

                    for (int i = 0; i < recipes.size(); i++) {

                        if (recipes.get(i).equals("")) {
                            if (recipes.get(i + 1).contains(searchedWord)) {
                                System.out.print(recipes.get(i + 1) + ", cooking time: " + recipes.get(i + 2));
                                System.out.println("");
                            }
                        }
                    }

                } else if (command.equals("find cooking time")) {
                    System.out.print("Max cooking time: ");
                    int cookingTime = Integer.valueOf(scanner.nextLine());
                    System.out.println();
                    System.out.println("Recipes:");
                    int timeCook = Integer.valueOf(recipes.get(1));
                    if (timeCook <= cookingTime) {
                        System.out.print(recipes.get(0) + ", cooking time: " + recipes.get(1)+ "\n");
                    }
                    for (int i = 0; i < recipes.size(); i++) {
                        if (recipes.get(i).equals("")) {
                            if (Integer.valueOf(recipes.get(i + 2)) <= cookingTime) {
                                System.out.print(recipes.get(i + 1) + ", cooking time: " + recipes.get(i + 2) + "\n");
                            }
                        }
                    }
                } else if (command.equals("find ingredient")) {
                    System.out.print("Ingredient: ");
                    String ingredient = scanner.nextLine();
                    ArrayList<String> recipesFind = new ArrayList<>();
                    int counter = 0;
                    String output = "";
                    System.out.println();
                    System.out.println("Recipes:");
                    for (int i = 0; i < recipes.size(); i++) {
                        if (recipes.get(i).equals(ingredient)) {
                            counter++;
                            for (int j = i; j >= 0; j--) {
                                if (recipes.get(j).matches("[0-9]+")) {
                                    output += recipes.get(j - 1) + ", cooking time: " + recipes.get(j) + "\n";
                                    recipesFind.add(output);
                                }
                            }
                        }
                    }
                    if (counter == 1) {
                        System.out.println(recipesFind.get(0));
                    } else if (counter == 2) {
                        System.out.println(recipesFind.get(1));
                    } else if (counter == 3) {
                        System.out.println(recipesFind.get(2));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
