public class SampleExam{

    public class TreeNode {
        public  int value = null;
        public  TreeNode[] children = new TreeNode[100];
        public  int childCount = 0; // actual child count in the children array
        TreeNode(int value) {
            this.value = value;
        }
    }

    public static boolean ProperCheck(TreeNode node){

        // Check if a tree is proper
        // A proper tree is a tree when each child has the same number of children as its parent, unless the child is a leaf node

        // Base Case
        if(node == null){
            return true;
        }

        // Recursive Case
        for(int i = 0; i < node.childCount; i++){
            if(node.children[i].childCount != 0 && node.children[i].childCount != node.childCount){
                return false;
            }
            if(!ProperCheck(node.children[i])){
                return false;
            }
        }

        return true;

    }

    public static boolean proper_check(TreeNode node) {
        // base case: if node is a leaf
        if (node.childCount == 0) return true;

        // for non-leaf nodes: check if the child nodes have the same child count as the parent
        for (int i = 0; i < node.childCount; i++) {
            if (node.children[i].childCount != 0 && node.children[i].childCount != node.childCount) {
                return false; // if any of the non-leaf child nodes does not have the same child count as the parent, return false
            }
            
            // recursively check the child nodes
            if (!proper_check(node.children[i])) return false;
        }

        // if all child nodes satisfy the conditions, return true
        return true;
    }

    public static void main(String[] args){

        SampleExam se = new SampleExam();

        TreeNode root = se.new TreeNode(1);
        TreeNode child1 = se.new TreeNode(2);
        TreeNode child2 = se.new TreeNode(3);
        TreeNode child3 = se.new TreeNode(4);
        TreeNode child4 = se.new TreeNode(5);
        TreeNode child5 = se.new TreeNode(6);
        TreeNode child6 = se.new TreeNode(7);

        root.children[0] = child1;
        root.children[1] = child2;
        root.children[2] = child3;
        root.children[3] = child4;
        root.children[4] = child5;
        root.children[5] = child6;

        System.out.println("Proper Check: ");
        System.out.println(ProperCheck(root));
        System.out.println(proper_check(root));

    }

}