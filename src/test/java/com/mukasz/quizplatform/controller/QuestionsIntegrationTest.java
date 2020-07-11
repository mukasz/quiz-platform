package com.mukasz.quizplatform.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mukasz.quizplatform.dao.QuestionDAO;
import com.mukasz.quizplatform.dto.AnswerDTO;
import com.mukasz.quizplatform.dto.QuestionDTO;
import com.mukasz.quizplatform.model.QuestionType;
import com.mukasz.quizplatform.model.entity.Answer;
import com.mukasz.quizplatform.model.entity.Question;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuestionsIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private QuestionDAO questionDAO;

    @Test
    @DisplayName("GET /questions -> Questions[]: length 2")
    @SneakyThrows
    void whenGetAllQuestions_shouldReturnProperQuestionsList() {
        String responseBody = mockMvc.perform(get("/questions")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        QuestionDTO[] questionDTOs = objectMapper.readValue(responseBody, QuestionDTO[].class);
        assertThat(questionDTOs.length).isEqualTo(2);
    }

    @Test
    @DisplayName("GET /questions/1100 -> Question(Test Q1 ,a - correct, b - incorrect, c - incorect)")
    @SneakyThrows
    void whenGetQuestion1_shouldReturnProperQuestion() {
        String responseBody = mockMvc.perform(get("/questions/1100")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        QuestionDTO questionDTO = objectMapper.readValue(responseBody, QuestionDTO.class);
        assertThat(questionDTO.getCorrectAnswers().size()).isEqualTo(1);
        assertThat(questionDTO.getCorrectAnswers()).contains("a");
        assertThat(questionDTO.getAnswers().size()).isEqualTo(3);
        assertThat(questionDTO.getTitle()).isEqualTo("Test Q1");
        assertThat(questionDTO.getPoints()).isEqualTo(1);
        assertThat(questionDTO.getAnswers().stream().anyMatch(a -> "Incorrect Q1".equals(a.getAnswerContent())));
    }

    @Test
    @DisplayName("GET /questions/3(wrong id) -> HTTP NOT_FOUND(404)")
    @SneakyThrows
    void whenGetQuestionWithWrongId_shouldReturn404() {
        mockMvc.perform(get("/questions/3")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    @DisplayName("GET /questions/str(wrong id) -> HTTP BAD_REQUEST(400)")
    @SneakyThrows
    void whenGetQuestionWithStringId_shouldReturn400() {
        mockMvc.perform(get("/questions/str")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    @DisplayName("POST /questions with proper response bode -> HTTP 200, added new Question")
    @SneakyThrows
    void whenPostQuestionWithProperQuestion_shouldAddQuestion() {
        String responseBody = mockMvc.perform(post("/questions")
                .content(objectMapper.writeValueAsString(getTestQuestion_correct()))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        QuestionDTO questionDTO = objectMapper.readValue(responseBody, QuestionDTO.class);
        Long id = questionDTO.getId();
        Question questionInDb = questionDAO.findById(id).orElseThrow();

        assertThat(questionInDb.getPoints()).isEqualTo(1);
        assertThat(questionInDb.getType()).isEqualTo(QuestionType.SINGLE_CHOICE);
        assertThat(questionInDb.getTitle()).isEqualTo("Newly added Question");

        List<Answer> answersInDb = questionInDb.getAnswers();

        assertThat(answersInDb.size()).isEqualTo(2);
        assertThat(answersInDb.stream().anyMatch(a -> "a".equals(a.getInternalId()) && a.getIsCorrect())).isEqualTo(true);
        assertThat(answersInDb.stream().anyMatch(a -> "b".equals(a.getInternalId()) && !a.getIsCorrect())).isEqualTo(true);

        //Cleanup
        questionDAO.deleteById(id);
    }

    @Test
    @DisplayName("POST /questions with inproper response body -> HTTP 400 BAD REQUEST")
    @SneakyThrows
    void whenPostQuestionWithBadQuestion_shouldReturn400() {
        String responseBody = mockMvc.perform(post("/questions")
                .content(getTestQuestionJSON_badlyFormatted())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    private QuestionDTO getTestQuestion_correct() {
        AnswerDTO answer1 = AnswerDTO.builder()
                .id("a")
                .answerContent("correct answer")
                .build();
        AnswerDTO answer2 = AnswerDTO.builder()
                .id("b")
                .answerContent("incorrect answer")
                .build();
        return QuestionDTO.builder()
                .title("Newly added Question")
                .points(1)
                .correctAnswers(List.of("a"))
                .answers(List.of(answer1, answer2))
                .build();
    }

    private String getTestQuestionJSON_badlyFormatted() {
        return "{ 'title': 'New Question_BAD'," +
                " 'answers': [ {'id': 'a', 'answerContent': 'answer1'}, {'id': 'b', 'answerContent': 'answer2'} ]," +
                " 'answerContent': [ 'a' ]," +
                " 'points': 1" +
                " badField: 'asdacs'" +
                "}";
    }
}