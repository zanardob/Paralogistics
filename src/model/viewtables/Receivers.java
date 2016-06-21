package model.viewtables;

public class Receivers {
    private String cpf;
    private String name;
    private String rg;

    public Receivers(String cpf, String name, String rg) {
        this.cpf = cpf;
        this.name = name;
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
}
