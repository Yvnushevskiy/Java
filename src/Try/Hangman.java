package Try;

import java.util.ArrayList;
import java.util.Scanner;

import static Try.Game.*;
import static Try.Settings.*;
import static Try.WordWork.*;
import static Try.Space.*;

public class Hangman {
    public static void main(String[] args){
        SetDificult();
        CreateField();
        FirstTurn();
        FindLetter();
        while (IsGameContinue() && QtyMistakes-mistakes!=0){
            Turn();
        }
        if(QtyMistakes-mistakes!=0){
            System.out.println("Вы отгадали слово:  "+ randomWord);
        }

    }
}
class WordWork {
    static String randomWord = Library.getRandomWordFromFile("rus.txt",Difficult);
    static int LenWord = randomWord.length();
    static String letter = null;
    static ArrayList<String> entered = new ArrayList<>();




    public static char CheckInput() {
        Scanner scanner = new Scanner(System.in);
        //System.out.print("Введите букву: "); временно не нужно, т.к. планирую перенести в game
        while (!scanner.hasNext("[а-яА-Я]")) {
            System.out.print("Это не буква! Введите букву: ");
            scanner.next();
        }
        String l = scanner.next();
        while (entered.contains(l)){
            System.out.print("Вы уже вводили это букву! Введите другую букву:");
            l= scanner.next();
        }
        letter = l;
        entered.add(letter);
        return letter.charAt(0);
    }




    public static char[] FindLetter() {
        int n = 0;
        for (int i = 0; i < LenWord; i++) {
            if (randomWord.charAt(i) == letter.charAt(0)) {
                field[i] = letter.charAt(0);
                n += 1;
            } else {
                //
            }
        }
        if (n == 0) {
            mistakes += 1;
            System.out.print("В слове нет этой буквы \n");
            Deadman();
        } else {
            System.out.println("Откройте нам букву "+ letter);
            System.out.println(field);
        }
        return null;
    }
}




class Space{
    static char[] field;


    public static char[] CreateField() {
        field = new char[LenWord];
        for (int i = 0; i < LenWord; i++) {
            field[i] = '_';
        }
        return field;
    }

}
class Game {
    static int mistakes = 0;

    public static void Deadman(){
        String[][] matrix = {
                {"|", "-", "-", ""},
                {"|", "", " |", ""},
                {"|", "", "", ""},
                {"|", "", "", ""},
                {"|", "", "", ""},
                {"|", "_", "_", "_"},
        };
        switch (mistakes) {
            case 1:
                matrix[2][2] = " o";
                System.out.println("Введите новую букву");
                break;
            case 2:
                matrix[2][2] = " o";
                matrix[3][2] = " O ";
                System.out.println("Введите новую букву");
                break;
            case 3:
                matrix[2][2] = " o";
                matrix[3][2] = "/O ";
                System.out.println("Введите новую букву");
                break;
            case 4:
                matrix[2][2] = " o";
                matrix[3][2] = "/O\\";
                System.out.println("Введите новую букву");
                break;
            case 5:
                matrix[2][2] = " o";
                matrix[3][2] = "/O\\";
                matrix[4][2] = "/";
                System.out.println("Введите новую букву");
                break;
            case 6:
                matrix[2][2] = " o";
                matrix[3][2] = "/O\\";
                matrix[4][2] = "/";
                matrix[4][3] = "\\";
                System.out.println("Вы проиграли загаданное слово было :"+ randomWord);
                break;
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static int SetDificult() {
        System.out.println("Введите цифру от 1 до 3 , для выбора сложности где:\n 1- слово состоящие из 3-5 букв\n 2 - слово состоящее из 6-8 букв\n 3 - слово состоящее из 9 и более букв");
        Scanner scanner1 = new Scanner(System.in);
        while (!scanner1.hasNext("[1-3]")) {
            System.out.print("Неправильный ввод! Введите цифру от 1 до 3: ");
            scanner1.next();
        }
        Difficult = Integer.parseInt(scanner1.next());
        return Difficult;
    }

    public static char FirstTurn() {
        System.out.print("Мы загадали слово из " + LenWord + " букв");
        System.out.println("\nВведите букву:");
        return CheckInput();
    }

    public static void Turn() {
        CheckInput();
        FindLetter();
    }

    public static boolean IsGameContinue() {
        boolean containsUnderscore = false;
        for (char c : field) {
            if (c == '_') {
                return true;
            }
        }return containsUnderscore;
    }
}
class Settings{
    static int QtyMistakes= 6;
    static int Difficult = 0;
}

