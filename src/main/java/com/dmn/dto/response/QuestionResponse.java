package com.dmn.dto.response;

import com.dmn.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponse {

    private Integer id;

    private String questionTitle;

    private String option1;
    private String option2;
    private String option3;
    private String option4;



    public QuestionResponse(Question q){
        this.id=q.getId();
        this.questionTitle=q.getQuestionTitle();
        this.option1=q.getOption1();
        this.option2=q.getOption2();
        this.option3=q.getOption3();
        this.option4=q.getOption4();
    }

}
