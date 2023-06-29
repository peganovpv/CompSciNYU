public class App {

    public static void main(String[] args) {
        TreeNode<Integer> left = new TreeNode<Integer>(3, null, null);
        TreeNode<Integer> right = new TreeNode<Integer>(7, null, null);
        TreeNode<Integer> root = new TreeNode<Integer>(5, left, right);
        root.traverse(root, 0);
    }
    
}
