import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

class Road {
    public String city1;
    public String city2;

    public Road(String city1, String city2) {
        this.city1 = city1;
        this.city2 = city2;
    }

}

class RoadMap {
    Map<String, Set<Road>> roadMap = new HashMap<String, Set<Road>>();

    //This function helps to get all the cities in the graph
    public Set<String> getAllCities() {
        return this.roadMap.keySet();
    }

    //This function will read the input
    public void readLine(String line) {
        String[] csv = line.split(",");
        String city1 = csv[0];
        String city2 = csv[1];
        addRoad(city1, city2);
    }

    private void addCity(String city) {
        this.roadMap.put(city, new HashSet<Road>());
    }

    //This function will add both outgoing and incoming roads between two cities
    private void addRoad(String city1, String city2) {
        Road road1 = new Road(city1, city2);
        Road road2 = new Road(city2, city1);
        if (!this.roadMap.containsKey(city1)) {
            addCity(city1);
        }
        if (!this.roadMap.containsKey(city2)) {
            addCity(city2);
        }
        this.roadMap.get(city1).add(road1);
        this.roadMap.get(city2).add(road2);
    }

    //This function will return all the outgoing roads from a city
    public Set<Road> getAllOutgoingRoads(String node) {
        return this.roadMap.get(node);
    }

}

public class GraphAssignment {
    static RoadMap roadMap = new RoadMap();

    public static void readMap(Scanner scanner) {
        while (true) {
            String mapLine = scanner.nextLine();
            if (mapLine.equals("")) {
                break;
            }
            roadMap.readLine(mapLine);
        }
        System.out.println("Read map");

    }
    public static void findAnyRouteToCity(String source, String destination) {
        // PART-A: Write the required java code to find the possible routes from source to destination
        //Set<String> hh=new HashSet<String>();
        //hh.add(roadMap.addCity)
//        LinkedList<String> l=new LinkedList<String>();
//        l.add(source);
//        l.add();
//        Set<Road> sh=new HashSet<Road>();
//        Set<String> s=new HashSet<String>();
//        Queue<String> q=new LinkedList<String>();
//        s= roadMap.getAllCities();
//        sh= roadMap.getAllOutgoingRoads(source);
//        LinkedList<Road> ll=new LinkedList<Road>();
//        ll.add(sh);
//        System.out.print(ll);
//        int n=s.size();
//        String arr[]=new String[n];
//        int i=0;
//        for(String x:s) {
//            arr[i++] = x;
//            q.add(x);
//            //System.out.print(x+"->");
//        }
//        for(int j=0;j<q.size()-1;j++)
//        {
//            String a=q.remove();
//            System.out.print(a+"->");
//            if(a.equals(destination))
//            {
//                System.out.print(destination);
//                break;
//            }
//	       q.remove();
//            else
//            {
//               q.remove();
//            }
        Queue<String> q = new LinkedList<String>();// Created queue for storing cities with respect to source
        Map<String, String> path = new HashMap<String, String>();//Created Map for storing all the cities
        Map<String, Boolean> v = new HashMap<String, Boolean>();//Created Map for storing all the outgoing paths from particulr node

        for (String city : roadMap.getAllCities()) {
            v.put(city, false);
        }
        v.put(source, true);
        q.add(source);
        while (!q.isEmpty())
        {
            String currentCity = q.remove();
            for (Road road : roadMap.getAllOutgoingRoads(currentCity))
            {
                if (!v.get(road.city2))
                {
                    q.add(road.city2);
                    v.put(road.city2, true);
                    path.put(road.city2, currentCity);
                }
            }
        }
        if (v.get(destination)) //checking whether map has path from source to destination
        {
            String currentCity = destination;
            String result = destination;
            while (!currentCity.equals(source)) {
                currentCity = path.get(currentCity);
                //path = currentCity + " -> " + path;
                //System.out.print(currentCity + " -> " + path);
                result=currentCity + " -> " + result;
            }
            System.out.println(result);
        }
        else
        {
            System.out.println("Not Reachable");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Map");
        readMap(scanner);

        System.out.println("Enter the source ");
        String source = scanner.nextLine();
        System.out.println("Enter the destination ");
        String destination = scanner.nextLine();
        System.out.println("The route from "+source+" to "+destination+" is");

        findAnyRouteToCity(source, destination);
    }

}
