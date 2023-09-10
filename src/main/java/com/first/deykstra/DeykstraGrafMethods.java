package com.first.deykstra;

import java.util.HashMap;
import java.util.List;

public interface DeykstraGrafMethods {

    HashMap<String, HashMap<String, Integer>> addNodesToGraf( String parentNode, HashMap<String,HashMap<String,Integer>> deykstraGraf ,HashMap<String,Integer> value);

    void createCostsTable(HashMap<String, HashMap<String,Integer>> deykstraGraf);

    void createParentNodesTable(HashMap<String, HashMap<String,Integer>> deykstraGraf);

    String findLowestNode(HashMap<String,Integer> costsTable);

    List<String> getAllNodes (HashMap<String,HashMap<String,Integer>> deykstraGraf);

    void processingRoute();

    HashMap<String, HashMap<String, Integer>> getDeykstraGraf();

    HashMap<String,Integer> getCostsTable();

    HashMap<String,String> getParentNodesTable();

}
