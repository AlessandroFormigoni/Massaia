public class Ingrediente {
    String nome;
    double peso; //ettogrammi
    double calorie; //kcal

    public Ingrediente(String nome, double peso, double calorie) {
        this.nome = nome;
        this.peso = peso;
        this.calorie = calorie;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getCalorie() {
        return calorie;
    }

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }

    public String toString() {
        return "Possiedi "+ peso + "hg dell'ingrediente " + nome + "\n";
    }
}
