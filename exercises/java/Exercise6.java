/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Patricia
 */
import java.util.*;

public class Exercise6 {
    public static void main(String[] args){
        DataObject []dataObj = new DataObject [5];
        DataObject el0 = new DataObject();
        DataObject el1 = new DataObject();
        DataObject el2 = new DataObject();
        DataObject el3 = new DataObject();
        DataObject el4 = new DataObject();
        el0.count=0;
        el1.count=1;
        el2.count=2;
        el3.count=3;
        el4.count=4;
        
        el0.code="friday";
        el1.code="friday";
        el2.code="saturday";
        el3.code="saturday";
        el4.code="saturday";
        
        dataObj[0]=el0;
        dataObj[1]=el1;
        dataObj[2]=el2;
        dataObj[3]=el3;
        dataObj[4]=el4;
        
        HashMap<String, Integer> sumsForCodes = new HashMap<String, Integer>();
        
        for (int i=0; i<dataObj.length;i++){
            //read the sum for code
            Integer sumForCode = sumsForCodes.get(dataObj[i].code);
            
            //if no sum for that code
            if (sumForCode==null)
                sumForCode=new Integer(0);
            
            //add count to sum
            sumForCode=sumForCode.intValue() + dataObj[i].count;
            
            //store new sum in map
            sumsForCodes.put(dataObj[i].code, sumForCode);
        }
        
        //Iterate all keys (codes) in map
        for (String code : sumsForCodes.keySet()){
            //print the sum for that key
            System.out.println(code+" = "+sumsForCodes.get(code));
        }
        
    }
}
