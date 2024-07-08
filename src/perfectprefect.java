import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class perfectprefect {
    public static void main(String[] args){
        Game.SetDificult();
        Space.CreateField();
        Game.FirstTurn();
        WordWork.FindLetter();
        while (Game.IsGameContinue()&&Settings.QtyMistakes-Game.mistakes!=0){
            Game.Turn();
        }
        if(Settings.QtyMistakes-Game.mistakes==0){
            System.out.println("\nU lose it was "+ WordWork.randomWord);
        }else{
            System.out.println("U won it was "+ WordWork.randomWord);
        }

    }
}
class WordWork {
    static String randomWord = Library.getRandomWordFromFile("rus.txt",Settings.Difficult);
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
        for (int i = 0; i < WordWork.LenWord; i++) {
            if (WordWork.randomWord.charAt(i) == letter.charAt(0)) {
                Space.field[i] = letter.charAt(0);
                n += 1;
            } else {
                //
            }
        }
        if (n == 0) {
            Game.mistakes += 1;
            System.out.print("В слове нет этой буквы, осталось жизней:");
            System.out.print(Settings.QtyMistakes - Game.mistakes+"\n");
        } else {
            System.out.println("Откройте нам букву "+ letter);
            System.out.println(Space.field);
            System.out.println("\n");
        }
        return null;
    }
}




class Space{
    static char[] field;


    public static char[] CreateField() {
        field = new char[WordWork.LenWord];
        for (int i = 0; i < WordWork.LenWord; i++) {
            field[i] = '_';
        }
        return field;
    }

}
class Game {
    static int mistakes = 0;

    public static int SetDificult() {
        System.out.println("Введите цифру от 1 до 3 , для выбора сложности где:\n 1- слово состоящие из 3-5 букв\n 2 - слово состоящее из 6-8 букв\n 3 - слово состоящее из 9 и более букв");
        Scanner scanner1 = new Scanner(System.in);
        while (!scanner1.hasNext("[1-3]")) {
            System.out.print("Неправильный ввод! Введите цифру от 1 до 3: ");
            scanner1.next();
        }
        Settings.Difficult = Integer.parseInt(scanner1.next());
        return Settings.Difficult;
    }

    public static char FirstTurn() {
        System.out.print("Мы загадали слово из " + WordWork.LenWord + " букв");
        System.out.println("\nВведите букву:");
        return WordWork.CheckInput();
    }

    public static void Turn() {
        WordWork.CheckInput();
        WordWork.FindLetter();
    }

    public static boolean IsGameContinue() {
        boolean containsUnderscore = false;
        for (char c : Space.field) {
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
