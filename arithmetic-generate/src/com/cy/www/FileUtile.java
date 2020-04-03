package com.cy.www;

import java.io.*;
import java.util.*;

/**
 * @author: chenyu
 * @date: 2020/4/3 9:58
 */
public class FileUtile {

    /**
     * 将题目写入文件中
     *@author chenyu
     *@date 10:09 2020/4/3
     *@param printWriter io
     *       map :题目
     *@return
     **/
    public  static void writeTitle(PrintWriter printWriter, Map<String,Integer> map){
        Set<String> titles=map.keySet();
        int i=1;
        for(String title:titles){
           printWriter.println(i+":"+title);
           i++;
        }

    }


    //将答案写入文件中
    public static void  writeAnswer(PrintWriter printWriter,Map<String,Integer> map){
        Set<String> answer=map.keySet();
        int i=1;
        for (String key :answer){
            Integer value=map.get(key);
            printWriter.println(i+":"+value);
            i++;
        }
    }


    //比较学生答案和标准答案
    public static void compare(String filePath,Map<String,Integer> map,int number) throws IOException {
        FileReader fileReader=new FileReader(filePath);
        BufferedReader br=new BufferedReader(fileReader);
        //存储学生答案
        String[] answer=new String[number];
        //遍历答案辅助
        String s=null;
        int i=0;
        //回答正确错误的题号
        LinkedList<Integer> right=new LinkedList<>();
        LinkedList<Integer>  wrong=new LinkedList<>();
        while ((s=br.readLine())!=null){
            answer[i]=s;
            i++;
        }
        br.close();
        fileReader.close();
        Iterator<Map.Entry<String,Integer>> it = map.entrySet().iterator();
        for (int k=0;k<number;k++){
            Map.Entry<String,Integer> entry=it.next();

            if(entry.getValue() ==Integer.parseInt(answer[k])){
                right.add(k+1);
            }else {
                wrong.add(k+1);
            }
        }
        FileWriter fileWriter = null;
        //反馈正确与错误题目的信息
        File file=new File("Grade.txt");
        fileWriter = new FileWriter(file, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(" ");
        printWriter.print("Correct:"+right.size()+"(");
        for (int str: right) {
            printWriter.println(str+",");
        }
        printWriter.println(")");
        printWriter.print("Wrong:"+wrong+"(");
        for (int str: wrong) {
            printWriter.println(str+",");
        }
        printWriter.print(")");
        printWriter.flush();
        fileWriter.flush();
        printWriter.close();
        fileWriter.close();

    }
}
