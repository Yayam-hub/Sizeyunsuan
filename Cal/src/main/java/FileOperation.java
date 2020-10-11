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
        for (int i = 1; i < n + 1; i++) {
            String formulas = calc.getExpression(r);
            List<String> cnkiexp1 = getCheck(formulas);
            if(cnkiexp1==null||cnkiexp1.isEmpty()){
                formulas=calc.getExpression(r);
                cnkiexp1 = getCheck(formulas);
            }
            String cnki1 = trans.ToString(cnkiexp1);
            boolean contains2 = false;
            if (cnkiexp1.get(0).equals("+") || cnkiexp1.get(0).equals("×")) {
                List<String> cnkiexp2 = cnkiexp1;
                String t1 = cnkiexp2.get(1);
                String t2 = cnkiexp2.get(2);
                cnkiexp2.set(1, t2);
                cnkiexp2.set(2, t1);
                String cnki2 = trans.ToString(cnkiexp2);
                contains2 = map.containsKey(cnki2);
            }
            if (i == 1) {
                map.put(cnki1, "");
            }
            boolean contains = map.containsKey(cnki1);
            if (contains == true || contains2 == true) {
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
            Bexersise.write(i + ". " + formulas + "="+"\r\n");
            Banswer.write(i + ". " + answers + "\r\n");
        }
        Bexersise.flush();
        Banswer.flush();
        Bexersise.close();
        Banswer.close();

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
            if (!calculates.IsOperator(kk)) {
                st.push(kk);
            } else if (kk.equals("+") || kk.equals("-") || kk.equals("×")) {
                cnkiexp.add(kk);
                top1 = st.pop();
                top2 = st.pop();
                if (top2 != "@") {
                    cnkiexp.add(top2);
                }
                if (top1 != "@") {
                    cnkiexp.add(top1);
                }
                st.push("@");
            } else if (kk.equals("÷")) {
                cnkiexp.add(kk);
                top1 = st.pop();
                top2 = st.pop();
                cnkiexp.add(top2);
                cnkiexp.add(top1);
                st.push("@");
            }
        }
        return cnkiexp;
    }

}


