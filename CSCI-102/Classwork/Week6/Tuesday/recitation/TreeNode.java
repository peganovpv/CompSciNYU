public class TreeNode<T> {
	// class modified for binary search tree

	private T value = null;

	private TreeNode<T> LeftChild = null;
	private TreeNode<T> RightChild = null;

	private int childCount = 0;

	TreeNode(T value, TreeNode<T> left, TreeNode<T> right) {
		this.value = value;
		this.LeftChild = left;
		this.RightChild = right;
		if(left != null) {
			this.childCount++;
		}
		if(right != null) {
			this.childCount++;
		}
	}

	// add element to binary search tree
	public void add(T value) {
		if (this.value == null) {
			this.value = value;
		} else {
			if ((int) value < (int) this.value) {
				if (this.LeftChild == null) {
					this.LeftChild = new TreeNode<T>(value, null, null);
					this.childCount++;
				} else {
					this.LeftChild.add(value);
				}
			} else {
				if (this.RightChild == null) {
					this.RightChild = new TreeNode<T>(value, null, null);
					this.childCount++;
				} else {
					this.RightChild.add(value);
				}
			}
		}
	}

	// traverse the binary search tree
	public void traverse(TreeNode<T> obj, int level) {
		if (obj != null) {
			traverse(obj.LeftChild, level + 1);
			System.out.println(obj.value);
			traverse(obj.RightChild, level + 1);
		}
	}
	 
	void printTree(TreeNode<T> obj) {
		traverse(obj,0);
		System.out.println(obj.value);
	}

	// return the height of the binary search tree
	public int height(TreeNode<T> obj) {
		if (obj == null) {
			return 0;
		} else {
			int leftHeight = height(obj.LeftChild);
			int rightHeight = height(obj.RightChild);

			if (leftHeight > rightHeight) {
				return (leftHeight + 1);
			} else {
				return (rightHeight + 1);
			}
		}
	}
	
}
