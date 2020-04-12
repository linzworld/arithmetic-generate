package com.cy.www;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author: chenyu
 * @date: 2020/4/3 9:58
 */
public class FileUtils {

    /**
     * 将题目写入文件中
     *@author chenyu
     *@date 10:09 2020/4/3
     *@param printWriter io
     *       map :题目
     *@return
     **/
    public  static void writeTitle(PrintWriter printWriter, Map<String,String> map){
        Set<String> titles=map.keySet();
        int i=1;
        for(String title:titles){
           printWriter.println(i+":"+title);
           i++;
        }

    }


    //将答案写入文件中
    public static void  writeAnswer(PrintWriter printWriter,Map<String,String> map){
        Set<String> answer=map.keySet();
        int i=1;
        for (String key :answer){
            String value=map.get(key);
            printWriter.println(i+":"+value);
            i++;
        }
    }


    //比较学生答案和标准答案
//    public static void compare(String filePath,Map<String,String> map,int number) throws IOException {
//        FileReader fileReader=new FileReader(filePath);
//        BufferedReader br=new BufferedReader(fileReader);
//        //存储学生答案
//        String[] answer=new String[number];
//        //遍历答案辅助
//        String s=null;
//        int i=0;
//        //回答正确错误的题号
//        LinkedList<Integer> right=new LinkedList<>();
//        LinkedList<Integer>  wrong=new LinkedList<>();
//        while ((s=br.readLine())!=null){
//            answer[i]=s;
//            i++;
//        }
//        br.close();
//        fileReader.close();
//        Iterator<Map.Entry<String,String>> it = map.entrySet().iterator();
//        for (int k=0;k<number;k++){
//            Map.Entry<String,String> entry=it.next();
//
//            if(entry.getValue().equals(answer[k])){
//                right.add(k+1);
//            }else {
//                wrong.add(k+1);
//            }
//        }
//        FileWriter fileWriter = null;
//        //反馈正确与错误题目的信息
//        File file=new File("Grade.txt");
//        fileWriter = new FileWriter(file, true);
//        PrintWriter printWriter = new PrintWriter(fileWriter);
//        printWriter.println(" ");
//        printWriter.print("Correct:"+right.size()+"(");
//        for (int str: right) {
//            printWriter.println(str+",");
//        }
//        printWriter.println(")");
//        printWriter.print("Wrong:"+wrong+"(");
//        for (int str: wrong) {
//            printWriter.println(str+",");
//        }
//        printWriter.print(")");
//        printWriter.flush();
//        fileWriter.flush();
//        printWriter.close();
//        fileWriter.close();
//
//    }

    public static void compare(File answerFile,File exerciseFile) throws IOException {
        if (!exerciseFile.exists()) {
            System.out.println("练习答案文件不存在");
            return;
        }
        if (!answerFile.exists()) {
            System.out.println("答案文件不存在");
            return;
        }
        //key是题号，value是答案
        Map<Integer, String> exerciseMap = new HashMap<>();
        Map<Integer, String> answerMap = new HashMap<>();
        //对比的结果
        List<Integer>  rightRsult=new LinkedList<>();
        List<Integer>  errorRsult=new LinkedList<>();
        InputStreamReader exerciseIn = new InputStreamReader(new FileInputStream(exerciseFile.getAbsolutePath()), StandardCharsets.UTF_8);
        InputStreamReader answerIn = new InputStreamReader(new FileInputStream(answerFile.getAbsolutePath()), StandardCharsets.UTF_8);
        BufferedReader exerciseReader = new BufferedReader(exerciseIn);
        BufferedReader answerReader = new BufferedReader(answerIn);
        String string = null;
        //存储练习的答案
        while ((string = exerciseReader.readLine()) != null) {
            string = string.replaceAll(" +", "");
            string = string.replaceAll("\uFEFF", "");
            String TEXT=string.split("[:]")[0];
            exerciseMap.put(Integer.valueOf(string.split("[:]")[0]), string.split(":")[1]);
        }
        while ((string = answerReader.readLine()) != null) {
            string = string.replaceAll(" +", "");
            string = string.replaceAll("\uFEFF", "");
            answerMap.put(Integer.valueOf(string.split("[:]")[0]), string.split(":")[1]);
        }
        exerciseReader.close();
        answerReader.close();

        //比较答案
        for (int i = 1; i <= answerMap.size(); i++){
            if(exerciseMap.containsKey(i)){
                if(exerciseMap.get(i).equals(answerMap.get(i))){
                    rightRsult.add(i);
                }else {
                    errorRsult.add(i);
                }
            }
        }
        //将比较结果存储到文件中
        File file=new File("Grade.txt");
        FileWriter fileWriter = new FileWriter(file, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(" ");
        printWriter.print("Correct:正确题数："+rightRsult.size()+"(");
        for (int str: rightRsult) {
            printWriter.print(str+",");
        }
        printWriter.println(")");
        printWriter.print("Wrong:错误题数："+errorRsult.size()+"(");
        for (int str: errorRsult) {
            printWriter.print(str+",");
        }
        printWriter.print(")");
        printWriter.flush();
        fileWriter.flush();
        printWriter.close();
        fileWriter.close();
    }

}
