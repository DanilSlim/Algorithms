package com.first.graf;

import java.util.HashMap;
import java.util.List;

public interface GrafsMethod {

HashMap<String, List<String>>addNeigbornsToGraf(String neigborn, List<String> neigborns);

void deleteNeigbornFromGraf(String neigborn);

HashMap<String, List<String>>getGraf();

void setKey (String key);

boolean searchInGraf(String searchKey,Graf graf);

}
