package com.first.graf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Graf implements GrafsMethod {

    private HashMap<String, List<String>> graf;
    private List<String> neigborns;
    private String headGraf;

    public Graf(String headGraf) {
        this.headGraf = headGraf;
        this.neigborns = new ArrayList<>();
        this.graf = new HashMap<>();
        this.graf.put(headGraf, this.neigborns);
    }


    @Override
    public HashMap<String, List<String>> addNeigbornsToGraf(String neigborn, List<String> neigborns) {
        if (this.graf.containsKey(neigborn)) {
            this.graf.put(neigborn, neigborns);
            return this.graf;
        } else {
            return this.graf;
        }
    }

    @Override
    public void deleteNeigbornFromGraf(String neigborn) {
        if (this.graf.containsKey(neigborn)){
            List<String>value=this.graf.get(neigborn);
            this.graf.put(neigborn,value);
        }
    }

    @Override
    public HashMap<String, List<String>> getGraf() {
        return this.graf;
    }

    @Override
    public void setKey(String key) {
        this.graf.put(key,null);
    }

    @Override
    public boolean searchInGraf(String searchKey, Graf graf) {

        ArrayList<String> searchName=new ArrayList<>(graf.getGraf().size());//Проверенные имена
        //HashMap<String, List<String>>
        int i=0;

 //Реализовать механизм поиска в ширину по графу
        LinkedList<String> queie =new LinkedList<>();
        queie.addAll(graf.getGraf().get(this.headGraf));

        while (queie.size()!=0) {
            String name = queie.pollFirst();
            if (name.equals(searchKey)) i++;
            if (!(graf.getGraf().get(name)==null || graf.getGraf().get(name).size()==0))
            queie.addAll(graf.getGraf().get(name));
        }
        return i>0;
    }
}