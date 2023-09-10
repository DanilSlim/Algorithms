package com.first.deykstra;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс реализует направленный взвешенный граф и реализует метод расчета пути с нименьшей стоимостью
 * по алгоритму Дейкстры
 */
public class DeykstraGraf implements DeykstraGrafMethods{

    private String startNode;

    private HashMap<String,HashMap<String,Integer>> deykstraGraf =new HashMap<>();

    private HashMap<String, Integer> costsTable=new HashMap<>();

    private HashMap<String,String> parentsNodesTable=new HashMap<>();

    private ArrayList<String>processedNodes=new ArrayList<>();



    private final static  Integer infinity=Integer.MAX_VALUE;

    public DeykstraGraf(String startNode){

        this.startNode=startNode;

        this.deykstraGraf.put(this.startNode,null);

    }

    /**
     * @param parentNode   - родительский узел
     * @param deykstraGraf -Мапа сожержит родительский узел (key) и список соседних узлов со стоимостью перехода до них
     * @param valueCosts   - Мапа содержит дочерний узел и стоимость пути к нему
     * @return deykstraGraf -Мапа сожержит родительский узел (key) и список соседних узлов со стоимостью перехода до них
     */
    @Override
    public HashMap<String, HashMap<String, Integer>> addNodesToGraf(String parentNode, HashMap<String, HashMap<String, Integer>> deykstraGraf , HashMap<String, Integer> valueCosts) {

        if(deykstraGraf.containsKey(parentNode)){
            HashMap<String,Integer> childNodesWithCosts=new HashMap<>(valueCosts.size());
            childNodesWithCosts.putAll(valueCosts);
            deykstraGraf.put(parentNode,childNodesWithCosts);
            childNodesWithCosts.forEach((key,value)->{ //если узел уже есть в корневых его обнулять не нужно!!!!
                if(!deykstraGraf.containsKey(key))//если узла еще нет в графе заносим его в граф
                deykstraGraf.put(key,null);
            });
        }
        return deykstraGraf;
    }

    /**
     * Метод создает таблицу стоимости пути к узлам заданного графа
     * @param deykstraGraf - исходный направленный граф с весами
     */
    @Override
    public void createCostsTable(HashMap<String, HashMap<String, Integer>> deykstraGraf) {

        deykstraGraf.forEach((key,value)->{
            if(key.equals(this.startNode)){
                HashMap<String,Integer> costNeighborNodes=value;
                costNeighborNodes.forEach((k,v)->{
                    this.costsTable.put(k,v);//определяем стоимость от первого узла до соседей
                });
                //this.costsTable.put(key,value.);//определяем стоимость от первого узла до соседей
            }
            else
                this.costsTable.put(key,infinity);//Остальные узлы еще не посещали, поэтому стоимость infinity
        });
    }

    /**
     * Метод создает таблицу узел - родитель
     * @param deykstraGraf - исходный граф
     */
    @Override
    public void createParentNodesTable(HashMap<String, HashMap<String, Integer>> deykstraGraf) {
        deykstraGraf.forEach((key,value)->{
            if(key.equals(startNode)&&value!=null){
                value.forEach((chaildKey,chaildValue)->{
                    parentsNodesTable.put(chaildKey,key);
                });
            }
            else
                parentsNodesTable.put(key,null);//нет дочерних узлов
        });

    }


