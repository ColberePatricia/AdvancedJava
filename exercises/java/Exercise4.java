/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Patricia
 */
public class Exercise4 {
    public static void main(String[] args){
        DataObject []dataObj = new DataObject [3];
        DataObject el0 = new DataObject();
        DataObject el1 = new DataObject();
        DataObject el2 = new DataObject();
        el0.count=0;
        el1.count=1;
        el2.count=2;
        
        dataObj[0]=el0;
        dataObj[1]=el1;
        dataObj[2]=el2;
        
        int sum=0;
        for (int i=0; i<dataObj.length;i++)
            sum+=dataObj[i].count;
        
        
        System.out.println("The sum of count is: "+sum);
    }
}
