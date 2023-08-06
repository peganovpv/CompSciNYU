public class Main {
    public static void main(String[] args) {

        DoublyLinkedList<Character> dll = new DoublyLinkedList<Character>();

        dll.Root('A');
        
        dll.Add('B', "East");
        
        dll.Move("East");
        
        dll.Add('C', "South");

        dll.Move("South");

        dll.Add('D', "West");

        dll.Move("West");
        
        dll.PrintLewisStructure();
        
    }
}
