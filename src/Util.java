import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Util {

    private static Ingrediente creaIngrediente(Scanner in) {
       try {
           double peso = 0;
           double calorie = 0;
           System.out.println("Inserire nome ingrediente: ");
           String nome = in.nextLine();
           do {
               System.out.println("Inserire peso(in hg): ");
               peso = in.nextDouble();
           } while (peso <= 0);

           do {
               System.out.println("Inserire calorie per hg: ");
               calorie = in.nextDouble();
           } while (calorie <= 0);

           return new Ingrediente(nome, peso, calorie);
       } catch (InputMismatchException e) {System.out.println("Errore nel inserimento dei dati");}
       return null;
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
        ArrayList<Ricetta[]> combinazioni = new ArrayList<>();
            System.out.println("Benvenuto, utente, all'applicazione che ti rendera la vita piu facile");
            System.out.println("Seleziona cosa vuoi fare dal menu");
            do {
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("1. Aggiungi ricetta alla lista");
                System.out.println("2. Ricetta da ingredienti presenti (devi aver inserito almeno una ricetta)");
                System.out.println("3. Inserisci un nuovo ingrediente");
                System.out.println("4. Visualizza ricette");
                System.out.println("5. Prepara menu");
                System.out.println("6. Salva i dati");
                System.out.println("7. Termina il programma");
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
                        if (menu.getSetIngredienti().size()==0)
                            System.out.println("Errore! Non ci sono ingredienti!");
                        else {
                            ArrayList<Ingrediente> ing = menu.getIngredient(in);
                            if (ing == null)
                                System.out.println("Oops, qualcosa e' andato storto");
                            else
                                menu.addRicetta(createRicettaFromExistingIngredients(ing, in));
                        }
                        break;
                    }
                    case 3: {
                        boolean internalLoop = false;
                        do {
                            System.out.println("Inserisci il nuovo ingrediente: ");
                            menu.addIngrediente(creaIngrediente(in));
                            in.nextLine();
                            internalLoop = yesOrNo(in,"Vuoi aggiungere altri ingredienti?");
                        } while (internalLoop);
                        break;
                    }
                    case 4: {
                        System.out.println(menu.toString());
                        break;
                    }
                    case 5: {
                        System.out.println("Inserire l'apporto calorico massimo: ");
                        double maxCal = in.nextDouble();
                        combinazioni = menu.combinazioneRicette(maxCal);
                        printArray(menu.combinazioneRicette(maxCal));
                        ScriviCombinazioni.write(combinazioni, maxCal);
                        break;
                    }
                    
                    case 6: {
                    	try {
                    		Menu tempMenu = menu;
                    		ScriviFile.write(tempMenu);
                    	} catch (Exception e) {System.out.println("Salvataggio non riuscito");}
                    	System.out.println("Salvataggio riuscito!");
                    	break;
                    }

                    case 7: {
                        loop = !yesOrNo(in, "Vuoi davvero terminare (tutti i dati non salvati saranno persi)?");
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
