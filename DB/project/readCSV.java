import java.util.*;
import java.io.*;
/**
 * Reads csv file for DB
 * 
 * @author (Sterling Kohel) 
 * @version (April 7, 2014)
 */
public class readCSV{
    String [] assessment = new String [700];
    HashMap<String,String> AI = new HashMap<String,String>();
    HashMap<String,String> criterion = new HashMap<String,String>();
    String [] criteria = new String [700];
    String [] criteriaID = new String [700];
    String [][] student = new String [400][5];
    String [][] takes = new String [15000][4];
    
    public void readfile() throws IOException{
        int column = 0;
        int row = 0, AIrow = 0, takesrow = 0;
        int stuID = 100000;
        Scanner scan = new Scanner(new File("cdai-data.csv"));
        String line = scan.nextLine();
        String next, word, aicode= "", scode="", dcode="", ccode="", code="";
        line = line.replaceAll(",,", ",***,");
        line = line.replaceAll(",,", ",***,");
        Scanner scan2 = new Scanner(line);
        //***********************1st row ********************
        scan2.useDelimiter("[,]+");
        while (scan2.hasNext()){
            next = scan2.next();
            if (next.equals("***")){
                column++;
            }
            else{
                assessment[column++] = next;
            }
        }
        scan2.close();
        
        //***********************2nd row ********************
        column = 0;
        line = scan.nextLine();
        line = line.replaceAll(",,", ",***,");
        line = line.replaceAll(",,", ",***,");
        scan2 = new Scanner(line);
        scan2.useDelimiter("[,]+");
        while (scan2.hasNext()){
            criteria[column++] = scan2.next();
        }
        scan2.close();
        //***********************3rd row ********************
        column = 0;
        line = scan.nextLine();
        
        scan2 = new Scanner(line);
        scan2.useDelimiter("[,]+");
         while (scan2.hasNext()){
            criteriaID[column++] = scan2.next();
        }
        while (scan.hasNext()){

            //***********************reads student data*********
            column = 0;
            line = scan.nextLine();
            line = line.replaceAll(",,", ",***,");
            line = line.replaceAll(",,", ",***,");
            scan2 = new Scanner(line);
            scan2.useDelimiter("[,]+");
            student[row][column++] = Integer.toString(stuID++);
            for(int i = 1; i < 5; i++){
                if (scan2.hasNext()){
                    next =  scan2.next();
                    if (i == 2 && next.equals("***")){
                        column++;
                    }
                    else                    
                        student[row][column++] = next;
                }
            }
            //***********************scores ********************
            column--;
            while (scan2.hasNext()){
                next = scan2.next();
                if (next.equals("***"))
                    column++;
                else{
                    word = assessment[column];
                    if (word != null){
                        aicode = word.substring(16,18);
                    }
                    if (next.equals("Fall") || next.equals("\"Fall")){
                        next = "Fall" + " " + scan2.next();
                        scode = next.substring(0, 1);
                        dcode = next.substring(next.length()-3, next.length()-1);
                    }
                    if (next.equals("Spring") || next.equals("\"Spring")){
                        next = "Spring" + scan2.next();
                        scode = next.substring(0, 1);
                        dcode = next.substring(next.length()-3, next.length()-1);
                    }
                    if (next.contains("\"")){
                        char[] charArray = next.toCharArray();
                        next = next.substring(0, next.length()-1);
                    }
                    if (next.contains("Spring") || next.contains("Fall")){
                        code = aicode+scode+dcode;
                    }
                    if (next.length() == 1){
                        String num = criteria[column].substring(criteria[column].length()-1,criteria[column].length());
                        ccode = code+ "C"+num;
                    }
                    if (next.length() == 1){
                        String rub = "1-4";
                        criterion.put(ccode, rub);
                        takes[takesrow][0] = student[row][0];
                        takes[takesrow][1] = code;
                        takes[takesrow][2] = ccode;
                        takes[takesrow++][3] = next;
                    }
                    else{
                        AI.put(code, next);
                    }
                    column++;
                }
            }
            row++;
        }
        scan.close();
        scan2.close();
    }
    public void writefile() throws IOException{
        PrintWriter writer = new PrintWriter("records.txt");
        PrintWriter writer2 = new PrintWriter("tables.txt");
        String emph;

        for (int i = 0; i < 380; i++){
            if (student[i][4].contains("CIS")){
                emph = "CIS";
            }
            else if (student[i][4].contains("Software"))
                emph = "SE";
            else if (student[i][4].contains("Undec"))
                emph = "UND";
            else
                emph = "CS";
            writer.println("INSERT INTO Student VALUES("+ Integer.parseInt(student[i][0]) +",'" + student[i][1] + "','" + student[i][2] + "','"
                            + student[i][3] + "','" + emph + "', null);");
        }
        
        for (String key : AI.keySet()){
            writer.println("INSERT INTO AI VALUES('"+ key +"',null,null,'" + AI.get(key)+ "');");
        }

        for (String key : criterion.keySet()){
            writer.println("INSERT INTO Criteria VALUES('" + key +"','" + criterion.get(key)+ "','" + key.substring(0,key.length()-2)+"');");
        }
        int i = 0;

        while (takes[i][0] != null ){
            writer.println("INSERT INTO Result VALUES("+ Integer.parseInt(takes[i][0]) +",'" + takes[i][1] + "','" + takes[i][2] + "','"
                            + takes[i][3] + "');");
            i++;
        }

        for (i = 0; i < 290; i++){
            if (student[i][4].contains("CIS")){
                List l = findDate(student[i][0]);
                if (!l.get(0).equals(" "))
                    writer2.println("INSERT INTO CIS VALUES("+ Integer.parseInt(student[i][0]) + ",'" + l.get(0) + "','" + l.get(l.size()-1) +"');");
            }
            else if (student[i][4].contains("Software")){
                List l = findDate(student[i][0]);
                if (!l.get(0).equals(" "))
                    writer2.println("INSERT INTO SE VALUES("+ Integer.parseInt(student[i][0]) + ",'" + l.get(0) + "','" + l.get(l.size()-1) +"');");
            }
                
            else if (student[i][4].contains("Undec")){
                List l = findDate(student[i][0]);
                if (!l.get(0).equals(" "))
                    writer2.println("INSERT INTO UND VALUES("+ Integer.parseInt(student[i][0]) + ",'" + l.get(0) + "','" + l.get(l.size()-1) +"');");
            }
            else{
                List l = findDate(student[i][0]);
                if (!l.get(0).equals(" "))
                    writer2.println("INSERT INTO CS VALUES("+ Integer.parseInt(student[i][0]) + ",'" + l.get(0) + "','" + l.get(l.size()-1) +"');");
            }
        }

        writer.close();
        writer2.close();
    }
     
