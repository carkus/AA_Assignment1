import java.io.*;
import java.util.*;

/**
 * Adjacency list implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */
public class AdjList <T extends Object> implements FriendshipGraph<T>
{   

    protected Node mHead;
    protected int mLength;
        
    /**
     * Contructs empty graph.
     */
    public AdjList() {
        mHead = null;
        mLength = 0;
        // Implement me!
    } // end of AdjList()
    
    
    public void addVertex(T vertLabel) {
        Node vertexNode = new Node(vertLabel);
             
        // If head is empty, then list is empty and head reference need to be initialised.
        if (mHead == null) {
            mHead = vertexNode;
        }
        // otherwise, add node to the head of list.
        else {
            vertexNode.setNextVertex(mHead);
            mHead = vertexNode; 
        }
        
        mLength++;
    } // end of addVertex()
    
    
    public void addEdge(T srcLabel, T tarLabel) {

        Node currNode = mHead;
        int check = 0;

        Node srcEdgeNode = new Node(tarLabel);
        Node tarEdgeNode = new Node(srcLabel);

        Node srcNode = null;
        Node tarNode = null;
        
        for (int i = 0; i < mLength; ++i) {
            if ((currNode.getValue()).equals(srcLabel)){   
                srcNode = currNode;
                System.out.println("src");
            }
            else if((currNode.getValue()).equals(tarLabel)){
                tarNode = currNode;
                System.out.println("tar");
            }
            currNode = currNode.getNextVertex();
            System.out.println("next");
        }

        if (srcNode != null && tarNode != null){
            attachEdge(srcNode, srcEdgeNode);
            attachEdge(tarNode, tarEdgeNode);
        }
        else{
            System.out.println(">Error! One or both vertex don't exist");
        }



        // Implement me!
    } // end of addEdge()
    

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        
        // Implement me!
        
        return neighbours;
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {
        // Implement me!
    } // end of removeVertex()
    
    
    public void removeEdge(T srcLabel, T tarLabel) {
        // Implement me!
    } // end of removeEdges()
    
    
    public void printVertices(PrintWriter os) {
        // Implement me!
        Node currNode = mHead;

        StringBuffer str = new StringBuffer();

        while (currNode != null) {
            str.append(currNode.getValue() + " ");
            currNode = currNode.getNextVertex();
        }

        System.out.println(str);

    } // end of printVertexs()
    
    
    public void printEdges(PrintWriter os) {

        Node currNode = mHead;
        Node currEdgeNode = null;

        StringBuffer str = new StringBuffer();

        while (currNode != null) {
            str.append(currNode.getValue() + " ");
            currEdgeNode = currNode.getNextEdge();
            while(currEdgeNode != null){
                str.append(currEdgeNode.getValue() + " ");
                currEdgeNode = currEdgeNode.getNextEdge();
            }
            str.append("\n");
            currNode = currNode.getNextVertex();
        }

        System.out.println(str);
        // Implement me!
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
        // Implement me!
        
        // if we reach this point, source and target are disconnected
        return disconnectedDist;        
    } // end of shortestPathDistance()




    public void attachEdge(Node vertex, Node edge) {
        if (vertex.getNextEdge() == null) {
            vertex.setNextEdge(edge);
        }
        // otherwise, add node to the head of list.
        else {
            edge.setNextEdge(vertex.getNextEdge());
            vertex.setNextEdge(edge); 
        }
        
    } // end of attachEdge()


    private class Node
    {
        /** Stored value of node. */
        protected T mValue;
        /** Reference to next node. */
        protected Node mNextVertex;
        /** Reference to edges list**/
        protected Node mNextEdge;

        public Node(T vertex) {
            mValue = vertex;
            mNextVertex = null;
            mNextEdge = null;
        }

        public T getValue() {
            return mValue;
        }


        public Node getNextVertex() {
            return mNextVertex;
        }

        public Node getNextEdge() {
            return mNextEdge;
        }


        public void setValue(T value) {
            mValue = value;
        }


        public void setNextVertex(Node next) {
            mNextVertex = next;
        }

        public void setNextEdge(Node next) {
            mNextEdge = next;
        }
    } // end of inner class Node
    
} // end of class AdjList