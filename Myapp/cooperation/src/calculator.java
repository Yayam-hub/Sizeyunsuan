import java.util.Random;

public class calculator {

    public  Random random=new Random();
    calculates c = new calculates();

    /*
  随机获取操作符
   */
  public  char getOperator() {
        int opnum=random.nextInt(4);
        char op;
        switch(opnum){
            case 0:
                op='+';
                break;
            case 1:
                op='-';
                break;
            case 2:
                op='×';
                break;
            case 3:
                op='÷';
                break;
            default:
                op='+';
        }
        return op;
    }

    /*
    生成表达式
     */
    public  String getExpression(int r) {
        String expression;
        expression = new String();
        int ran=random.nextInt(3);//根据随机数决定生成运算符的数量
        switch (ran) {
            case 0:
                expression= OneOperator(r);
                break;
            case 1:
                expression= TwoOperators(r);
                break;
            case 2:
                expression= ThreeOperator(r);
                break;
        }
        return expression;
    }


    public String OneOperator(int r) {//一个运算符
        Fraction f1=new Fraction();
        Fraction f2=new Fraction();
        f1.getRandom(r);
        f2.getRandom(r);
        char op= getOperator();
        String formula = "";
        switch (op) {
            case '+':
                formula= f1+" + "+f2;
                break;
            case '-':
                if(!f1.CompareFraction(f2)) {
                    Fraction temp;
                    temp=f1;f1=f2;f2=temp;
                }

                formula= f1+" - "+f2;
                break;
            case '×':
                formula= f1+" × "+f2;
                break;
            case '÷':
                while(f2.JudgeNum()) {
                    f2.getRandom(r);
                }
                formula= f1+" ÷ "+f2;
                break;
        }
        return formula;
    }


    public String TwoOperators(int r){//两个运算符
        Fraction f1=new Fraction();
        Fraction f2=new Fraction();
        Fraction f3=new Fraction();
        f1.getRandom(r);
        f2.getRandom(r);
        f3.getRandom(r);
        char op1= getOperator();
        char op2= getOperator();
        String formula ="";
        String formula1="";
        switch (op1) {
            case '+':

                formula= f1+" + "+f2;
                break;
            case '-':

                if(!f1.CompareFraction(f2)) {
                    Fraction temp;
                    temp=f1;f1=f2;f2=temp;
                }

                formula= f1+" - "+f2;
                break;
            case '×':

                formula= f1+" × "+f2;
                break;
            case '÷':
                while(f2.JudgeNum()) {
                    f2.getRandom(r);;
                }

                formula= f1+" ÷ "+f2;
                break;
            default:
                formula= f1+" + "+f2;
                break;
        }

        switch (op2) {
            case '+':

                formula1=formula+" + "+f3;
                break;
            case '-':

                if(!c.calculate(formula).CompareFraction(f3)) {

                    formula1= f3+" - "+"("+formula+")";
                }else {

                    formula1= formula+" - "+f3;}
                break;
            case '×':

                formula1= "("+formula+")"+" × "+f3;
                break;
            case '÷':
                while(f3.JudgeNum()) {
                    f3.getRandom(r);;
                }

                formula1="("+formula+")"+" ÷ "+f3;
                break;
            default:
                formula1=formula+" + "+f3;
                break;
        }

        return formula1;
    }


    public String ThreeOperator(int r){//三个运算符
        Fraction f1=new Fraction();
        Fraction f2=new Fraction();
        Fraction f3=new Fraction();
        Fraction f4=new Fraction();
        f1.getRandom(r);
        f2.getRandom(r);
        f3.getRandom(r);
        f4.getRandom(r);
        char op1= getOperator();
        char op2= getOperator();
        char op3= getOperator();
        String formula="";
        String formula1="";;
        String formula2="";
        switch (op1) {
            case '+':

                formula= f1+" + "+f2;
                break;
            case '-':

                if(!f1.CompareFraction(f2)) {
                    Fraction temp;
                    temp=f1;f1=f2;f2=temp;
                }

                formula= f1+" - "+f2;
                break;
            case '×':

                formula= f1+" × "+f2;
                break;
            case '÷':
                while(f2.JudgeNum()) {
                    f2.getRandom(r);;
                }

                formula= f1+" ÷ "+f2;
                break;
            default:
                formula= f1+" + "+f2;
                break;
        }
        switch (op2) {
            case '+':

                formula1=formula+" + "+f3;
                break;
            case '-':
                if(!c.calculate(formula).CompareFraction(f3)) {

                    formula1= f3+" - "+"("+formula+")";
                }else {

                    formula1= formula+" - "+f3;}
                break;
            case '×':

                formula1= "("+formula+")"+" × "+f3;
                break;
            case '÷':
                while(f3.JudgeNum()) {
                    f3.getRandom(r);;
                }

                formula1="("+formula+")"+" ÷ "+f3;
                break;
            default:
                formula1=formula+" + "+f3;
                break;
        }
        switch (op3) {
            case '+':

                formula2=formula1+" + "+f4;
                break;
            case '-':
                if(!c.calculate(formula1).CompareFraction(f4)) {

                    formula2= f4+" - "+"("+formula1+")";
                }else {

                    formula2= formula1+" - "+f4;}
                break;
            case '×':

                formula2= "("+formula1+")"+" × "+f4;
                break;
            case '÷':
                while(f4.JudgeNum()) {
                    f4.getRandom(r);;
                }

                formula2="("+formula1+")"+" ÷ "+f4;
                break;
            default:
                formula2=formula1+" + "+f4;
                break;
        }

        return formula2;
    }


}
