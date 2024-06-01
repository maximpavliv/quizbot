-- Insert questions
insert into question (question_id, question_text) values (32, 'Find the output of the following code.');
insert into question (question_id, question_text) values (33, 'Find the output of the following program.');
insert into question (question_id, question_text) values (34, 'Find the output of the following program.');
insert into question (question_id, question_text) values (35, 'Find the output of the following program.');
insert into question (question_id, question_text) values (36, 'Find the value of A[[1]] after execution of the following program.');
insert into question (question_id, question_text) values (37, 'Identify the correct restriction on static methods.');
insert into question (question_id, question_text) values (38, 'Which of the following can directly access and change the value of the variable res?');
insert into question (question_id, question_text) values (39, 'Identify the output of the following program.');
insert into question (question_id, question_text) values (40, 'Identify the output of the following program.');
insert into question (question_id, question_text) values (41, 'Identify the output of the following program.');
insert into question (question_id, question_text) values (42, 'What does the following operation do to given string?');
insert into question (question_id, question_text) values (43, 'How many objects will be created in the following code?');
insert into question (question_id, question_text) values (44, 'Find the output of the following code.');
insert into question (question_id, question_text) values (45, 'Find the output of the following code.');
insert into question (question_id, question_text) values (46, 'Find the output of the following code.');
insert into question (question_id, question_text) values (47, 'Identify the prototype of the default constructor.');
insert into question (question_id, question_text) values (48, 'Identify the correct way of declaring a constructor.');
insert into question (question_id, question_text) values (49, 'Find the output of the following code.');
insert into question (question_id, question_text) values (50, 'How many times will "QuizBot" be printed?');


