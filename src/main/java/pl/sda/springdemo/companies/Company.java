package pl.sda.springdemo.companies;

public class Company {

    private long NIP;
    private String nazwa;
    private int regon;
    private Address address;

    public Company(long NIP, String nazwa, int regon, Address address) {
        this.NIP = NIP;
        this.nazwa = nazwa;
        this.regon = regon;
        this.address = address;
    }

    public long getNIP() {
        return NIP;
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getRegon() {
        return regon;
    }

    public Address getAddress() {
        return address;
    }
}
