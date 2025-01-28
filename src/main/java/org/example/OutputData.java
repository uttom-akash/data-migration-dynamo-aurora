package org.example;

import lombok.Data;

@Data
public class OutputData {
    private String id;
    private String name;
    private String age;
    private String status;

    public OutputData(String id, String name, String age, String status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.status = status;
    }
}

