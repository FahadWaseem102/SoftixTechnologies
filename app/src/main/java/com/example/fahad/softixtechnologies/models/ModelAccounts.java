package com.example.fahad.softixtechnologies.models;

public class ModelAccounts {
    String accName ;
    String opnBlnc ;

    public ModelAccounts(String accName, String opnBlnc) {
        this.accName = accName;
        this.opnBlnc = opnBlnc;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getOpnBlnc() {
        return opnBlnc;
    }

    public void setOpnBlnc(String opnBlnc) {
        this.opnBlnc = opnBlnc;
    }
}
