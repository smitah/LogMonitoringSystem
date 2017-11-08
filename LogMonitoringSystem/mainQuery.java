/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package query_tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author smitahirve
 */
public class mainQuery {
    public static HashMap<String, ArrayList<logData>> logMap = new HashMap<String, ArrayList<logData>>();
    public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    
    public static void main(String args[]) throws FileNotFoundException, IOException, ParseException{
        String dataPath = args[0];
        System.out.println(dataPath);
        intialize(dataPath); 
        Scanner sc = new Scanner(System.in);
        String inputQuery = "";
        while(true){
            System.out.println(" Enter your query: ");
            inputQuery = sc.nextLine();
            if(inputQuery.equals("EXIT"))
                break;
            else{
                calcValues(inputQuery);
            }
            inputQuery = "";
        }  
    }
    
    
    public static void calcValues(String input) throws ParseException{
        String[] queryValues = input.split(" ");
        String queryKey = queryValues[1] + " " + queryValues[2];
        Date startDate = dateFormat.parse(queryValues[3] + " " + queryValues[4]);
        long unixStarttime = (long) startDate.getTime()/1000;
        Date endDate = dateFormat.parse(queryValues[5] + " " + queryValues[6]);
        long unixEndtime = (long) endDate.getTime()/1000;
        
        printResult(queryKey, Long.toString(unixStarttime), Long.toString(unixEndtime));
    }
    
    
    public static void intialize(String dataPath) throws IOException{
        String[] files = fileList(dataPath);
       
        for(String file: files){
             String fileRoute = dataPath + "/" +  file;
             populateMap(fileRoute);
        } 
    }
    
    
    public static String[] fileList(String folderPath){
         return new File(folderPath).list();
    }
    
    public static void populateMap(String filePath) throws FileNotFoundException, IOException{
        String contents = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] rowArray = contents.split("\n");
        for(int i = 1; i<rowArray.length; i++){
            String rowData = rowArray[i];
            String[] rowValues = rowData.split(" ");
            logData obj = new logData();
            obj.setUnix_time(rowValues[0]);
            obj.setIp_address(rowValues[1]);
            obj.setCpu_id(rowValues[2]);
            obj.setCpu_usage(rowValues[3]);
            String key = obj.getIp_address() + " " + obj.getCpu_id();
            ArrayList<logData> values = new ArrayList<logData>();
            if(!logMap.containsKey(key)){
                values.add(obj);
                logMap.put(key, values);
            }else{
                values = logMap.get(key);
                values.add(obj);
                logMap.put(key, values);
            }
        }   
    }
       
    public static void printResult(String key, String startTime, String endTime) throws ParseException{
        
        ArrayList<logData> result = logMap.get(key);
        StringBuffer queryResult = new StringBuffer();
        for(logData resultValue: result){
             if((resultValue.getUnix_time().compareTo(startTime)>=0) &&(resultValue.getUnix_time().compareTo(endTime)<0)){
                Date resultValuetime;
                resultValuetime = new Date((long)Long.parseLong(resultValue.getUnix_time())*1000);
                queryResult.append("(");
                queryResult.append(dateFormat.format(resultValuetime) + " , " + resultValue.getCpu_usage());
                queryResult.append("%)");
                queryResult.append(", ");
            }     
        } 
        if(queryResult.length() == 0)
            System.out.println(" No logs found for the given range!");
        else{
            queryResult.deleteCharAt(queryResult.length()-2);
            System.out.println(queryResult);
        }
        queryResult = new StringBuffer();
    }
}