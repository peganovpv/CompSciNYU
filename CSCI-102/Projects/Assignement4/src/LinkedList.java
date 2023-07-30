import java.util.*;
public  class LinkedList {

    static class node {

        char value;
        node north,east,west,south;

        public node(char v) {
            this.value = v;
            this.north = null;
            this.west = null;
            this.south = null;
            this.east = null;
        }
    }

    node root;

    node current;

    public void Root(char l) {
        this.root = new node(l);
        this.current = this.root;
    }

    public void Add(char l, String d) {
        if(d.equals("North")) {
            this.current.north = new node(l);
            this.current.north.south = this.current;
            this.current = this.current.north;
        } else if(d.equals("East")) {
            this.current.east = new node(l);
            this.current.east.west = this.current;
            this.current = this.current.east;
        } else if(d.equals("West")) {
            this.current.west = new node(l);
            this.current.west.east = this.current;
            this.current = this.current.west;
        } else if(d.equals("South")) {
            this.current.south = new node(l);
            this.current.south.north = this.current;
            this.current = this.current.south;
        }
    }

    public void Move(String d) {
        if(d.equals("North")) {
            this.current = this.current.north;
        }    else if(d.equals("East")) {
            this.current = this.current.east;
        } else if(d.equals("West")) {
            this.current = this.current.west;
        } else if(d.equals("South")) {
            this.current = this.current.south;
        }
    }

    public void Print() {

        Set<node> list = new HashSet<>();
        this.p(this.root, list);
        Iterator i = list.iterator();

        while(i.hasNext()) {
            System.out.print(((node)i.next()).value + " ");
        }
    }

    public void p(node cur, Set<node> list) {
        if(cur == null) {
            return;
        }
        list.add(cur);
        if(!list.contains(cur.east)) {
            p(cur.east, list);
        }
        if(!list.contains(cur.south)) {
            p(cur.south, list);
        }
        if(!list.contains(cur.west)) {
            p(cur.west, list);
        }
        if(!list.contains(cur.north)) {
            p(cur.north, list);
        }
    }

}