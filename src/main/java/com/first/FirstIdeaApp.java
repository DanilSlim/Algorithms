package com.first;

import com.first.deykstra.DeykstraGraf;
import com.first.graf.Graf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirstIdeaApp {

    /**
     *
     * @param original- original List for clone
     * @return copy of original List
     * @param <T>
     */
    public static<T> List<T> clone(List<T> original) {
        List<T> copy = new ArrayList<>();
        copy.addAll(original);
        return copy;
    }



    /**
     *
     * @param enterArray - array for computing summa its elements
     * @param i - counter arrays elements
     * @return summa arrays elements or recursion call of function
     */
    public static int arraySumm(int [] enterArray, int i){

        if (i==enterArray.length-1) //border condition
            return enterArray[i];
        else
            return enterArray[i] + arraySumm(enterArray, ++i); //recursive condition
    }




    public static void main(String[] args) {
        System.out.println("Hello world!!!");

        int [] arr={2,4,6,8};

        int i= arraySumm(arr,0);

        System.out.println(i);


        First testClass1 = new TestClass();

        System.out.println(testClass1.doSomething(""));

        Second testClass2=new TestClass();

        System.out.println(testClass2.doSomething("")+testClass2.doSomethingSecond(""));

        Second testClass3= (Second) testClass1;

        System.out.println(testClass3.doSomethingSecond(""));

        //First testClass4= testClass2;

        testClass1=testClass2;

        testClass2=(Second) testClass1;

        HashMap map=new HashMap<String, List<String>>();

        List<String> names =new ArrayList<>();

        names.add("Alice");
        names.add("Numan");

        map.put("my",clone(names));

        names.clear();
        System.out.println(names);

        names.add("Grogu");

        map.put("arr",names);

        System.out.println(map.get("my"));
        System.out.println(map.get("arr"));

        Graf neigbornsGraf=new Graf("My");

        List<String>firstNeigborns= List.of(new String[]{"Hobbo", "Hamster", "Jezzi"});

        neigbornsGraf.addNeigbornsToGraf("My",firstNeigborns);

        List<String>secondFirstNeigborns=List.of("Panny","Ronny","Torch");

        List<String> secondSecondNeigborns=List.of("Suzi","Emma","Garry","Torch");

        neigbornsGraf.setKey("Hobbo");
        neigbornsGraf.setKey("Hamster");
        neigbornsGraf.setKey("Jezzi");

        neigbornsGraf.addNeigbornsToGraf("Hobbo",secondFirstNeigborns);

        neigbornsGraf.addNeigbornsToGraf("Jezzi",secondSecondNeigborns);

        System.out.println(neigbornsGraf.getGraf());

        neigbornsGraf.searchInGraf("Torch",neigbornsGraf);

        //Create DeykstraGraf

        DeykstraGraf deykstraGraf=new DeykstraGraf("START");

        HashMap<String,Integer> chaildNodesWithCost=new HashMap<>();

        chaildNodesWithCost.put("A",2);
        chaildNodesWithCost.put("B",5);
        deykstraGraf.addNodesToGraf("START",deykstraGraf.getDeykstraGraf(),chaildNodesWithCost);

        System.out.println(deykstraGraf.getDeykstraGraf());

        chaildNodesWithCost.clear();
        chaildNodesWithCost.put("B",8);
        chaildNodesWithCost.put("C",7);

        deykstraGraf.addNodesToGraf("A",deykstraGraf.getDeykstraGraf(),chaildNodesWithCost);

        System.out.println(deykstraGraf.getDeykstraGraf());

        chaildNodesWithCost.clear();
        chaildNodesWithCost.put("C",2);
        chaildNodesWithCost.put("D",4);

        deykstraGraf.addNodesToGraf("B",deykstraGraf.getDeykstraGraf(),chaildNodesWithCost);

        System.out.println(deykstraGraf.getDeykstraGraf());

        chaildNodesWithCost.clear();
        chaildNodesWithCost.put("END",1);

        deykstraGraf.addNodesToGraf("C",deykstraGraf.getDeykstraGraf(),chaildNodesWithCost);

        System.out.println(deykstraGraf.getDeykstraGraf());

        chaildNodesWithCost.clear();
        chaildNodesWithCost.put("C",6);
        chaildNodesWithCost.put("END",3);

        deykstraGraf.addNodesToGraf("D",deykstraGraf.getDeykstraGraf(),chaildNodesWithCost);

        System.out.println("Build graf complete");

        System.out.println(deykstraGraf.getDeykstraGraf().get("START"));

        System.out.println(deykstraGraf.getDeykstraGraf());




        deykstraGraf.createCostsTable(deykstraGraf.getDeykstraGraf());

        System.out.println("Build CostsTable complete");

        System.out.println(deykstraGraf.getCostsTable());

        deykstraGraf.createParentNodesTable(deykstraGraf.getDeykstraGraf());

        System.out.println("Build ParentNodesTable complete");

        //System.out.println(deykstraGraf.getParentNodesTable());

        //System.out.println(deykstraGraf.findLowestNode(deykstraGraf.getCostsTable()));

        deykstraGraf.processingRoute();



     //Create anothe DeykstraGraf

        DeykstraGraf deykstraGraf1=new DeykstraGraf("START");

        chaildNodesWithCost.clear();

        chaildNodesWithCost.put("A",6);
        chaildNodesWithCost.put("B",2);
        deykstraGraf.addNodesToGraf("START",deykstraGraf1.getDeykstraGraf(),chaildNodesWithCost);


        chaildNodesWithCost.clear();
        chaildNodesWithCost.put("END",1);

        deykstraGraf.addNodesToGraf("A",deykstraGraf1.getDeykstraGraf(),chaildNodesWithCost);

        chaildNodesWithCost.clear();
        chaildNodesWithCost.put("A",3);
        chaildNodesWithCost.put("END",5);

        deykstraGraf.addNodesToGraf("B",deykstraGraf1.getDeykstraGraf(),chaildNodesWithCost);

        System.out.println("Build graf1 complete");
        System.out.println(deykstraGraf1.getDeykstraGraf());

        deykstraGraf1.createCostsTable(deykstraGraf1.getDeykstraGraf());
        System.out.println("Build CostsTable complete");
        System.out.println(deykstraGraf1.getCostsTable());

        deykstraGraf1.createParentNodesTable(deykstraGraf.getDeykstraGraf());
        System.out.println("Build ParentNodesTable complete");
        System.out.println(deykstraGraf1.getParentNodesTable());

        deykstraGraf1.processingRoute();

    }


}
