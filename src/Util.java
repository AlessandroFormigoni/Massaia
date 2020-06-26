import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Util {

    private static Ingrediente creaIngrediente(Scanner in) {
        double peso = 0;
        double calorie = 0;
        System.out.println("Inserire nome ingrediente: ");
         String nome = in.nextLine();
         do {
             System.out.println("Inserire peso(in hg): ");
             peso = in.nextDouble();
         } while (peso<=0);

         do {
             System.out.println("Inserire calorie per hg: ");
             calorie = in.nextDouble();
         } while (calorie<=0);

         return new Ingrediente(nome, peso, calorie);
    }

    private static Ricetta creaRicetta(Scanner in) {
        String portata = null;
        Portata p = null;
        boolean loop = false;
        System.out.println("Inserire nome ricetta: ");
        String nome = in.nextLine();

        do {
            System.out.println("Inserire tipo di portata(P/S): ");
            portata = in.nextLine();
            p = getPortata(portata);
        } while (p.equals(null));

        Ricetta ricetta = new Ricetta(nome,p);
        do {
            System.out.println("Inserire gli ingredienti necessari: ");
            ricetta.addIngrediente(creaIngrediente(in));
            in.nextLine();
            loop = yesOrNo(in,"Servono altri ingredienti?");
        } while (loop);
        return ricetta;
    }

    private static Portata getPortata(String portata) {
        if (portata.toUpperCase().equals("P"))
            return Portata.PRIMO;
        else if (portata.toUpperCase().equals("S"))
            return Portata.SECONDO;
        else
            return null;
    }

    private static void printArray(ArrayList<Ricetta[]> comb) {
        for (Ricetta[] ricetta : comb) {
            System.out.println("Il menu formato da "+ricetta[0].getNomeRicetta()+" e "+ricetta[1].getNomeRicetta());
        }
    }

    private static boolean yesOrNo(Scanner in, String messaggio) {
        System.out.println(messaggio+" (S/N)");
        String ans = in.nextLine();
        if (ans.toUpperCase().equals("S"))
            return true;
        else if (ans.toUpperCase().equals("N"))
            return false;
        else
            return false;
    }

    private static Ricetta createRicettaFromExistingIngredients(ArrayList<Ingrediente> ing, Scanner in) {
        String portata = null;
        Portata p = null;
        boolean loop = false;
        System.out.println("Inserire nome ricetta: ");
        String nome = in.nextLine();

        do {
            System.out.println("Inserire tipo di portata(P/S): ");
            portata = in.nextLine();
            p = getPortata(portata);
        } while (p.equals(null));

        Ricetta ricetta = new Ricetta(nome,p);
        ricetta.setElenco(ing);
        return ricetta;
    }

    public static void menu(Menu menu, Scanner in)  {
        boolean loop = true;
        int s = 5;
            System.out.println("Benvenuto, utente, all'applicazione che ti rendera la vita piu facile");
            System.out.println("Seleziona cosa vuoi fare dal menu");
            do {
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("1. Aggiungi ricetta alla lista");
                System.out.println("2. Ricetta da ingredienti presenti (devi aver inserito almeno una ricetta)");
                System.out.println("3. Visualizza ricette");
                System.out.println("4. Prepara menu");
                System.out.println("5. Termina il programma");
                System.out.print(">");
                try {
                    s = in.nextInt();
                } catch (NoSuchElementException e) {
                    e.printStackTrace();
                }
                in.nextLine();

                switch (s) {
                    case 1: {
                        menu.addRicetta(creaRicetta(in));
                        break;
                    }

                    case 2: {
                        menu.addRicetta(createRicettaFromExistingIngredients(menu.getIngredient(in),in));
                    }
                    case 3: {
                        System.out.println(menu.toString());
                        break;
                    }
                    case 4: {
                        System.out.println("Inserire l'apporto calorico massimo: ");
                        double maxCal = in.nextDouble();
                        printArray(menu.combinazioneRicette(maxCal));
                        break;
                    }

                    case 5: {
                        loop = !yesOrNo(in, "Vuoi davvero terminare?");
                        break;
                    }

                    default: {
                        System.out.println("Comando non trovato");
                        break;
                    }
                }
            } while (loop);
    }
}
