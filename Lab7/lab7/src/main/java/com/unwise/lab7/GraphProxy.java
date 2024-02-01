package com.unwise.lab7;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.graph4j.Edge;
import org.graph4j.Graph;
import org.graph4j.alg.sp.BFSSinglePairShortestPath;
import org.graph4j.generate.GraphGenerator;
import org.graph4j.alg.GraphAlgorithm;
import org.graph4j.util.Path;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GraphProxy implements Serializable {


    public class ProxyEdge implements Serializable {
        private int node1;
        private int node2;

        public ProxyEdge(int node1, int node2) {
            this.node1 = node1;
            this.node2 = node2;
        }

        public int getNode1(){
            return node1;
        }

        public void setNode1(int node1){
            this.node1 = node1;
        }
        public int getNode2(){
            return node2;
        }

        public void setNode2(int node2){
            this.node2 = node2;
        }
    }
    private int startNode;
    private int endNode;
    private  List<Integer> correctPath;

    //We can't serialize this but we will keep it for now
    private transient Graph graph;

    public GraphProxy() {

        //create an empty graph
        int n = 20; // Number of nodes
        double edgeProbability = 0.15; // Probability of an edge existing between each pair of nodes

        this.graph = GraphGenerator.randomGnp(n, edgeProbability);

        //We decide on start and end

        Random rand = new Random();

        // Generate two different random points
        Path path;
        do {
            startNode = rand.nextInt(n); // generate a random number between 0 and n-1
            endNode = rand.nextInt(n);   // generate a random number between 0 and n-1

            BFSSinglePairShortestPath shortestPath = new BFSSinglePairShortestPath( graph,  startNode, endNode);
            path = shortestPath.findPath();
            if(path == null || path.vertices().length < 3){
                path = null;
                continue;
            }

            Integer[] verticesArray = Arrays.stream( path.vertices() ).boxed().toArray(Integer[]::new);
            correctPath = Arrays.asList(verticesArray);
        } while (path == null);
        // Ensure that startNode and endNode are different
        // Ensure the existence of a path

    }

    public  List<ProxyEdge> getProxyEdges(){
        List<ProxyEdge> proxyEdges = new ArrayList<>();

        for (Edge edge : graph.edges()) {
            ProxyEdge proxy = new ProxyEdge( edge.source(), edge.target() );
            proxyEdges.add(proxy);
        }
        return proxyEdges;
    }

    public int getStartNode(){
        return startNode;
    }
    public int getEndNode(){
        return endNode;
    }

    public List<Integer> getCorrectPath(){
        return correctPath;
    }

}
