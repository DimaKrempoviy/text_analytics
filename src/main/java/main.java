import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class Main {

    private static final String WORD_REGEX = "[а-яА-Яa-zA-Z]{2,}";
    private static final String ANY_SYMBOL_REGEX = ".{1}";

    public static void main(String[] args) throws IOException {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к файлу: ");

        final Path fileLocation = Paths.get(scanner.nextLine());
        final String content = getContent(fileLocation);

        if (content == null || content.isEmpty()) {
            System.out.println("Файл пуст");
            return;
        }

        System.out.println(String.format("Файл успешно считан, размер %d байт", Files.size(fileLocation)));

        System.out.println("Пожалуйста, выберите действие:\n\t1.\tтоп N найбільш використаних слів\n\t2.\tтоп N найбільш використаних символів\n\t3.\tаналітика по словам\n\t4.\tаналітика по символам\n\t5.\tзберегти аналітику");
        System.out.print("Ваш выбор: ");
        int userChoose = scanner.nextInt();

        System.out.print("Пожалуйста, введите число N: ");
        int limit = scanner.nextInt();

        switch (userChoose) {
            case 1: {
                outputResult(
                        topMostPopular(content, WORD_REGEX, limit)
                );
                break;
            }
            case 2: {
                outputResult(
                        topMostPopular(content, ANY_SYMBOL_REGEX, limit)
                );
                break;
            }
            default: {
                System.out.println("Такого действия не существует!");
            }
        }

        scanner.close();
    }

    private static String getContent(Path fileLocation) {
        try {
            return
                    new String(Files.readAllBytes(fileLocation));
        } catch (IOException e) {
            System.err.println(String.format("Error while read file because %s", e.getMessage()));
            return null;
        }
    }

    private static void outputResult(Map<String, Long> result) {
        int top = 1;
        for (Map.Entry<String, Long> entry : result.entrySet()) {
            System.out.println(String.format("%d место\t \"%s\" / %d повторений", top++, entry.getKey(), entry.getValue()));
        }
    }

    private static Map<String, Long> topMostPopular(String content, String regex, int limit) {
        return splitContentBy(content, regex).stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue(Long::compareTo)))
                .limit(limit)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private static List<String> splitContentBy(String content, String regex) {
        final List<String> result = new ArrayList<>();
        final Matcher matcher = Pattern.compile(regex)
                .matcher(content);
        while (matcher.find()) {
            result.add(matcher.group(0).toLowerCase());
        }

        return result;
    }






}