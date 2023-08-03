
public class Map {
	Node head = new Node(-1,1);
	Node curr;
	Node[] map;
	
	Map (int mapSize) {
		generateMap(mapSize);
		addNextLists(mapSize);
		displayNodeList();
	}
	
	public void generateMap(int mapSize) {
		Node[] tempMap = new Node[mapSize];
		for(int i =0; i<mapSize;i++) {
			int random = generateRandom(mapSize)/5;
			if (random ==0) {
				random =1;
			}
			Node nextNode = new Node(i,random);
			tempMap[i]= nextNode;
		}
		this.map=tempMap;
	}
	
	public void addNextLists(int mapSize) {
		for(int i=0; i<map.length;i++) {
			for(int j=0;j<map[i].next.length;j++) {
				int random = generateRandom (mapSize);
				map[i].next[j] = map[random];
			}
		}
	}
	
	public int generateRandom(int mapSize) {
		int randomNext = (int) (Math.random()*mapSize);
		return randomNext;
	}
	
	public String displayNodeList() {
		StringBuilder newString = new StringBuilder();
		for(int i=0; i<map.length;i++) {		
			newString.append("Position: "+map[i].index+" Can move to: ");
			System.out.print("Position: "+map[i].index+" Can move to: ");
			for(int j=0;j<map[i].next.length;j++) {
				System.out.print(map[i].next[j].index+",");
				newString.append(map[i].next[j].index+",");
			}
			newString.append("\n");
			System.out.println();
		}
		return newString.toString();
	}

	public String displayNodeList(Node node) {
		StringBuilder newString = new StringBuilder();
		
			newString.append("Position: "+node.index+" Can move to: ");
			for(int j=0;j<node.next.length;j++) {
				System.out.print(node.next[j].index+",");
				newString.append(node.next[j].index+",");
			}
			newString.append("\n");
			System.out.println();

		return newString.toString();
	}
}

