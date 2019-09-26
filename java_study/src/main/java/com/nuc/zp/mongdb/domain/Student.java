package com.nuc.zp.mongdb.domain;

import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="student")
public class Student {

    @Id
    @BsonId
    @BsonProperty("_id")
    private ObjectId id;

    private String name;

    private int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student(ObjectId id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
