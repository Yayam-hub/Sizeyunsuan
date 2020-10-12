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
        grade.check("E:\\Sizeyunsuan\\Myapp\\Cooperation\\StuAnswers.txt","E:\\Sizeyunsuan\\Myapp\\Cooperation\\Answers.txt");
        System.out.println("已生成10000道四则运算及答案");
    }
    @Test
    public void test2() throws IOException {
        FileOperation Build =new FileOperation();
        Build.build(15,10000);
        JudgeGrade grade=new JudgeGrade();
        grade.check("E:\\Sizeyunsuan\\Myapp\\StuAnswers.txt","E:\\Sizeyunsuan\\Myapp\\Answers.txt");
        System.out.println("已生成10道四则运算及答案");
    }


    @After
    public void tearDown() throws Exception {
        System.out.println("测试结束！");
    }
}