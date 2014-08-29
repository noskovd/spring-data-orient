package org.springframework.data.orient.graph;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;

public class PrepareGraphDatabase {

    public static void main(String[] args) {
        
        OrientGraphFactory factory = new OrientGraphFactory("remote:localhost/spring-data-graph");
        
        OrientGraph graph = factory.getTx();
        
        try {
            //graph.createVertexType("Person");
//            Vertex person = graph.addVertex("Person", "Person");
//            person.setProperty("name", "Dzmitry");
//            graph.commit();

            for (Vertex vertex : graph.getVertices()) {
                System.out.println(vertex);
                for (Edge edge : vertex.getEdges(Direction.BOTH)) {
                    System.out.println(edge);
                }
                
                System.out.println(vertex.getPropertyKeys());
            }

            for (Edge edge : graph.getEdges()) {
                System.out.println(edge);
            }
            
            //graph.addEdge(null, graph.getVertex("#13:0"), graph.getVertex("#13:0"), "link");
        } finally {
            graph.shutdown();
        }
    }
}
