import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MainTest {
    @Before
public void setUp() throws Exception {
        System.out.println("测试开始！");
}
    @Test
    public void test() throws IOException {
        FileOperation Build =new FileOperation();
        Build.build(10000,15);
        JudgeGrade grade=new JudgeGrade();
        grade.check("E:\\Sizeyunsuan\\Myapp\\StuAnswers.txt","E:\\Sizeyunsuan\\Myapp\\Answers.txt");
    }


    @After
    public void tearDown() throws Exception {
        System.out.println("测试结束！");
    }
}