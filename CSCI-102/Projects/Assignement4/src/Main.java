public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello World");
        LinkedList list = new LinkedList();
        list.Root('N');
        list.Add('C', "East");
        list.Add('A', "South");
        list.Move("North");
        list.Move("West");
        list.Add('K', "South");
        list.Add('M', "West");
        list.Print();
    }
}