-- Insert snippets to questions
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (1, 32, 'java', substring($$
int Integer = 24;
char String = 'I';
System.out.print(Integer);
System.out.print(String);
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (2, 33, 'java', substring($$
public class Solution {
    public static void main(String[] args) {
        short x = 10;
        x = x*5;
        System.out.print(x);
    }
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (3, 34, 'java', substring($$
public class Solution {
    public static void main(String[] args) {
        byte x = 127;
        x++;
        x++;
        System.out.print(x);
    }
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (4, 35, 'java', substring($$
public class Solution {
    public static void main(String[] args) {
        int[] x = {120, 200, 016};
        for (int i=0; i<x.length; i++) {
            System.out.print(x[i] + " ");
        }
    }
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (5, 36, 'java', substring($$
int[] A = {0, 2, 4, 1, 3};
for (int i=0; i<A.length; i++) {
    A[i] = A[(A[i] + 3) % A.length];
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (6, 37, '', substring($$
1. They must access only static data.
2. They can only call other static methods.
3. They cannot refer to this or super.
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (7, 38, 'java', substring($$
package com.mypackage;

public class Solution {
    private int res = 100;
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (8, 39, 'java', substring($$
String str = "abcde";
System.out.println(str.substring(1, 3));
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (9, 40, 'java', substring($$
String str = "Hellow";
System.out.println(str.indexOf('t'));
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (10, 41, 'java', substring($$
public class Test {
    public static void main(String args[]) {
        String str1 = "one";
        String str2 = "two";
        System.out.println(str1.concat(str2));
    }
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (11, 42, 'java', substring($$
String str1 = "QuizzzBot".replace('z','s');
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (12, 43, 'java', substring($$
String a = new String("QuizBot");
String b = new String("QuizBot");
Strinc c = "QuizBot";
String d = "QuizBot";
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (13, 44, 'java', substring($$
int ++a = 100;
System.out.println(++a);
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (14, 45, 'java', substring($$
if (1 + 1 + 1 + 1 + 1 == 5) {
    System.out.print("TRUE");
} else {
    System.out.print("FALSE");
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (15, 46, 'java', substring($$
public class Solution {
    public static void main(String... args) {
        int x = 5;
        x *= (3 + 7);
        System.out.println(x);
    }
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (16, 47, 'java', substring($$
public class Solution {}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (17, 48, 'java', substring($$
public class Solution {}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (18, 49, 'java', substring($$
public class Solution {
    public static void main(String args[]) {
        int i;
        for (i=1; i<6; i++) { 
            if (i > 3) continue;
        }
        System.out.println(i);
    }
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (19, 50, 'java', substring($$
int count = 0;
do {
    System.out.println("QuizBot");
    count++;
} while (count < 10);
$$ from 2));


-- Insert possible choices to questions
insert into choice (choice_id, question_id, choice_text) values (125, 32, 'Compile error');
insert into choice (choice_id, question_id, choice_text) values (126, 32, 'Throws exception');
insert into choice (choice_id, question_id, choice_text) values (127, 32, 'I');
insert into choice (choice_id, question_id, choice_text) values (128, 32, '24I');
insert into choice (choice_id, question_id, choice_text) values (129, 33, '50');
insert into choice (choice_id, question_id, choice_text) values (130, 33, '10');
insert into choice (choice_id, question_id, choice_text) values (131, 33, 'Compile error');
insert into choice (choice_id, question_id, choice_text) values (132, 33, 'Exception');
insert into choice (choice_id, question_id, choice_text) values (133, 34, '-127');
insert into choice (choice_id, question_id, choice_text) values (134, 34, '127');
insert into choice (choice_id, question_id, choice_text) values (135, 34, '129');
insert into choice (choice_id, question_id, choice_text) values (136, 34, '2');
insert into choice (choice_id, question_id, choice_text) values (137, 35, '120 200 016');
insert into choice (choice_id, question_id, choice_text) values (138, 35, '120 200 14');
insert into choice (choice_id, question_id, choice_text) values (139, 35, '120 200 16');
insert into choice (choice_id, question_id, choice_text) values (140, 35, 'None');
insert into choice (choice_id, question_id, choice_text) values (141, 36, '0');
insert into choice (choice_id, question_id, choice_text) values (142, 36, '1');
insert into choice (choice_id, question_id, choice_text) values (143, 36, '2');
insert into choice (choice_id, question_id, choice_text) values (144, 36, '3');
insert into choice (choice_id, question_id, choice_text) values (145, 37, '1 and 2');
insert into choice (choice_id, question_id, choice_text) values (146, 37, '2 and 3');
insert into choice (choice_id, question_id, choice_text) values (147, 37, 'Only 3');
insert into choice (choice_id, question_id, choice_text) values (148, 37, '1, 2 and 3');
insert into choice (choice_id, question_id, choice_text) values (149, 38, 'Any class');
insert into choice (choice_id, question_id, choice_text) values (150, 38, 'Only Solution class');
insert into choice (choice_id, question_id, choice_text) values (151, 38, 'Any class that extends Solution');
insert into choice (choice_id, question_id, choice_text) values (152, 38, 'None');
insert into choice (choice_id, question_id, choice_text) values (153, 39, 'abc');
insert into choice (choice_id, question_id, choice_text) values (154, 39, 'bc');
insert into choice (choice_id, question_id, choice_text) values (155, 39, 'bcd');
insert into choice (choice_id, question_id, choice_text) values (156, 39, 'cd');
insert into choice (choice_id, question_id, choice_text) values (157, 40, '0');
insert into choice (choice_id, question_id, choice_text) values (158, 40, '1');
insert into choice (choice_id, question_id, choice_text) values (159, 40, 'true');
insert into choice (choice_id, question_id, choice_text) values (160, 40, '-1');
insert into choice (choice_id, question_id, choice_text) values (161, 41, 'one');
insert into choice (choice_id, question_id, choice_text) values (162, 41, 'two');
insert into choice (choice_id, question_id, choice_text) values (163, 41, 'onetwo');
insert into choice (choice_id, question_id, choice_text) values (164, 41, 'twoone');
insert into choice (choice_id, question_id, choice_text) values (165, 42, 'Replaces single occurence of ''z'' to ''s''');
insert into choice (choice_id, question_id, choice_text) values (166, 42, 'Replaces all occurences of ''z'' to ''s''');
insert into choice (choice_id, question_id, choice_text) values (167, 42, 'Replaces single occurence of ''s'' to ''z''');
insert into choice (choice_id, question_id, choice_text) values (168, 42, 'None');
insert into choice (choice_id, question_id, choice_text) values (169, 43, '2');
insert into choice (choice_id, question_id, choice_text) values (170, 43, '3');
insert into choice (choice_id, question_id, choice_text) values (171, 43, '4');
insert into choice (choice_id, question_id, choice_text) values (172, 43, 'None');
insert into choice (choice_id, question_id, choice_text) values (173, 44, '101');
insert into choice (choice_id, question_id, choice_text) values (174, 44, 'Compile error as ++a is not valid identifier');
insert into choice (choice_id, question_id, choice_text) values (175, 44, '100');
insert into choice (choice_id, question_id, choice_text) values (176, 44, 'None');
insert into choice (choice_id, question_id, choice_text) values (177, 45, 'TRUE');
insert into choice (choice_id, question_id, choice_text) values (178, 45, 'FALSE');
insert into choice (choice_id, question_id, choice_text) values (179, 45, 'Compile error');
insert into choice (choice_id, question_id, choice_text) values (180, 45, 'None');
insert into choice (choice_id, question_id, choice_text) values (181, 46, '50');
insert into choice (choice_id, question_id, choice_text) values (182, 46, '22');
insert into choice (choice_id, question_id, choice_text) values (183, 46, '10');
insert into choice (choice_id, question_id, choice_text) values (184, 46, 'None');
insert into choice (choice_id, question_id, choice_text) values (185, 47, 'Solution(void)');
insert into choice (choice_id, question_id, choice_text) values (186, 47, 'Solution()');
insert into choice (choice_id, question_id, choice_text) values (187, 47, 'public Solution(void)');
insert into choice (choice_id, question_id, choice_text) values (188, 47, 'public Solution()');
insert into choice (choice_id, question_id, choice_text) values (189, 48, 'A. Solution(){}');
insert into choice (choice_id, question_id, choice_text) values (190, 48, 'B. public Solution(){}');
insert into choice (choice_id, question_id, choice_text) values (191, 48, 'C. Solution(void){}');
insert into choice (choice_id, question_id, choice_text) values (192, 48, 'D. Both (A) and (B)');
insert into choice (choice_id, question_id, choice_text) values (193, 49, '3');
insert into choice (choice_id, question_id, choice_text) values (194, 49, '4');
insert into choice (choice_id, question_id, choice_text) values (195, 49, '5');
insert into choice (choice_id, question_id, choice_text) values (196, 49, '6');
insert into choice (choice_id, question_id, choice_text) values (197, 50, '8');
insert into choice (choice_id, question_id, choice_text) values (198, 50, '9');
insert into choice (choice_id, question_id, choice_text) values (199, 50, '10');
insert into choice (choice_id, question_id, choice_text) values (200, 50, '11');


-- Insert right answers to questions
insert into answer (answer_id, question_id, choice_id) values (32, 32, 128);
insert into answer (answer_id, question_id, choice_id) values (33, 33, 131);
insert into answer (answer_id, question_id, choice_id) values (34, 34, 133);
insert into answer (answer_id, question_id, choice_id) values (35, 35, 138);
insert into answer (answer_id, question_id, choice_id) values (36, 36, 142);
insert into answer (answer_id, question_id, choice_id) values (37, 37, 148);
insert into answer (answer_id, question_id, choice_id) values (38, 38, 150);
insert into answer (answer_id, question_id, choice_id) values (39, 39, 154);
insert into answer (answer_id, question_id, choice_id) values (40, 40, 160);
insert into answer (answer_id, question_id, choice_id) values (41, 41, 163);
insert into answer (answer_id, question_id, choice_id) values (42, 42, 166);
insert into answer (answer_id, question_id, choice_id) values (43, 43, 170);
insert into answer (answer_id, question_id, choice_id) values (44, 44, 174);
insert into answer (answer_id, question_id, choice_id) values (45, 45, 177);
insert into answer (answer_id, question_id, choice_id) values (46, 46, 181);
insert into answer (answer_id, question_id, choice_id) values (47, 47, 188);
insert into answer (answer_id, question_id, choice_id) values (48, 48, 192);
insert into answer (answer_id, question_id, choice_id) values (49, 49, 196);
insert into answer (answer_id, question_id, choice_id) values (50, 50, 199);

