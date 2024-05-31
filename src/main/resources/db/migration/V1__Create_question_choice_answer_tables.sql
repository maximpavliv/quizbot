-- Create question table
CREATE TABLE question (
    question_id INT PRIMARY KEY,
    question_text TEXT NOT NULL
);
-- Create choice table
CREATE TABLE choice (
    choice_id INT PRIMARY KEY,
    question_id INT NOT NULL,
    choice_text TEXT NOT NULL,
    FOREIGN KEY (question_id) REFERENCES question(question_id) ON DELETE CASCADE
);
-- Create answer table
CREATE TABLE answer (
    answer_id INT PRIMARY KEY,
    question_id INT NOT NULL UNIQUE,
    choice_id INT NOT NULL UNIQUE,
    FOREIGN KEY (question_id) REFERENCES question(question_id) ON DELETE CASCADE,
    FOREIGN KEY (choice_id) REFERENCES choice(choice_id) ON DELETE CASCADE
);

-- Insert 5 questions
insert into question (question_id, question_text) values (1, 'How many primitive data types are there in Java?');
insert into question (question_id, question_text) values (2, 'What is the size of float and double in Java?');
insert into question (question_id, question_text) values (3, 'In which case is automatic type conversion possible?');
insert into question (question_id, question_text) values (4, 'Select the valid statement.');
insert into question (question_id, question_text) values (5, 'When an array is passed to a method, what does the method receive?');

-- Insert possible choices to 5 questions
insert into choice (choice_id, question_id, choice_text) values (1, 1, '6');
insert into choice (choice_id, question_id, choice_text) values (2, 1, '7');
insert into choice (choice_id, question_id, choice_text) values (3, 1, '8');
insert into choice (choice_id, question_id, choice_text) values (4, 1, '9');
insert into choice (choice_id, question_id, choice_text) values (5, 2, '32 and 64');
insert into choice (choice_id, question_id, choice_text) values (6, 2, '32 and 32');
insert into choice (choice_id, question_id, choice_text) values (7, 2, '64 and 64');
insert into choice (choice_id, question_id, choice_text) values (8, 2, '64 and 32');
insert into choice (choice_id, question_id, choice_text) values (9, 3, 'int to byte');
insert into choice (choice_id, question_id, choice_text) values (10, 3, 'int to long');
insert into choice (choice_id, question_id, choice_text) values (11, 3, 'long to int');
insert into choice (choice_id, question_id, choice_text) values (12, 3, 'short to byte');
insert into choice (choice_id, question_id, choice_text) values (13, 4, 'char[] ch = new char(5);');
insert into choice (choice_id, question_id, choice_text) values (14, 4, 'char[] ch = new char[5];');
insert into choice (choice_id, question_id, choice_text) values (15, 4, 'char[] ch = new char();');
insert into choice (choice_id, question_id, choice_text) values (16, 4, 'char[] ch = new char[];');
insert into choice (choice_id, question_id, choice_text) values (17, 5, 'The reference of the array');
insert into choice (choice_id, question_id, choice_text) values (18, 5, 'A copy of the array');
insert into choice (choice_id, question_id, choice_text) values (19, 5, 'Length of the array');
insert into choice (choice_id, question_id, choice_text) values (20, 5, 'Copy of first element');

-- Insert right answers to 5 questions
insert into answer (answer_id, question_id, choice_id) values (1, 1, 3);
insert into answer (answer_id, question_id, choice_id) values (2, 2, 5);
insert into answer (answer_id, question_id, choice_id) values (3, 3, 10);
insert into answer (answer_id, question_id, choice_id) values (4, 4, 14);
insert into answer (answer_id, question_id, choice_id) values (5, 5, 17);

