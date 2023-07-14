package com.dmn.service;
import com.dmn.dto.response.QuestionResponse;
import com.dmn.entity.Answer;
import com.dmn.entity.Question;
import com.dmn.entity.Quiz;
import com.dmn.exception.ResourceNotFoundException;
import com.dmn.repository.QuestionDao;
import com.dmn.repository.QuizDao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizService {


   private final QuizDao quizDao;


   private final QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(String category, Integer numOfQuestion, String title) {

        List<Question>  questions=questionDao.findRandomQuestionByCategory(category,numOfQuestion);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionResponse>> getQuizQuestionById(Integer id) {

        Quiz quiz=quizDao.findById(id).orElseThrow(()->new ResourceNotFoundException("Quiz couldn't found by id : "+id));

        List<QuestionResponse> questionResponseList=quiz.getQuestions().stream().map(QuestionResponse::new).collect(Collectors.toList());

        return new ResponseEntity<>(questionResponseList,HttpStatus.OK);


    }

    public ResponseEntity<Map<String, Integer>> getResultToUser(Integer id, List<Answer> answers) {

        Quiz quiz=quizDao.findById(id).orElseThrow(()->new ResourceNotFoundException("Quiz couldn't found by id : "+id));
        Integer correctAnswer=0;
        Integer wrongAnswer=0;
        int i=0;

        for(Answer answer:answers){
            if(answer.getUserAnswer().equals(quiz.getQuestions().get(i).getRightAnswer())){
                correctAnswer++;
            }else {
                wrongAnswer++;
            }
            i++;
        }
        Integer point=calculatePoints(correctAnswer,wrongAnswer,quiz);

        Map<String,Integer> theExamResult=new HashMap<>();

        int emptyQ=(quiz.getQuestions().size()-(correctAnswer+wrongAnswer));

        theExamResult.put("Num Of Total Question: ",quiz.getQuestions().size());
        theExamResult.put("Your Correct Answer  : ",correctAnswer);
        theExamResult.put("Your  Wrong  Answer  : ",wrongAnswer);
        theExamResult.put("Your  Empty  Answer  : ",emptyQ);
        theExamResult.put("Your   Exam   Point  : ",point);

        return new ResponseEntity<>(theExamResult,HttpStatus.OK);

    }





    private Integer calculatePoints(Integer correctAnswer, Integer wrongAnswer, Quiz quiz){

        double questionPoint=100.0/quiz.getQuestions().size();
        return (int) (questionPoint*correctAnswer);

    }


//    public ResponseEntity<Page<QuestionResponse>> getQuizQByIdPageToPage(Integer id, int page, int size, String sortedBy, Sort.Direction direction) {
//
//        Pageable pageable= PageRequest.of(page,size,Sort.by(direction,sortedBy));
//
//      Quiz quiz=quizDao.fi
//
//
//    }
}
