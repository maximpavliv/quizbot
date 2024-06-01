-- Insert more questions
insert into question (question_id, question_text) values (51, 'Who invented Java Programming?');
insert into question (question_id, question_text) values (52, 'Which statement is true about Java?');
insert into question (question_id, question_text) values (53, 'Which component is used to compile, debug and execute the Java programs?');
insert into question (question_id, question_text) values (54, 'Which of these cannot be used for a variable name in Java?');
insert into question (question_id, question_text) values (55, 'What is the extension of Java code files?');
insert into question (question_id, question_text) values (56, 'What will be the output of the following Java code?');
insert into question (question_id, question_text) values (57, 'Which environment variable is used to set the Java path?');
insert into question (question_id, question_text) values (58, 'What will be the output of the following Java program?');
insert into question (question_id, question_text) values (59, 'Which of the following is not an OOP concept in Java?');
insert into question (question_id, question_text) values (60, 'What will be the output of the following Java program?');
insert into question (question_id, question_text) values (61, 'Which of the following is a type of polymorphism in Java programming?');
insert into question (question_id, question_text) values (62, 'What will be the output of the following Java program?');
insert into question (question_id, question_text) values (63, 'What will be the output of the following Java code?');
insert into question (question_id, question_text) values (64, 'What is Truncation in Java?');
insert into question (question_id, question_text) values (65, 'What will be the output of the following Java program?');
insert into question (question_id, question_text) values (66, 'What will be the output of the following Java code snippet?');
insert into question (question_id, question_text) values (67, 'What is the extension of compiled Java classes?');
insert into question (question_id, question_text) values (68, 'Which exception is thrown when java is out of memory?');
insert into question (question_id, question_text) values (69, 'What will be the output of the following Java code?');
insert into question (question_id, question_text) values (70, 'Which of these is a selection statement in Java?');
insert into question (question_id, question_text) values (71, 'What will be the output of the following Java program?');
insert into question (question_id, question_text) values (72, 'What will be the output of the following Java code?');
insert into question (question_id, question_text) values (73, 'Which of these keywords is used to define interfaces in Java?');
insert into question (question_id, question_text) values (74, 'What will be the output of the following Java program?');
insert into question (question_id, question_text) values (75, 'What will be the output of the following Java code?');
insert into question (question_id, question_text) values (76, 'What will be the output of the following Java program?');
insert into question (question_id, question_text) values (77, 'Which of the following is a superclass of every class in Java?');
insert into question (question_id, question_text) values (78, 'What will be the output of the following Java code?');
insert into question (question_id, question_text) values (79, 'What will be the output of the following Java program? Note: Host URL is having length of content 127.');
insert into question (question_id, question_text) values (80, 'Which of the below is not a Java Profiler?');
insert into question (question_id, question_text) values (81, 'What will be the output of the following Java program?');
insert into question (question_id, question_text) values (82, 'What will be the output of the following Java code snippet?');
insert into question (question_id, question_text) values (83, 'Which of these packages contains the Stack Overflow error in Java?');
insert into question (question_id, question_text) values (84, 'What will be the output of the following Java program?');
insert into question (question_id, question_text) values (85, 'Which of these keywords is used for the block to be examined for exceptions?');
insert into question (question_id, question_text) values (86, 'What will be the output of the following Java code?');
insert into question (question_id, question_text) values (87, 'Which one of the following is not an access modifier?');
insert into question (question_id, question_text) values (88, 'What will be the output of the following Java program?');
insert into question (question_id, question_text) values (89, 'What is the numerical range of a char data type in Java?');
insert into question (question_id, question_text) values (90, 'Which class provides system independent server side implementation?');
insert into question (question_id, question_text) values (91, 'What will be the output of the following Java program?');


