/* Questions */
INSERT INTO questions (question_id, points, title, type) VALUES (1100, 1, 'Test Q1', 0);
INSERT INTO questions (question_id, points, title, type) VALUES (1101, 1, 'Test Q2', 1);

/* Answers */
INSERT INTO answers (id, answer_content, internal_id, is_correct, question_id) VALUES (1000, 'Correct Q1', 'a', TRUE , 1100);
INSERT INTO answers (id, answer_content, internal_id, is_correct, question_id) VALUES (1001, 'Incorrect Q1', 'b', FALSE , 1100);
INSERT INTO answers (id, answer_content, internal_id, is_correct, question_id) VALUES (1002, 'Incorrect2 Q1', 'c', FALSE , 1100);

INSERT INTO answers (id, answer_content, internal_id, is_correct, question_id) VALUES (1003, 'Correct Q2', 'a', TRUE , 1101);
INSERT INTO answers (id, answer_content, internal_id, is_correct, question_id) VALUES (1004, 'Correct Q2', 'b', TRUE , 1101);
INSERT INTO answers (id, answer_content, internal_id, is_correct, question_id) VALUES (1005, 'Incorrect Q2', 'c', FALSE , 1101);
