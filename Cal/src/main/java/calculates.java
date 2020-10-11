import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class calculates {

    static char[] op6= {'+','-','×','÷','(',')'};
    static String[] op4={"+","-","×","÷"};

    /*
    对字符进行判断
     */
    public static boolean IfOperator(char c) {
        for(char op:op6) {
            if(op==c) return true;
        }
        return false;
    }//是否是操作符
    public static boolean IfFraction(char c) {
        if(c>='0'&&c<='9')
        {return true;}
        return false;
    }//是否是数字
    public static boolean IfFractionOperator(char c) {
        if(c=='\''||c=='/') {
            return true;
        }
        return false;
    }
    public static boolean IsOperator(String s) {
        for(String op1:op4) {
            if(s.equals(op1)) return true;
        }
        return false;
    }//判断字符串是否是操作符


    /*
    决定运算符优先级
     */

    private static int ADDITION=1;
    private static int SUBTRACTION=1;
    private static int MULTIPLICATION=2;
    private static int DIVISION=2;

    public static int getValue(String op){
        int value;
        switch (op){
            case "+":
                value=ADDITION;
                break;
            case "-":
                value=SUBTRACTION;
                break;
            case "×":
                value=MULTIPLICATION;
                break;
            case "÷":
                value=DIVISION;
                break;
            default:
                value=0;
        }
        return value;
    }

    /*
   将中缀表达式转换为后缀表达式
   */
    public static List<String> TransferToPostfix(List<String> infix){
        List<String> postfix=new ArrayList<String>();
        Stack<String> s1=new Stack<String>();
        for (String str : infix) {
            if(str.equals("(")) {
                s1.push(str);
            }//若是左括号，进栈
            else if(str.equals(")")) {
                while(!s1.peek().equals("(")) {
                    postfix.add(s1.pop());
                }//若是右括号，将栈内内容出栈直到遇到左括号为止
                s1.pop();
            }
            else if(str.equals("+")||str.equals("-")||str.equals("×")||str.equals("÷")) {//遇到运算符时
                while (s1.size() != 0 && getValue(s1.peek()) >= getValue(str)) {
                    postfix.add(s1.pop());
                }//栈非空，从栈中弹出元素直到遇到更低级的运算符/栈空，再入栈
                s1.push(str);
            } else {
                postfix.add(str);
            }//栈空，直接入栈
        }
        while (s1.size() != 0) {
            postfix.add(s1.pop());
        }//将栈内所有元素弹出
        return postfix;
    }



    /*
    计算后缀表达式
    */
    public Fraction calculate(String str) {
        List<String> linfix=trans.ToList(str);
        List<String> lpostfix= TransferToPostfix(linfix);
        Fraction num= new Fraction();
        Stack<Fraction> s2=new Stack<Fraction>();
        Fraction num1;
        Fraction num2;
        Fraction answer=new Fraction();
        for(String cal:lpostfix) {
            if(!IsOperator(cal)) {
                num =num.translate(cal);
                s2.push(num);
            } else {
                switch (cal) {
                    case "+":
                        num1=s2.pop();
                        num2=s2.pop();
                        answer=answer.add(num2,num1);
                        s2.push(answer);
                        break;
                    case "-":
                        num1=s2.pop();
                        num2=s2.pop();
                        answer=answer.subtract(num2,num1);
                        s2.push(answer);
                        break;
                    case "×":
                        num1=s2.pop();
                        num2=s2.pop();
                        answer=answer.mutiply(num2,num1);
                        s2.push(answer);
                        break;
                    case "÷":
                        num1=s2.pop();
                        num2=s2.pop();
                        answer=answer.divide(num2,num1);
                        s2.push(answer);
                        break;
                }
            }
        }
        return s2.pop();
    }

}