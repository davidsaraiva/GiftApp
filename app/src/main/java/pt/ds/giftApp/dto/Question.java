package pt.ds.giftApp.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DS on 22/05/2017.
 */

public class Question {

    private String questionTxt;
    private List<Answer> answers;

    private Question(QuestionBuilder builder) {
        this.questionTxt = builder.questionTxt;
        this.answers = builder.answers;
    }

    public String getQuestionTxt() {
        return questionTxt;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public ArrayList<String> getAnswersTexts() {
        ArrayList<String> answersTexts = new ArrayList<>();
        for (Answer answer : getAnswers()) {
            answersTexts.add(answer.getAnswerText());
        }
        return answersTexts;
    }

    public boolean isCorrectAnswer(String answerText) {
        if (answerText == null || answerText.trim().isEmpty()) {
            return false;
        }
        for (Answer answer : getAnswers()) {
            if (answerText.equals(answer.getAnswerText())) {
                return answer.isCorrectAnswer();
            }
        }
        return false;
    }

    public static class QuestionBuilder {
        private final String questionTxt;
        private List<Answer> answers;

        public QuestionBuilder(String questionTxt) {
            this.questionTxt = questionTxt;
        }

        public QuestionBuilder addAnswer(String answerText, boolean correctAnswer) {
            if (answers==null || answers.isEmpty()) {
                answers = new ArrayList<>();
            }
            answers.add(new Answer(answerText,correctAnswer));
            return this;
        }

        public Question build() {
            return new Question(this);
        }

    }


}
