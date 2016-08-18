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
    } // end of AdjList()
    
    
    public void addVertex(T vertLabel) {
        Node vertexNode = new Node(vertLabel);
        Node checkNode = mHead;

        //Check to see if vertex already exists.
        while(checkNode != null){
            if ((checkNode.getValue()).equals(vertLabel)){
                System.out.println(">Vertex already exists.");
                return;
            }
            checkNode = checkNode.getNextVertex();
        }
             
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
        
        //Finds both src and tar vertex nodes.
        for (int i = 0; i < mLength; ++i) {
            if ((currNode.getValue()).equals(srcLabel)){   
                srcNode = currNode;
            }
            else if((currNode.getValue()).equals(tarLabel)){
                tarNode = currNode;
            }
            currNode = currNode.getNextVertex();
        }

        //checks to make sure both vertex exist.
        if (srcNode != null && tarNode != null){
            if (attachEdge(srcNode, srcEdgeNode)){
                attachEdge(tarNode, tarEdgeNode);
            }
        }
        else{
            System.out.println(">Error! One or both vertex don't exist");
        }
    } // end of addEdge()
    

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        Node vertexNode = mHead;
        Node edgeNode = null;
        int arrayCount = 0;

        //Finds vertex
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
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {
        Node currVertexNode = mHead;
        Node prevVertexNode = null;

        //If there are no vertex's.
        if (currVertexNode == null){
            System.out.println(">There are no vertex's to remove.");
            return;
        }

        //If the vertex is the head.
        if ((currVertexNode.getValue()).equals(vertLabel)){
            removeEdgeForVertex(currVertexNode);
            mHead = currVertexNode.getNextVertex();
            mLength --;
            return;
        }

        prevVertexNode = currVertexNode;
        currVertexNode = currVertexNode.getNextVertex();

        //If the vertex is not the head.
        for (int i = 0; i < mLength - 1; i++){
            if ((currVertexNode.getValue()).equals(vertLabel)){
                removeEdgeForVertex(currVertexNode);
                prevVertexNode.setNextVertex(currVertexNode.getNextVertex());
                mLength --;
                return;
            }
            prevVertexNode = currVertexNode;
            currVertexNode = currVertexNode.getNextVertex();
        }

        //If the loop can't find the vertex.
        System.out.println(">No such vertex exists.");
        return;
    } // end of removeVertex()

    
    public void removeEdgeForVertex(Node deleteNode) { //Created to find edges of the vertex to then delete
        Node edgeNode = deleteNode.getNextEdge();

        while (edgeNode != null){
            removeEdge(deleteNode.getValue(), edgeNode.getValue());

            edgeNode = edgeNode.getNextEdge();
        }
    }
    
    public void removeEdge(T srcLabel, T tarLabel) {
        Node vertexNode = mHead;
        Node currEdgeNode = null;
        Node prevEdgeNode = null;

        //Find the vertexs that are connected.
        for (int i = 0; i < mLength; i++){
            if ((vertexNode.getValue()).equals(srcLabel) || (vertexNode.getValue()).equals(tarLabel)){
                prevEdgeNode = vertexNode;
                currEdgeNode = vertexNode.getNextEdge();

                //If there are no edges.
                if (currEdgeNode == null){
                    System.out.println(">This vertex has no edges.");
                    return;
                }

                //Finds both edges and deletes
                while(currEdgeNode != null){
                    if ((currEdgeNode.getValue()).equals(srcLabel) || (currEdgeNode.getValue()).equals(tarLabel)){
                        prevEdgeNode.setNextEdge(currEdgeNode.getNextEdge());
                        currEdgeNode = null;
                    }
                    else{
                        prevEdgeNode = currEdgeNode;
                        currEdgeNode = currEdgeNode.getNextEdge();
                    }
                }
            }
            vertexNode = vertexNode.getNextVertex();
        }
    } // end of removeEdges()
    
    
    public void printVertices(PrintWriter os) {
        Node currNode = mHead;

        StringBuffer str = new StringBuffer();

        while (currNode != null) {
            str.append(currNode.getValue() + " ");
            currNode = currNode.getNextVertex();
        }

        os.println(str);

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

        os.println(str);
        // Implement me!
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
        // Implement me!
        ArrayList<T> neighbours;
        ArrayList<T> checkedNeighbours = new ArrayList<T>();
        T currNeighbour = vertLabel1;
        int distance = disconnectedDist;
        int check = 0;

        neighbours = neighbours(currNeighbour);
        for(int i = 0; i < neighbours.size(); i++){
            checkedNeighbours.add(currNeighbour);
            for(int j = 0; j < checkedNeighbours.size(); j++){
                if ((checkedNeighbours.get(j)).equals(neighbours.get(i))){

                }
                else if((checkedNeighbours.get(j)).equals((checkedNeighbours.size())-1)){
                    return disconnectedDist;
                }
                else if(neighbours.get(i).equals(vertLabel2)){
                    distance = 1;
                    return distance;
                }
                else if(distance == 0){
                    check = shortestPathDistanceRecursive(neighbours.get(i), vertLabel2, checkedNeighbours); 
                }
            }
            if (i == 0){
                distance = check;
            }
            if (check < distance && check > 0){
                distance = check;
            }
        }
        if (distance == disconnectedDist){
            return disconnectedDist;
        }
        else{
            distance ++;
        return distance;
        }
    } // end of shortestPathDistance()

    public int shortestPathDistanceRecursive(T vertLabel1, T vertLabel2, ArrayList<T> checked) {
     // Implement me!
        ArrayList<T> neighbours;
        ArrayList<T> checkedNeighbours = checked;
        T currNeighbour = vertLabel1;
        int distance = disconnectedDist;
        int check = 0;

        neighbours = neighbours(currNeighbour);
        for(int i = 0; i < neighbours.size(); i++){
            checkedNeighbours.add(currNeighbour);
            for(int j = 0; j < checkedNeighbours.size(); j++){
                if ((checkedNeighbours.get(j)).equals(neighbours.get(i))){

                }
                else if((checkedNeighbours.get(j)).equals((checkedNeighbours.size())-1)){
                    return disconnectedDist;
                }
                else if(neighbours.get(i).equals(vertLabel2)){
                    distance = 1;
                    return distance;
                }
                else if(distance == 0){
                    check = shortestPathDistanceRecursive(neighbours.get(i), vertLabel2, checkedNeighbours); 
                }
            }
            if (i == 0){
                distance = check;
            }
            if (check < distance && check > 0){
                distance = check;
            }
        }
        if (distance == disconnectedDist){
            return disconnectedDist;
        }
        else{
            distance ++;
        return distance;
        }
    } // end of shortestPathDistance()




    public boolean attachEdge(Node vertex, Node edge) {
        Node checkNode = vertex.getNextEdge();

        //Checks if edge already exists.
        while(checkNode != null){
            if ((checkNode.getValue()).equals(edge.getValue())){
                System.out.println(">Edge already exists.");
                return false;
            }
            checkNode = checkNode.getNextEdge();
        }

        //Check if list is empty.
        if (vertex.getNextEdge() == null) {
            vertex.setNextEdge(edge);
        }
        // otherwise, add edge to the head of list.
        else {
            edge.setNextEdge(vertex.getNextEdge());
            vertex.setNextEdge(edge); 
        }
        return true;
        
    } // end of attachEdge()




    private class Node
    {
        /** Stored value of node*/
        protected T mValue;
        /** Reference to next Vertex node*/
        protected Node mNextVertex;
        /** Reference to next edge*/
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