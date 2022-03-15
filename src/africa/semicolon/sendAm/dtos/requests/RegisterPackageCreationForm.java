package africa.semicolon.sendAm.dtos.requests;


public class RegisterPackageCreationForm {
    private String name;
    private int weightInGrammes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeightInGrammes() {
        return weightInGrammes;
    }

    public void setWeightInGrammes(int weightInGrammes) {
        this.weightInGrammes = weightInGrammes;
    }
}
