package com.example.nagato.otpcreator.database;

public class DataModel {

    public static final String Table_Name = "contacts";

    public static final String Column_ID = "id";
    public static final String Column_Data = "data";

    private int id;
    private String data;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + Table_Name + "("
                    + Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Column_Data + " DATA,"
                    + ")";

    public DataModel() {
    }

    public DataModel(int id, String data)
    {
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setData(String data) {
        this.data = data;
    }
}
