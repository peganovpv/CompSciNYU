public class Driver {
    public static void main(String[] args) {

        Driver d = new Driver();
        TreeNode root = d.makeBinaryTreeFirstRow();
        d.removeElements(root);
        TreeNode root2 = d.makeBinaryTreeSecondRow();
        d.removeElements2(root2);
        TreeNode root3 = d.makeBinaryAlphabetTree();
        System.out.println(root3.GetHeight());
        d.removeVowels(root3);
        System.out.println(root3.GetHeight());

    }

    // Question 1) Add q,w,e,r,t,y,u,i,o,p to the tree
    public TreeNode makeBinaryTreeFirstRow(){

        TreeNode root = new TreeNode(0, null, null, null);
        root.setRoot('Q');

        char[] letters = {
            'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'
        };

        for (int i = 0; i < letters.length; i++) {
            root.AddElement(letters[i]);
        }

        return root;

    }

    // Question 2) Remove U, E, Q in that order
    public void removeElements(TreeNode root){

        root.RemoveElement('U');
        root.RemoveElement('E');
        root.RemoveElement('Q');

    }

    // Question 3) Make a binary tree starting on the keyboard middle and do the first 9 elements in middle row. So the order of element are A..S..D  ..F…….L.  Show the results of the binary tree in an array form.
    public TreeNode makeBinaryTreeSecondRow(){

        TreeNode root = new TreeNode('S', null, null, null);
        
        char[] letters = {
            'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L'
        };

        for (int i = 0; i < letters.length; i++) {
            root.AddElement(letters[i]);
        }

        return root;

    }

    // Question 4) Remove F, S, A in that order
    public void removeElements2(TreeNode root){

        root.RemoveElement('F');
        root.RemoveElement('S');
        root.RemoveElement('A');

    }

    // Question 6) Put entire alphabet in a binary tree and show the results in an array form.
    public TreeNode makeBinaryAlphabetTree(){

        TreeNode root = new TreeNode('A', null, null, null);

        char[] letters = {
            'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
            'L', 'M', 'N','O', 'P', 'Q', 'R', 'S', 'T', 'U',
            'V', 'W', 'X','Y', 'Z'
        };
        
        for(int i = 0; i < letters.length; i++){
            root.AddElement(letters[i]);
        }

        return root;

    }

    public void removeVowels(TreeNode root){
            
            root.RemoveElement('A');
            root.RemoveElement('E');
            root.RemoveElement('I');
            root.RemoveElement('O');
            root.RemoveElement('U');
    }

}
