package com.aimeelina.communityvue.utils;

public class UtilFuncs {
    public static String intAnswerToStringAnswer(int intAns,int questionType){
        if(questionType==0){//单选
            if(intAns==1){//0001
                return "A";
            }
            if(intAns==2){//0010
                return "B";
            }
            if(intAns==4){//0100
                return "C";
            }
            else{//1000
                return "D";
            }
        }
        else if(questionType==2){//判断
            if(intAns==1){//0001
                return "True";
            }
            else{//0010
                return "False";
            }
        }
        else{//多选
            StringBuilder res = new StringBuilder(4);
            if(intAns%2==1){
                res.append("A");
            }
            intAns/=2;
            if(intAns%2==1){
                res.append("B");
            }
            intAns/=2;
            if(intAns%2==1){
                res.append("C");
            }
            intAns/=2;
            if(intAns%2==1){
                res.append("D");
            }
            return res.toString();
        }
    }
    public static int stringAnswerToIntAnswer(String stringAns){
        int intAns=0;
        if(stringAns.indexOf('A')!=-1){
            intAns+=1;
        }
        if(stringAns.indexOf('B')!=-1){
            intAns+=2;
        }
        if(stringAns.indexOf('C')!=-1){
            intAns+=4;
        }
        if(stringAns.indexOf('D')!=-1){
            intAns+=8;
        }
        if(stringAns.indexOf('T')!=-1){
            intAns+=1;
        }
        if(stringAns.indexOf('F')!=-1){
            intAns+=2;
        }
        return intAns;
    }

}
