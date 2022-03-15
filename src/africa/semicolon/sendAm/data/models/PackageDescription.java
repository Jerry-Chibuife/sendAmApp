package africa.semicolon.sendAm.data.models;

public class PackageDescription {
    private String name;
    private int weightInGrammes;

    @Override
    public String toString() {
        return "PackageDescription{" +
                "name='" + name + '\'' +
                ", weightInGrammes=" + weightInGrammes +
                '}';
    }

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
