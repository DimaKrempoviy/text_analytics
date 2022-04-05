import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the absolute path to the file: ");
        System.out.flush();
        String filename = scanner.nextLine();
        File file = new File(filename);

        System.out.println("Choose one of the following actions: ") ;
        System.out.println("1) top n most used words ");
        System.out.println("2) top n most used characters");
        System.out.println("3) analytics by words (how many which word occurs in percentage and their number)");
        System.out.println("4) character analytics (how many and how many characters occur in percentage)");
        System.out.println("5) save analytics");

    }

}
