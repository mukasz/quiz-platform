package com.mukasz.quizplatform.model.converter;


import com.mukasz.quizplatform.dto.AnswerDTO;
import com.mukasz.quizplatform.dto.QuestionDTO;
import com.mukasz.quizplatform.model.QuestionType;
import com.mukasz.quizplatform.model.entity.Answer;
import com.mukasz.quizplatform.model.entity.Question;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionConverter {
    public static Question convertDTOToQuestion(QuestionDTO questionDTO) {
        List<Answer> answers = questionDTO
                .getAnswers()
                .stream()
                .map(answerDTO -> mapToAnswerEntity(answerDTO, questionDTO.getCorrectAnswers()))
                .collect(Collectors.toList());
        Question question = Question.builder()
                .answers(answers)
                .points(questionDTO.getPoints())
                .title(questionDTO.getTitle())
                .type(getQuestionType(questionDTO))
                .build();
        question.getAnswers().forEach(answer -> answer.setQuestion(question));
        return question;
    }

    public static QuestionDTO convertToDTO(Question question) {
        List<AnswerDTO> answers = new ArrayList<>();
        List<String> correct = new ArrayList<>();


        question.getAnswers().forEach(answer -> {
            answers.add(mapToAnswerDTO(answer));
            if (answer.getIsCorrect()) {
                correct.add(answer.getInternalId());
            }
        });
        return QuestionDTO.builder()
                .answers(answers)
                .correctAnswers(correct)
                .points(question.getPoints())
                .title(question.getTitle())
                .id(question.getId())
                .build();
    }

    private static Answer mapToAnswerEntity(AnswerDTO answerDTO, Collection<String> correctAnswers) {
            return Answer.builder()
                    .answerContent(answerDTO.getAnswerContent())
                    .internalId(answerDTO.getId())
                    .isCorrect(correctAnswers.contains(answerDTO.getId()))
                    .build();
    }

    private static AnswerDTO mapToAnswerDTO(Answer answer) {
        return AnswerDTO.builder()
                .id(answer.getInternalId())
                .answerContent(answer.getAnswerContent())
                .build();
    }

    private static QuestionType getQuestionType(QuestionDTO questionDTO) {
        int correctAnswersCount = questionDTO.getCorrectAnswers().size();
        int answersCount = questionDTO.getCorrectAnswers().size();

        if (correctAnswersCount == 1) {
            return QuestionType.SINGLE_CHOICE;
        } else if (correctAnswersCount > 1 && correctAnswersCount <= answersCount) {
            return QuestionType.MULTI_CHOICE;
        } else {
            // TODO: throw custom exception
            throw new RuntimeException("Wrong nuber of correct answers, should have at leas 1 correct and no more then total answers count");
        }
    }
}