    /**
      * Метод определяет узел с наименьшей стоимостью
      * @param costsTable - Мапа кторая хранит стоимость пути к узлу
     * @return - метод возвращает узел типа String с наименьшей стоимостью
     */
    @Override
    public String findLowestNode(HashMap<String, Integer>costsTable) {

        //HashMap<String,Integer> neighborns= costsTable.get(this.startNode);

        LinkedHashMap <String,Integer> sortedMap = costsTable.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));
        return sortedMap.entrySet().stream().findFirst().get().getKey();
    }


    @Override
    public List<String> getAllNodes(HashMap<String, HashMap<String, Integer>> deykstraGraf) {
        
        ArrayList<String> keysList=new ArrayList<>(deykstraGraf.size());
        deykstraGraf.keySet().forEach((key)->{
            keysList.add(key);
        });

        return keysList;
    }

    /**
     * Метод вычисляет маршрут с наименьшей стоимостью по алгоритму Дейкстры
      */
    @Override
    public void processingRoute() {

        List<String> nodesForProcessing=new ArrayList<>(this.deykstraGraf.size());

        HashMap<String,HashMap<String,Integer>> copyGraf=new HashMap<>(this.deykstraGraf.size());

        HashMap<String, Integer> copyCostsTable=new HashMap<>(this.costsTable.size());

        HashMap<String, Integer> workCostsTable=new HashMap<>(this.costsTable.size());

        HashMap<String,String>copyParentsNodesTable =new HashMap<>(this.parentsNodesTable.size());

        Integer costLowestNode=-1;//стоимость пути до узла из таблицы costs

        HashMap<String, Integer> neighborsLowestNode=new HashMap<>();//соседи узла lowestNode

        Integer newCost=-1;

        /*Необходимо работать с копией графа*/
        copyGraf.putAll(this.deykstraGraf);
        copyGraf.remove(this.startNode);
        copyGraf.keySet().forEach((key)->{
            nodesForProcessing.add(key);
        });

        copyCostsTable.putAll(this.costsTable);

        workCostsTable.putAll(this.costsTable);

        copyParentsNodesTable.putAll(this.parentsNodesTable);


        /*перебираем узлы пока не переберем все. Начинаем с соседнего от начального
         узла с наименьшей стоимостью*/
        String lowestNode=findLowestNode(workCostsTable);

        while(nodesForProcessing.size()!=0){

            costLowestNode=copyCostsTable.get(lowestNode);

            //получить список соседей узла costLowesNode
            neighborsLowestNode=deykstraGraf.get(lowestNode);

            //перебрать соседей lowestNode
            if(neighborsLowestNode!=null){
                for(String key: neighborsLowestNode.keySet()){

                 /*У каждого узла имеется стоимость, которая определяет, сколько времени
                потребуется для достижения этого узла от начала.*/
                    Integer costNode=neighborsLowestNode.get(key);
                    newCost=costLowestNode+costNode;

                    //сравним стоимости - новую и из таблицы costsTable
                    if(copyCostsTable.get(key)>newCost){

                        copyCostsTable.put(key,newCost);//если новая стоимость меньше старой заменяем ее и таблице costsTable
                        workCostsTable.put(key,newCost);
                        /*Новый путь проходит через узел В, поэтому В назначается новым родителем.
                         * Этот узел становится новым родителем для соседа*/
                        copyParentsNodesTable.put(key,lowestNode);
                    }

                }
            }


            //copyGraf.remove(lowestNode);//узел обработан
            nodesForProcessing.remove(lowestNode);
            workCostsTable.remove(lowestNode);
            if (workCostsTable.size()!=0)
                lowestNode=findLowestNode(workCostsTable);//берем следующий узел
        }

        System.out.println(copyCostsTable);
        //System.out.println(copyParentsNodesTable);

        ArrayList<String> route=new ArrayList<>(copyParentsNodesTable.size());

        String flag="END";
        route.add(flag);
        while(flag!=null){

            flag=copyParentsNodesTable.get(flag);
            if(flag!=null)
                route.add(flag);
        }

        for (int i=route.size()-1;i>=0;i--) {
            System.out.print(route.get(i)+" ");
        }

        System.out.println();



    }

    @Override
    public HashMap<String, HashMap<String, Integer>> getDeykstraGraf() {
        return this.deykstraGraf;
    }

    @Override
    public HashMap<String, Integer> getCostsTable() {
        return this.costsTable;
    }

    @Override
    public HashMap<String, String> getParentNodesTable() {
        return this.parentsNodesTable;
    }


}
