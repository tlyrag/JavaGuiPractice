
public class Node {
	int index;
	Node[] next;
	Node(int index,int nextSize) {
		Node[] temp = new Node[nextSize];
		next = temp;
		this.index= index;
		
	}
}
