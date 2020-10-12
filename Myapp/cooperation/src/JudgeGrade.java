import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class JudgeGrade {
    public void check(String expath,String anpath) throws IOException {
        BufferedReader BRexersise=new BufferedReader(new FileReader(expath));
        BufferedReader BRanswer=new BufferedReader(new FileReader(anpath));
        BufferedWriter Bgrade=new BufferedWriter(new FileWriter(".\\Grade.txt"));
        DecimalFormat df = new DecimalFormat("0.00%");

        List<String> Correct=new ArrayList<String>();
        List<String> Wrong=new ArrayList<String>();
        String an=null;
        String ex=null;

        while((an=BRanswer.readLine())!=null) {
            ex=BRexersise.readLine();
            int point=an.indexOf(".");//找到"."所在处，因为答题格式和答案格式均为No.Answer，只需要比较“."后的答案即可
            String stran=an.substring(point+1);//取答案
            stran=stran.trim();
            String strex=ex.substring(point+1);//取标准答案
            strex=strex.trim();
            if(stran.equals(strex)) {
                //答案和标准答案相同时，将题号压入Correct栈
                String cno=an.substring(0, point);
                Correct.add(cno);
            }else {
                //答案和标准答案不同时，将题号压入Wrong栈
                String wno=an.substring(0, point);
                Wrong.add(wno);
            }
        }

        int i=0,j=0;
        i=Correct.size();
        j=Wrong.size();
        double k=(double)i/(i+j);
        String corr=String.join(",",Correct);
        String wr=String.join(",",Wrong);
        Bgrade.write("Accuracy: "+df.format(k)+"\r\n");//输出正确率
        Bgrade.write("Correct: "+Correct.size()+" ("+corr+")"+"\r\n");//输出正确题数和题号
        Bgrade.write("Wrong: "+Wrong.size()+" ("+wr+")");//输出错误题数和题号
        Bgrade.flush();
        Bgrade.close();
    }
}
