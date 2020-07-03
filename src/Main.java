import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Menu menu = LeggiFile.getMenu();
        ScriviCombinazioni.initializeWriter();
        Util.menu(menu,in);
        in.close();
    }
}
