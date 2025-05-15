//package com.iq.question;
//
//import com.iq.question.dao.QuestionDao;
//import com.iq.question.model.Question;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//public class DataLoader {
//
//    @Bean
//    ApplicationRunner initDatabase(QuestionDao questionRepository) {
//        return args -> {
//            List<Question> questions = new ArrayList<>();
//
//            for (int i = 1; i <= 50; i++) {
//                Question question = new Question();
//                question.setCategory("test");
//                question.setCorrectAnswer("Option A" + i);
//                question.setDifficultyLevel(getDifficultyLevel(i));
//                question.setOption1("Option A" + i);
//                question.setOption2("Option B" + i);
//                question.setOption3("Option C" + i);
//                question.setOption4("Option D" + i);
//                question.setQuestionTitle("Sample Question " + i + ": What is the answer to question " + i + "?");
//                questions.add(question);
//            }
//
//            questionRepository.saveAll(questions);
//        };
//    }
//
//    private String getDifficultyLevel(int i) {
//        if (i <= 20) return "Easy";
//        else if (i <= 40) return "Medium";
//        else return "Hard";
//    }
//}
