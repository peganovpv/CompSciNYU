public class Main {

    public static void main(String[] args) {

        DoublyLinkedList<Character> dll = new DoublyLinkedList<Character>();

        dll.Root('A');
		dll.Add('B', "South");
		dll.Add('C', "East");
		dll.Add('D', "West");
		dll.Add('E', "North");
        
        dll.Print();
        dll.PrintLewisStructure();
        
    }
    
}
