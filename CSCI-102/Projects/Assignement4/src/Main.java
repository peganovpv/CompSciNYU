public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello World");
        LinkedList l = new LinkedList();
        l.Root('N');
        l.Add('C', "East");
        l.Add('A', "South");
        l.Move("North");
        l.Move("West");
        l.Add('K', "South");
        l.Add('M', "West");
        l.Print();
        }
}
