import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JudgeGrade {
    public void check(String expath,String anpath) throws IOException {
        BufferedReader BRexersise=new BufferedReader(new FileReader(expath));
        BufferedReader BRanswer=new BufferedReader(new FileReader(anpath));
        BufferedWriter Bgrade=new BufferedWriter(new FileWriter(".\\Grade.txt"));
        List<String> Correct=new ArrayList<String>();
        List<String> Wrong=new ArrayList<String>();
        String an=null;
        String ex=null;
        while((an=BRanswer.readLine())!=null) {
            ex=BRexersise.readLine();
            int point=an.indexOf(".");
            String stran=an.substring(point+1);
            stran=stran.trim();
            String strex=ex.substring(point+1);
            strex=strex.trim();
            if(stran.equals(strex)) {
                String cno=an.substring(0, point);
                Correct.add(cno);
            }else {
                String wno=an.substring(0, point);
                Wrong.add(wno);
            }
        }
        String corr=String.join(",",Correct);
        String wr=String.join(",",Wrong);
        Bgrade.write("Correct: "+Correct.size()+" ("+corr+")"+"\r\n");
        Bgrade.write("Wrong: "+Wrong.size()+" ("+wr+")");
        Bgrade.flush();
        Bgrade.close();
    }
}
