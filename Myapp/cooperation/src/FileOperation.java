import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class FileOperation {

    calculator calc=new calculator();
    calculates cal = new calculates();
    HashMap<String, String> map = new HashMap<String, String>();

    /*
    在当前目录下生成练习题和答案
     */
    public void build(int n, int r) throws IOException {
        BufferedWriter Bexersise = new BufferedWriter(new FileWriter(".\\Exercises.txt"));
        BufferedWriter Banswer = new BufferedWriter(new FileWriter(".\\Answers.txt"));
        BufferedWriter StuBanswer = new BufferedWriter(new FileWriter(".\\StuAnswers.txt"));
        for (int i = 1; i < n + 1; i++) {
            String formulas = calc.getExpression(r);
            List<String> cnkiexp1 = getCheck(formulas);

            if(cnkiexp1==null||cnkiexp1.isEmpty()){
                //当产生表达式出现空值时重新生成
                formulas=calc.getExpression(r);
                cnkiexp1 = getCheck(formulas);
            }
            String cnki1 = trans.ToString(cnkiexp1);
            boolean contains2 = false;
            //对"+"和"x"运算进行查重
            if (cnkiexp1.get(0).equals("+") || cnkiexp1.get(0).equals("×")) {

                List<String> cnkiexp2 = cnkiexp1;

                //将运算符左右的运算数调换存入cnkiexp2
                String t1 = cnkiexp2.get(1);
                String t2 = cnkiexp2.get(2);
                cnkiexp2.set(1, t2);
                cnkiexp2.set(2, t1);
                String cnki2 = trans.ToString(cnkiexp2);
                contains2 = map.containsKey(cnki2);//判断cnki2和cnki1是否相同
            }
            if (i == 1) {
                map.put(cnki1, "");
            }
            boolean contains1 = map.containsKey(cnki1);
            if (contains1 == true || contains2 == true) {//相同时重新生成表达式
                formulas = calc.getExpression(r);
                cnkiexp1 = getCheck(formulas);
                cnki1 = trans.ToString(cnkiexp1);
                contains2 = false;
                if (cnkiexp1.get(0).equals("+") || cnkiexp1.get(0).equals("×")) {
                    List<String> cnkiexp2 = cnkiexp1;
                    String t1 = cnkiexp2.get(1);
                    String t2 = cnkiexp2.get(2);
                    cnkiexp2.set(1, t2);
                    cnkiexp2.set(2, t1);
                    String cnki2 = trans.ToString(cnkiexp2);
                    contains2 = map.containsKey(cnki2);
                }
            }
            map.put(cnki1, "");

            Fraction answer = cal.calculate(formulas);
            String answers = answer.toString();
            Bexersise.write(i + ". " + formulas + "="+"\r\n");//将题目写入题目文件
            Banswer.write(i + ". " + answers + "\r\n");//将答案写入答案文件
            StuBanswer.write(i + ". " + "\r\n");//将题号写入答题文件
        }
        Bexersise.flush();
        Banswer.flush();
        StuBanswer.flush();
        Bexersise.close();
        Banswer.close();
        StuBanswer.close();

    }

    /*
    查重表达式的生成
     */
    public List<String> getCheck(String str) {
        List<String> linfix = trans.ToList(str);
        List<String> lpostfix = calculates.TransferToPostfix(linfix);
        List<String> cnkiexp = new ArrayList<String>();
        Stack<String> st = new Stack<String>();
        String top1 ;
        top1= "";
        String top2 ;
        top2= "";
        for (String kk : lpostfix) {
            if (!calculates.IsOperator(kk)) {//不是运算符时
                st.push(kk);//压入栈
            } else if (kk.equals("+") || kk.equals("-") || kk.equals("×")) {//是运算符
                cnkiexp.add(kk);
                top1 = st.pop();
                top2 = st.pop();
                if (top2 != "￥") {
                    cnkiexp.add(top2);
                }
                if (top1 != "￥") {
                    cnkiexp.add(top1);
                }
                st.push("￥");
            } else if (kk.equals("÷")) {
                cnkiexp.add(kk);
                top1 = st.pop();
                top2 = st.pop();
                cnkiexp.add(top2);
                cnkiexp.add(top1);
                st.push("￥");
            }
        }
        return cnkiexp;
    }

}

