package com.cy.www;

import java.util.Map;

/**
 * @author: chenyu
 * @date: 2020/4/3 9:16
 */
public class numberUtils {

    /**
     * 将一个分数转为带分数
     *@author chenyu
     *@param  a是分子b是分母
     *@date 9:30 2020/4/3
     *@return
     **/
    public static String changeNumber(int a,int b){
        if(a>=b){
            int c=a/b;
            int d=a%b;
            if(d==0){
                return c+"";
            }
            else {
                return c+","+d+"/"+b;
            }
        }
        else{
            return a+"/"+b;
        }
    }





}