    List<String> findDate(String num){
        List<String> list = new ArrayList<String>();
        String bs="",es="",bd="",ed="";
        String[] test;
        Scanner scan2;
        
        int i = 0;
        
        while (takes[i][0] != null){
            if (takes[i][0] == num && !list.contains(takes[i][1])){
                list.add(AI.get(takes[i][1].toString()));
            }
            i++;
        }        
        
        for(String s: list){
            scan2 = new Scanner(s);
            scan2.useDelimiter("[\\s]+");
            if (s != null){
                String sem = scan2.next();
                String date = scan2.next();
                if (bs.equals("")){
                    bs=es=sem;
                    bd=ed=date;
                }
                else if ((date.compareTo(bd) < 0)||(date.equals(bd) && bs.equals("Fall") && date.equals("Spring"))){
                    bd = date;
                    bs = sem;
                }
                else if ((date.compareTo(ed) > 0)||(date.equals(bd) && bs.equals("Spring") && date.equals("Fall"))){
                    ed = date;
                    es = sem;
                }
                else{}
            }
            

        }
        list.clear();
        list.add(bs + " " + bd);
        list.add(es + " " + ed);
        return list;
    }


    public static void main(String[] args)
    {
        readCSV d =  new readCSV();
        try
        {
            d.readfile();
            d.writefile();
        }
        catch(IOException e)
        {
            System.out.println("File could not be opened!");
        }
    }
}







