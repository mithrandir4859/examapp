package ua.domain.exam.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Adevi on 8/8/2014.
 */
@Entity
public class Exam implements Serializable{

    private Long id;
    private String name;
    private String description;
    private List<Question> questions;
    /* One million questions may exist for this exam, but student must answer
    only some number of them in order to pass. "size" variable represents
    this number. Questions will be randomly picked up from a list ("questions")
    for every exam attempt. */
    private Integer size;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    @Column(unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "exam")
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Transient
    public Integer getTimeLimit(){
        int time = 0;
        for (Question question: questions)
            time += question.getTimeLimit();
        return time;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
