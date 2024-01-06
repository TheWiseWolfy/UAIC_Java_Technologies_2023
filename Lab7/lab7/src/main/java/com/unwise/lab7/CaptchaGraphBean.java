package com.unwise.lab7;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import org.graph4j.Graph;
import org.graph4j.generate.GraphGenerator;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named("captchaGraphBean")
@RequestScoped
public class CaptchaGraphBean implements Serializable {
    private GraphProxy proxyGraph;

    @PostConstruct
    public void init() {
        this.proxyGraph = new GraphProxy();
    }

    public List<GraphProxy.ProxyEdge> getEdgeProxies() {
        return proxyGraph.getProxyEdges();
    }

    public String getPathAsJson() {
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> pathData = new HashMap<>();

        pathData.put("startNode", this.proxyGraph.getStartNode());
        pathData.put("endNode", this.proxyGraph.getEndNode());

        // Assuming getVertices() method returns a primitive int array
       // pathData.put("path", this.proxyGraph.getCorrectPath() );

        try {
            return mapper.writeValueAsString(pathData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getEdgesAsJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this.proxyGraph.getProxyEdges());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "[]";
        }
    }
}
