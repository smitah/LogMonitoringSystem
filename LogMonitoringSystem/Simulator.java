/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.stream.IntStream;

/**
 *
 * @author smitahirve
 */
public class Simulator {
    static Date date = new Date();
    static Date end_date = new Date();
    static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
   
    

    public static void main(String[] args) throws IOException, ParseException{
        
        date = DATE_FORMAT.parse("25-05-2016");
        end_date = DATE_FORMAT.parse("26-05-2016");
        String name_of_file = "log_" + DATE_FORMAT.format(date);
        String path = args[0] + "/";
        FileWriter file_log = null;
        try{
            System.out.println(" Generating files...");
            String file_name = name_of_file + ".txt";
            file_log = new FileWriter(path+file_name);
            file_log.write("timestamp IP cpu_id usage \n");
                while(date.compareTo(end_date)<0){
                 String temp = get_nexttime() +" " +  get_IP() + " " + get_cpu_unit() + " " +  get_cpu_usage() +"\n";
                 file_log.write(temp);
                }
             }catch(Exception e){
            System.out.println(e.toString());
        }finally{
            file_log.close(); 
        }
         System.out.println("End of program");
        }
  
    public static String get_nexttime(){
        Random ran = new Random();
        int  n = ran.nextInt(10);
        Calendar cal_date = new GregorianCalendar();
        cal_date.setTime(date);
        cal_date.add(Calendar.SECOND, n);
        date = cal_date.getTime();
        long unix_time = (long) date.getTime()/1000;
        return Long.toString(unix_time); 
    }
    
    public static String get_IP(){
        Random ip_ran = new Random();
        int num = ip_ran.nextInt(1000);
        String ip = "192.168." + Integer.toString(num/256) + "." + Integer.toString(num%256);
        return ip;  
    }
    
    public static String get_cpu_unit(){
        Random cpu_ran = new Random();
        return Integer.toString(cpu_ran.nextInt(2));
    }
    
    public static String get_cpu_usage(){
        Random cpu_usage_ran = new Random();
        return Integer.toString(cpu_usage_ran.nextInt(101));
    }
   
}
