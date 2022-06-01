package com.example.votingapp2;

public class vote {


    private String id ;
    private String name ;
    private String v;

    public vote( String id,String name, String v) {

        this.id = id;
        this.name = name;
        this.v = v;
    }



    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getV() {
        return v;
    }
}
