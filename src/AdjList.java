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
            }
            else if((currNode.getValue()).equals(tarLabel)){
                tarNode = currNode;
            }
            currNode = currNode.getNextVertex();
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
        //Find vertex
        Node vertexNode = mHead;
        Node edgeNode = null;
        int arrayCount = 0;

        for (int i = 0; i < mLength; ++i){
            if ((vertexNode.getValue()).equals(vertLabel)){
                break;
            }
            vertexNode = vertexNode.getNextVertex();
        }

        //Check if vertex exists
        if (vertexNode != null){
            edgeNode = vertexNode.getNextEdge();

            //Copy edges of vertex into array
            while(edgeNode != null){
                neighbours.add(arrayCount, (edgeNode.getValue()));
                //Go to next edge and up position in arrayCount
                edgeNode = edgeNode.getNextEdge();
                arrayCount ++;
            }
            //return completed neighbours
            return neighbours;
        }
        else{
            //return empty neighbours
            return neighbours;
        }
        
        // Implement me!
        
        
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {
        Node currVertexNode = mHead;
        Node prevVertexNode = null;

        // if there are no vertex's
        if (currVertexNode == null){
            System.out.println(">There are no vertex's to remove.");
            return;
        }

        // If the vertex is the head
        if ((currVertexNode.getValue()).equals(vertLabel)){
            mHead = currVertexNode.getNextVertex();
            mLength --;
            return;
        }

        prevVertexNode = currVertexNode;
        currVertexNode = currVertexNode.getNextVertex();

        //if the node is not the head
        for (int i = 0; i < mLength - 1; i++){
            if ((currVertexNode.getValue()).equals(vertLabel)){
                prevVertexNode.setNextVertex(currVertexNode.getNextVertex());
                mLength --;
                return;
            }
            prevVertexNode = currVertexNode;
            currVertexNode = currVertexNode.getNextVertex();
        }

        // if the loop can't find the vertex.
        System.out.println(">No such vertex exists.");
        return;
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