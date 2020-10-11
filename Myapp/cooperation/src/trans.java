import java.util.ArrayList;
import java.util.List;

public class trans {


    /*
      将String类型表达式转换成List<String>类型
      */
    public static List<String> ToList(String str){
        List<String> infix = new ArrayList<String>();
        str=str.replace(" ", "");
        char op;
        String temp;
        temp=new String();
        for(int i=0;i<str.length();i++) {
            op=str.charAt(i);
            if(calculates.IfFraction(op)||calculates.IfFractionOperator(op)) {
                temp+=op;
            } else if(calculates.IfOperator(op)) {
                if(!temp.isEmpty()) {
                    infix.add(temp);
                    temp="";
                }
                infix.add(op+"");
            }
        }
        if(!temp.isEmpty()) {
            infix.add(temp);
            temp="";
        }
        return infix;
    }

    /*
    将List<String>类型表达式转换成String类型
    */
    public static String ToString(List<String> list) {
        String str1;
        str1=new String();
        for(String s:list) {
            str1=str1+s+" ";
        }
        return str1;
    }
}
