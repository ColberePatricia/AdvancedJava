/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Patricia
 */
public class Exercise5 {
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
        
        int sum=0;
        for (int i=0; i<dataObj.length;i++){
            if (dataObj[i].code.equals("friday"))
                sum+=dataObj[i].count;
        }
        
        
        System.out.println("The sum of count is: "+sum);
    }
}
