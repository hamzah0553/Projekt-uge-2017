package Models;

/**
 * Created by Kenedid on 25-09-2017.
 */
public class Customer {

    private String name;
    private int number;


    public Customer(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}