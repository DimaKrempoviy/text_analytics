import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


class Main {

    public static void main(String[] args) throws IOException {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file path: ");

        final Path fileLocation = Paths.get(scanner.nextLine());
        final String content = getContent(fileLocation);

        if (content == null || content.isEmpty()) {
            System.out.println("File is empty");
            return;
        }

        System.out.printf("File read successfully, size %d bytes", Files.size(fileLocation));

        System.out.println("Please select an action: ");
        System.out.println("1.   top n most used words");
        System.out.println("2.   top n most used characters");
        System.out.println("3.   analytics by words");
        System.out.println("4.   symbol analytics");
        System.out.println("5.   save analytics");
        System.out.print("Your choice: ");
        int userChoose = scanner.nextInt();


        switch (userChoose) {
            case 1: {
                System.out.print("Please enter number N: ");
                int limit = scanner.nextInt();
                outputResult(
                        topMostPopular(content.split(" "), limit)
                );
                break;
            }
            case 2: {
                System.out.print("Please enter number N : ");
                int limit = scanner.nextInt();
                outputResult(
                        topMostPopular(content.split(""), limit)
                );
                break;
            }
            case 3: {
                System.out.print("analytics by words");
                int limit = scanner.nextInt();
                outputResult(
                        topMostPopular(content.split(""), limit)
                );
                break;
            }

            case 4: {
                System.out.print("symbol analytics");
                int limit = scanner.nextInt();
                outputResult(
                        topMostPopular(content.split(""), limit)
                );
                break;
            }
            default: {
                System.out.println("There is no such action!");
            }
        }

        scanner.close();
    }

    private static String getContent(Path fileLocation) {
        try {
            return
                    new String(Files.readAllBytes(fileLocation));
        } catch (IOException e) {
            System.err.printf("Error while read file because %s", e.getMessage());
            return null;
        }
    }

    private static void outputResult(Map<String, ValueUse> result) {
        int top = 1;
        for (Map.Entry<String, ValueUse> entry : result.entrySet()) {
            System.out.printf(" \n %d place \t \"%s\" / %d repetitions", top++, entry.getKey(), entry.getValue());
        }
    }


    private static Map<String, ValueUse> topMostPopular(String[] content, int limit) {
        return Stream.of(content)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue(Long::compareTo)))
                .limit(limit)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }


}

