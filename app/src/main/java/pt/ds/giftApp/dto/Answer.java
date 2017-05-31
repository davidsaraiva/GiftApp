package pt.ds.giftApp.dto;

/**
 * Created by DS on 22/05/2017.
 */

class Answer {

    private String answerText;
    private boolean correctAnswer;

    Answer (String answerText, boolean correctAnswer) {
        this.answerText = answerText;
        this.correctAnswer = correctAnswer;
    }

    public String getAnswerText() {
        return answerText;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

}
