package Try;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Library {
    public static void main(String[] args) {
        String filename = "rus.txt"; // Путь к вашему файлу с словами
        int L = 0; // Значение переменной L

        String randomWord = getRandomWordFromFile(filename, L);

        // Теперь randomWord содержит случайное слово из файла

        if (randomWord != null) {
            System.out.println("Случайное слово: " + randomWord);
        } else {
            System.out.println("Не найдено подходящих слов.");
        }
    }

    // Метод для получения случайного слова из файла в зависимости от значения L
    public static String getRandomWordFromFile(String filename, int L) {
        List<String> words = readWordsFromFile(filename);

        if (words == null || words.isEmpty()) {
            return null;
        }

        // Определяем диапазоны длин слов в зависимости от L
        int minLength = 0;
        int maxLength = Integer.MAX_VALUE;

        switch (L) {
            case 1:
                minLength = 3;
                maxLength = 5;
                break;
            case 2:
                minLength = 6;
                maxLength = 8;
                break;
            case 3:
                minLength = 9;
                break;
            default:
                throw new IllegalArgumentException("Недопустимое значение L: " + L);
        }

        // Фильтруем слова по длине
        List<String> filteredWords = new ArrayList<>();
        for (String word : words) {
            if (word.length() >= minLength && word.length() <= maxLength) {
                filteredWords.add(word);
            }
        }

        if (filteredWords.isEmpty()) {
            return null;
        }

        // Выбираем случайное слово из отфильтрованного списка
        Random random = new Random();
        int randomIndex = random.nextInt(filteredWords.size());
        return filteredWords.get(randomIndex);
    }

    // Метод для чтения слов из файла и сохранения их в список
    public static List<String> readWordsFromFile(String filename) {
        List<String> words = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Убираем лишние пробелы и символы перевода строки
                String word = line.trim();

                // Добавляем слово в список, если оно не пустое
                if (!word.isEmpty()) {
                    words.add(word);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }

        return words;
    }
}