-- Insert snippets to more questions
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (20, 56, 'java', substring($$
public class Increment {
    public static void main(String args[]) {
        int g = 3;
        System.out.print(++g * 8);
    }
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (21, 58, 'java', substring($$
public class Output {
    public static void main(String args[]) {
        double a, b,c;
        a = 3.0/0;
        b = 0/4.0;
        c = 0/0.0;

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (22, 60, 'java', substring($$
public class VariableScope {
    public static void main(String args[]) {
        int x;
        x = 5;
        {
            int y = 6;
            System.out.print(x + " " + y);
        }
        System.out.println(x + " " + y);
    }
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (23, 62, 'java', substring($$
public class LeftShiftOperator {
    public static void main(String args[]) {
        byte x = 64;
        int i;
        byte y;
        i = x << 2;
        y = (byte) (x << 2);
        System.out.print(i + " " + y);
    }
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (24, 63, 'java', substring($$
class Box {
    int width;
    int height;
    int length;
}

public class Main {
    public static void main(String args[]) {
        Box obj = new Box();
        obj.width = 10;
        obj.height = 2;
        obj.length = 10;
        int y = obj.width * obj.height * obj.length;
        System.out.print(y);
    }
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (25, 65, 'java', substring($$
public class Output {
    public static void main(String args[]) {
        int arr[] = {1, 2, 3, 4, 5};
        for (int i = 0; i < arr.length - 2;++i)
            System.out.println(arr[i] + " ");
    }
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (26, 66, 'java', substring($$
public class Abc {
    public static void main(String args[]) {
        if(args.length > 0)
            System.out.println(args.length);
    }
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (27, 69, 'java', substring($$
public class StringDemo {
    public static void main(String args[]) {
        char chars[] = {'a', 'b', 'c'};
        String s = new String(chars);
        System.out.println(s);
    }
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (28, 71, 'java', substring($$
class Recursion {
    int func(int n) {
        int result;
        if (n == 1)
            return 1;
        result = func (n - 1);
        return result;
    }
}

public class Output {
    public static void main(String args[]) {
        Recursion obj = new Recursion();
        System.out.print(obj.func(5));
    }
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (29, 72, 'java', substring($$
public class Output {
    public static void main(String args[]) {
        String c = "Hello i love java";
        boolean var;
        var = c.startsWith("hello");
        System.out.println(var);
    }
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (30, 74, 'java', substring($$
public class Output {
    public static void main(String args[]) {
        StringBuffer s1 = new StringBuffer("Quiz");
        StringBuffer s2 = s1.reverse();
        System.out.println(s2);
    }
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (31, 75, 'java', substring($$
public class Output {
    public static void main(String args[]) {
        Integer i = new Integer(257);
        byte x = i.byteValue();
        System.out.print(x);
    }
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (32, 76, 'java', substring($$
public class Output {
    public static void main(String args[]) {
        double x = 2.0;
        double y = 3.0;
        double z = Math.pow( x, y );
        System.out.print(z);
    }
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (33, 78, 'java', substring($$
public class Output {
    public static void main(String args[]) {
        double x = 3.14;
        int y = (int) Math.ceil(x);
        System.out.print(y);
    }
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (34, 79, 'java', substring($$
import java.net.*;

public class Networking {
    public static void main(String[] args) throws Exception {
        URL obj = new URL("https://www.sanfoundry.com/javamcq");
        URLConnection obj1 = obj.openConnection();
        int len = obj1.getContentLength();
        System.out.print(len);
    }
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (35, 81, 'java', substring($$
import java.net.*;

public class Networking {
    public static void main(String[] args) throws MalformedURLException {
        URL obj = new URL("https://www.sanfoundry.com/javamcq");
        System.out.print(obj.toExternalForm());
    }
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (36, 82, 'java', substring($$
import java.util.*;

public class ArrayLists {
    public static void main(String args[]) {
        ArrayList obj = new ArrayList();
        obj.add("A");
        obj.add("B");
        obj.add("C");
        obj.add(1, "D");
        System.out.println(obj);
    }
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (37, 84, 'java', substring($$
import java.util.*;

public class CollectionIterators {
    public static void main(String args[]) {
        LinkedList list = new LinkedList();
        list.add(new Integer(2));
        list.add(new Integer(8));
        list.add(new Integer(5));
        list.add(new Integer(1));
        Iterator i = list.iterator();
        Collections.reverse(list);
        Collections.sort(list);
        while(i.hasNext())
            System.out.print(i.next() + " ");
    }
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (38, 86, 'java', substring($$
class NewThread extends Thread {
    Thread t1;
    Thread t2;
    NewThread() {
        t1 = new Thread(this,"Thread_1");
        t2 = new Thread(this,"Thread_2");
        t1.start();
        t2.start();
    }

    public void run() {
        t2.setPriority(Thread.MAX_PRIORITY);
        System.out.print(t1.equals(t2));
    }
}

public class MultithreadedPrograming {
    public static void main(String args[]) {
        new NewThread();
    }
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (39, 88, 'java', substring($$
final class A {
    int i;
}

class B extends A {
    int j;
    System.out.println(j + " " + i);
}

public class Inheritance {
    public static void main(String args[]) {
        B obj = new B();
        obj.display();
    }
}
$$ from 2));
insert into snippet (snippet_id, question_id, snippet_language, snippet_text) values (40, 91, 'java', substring($$
class Overload {
    int x;
    double y;

    void add(int a , int b) {
        x = a + b;
    }

    void add(double c , double d) {
        y = c + d;
    }

    Overload() {
        this.x = 0;
        this.y = 0;
    }
}

public class OverloadMethods
{
    public static void main(String args[]) {
        Overload obj = new Overload();
        int a = 2;
        double b = 3.2;
        obj.add(a, a);
        obj.add(b, b);
        System.out.println(obj.x + " " + obj.y);
    }
}
$$ from 2));


-- Insert possible choices to more questions
insert into choice (choice_id, question_id, choice_text) values (201, 51, 'Guido van Rossum');
insert into choice (choice_id, question_id, choice_text) values (202, 51, 'James Gosling');
insert into choice (choice_id, question_id, choice_text) values (203, 51, 'Dennis Ritchie');
insert into choice (choice_id, question_id, choice_text) values (204, 51, 'Bjarne Stroustrup');
insert into choice (choice_id, question_id, choice_text) values (205, 52, 'Java is a sequence-dependent programming language');
insert into choice (choice_id, question_id, choice_text) values (206, 52, 'Java is a code dependent programming language');
insert into choice (choice_id, question_id, choice_text) values (207, 52, 'Java is a platform-dependent programming language');
insert into choice (choice_id, question_id, choice_text) values (208, 52, 'Java is a platform-independent programming language');
insert into choice (choice_id, question_id, choice_text) values (209, 53, 'JRE');
insert into choice (choice_id, question_id, choice_text) values (210, 53, 'JIT');
insert into choice (choice_id, question_id, choice_text) values (211, 53, 'JDK');
insert into choice (choice_id, question_id, choice_text) values (212, 53, 'JVM');
insert into choice (choice_id, question_id, choice_text) values (213, 54, 'Identifiers and keywords');
insert into choice (choice_id, question_id, choice_text) values (214, 54, 'Identifiers');
insert into choice (choice_id, question_id, choice_text) values (215, 54, 'Keywords');
insert into choice (choice_id, question_id, choice_text) values (216, 54, 'None of the mentioned');
insert into choice (choice_id, question_id, choice_text) values (217, 55, '.js');
insert into choice (choice_id, question_id, choice_text) values (218, 55, '.txt');
insert into choice (choice_id, question_id, choice_text) values (219, 55, '.class');
insert into choice (choice_id, question_id, choice_text) values (220, 55, '.java');
insert into choice (choice_id, question_id, choice_text) values (221, 56, '32');
insert into choice (choice_id, question_id, choice_text) values (222, 56, '33');
insert into choice (choice_id, question_id, choice_text) values (223, 56, '24');
insert into choice (choice_id, question_id, choice_text) values (224, 56, '25');
insert into choice (choice_id, question_id, choice_text) values (225, 57, 'MAVEN_Path');
insert into choice (choice_id, question_id, choice_text) values (226, 57, 'JavaPATH');
insert into choice (choice_id, question_id, choice_text) values (227, 57, 'JAVA');
insert into choice (choice_id, question_id, choice_text) values (228, 57, 'JAVA_HOME');
insert into choice (choice_id, question_id, choice_text) values (229, 58, 'NaN');
insert into choice (choice_id, question_id, choice_text) values (230, 58, 'Infinity');
insert into choice (choice_id, question_id, choice_text) values (231, 58, '0.0');
insert into choice (choice_id, question_id, choice_text) values (232, 58, 'All of the mentioned');
insert into choice (choice_id, question_id, choice_text) values (233, 59, 'Polymorphism');
insert into choice (choice_id, question_id, choice_text) values (234, 59, 'Inheritance');
insert into choice (choice_id, question_id, choice_text) values (235, 59, 'Compilation');
insert into choice (choice_id, question_id, choice_text) values (236, 59, 'Encapsulation');
insert into choice (choice_id, question_id, choice_text) values (237, 60, 'Compilation error');
insert into choice (choice_id, question_id, choice_text) values (238, 60, 'Runtime error');
insert into choice (choice_id, question_id, choice_text) values (239, 60, '5 6 5 6');
insert into choice (choice_id, question_id, choice_text) values (240, 60, '5 6 5');
insert into choice (choice_id, question_id, choice_text) values (241, 61, 'Multiple polymorphism');
insert into choice (choice_id, question_id, choice_text) values (242, 61, 'Compile time polymorphism');
insert into choice (choice_id, question_id, choice_text) values (243, 61, 'Multilevel polymorphism');
insert into choice (choice_id, question_id, choice_text) values (244, 61, 'Static runtime polymorphism');
insert into choice (choice_id, question_id, choice_text) values (245, 62, '0 256');
insert into choice (choice_id, question_id, choice_text) values (246, 62, '0 64');
insert into choice (choice_id, question_id, choice_text) values (247, 62, '256 0');
insert into choice (choice_id, question_id, choice_text) values (248, 62, '64 0');
insert into choice (choice_id, question_id, choice_text) values (249, 63, '100');
insert into choice (choice_id, question_id, choice_text) values (250, 63, '400');
insert into choice (choice_id, question_id, choice_text) values (251, 63, '200');
insert into choice (choice_id, question_id, choice_text) values (252, 63, '12');
insert into choice (choice_id, question_id, choice_text) values (253, 64, 'Floating-point value assigned to a Floating type');
insert into choice (choice_id, question_id, choice_text) values (254, 64, 'Floating-point value assigned to an integer type');
insert into choice (choice_id, question_id, choice_text) values (255, 64, 'Integer value assigned to floating type');
insert into choice (choice_id, question_id, choice_text) values (256, 64, 'Integer value assigned to String type');
insert into choice (choice_id, question_id, choice_text) values (257, 65, '1 2 3 4 5');
insert into choice (choice_id, question_id, choice_text) values (258, 65, '1 2 3 4');
insert into choice (choice_id, question_id, choice_text) values (259, 65, '1 2');
insert into choice (choice_id, question_id, choice_text) values (260, 65, '1 2 3');
insert into choice (choice_id, question_id, choice_text) values (261, 66, 'The snippet compiles and runs but does not print anything');
insert into choice (choice_id, question_id, choice_text) values (262, 66, 'The snippet compiles, runs and prints 0');
insert into choice (choice_id, question_id, choice_text) values (263, 66, 'The snippet compiles, runs and prints 1');
insert into choice (choice_id, question_id, choice_text) values (264, 66, 'The snippet does not compile');
insert into choice (choice_id, question_id, choice_text) values (265, 67, '.txt');
insert into choice (choice_id, question_id, choice_text) values (266, 67, '.js');
insert into choice (choice_id, question_id, choice_text) values (267, 67, '.class');
insert into choice (choice_id, question_id, choice_text) values (268, 67, '.java');
insert into choice (choice_id, question_id, choice_text) values (269, 68, 'MemoryError');
insert into choice (choice_id, question_id, choice_text) values (270, 68, 'OutOfMemoryError');
insert into choice (choice_id, question_id, choice_text) values (271, 68, 'MemoryOutOfBoundsException');
insert into choice (choice_id, question_id, choice_text) values (272, 68, 'MemoryFullException');
insert into choice (choice_id, question_id, choice_text) values (273, 69, 'abc');
insert into choice (choice_id, question_id, choice_text) values (274, 69, 'a');
insert into choice (choice_id, question_id, choice_text) values (275, 69, 'b');
insert into choice (choice_id, question_id, choice_text) values (276, 69, 'c');
insert into choice (choice_id, question_id, choice_text) values (277, 70, 'break');
insert into choice (choice_id, question_id, choice_text) values (278, 70, 'continue');
insert into choice (choice_id, question_id, choice_text) values (279, 70, 'for()');
insert into choice (choice_id, question_id, choice_text) values (280, 70, 'if()');
insert into choice (choice_id, question_id, choice_text) values (281, 71, '1');
insert into choice (choice_id, question_id, choice_text) values (282, 71, '120');
insert into choice (choice_id, question_id, choice_text) values (283, 71, '0');
insert into choice (choice_id, question_id, choice_text) values (284, 71, 'None of the mentioned');
insert into choice (choice_id, question_id, choice_text) values (285, 72, '0');
insert into choice (choice_id, question_id, choice_text) values (286, 72, 'true');
insert into choice (choice_id, question_id, choice_text) values (287, 72, '1');
insert into choice (choice_id, question_id, choice_text) values (288, 72, 'false');
insert into choice (choice_id, question_id, choice_text) values (289, 73, 'intf');
insert into choice (choice_id, question_id, choice_text) values (290, 73, 'Intf');
insert into choice (choice_id, question_id, choice_text) values (291, 73, 'interface');
insert into choice (choice_id, question_id, choice_text) values (292, 73, 'Interface');
insert into choice (choice_id, question_id, choice_text) values (293, 74, 'QuizziuQ');
insert into choice (choice_id, question_id, choice_text) values (294, 74, 'ziuQQuiz');
insert into choice (choice_id, question_id, choice_text) values (295, 74, 'Quiz');
insert into choice (choice_id, question_id, choice_text) values (296, 74, 'ziuQ');
insert into choice (choice_id, question_id, choice_text) values (297, 75, '257');
insert into choice (choice_id, question_id, choice_text) values (298, 75, '256');
insert into choice (choice_id, question_id, choice_text) values (299, 75, '1');
insert into choice (choice_id, question_id, choice_text) values (300, 75, '0');
insert into choice (choice_id, question_id, choice_text) values (301, 76, '9.0');
insert into choice (choice_id, question_id, choice_text) values (302, 76, '8.0');
insert into choice (choice_id, question_id, choice_text) values (303, 76, '4.0');
insert into choice (choice_id, question_id, choice_text) values (304, 76, '2.0');
insert into choice (choice_id, question_id, choice_text) values (305, 77, 'ArrayList');
insert into choice (choice_id, question_id, choice_text) values (306, 77, 'Abstract class');
insert into choice (choice_id, question_id, choice_text) values (307, 77, 'Object class');
insert into choice (choice_id, question_id, choice_text) values (308, 77, 'String');
insert into choice (choice_id, question_id, choice_text) values (309, 78, '3');
insert into choice (choice_id, question_id, choice_text) values (310, 78, '0');
insert into choice (choice_id, question_id, choice_text) values (311, 78, '4');
insert into choice (choice_id, question_id, choice_text) values (312, 78, '3.0');
insert into choice (choice_id, question_id, choice_text) values (313, 79, '127');
insert into choice (choice_id, question_id, choice_text) values (314, 79, '126');
insert into choice (choice_id, question_id, choice_text) values (315, 79, 'Runtime Error');
insert into choice (choice_id, question_id, choice_text) values (316, 79, 'Compilation Error');
insert into choice (choice_id, question_id, choice_text) values (317, 80, 'JProfiler');
insert into choice (choice_id, question_id, choice_text) values (318, 80, 'Eclipse Profiler');
insert into choice (choice_id, question_id, choice_text) values (319, 80, 'JVM');
insert into choice (choice_id, question_id, choice_text) values (320, 80, 'JConsole');
insert into choice (choice_id, question_id, choice_text) values (321, 81, 'www.sanfoundry.com');
insert into choice (choice_id, question_id, choice_text) values (322, 81, 'https://www.sanfoundry.com/javamcq');
insert into choice (choice_id, question_id, choice_text) values (323, 81, 'sanfoundry');
insert into choice (choice_id, question_id, choice_text) values (324, 81, 'sanfoundry.com');
insert into choice (choice_id, question_id, choice_text) values (325, 82, '[A, D, C]');
insert into choice (choice_id, question_id, choice_text) values (326, 82, '[A, B, C]');
insert into choice (choice_id, question_id, choice_text) values (327, 82, '[A, B, C, D]');
insert into choice (choice_id, question_id, choice_text) values (328, 82, '[A, D, B, C]');
insert into choice (choice_id, question_id, choice_text) values (329, 83, 'java.io');
insert into choice (choice_id, question_id, choice_text) values (330, 83, 'java.system');
insert into choice (choice_id, question_id, choice_text) values (331, 83, 'java.lang');
insert into choice (choice_id, question_id, choice_text) values (332, 83, 'java.util');
insert into choice (choice_id, question_id, choice_text) values (333, 84, '1 2 5 8');
insert into choice (choice_id, question_id, choice_text) values (334, 84, '2 1 8 5');
insert into choice (choice_id, question_id, choice_text) values (335, 84, '1 5 8 2');
insert into choice (choice_id, question_id, choice_text) values (336, 84, '2 8 5 1');
insert into choice (choice_id, question_id, choice_text) values (337, 85, 'check');
insert into choice (choice_id, question_id, choice_text) values (338, 85, 'throw');
insert into choice (choice_id, question_id, choice_text) values (339, 85, 'catch');
insert into choice (choice_id, question_id, choice_text) values (340, 85, 'try');
insert into choice (choice_id, question_id, choice_text) values (341, 86, 'truetrue');
insert into choice (choice_id, question_id, choice_text) values (342, 86, 'falsefalse');
insert into choice (choice_id, question_id, choice_text) values (343, 86, 'true');
insert into choice (choice_id, question_id, choice_text) values (344, 86, 'false');
insert into choice (choice_id, question_id, choice_text) values (345, 87, 'protected');
insert into choice (choice_id, question_id, choice_text) values (346, 87, 'void');
insert into choice (choice_id, question_id, choice_text) values (347, 87, 'public');
insert into choice (choice_id, question_id, choice_text) values (348, 87, 'private');
insert into choice (choice_id, question_id, choice_text) values (349, 88, '2 2');
insert into choice (choice_id, question_id, choice_text) values (350, 88, '3 3');
insert into choice (choice_id, question_id, choice_text) values (351, 88, 'Runtime Error');
insert into choice (choice_id, question_id, choice_text) values (352, 88, 'Compilation Error');
insert into choice (choice_id, question_id, choice_text) values (353, 89, '0 to 256');
insert into choice (choice_id, question_id, choice_text) values (354, 89, '-128 to 127');
insert into choice (choice_id, question_id, choice_text) values (355, 89, '0 to 65535');
insert into choice (choice_id, question_id, choice_text) values (356, 89, '0 to 32767');
insert into choice (choice_id, question_id, choice_text) values (357, 90, 'Server');
insert into choice (choice_id, question_id, choice_text) values (358, 90, 'ServerReader');
insert into choice (choice_id, question_id, choice_text) values (359, 90, 'Socket');
insert into choice (choice_id, question_id, choice_text) values (360, 90, 'ServerSocket');
insert into choice (choice_id, question_id, choice_text) values (361, 91, '4 6.4');
insert into choice (choice_id, question_id, choice_text) values (362, 91, '6.4 6');
insert into choice (choice_id, question_id, choice_text) values (363, 91, '6.4 6.4');
insert into choice (choice_id, question_id, choice_text) values (364, 91, '6 6');


-- Insert right answers to more questions
insert into answer (answer_id, question_id, choice_id) values (51, 51, 202);
insert into answer (answer_id, question_id, choice_id) values (52, 52, 208);
insert into answer (answer_id, question_id, choice_id) values (53, 53, 211);
insert into answer (answer_id, question_id, choice_id) values (54, 54, 215);
insert into answer (answer_id, question_id, choice_id) values (55, 55, 220);
insert into answer (answer_id, question_id, choice_id) values (56, 56, 221);
insert into answer (answer_id, question_id, choice_id) values (57, 57, 228);
insert into answer (answer_id, question_id, choice_id) values (58, 58, 232);
insert into answer (answer_id, question_id, choice_id) values (59, 59, 235);
insert into answer (answer_id, question_id, choice_id) values (60, 60, 237);
insert into answer (answer_id, question_id, choice_id) values (61, 61, 242);
insert into answer (answer_id, question_id, choice_id) values (62, 62, 247);
insert into answer (answer_id, question_id, choice_id) values (63, 63, 251);
insert into answer (answer_id, question_id, choice_id) values (64, 64, 254);
insert into answer (answer_id, question_id, choice_id) values (65, 65, 260);
insert into answer (answer_id, question_id, choice_id) values (66, 66, 261);
insert into answer (answer_id, question_id, choice_id) values (67, 67, 267);
insert into answer (answer_id, question_id, choice_id) values (68, 68, 270);
insert into answer (answer_id, question_id, choice_id) values (69, 69, 273);
insert into answer (answer_id, question_id, choice_id) values (70, 70, 280);
insert into answer (answer_id, question_id, choice_id) values (71, 71, 281);
insert into answer (answer_id, question_id, choice_id) values (72, 72, 288);
insert into answer (answer_id, question_id, choice_id) values (73, 73, 291);
insert into answer (answer_id, question_id, choice_id) values (74, 74, 296);
insert into answer (answer_id, question_id, choice_id) values (75, 75, 299);
insert into answer (answer_id, question_id, choice_id) values (76, 76, 302);
insert into answer (answer_id, question_id, choice_id) values (77, 77, 307);
insert into answer (answer_id, question_id, choice_id) values (78, 78, 311);
insert into answer (answer_id, question_id, choice_id) values (79, 79, 313);
insert into answer (answer_id, question_id, choice_id) values (80, 80, 319);
insert into answer (answer_id, question_id, choice_id) values (81, 81, 322);
insert into answer (answer_id, question_id, choice_id) values (82, 82, 328);
insert into answer (answer_id, question_id, choice_id) values (83, 83, 331);
insert into answer (answer_id, question_id, choice_id) values (84, 84, 333);
insert into answer (answer_id, question_id, choice_id) values (85, 85, 340);
insert into answer (answer_id, question_id, choice_id) values (86, 86, 342);
insert into answer (answer_id, question_id, choice_id) values (87, 87, 346);
insert into answer (answer_id, question_id, choice_id) values (88, 88, 352);
insert into answer (answer_id, question_id, choice_id) values (89, 89, 355);
insert into answer (answer_id, question_id, choice_id) values (90, 90, 360);
insert into answer (answer_id, question_id, choice_id) values (91, 91, 361);

