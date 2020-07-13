package com.mukasz.quizplatform.service;

import com.mukasz.quizplatform.dao.QuestionDAO;
import com.mukasz.quizplatform.dto.QuestionDTO;
import com.mukasz.quizplatform.exception.QPNotFoundException;
import com.mukasz.quizplatform.model.converter.QuestionConverter;
import com.mukasz.quizplatform.model.entity.Answer;
import com.mukasz.quizplatform.model.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private final QuestionDAO questionDAO;

    @Autowired
    public QuestionService(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    public QuestionDTO createQuestion(QuestionDTO questionDTO) {
        Question question = QuestionConverter.convertDTOToQuestion(questionDTO);
        return QuestionConverter.convertToDTO(questionDAO.save(question));
    }

    public List<QuestionDTO> getAllQuestions() {
        return questionDAO.findAll()
                .stream()
                .map(QuestionConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public QuestionDTO getQuestionById(Long id) {
        Question question = questionDAO.findById(id)
                .orElseThrow(() ->
                        new QPNotFoundException(String.format("Could not find qustion with ID: %d", id))
                );
        return QuestionConverter.convertToDTO(question);
    }

    public List<Long> createManyQuestions(List<QuestionDTO> questions) {
        List<Question> toSave = questions.stream()
                .map(QuestionConverter::convertDTOToQuestion)
                .collect(Collectors.toList());
        return questionDAO.saveAll(toSave)
                .stream()
                .map(Question::getId)
                .collect(Collectors.toList());
    }
}
