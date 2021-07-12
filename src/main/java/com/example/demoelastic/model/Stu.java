package com.example.demoelastic.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;


@Document(indexName = "stu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stu {


    @Id
    private Long stuId;

    @Field
    private String name;

    @Field
    private Integer age;

    @Field
    private Float money;

    @Field
    private boolean isMarried;



}