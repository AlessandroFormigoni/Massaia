import java.util.*;

public class Ricetta {
    private final static String ELEMENT_NOT_FOUND = "elemento non trovato!";
    private ArrayList<Ingrediente> elenco = new ArrayList<>();

    private String nomeRicetta;
    private Portata piatto;

    public Ricetta(String nomeRicetta, Portata piatto) {
        this.nomeRicetta = nomeRicetta;
        this.piatto = piatto;
    }

    public void addIngrediente(Ingrediente i) {
        elenco.add(i);
    }

    public void removeIngrediente(Ingrediente i) {
        if (elenco.contains(i))
            elenco.remove(i);
        else
            System.out.println(ELEMENT_NOT_FOUND);
    }

    public void setElenco(ArrayList<Ingrediente> elenco) {
        this.elenco = elenco;
    }

    public void setNomeRicetta(String nomeRicetta) {
        this.nomeRicetta = nomeRicetta;
    }

    public void setPiatto(Portata piatto) {
        this.piatto = piatto;
    }

    public double apportoCalorico() {
        double ac = 0;
        for(Ingrediente ingrediente : elenco) {
            ac += ingrediente.getPeso() * ingrediente.getCalorie();
        }
        return ac;
    }

    public ArrayList<Ingrediente> getElenco() {
        return elenco;
    }

    public String getNomeRicetta() {
        return nomeRicetta;
    }

    public Portata getPiatto() {
        return piatto;
    }

    public String toString() {
        return "Ecco l'elenco di ingredienti per il tuo " + piatto + " piatto " +nomeRicetta+" : \n" + elenco;
    }
}
