public class TreeNode  {
    private int value = 0;
    public TreeNode leftChild, rightChild, parent;
    TreeNode(int value, TreeNode inleft, TreeNode inright, TreeNode inparent) {
        this.value = value;
        this.leftChild=inleft;
        this.rightChild =inright;
        this.parent=inparent;
    }
    public TreeNode setRoot(int  element) {
    	TreeNode  root;
    	root = new TreeNode(element, null, null,null) ;
    	return root;
    }
    public void AddElement(int element){
        TreeNode  node = new TreeNode(element,null,null,null);
        
        if( element>(int)value){
            if(rightChild==null)
            {
                rightChild=node;
                return;
            }
            rightChild.AddElement(element);
        }
        else{
            if(leftChild==null)
            {
                leftChild=node;
                return;
            }
            leftChild.AddElement(element);
        }
    }

    

    public int GetHeight(){
    	int leftHeight=0;
    	int rightHeight = 0;
        if(rightChild==null && leftChild==null)
            return 0;
        if (leftChild != null) {leftHeight = leftChild.GetHeight();}
        if (rightChild != null) {rightHeight = rightChild.GetHeight();}
        if (leftHeight > rightHeight) {
        	 return 1  + leftHeight;
        } else {
        return 1  + rightHeight;
        }
    }

    public void RemoveElement(int element){
        if(element==(int)value){
            if(leftChild==null && rightChild==null){
                if(parent.leftChild==this)
                    parent.leftChild=null;
                else
                    parent.rightChild=null;
            }
            else if(leftChild==null){
                if(parent.leftChild==this)
                    parent.leftChild=rightChild;
                else
                    parent.rightChild=rightChild;
            }
            else if(rightChild==null){
                if(parent.leftChild==this)
                    parent.leftChild=leftChild;
                else
                    parent.rightChild=leftChild;
            }
            else{
                TreeNode  temp=rightChild;
                while(temp.leftChild!=null)
                    temp=temp.leftChild;
                value=temp.value;
                temp.RemoveElement(temp.value);
            }
        }
        else if(element>(int)value){
            if(rightChild!=null)
                rightChild.RemoveElement(element);
        }
        else{
            if(leftChild!=null)
                leftChild.RemoveElement(element);
        }
    }

    public void TreeToArray(){

        // print out the tree in array form

        

    }

   
}
