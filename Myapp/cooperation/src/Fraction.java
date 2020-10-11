import java.util.Random;
/*
生成随机数
 */
public class Fraction {

    private Random random = new Random();
    public int numerator;//分子
    public int denominator;//分母

    /*
    获取随机一个小于r的整数
     */
    public int getnum(int r) {
        return random.nextInt(r);
    }

    /*
    获取随机运算数
     */
    public Fraction getRandom(int r) {
        if (random.nextBoolean()) {//根据随机布尔值决定生成整数还是分数
            return getInt(r);
        } else {
            return getFraction(r);
        }
    }

    /*
    随机到整数时
     */
    private Fraction getInt(int r) {
        numerator = getnum(r);
        denominator = 1;
        return this;
    }

    /*
    随机到分数时
     */
    private Fraction getFraction(int r) {
        numerator = getnum(r - 1) + 1;
        denominator = getnum(r - 1) + 1;
        int i = numerator > denominator ? denominator : numerator;
        //求公约数
        for (; i > 1; i--) {
            if (numerator % i == 0 && denominator % i == 0) {
                numerator = numerator/i;
                denominator =denominator/i;
                break;
            }
        }
        return this;
    }

    /*
    将分数Fraction类转字符串String类型
     */
    @Override
    public String toString() {
        if (this.denominator==1) {
            return String.valueOf(numerator);
        }
        else if(this.numerator>this.denominator) {
            int c=0;
            c=this.numerator/this.denominator;
            this.numerator=this.numerator%this.denominator;
            return String.valueOf(c)+"'"+String.valueOf(numerator)+"/"+String.valueOf(denominator);
        }
        else{
            return String.valueOf(numerator)+"/"+String.valueOf(denominator);}
    }

    /*
     * 将字符串String转换为分数Fraction
     */
    public  Fraction translate(String s) {
        Fraction f = new Fraction();
        if(s.contains("'")) {
            String[] strings = s.split("['/]");
            f.numerator = Integer.parseInt(strings[0])*Integer.parseInt(strings[2]) + Integer.parseInt(strings[1]);
            f.denominator = Integer.parseInt(strings[2]);
        }else if(s.contains("/")){
            String[] strings = s.split("/");
            f.numerator = Integer.parseInt(strings[0]);
            f.denominator = Integer.parseInt(strings[1]);
        }else {
            f.numerator = Integer.parseInt(s);
            f.denominator = 1;
        }
        return f;
    }

    /*
    判断减法运算结果是否小于零
     */
    public boolean CompareFraction(Fraction f) {
        int result=this.numerator * f.denominator-  f.numerator * this.denominator;
        if(result>0) {return true;}
        else return false;
    }
    /*
    判断除数是否为零
    */
    public boolean JudgeNum() {
        if(numerator==0){
        return true;}
        else return false;

    }

    /*
  对运算结果约分
   */
    public static Fraction simplify(Fraction p) {
        int i = p.numerator>p.denominator?p.denominator:p.numerator;
        for(;i>1;i--) {
            if(p.numerator%i==0&&p.denominator%i==0) {
                p.numerator /= i;
                p.denominator /= i;
                break;
            }
        }
        return p;
    }

    /*
    定义四则运算
     */
    public Fraction add(Fraction s1, Fraction s2){
       Fraction p = new Fraction();
        p.numerator = s1.numerator*s2.denominator + s1.denominator*s2.numerator;
        p.denominator = s1.denominator*s2.denominator;
        return simplify(p);
    }

    public Fraction subtract(Fraction s1, Fraction s2){
        Fraction p = new Fraction();
        p.numerator = s1.numerator*s2.denominator - s1.denominator*s2.numerator;
        p.denominator = s1.denominator*s2.denominator;
        return simplify(p);
    }

    public Fraction mutiply(Fraction s1, Fraction s2){
        Fraction p = new Fraction();
        p.numerator = s1.numerator*s2.numerator;
        p.denominator = s1.denominator*s2.denominator;
        return simplify(p);
    }

    public Fraction divide(Fraction s1, Fraction s2){
        Fraction p = new Fraction();
        p.numerator = s1.numerator*s2.denominator;
        p.denominator = s1.denominator*s2.numerator;
        return simplify(p);

    }


}
