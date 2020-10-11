import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void test() throws IOException {
        FileOperation Build =new FileOperation();
        Build.build(10000,15);
        JudgeGrade grade=new JudgeGrade();
        grade.check("E:\\\\Students'answers.txt","E:\\\\Answers.txt");
    }
}