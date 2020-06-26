import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Menu {

    private ArrayList<Ricetta> ricette = new ArrayList<>();
    private ArrayList<Ingrediente> setIngredienti = new ArrayList<>();

    public ArrayList<Ricetta> getPrimi() {
        ArrayList<Ricetta> primi = new ArrayList<>();
        for(Ricetta r: ricette) {
            if (r.getPiatto().equals(Portata.PRIMO))
                primi.add(r);
        }
        return primi;
    }

    public ArrayList<Ricetta> getSecondi() {
        ArrayList<Ricetta> secondi = new ArrayList<>();
        for(Ricetta r: ricette) {
            if (r.getPiatto().equals(Portata.SECONDO))
                secondi.add(r);
        }
        return secondi;
    }

    public ArrayList<Ricetta[]> combinazioneRicette(double maxCal) {
        ArrayList<Ricetta[]> combinazioni = new ArrayList<>();
        for(Ricetta primo: getPrimi()) {
            for (Ricetta secondo: getSecondi()) {
                if (primo.apportoCalorico()+secondo.apportoCalorico()<maxCal) {
                    combinazioni.add(new Ricetta[]{primo,secondo});
                }
            }
        }
        return combinazioni;
    }

    public void addRicetta(Ricetta r) {
        ricette.add(r);
        for (Ingrediente i : r.getElenco())
            setIngredienti.add(i);
    }

    public ArrayList<Ingrediente> getIngredient(Scanner in) {
        ArrayList<Ingrediente> ingredienti = new ArrayList<>();
        int count=0;
        System.out.println("-----------------------------------------------------------");
        for(Ingrediente i : setIngredienti) {
            System.out.println(count+". "+i.getNome());
            count++;
        }
        System.out.println("Scegli quali ingredienti inserire (separati da uno spazio) ");
        String elementi = in.nextLine();
        Scanner scan = new Scanner(elementi);
        try {
            do {
                int index = scan.nextInt();
                ingredienti.add(setIngredienti.get(index));
            } while (scan.hasNext());
        } catch (Exception e) {}
        return ingredienti;
    }

    public String toString() {
        return "Ecco la lista di tutte le ricette preparate: \n" + ricette;
    }
}
