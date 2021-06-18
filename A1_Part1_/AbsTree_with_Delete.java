// Assignment 1 Part 1: Starter Code
abstract class AbsTree {
	public AbsTree(int n) {
		value = n;
		left = null;
		right = null;
	}

	public void insert(int n) 
	{
		if (value == n)
			count_duplicates();
		else if (value < n)
			if (right == null) 
			{
				right = add_node(n);
				right.parent = this;
			} else
				right.insert(n);
		else if (left == null) 
		{
			left = add_node(n);
			left.parent = this ;
		} else
			left.insert(n);
	}

	public void delete(int n)
	{  
		AbsTree t = find(n);

		if (t == null) 
		{ // n is not in the tree
			System.out.println("Unable to delete " + n + " -- not in the tree!");
			return;
		}

		int c = t.get_count();
		if (c > 1) {
			t.set_count(c-1);
			return;
		}

		if (t.left == null && t.right == null) { // n is a leaf value
			if (t != this)
				case1(t);
			else
				System.out.println("Unable to delete " + n + " -- tree will become empty!");
			return;
		}
		if (t.left == null || t.right == null) { // t has one subtree only
			if (t != this) { // check whether t is the root of the tree
				case2(t);
				return;
			} else {
				if (t.right == null)
					case3L(t);
				else
					case3R(t);
				return;
			}
		}
		// t has two subtrees; go with smallest in right subtree of t
		case3R(t);
	}

	protected void case1(AbsTree t) 
	{ 
		//System.out.println("case 1");
		// remove the leaf
		// to be filled by you
		//System.out.println("inside case 1 : "+t.value);
		if(t.value >t.parent.value)
		{
			//delete the parent child relation by removing the left / right child connection and also the parent node connection from the current / calling node  
			t.parent.right = null;
			t.parent = null;
		}
		else
		{
			//delete the parent child relation by removing the left / right child connection and also the parent node connection from the current / calling node  
			t.parent.left =  null;
			t.parent = null;
		}
			
	}

	protected void case2(AbsTree t) 
	{ // remove internal node
		// to be filled by you
		//System.out.println("case 2");
		if(t.parent.right!=null)
			{
				if(t.left!=null)
					{
						t.parent.right =t.left;
						t.left.parent = t.parent;
						t.left =t.parent= null;
					}
				else
					if(t.right!=null)
					{
						t.parent.right =t.right;
						t.right.parent = t.parent;
						t.parent =t.right= null;
					}
			}
		else
			if(t.parent.left!=null)
			{
				if(t.right!=null)
				{
					t.parent.left =t.right;
					t.right.parent = t.parent;
					t.parent=t.right = null;	
				}
			else
				if(t.left!=null)
				{
					t.parent.left =t.left;
					t.left.parent = t.parent;
					t.parent = t.left = null;	
			    }
			}
	}
	protected void case3L(AbsTree t)
	{ 
		//System.out.println("case 3l");
		AbsTree max_node =  t.left.max();
		//System.out.println("The right side minimum value is"+max_node.value);
		int max_node_count =  max_node.get_count();
		int t_node_value = t.value;
		t.set_count(max_node_count);
		max_node.set_count(1);
		
		t.value = max_node.value;
		
		if(max_node.left==null&&max_node.right==null)
		{
			if(max_node.parent.left ==max_node)
			{
				max_node.parent.left =null;
			}
			else
			{
				max_node.parent.right =null;
			}
			//System.out.println("inside case max_node.left==null&&max_node.right==null");
			max_node.value = t_node_value ;
			max_node.parent=null;
		}
		else
			if(max_node.left!=null)
			{
				if(max_node.parent.right == max_node)
					max_node.parent.right = max_node.left;
					else
						if(max_node.parent.left==max_node)
							max_node.parent.left= max_node.left;
				max_node.left.parent=max_node.parent ;
				max_node.value = t_node_value;
				max_node.parent =max_node.left = null;
			}
	}

	protected void case3R(AbsTree t)
	{ 
		//System.out.println("case 3r");
		AbsTree min_node =  t.right.min();
		//System.out.println("The right side minimum value is"+min_node.value);
		int min_node_count =  min_node.get_count();
		int t_node_value = t.value;
		t.set_count(min_node_count);
		min_node.set_count(1);
		
		t.value = min_node.value;
		if(min_node.left==null&&min_node.right==null)
		{
			if(min_node.parent.left ==min_node)
			{
				min_node.parent.left =null;
			}
			else
			{
				min_node.parent.right =null;
			}
			
			min_node.value =  t_node_value;
			min_node.parent=null;
		}
		else 
			if(min_node.right!=null)
			{
				if(min_node.parent.right == min_node)
				min_node.parent.right = min_node.right;
				else
					if(min_node.parent.left==min_node)
						min_node.parent.left= min_node.right;
				min_node.right.parent=min_node.parent ;
				min_node.value = t_node_value;
				min_node.parent =min_node.right = null;
			}
	}		
	
	private AbsTree find(int n) {
        if(value == n){
            return this;
        }
        else if(n>value){
            if(this.right!=null) {
                return this.right.find(n);
            }
            else{
                return null;
            }
        }
        else{
            if(this.left!=null){
                return this.left.find(n);
            }
            else{
                return null;
            }
        }
    }

	public AbsTree min() {
		// to be filled by you
		if (left == null) 
			return this;
		else 
		{
			//this.parent = this ;
			return left.min();	
		}		
	}

	public AbsTree max() {
		// to be filled by you
		if(right == null) 
			return this ;
		else
		{
			//parent = this ;
			return right.max();
		}
	}

	protected int value;
	protected AbsTree left;
	protected AbsTree right;
	protected AbsTree parent;

	protected abstract AbsTree add_node(int n);
	protected abstract void count_duplicates();
	protected abstract int get_count();
	protected abstract void set_count(int v);
}

class Tree extends AbsTree {
	public Tree(int n) {
		super(n);
	}

	protected AbsTree add_node(int n) {
		return new Tree(n);
	}

	protected void count_duplicates() {
		;
	}

	protected int get_count() {
		// to be filled by you
		return 1 ;
	}

	protected void set_count(int v) {
		// to be filled by you
		//this.count = 1;
	}
}

class DupTree extends AbsTree {
	public DupTree(int n) {
		super(n);
		count = 1;
	};

	protected AbsTree add_node(int n) {
		return new DupTree(n);
	}

	protected void count_duplicates() {
		count++;
	}

	protected int get_count() {
		// to be filled by you
		return count ;
	}

	protected void set_count(int v) {
		// to be filled by you
		count = v;
	}

	protected int count;
}
class Tree_Test {

	public static void main(String[] args) {
		AbsTree tr = new Tree(100);
		tr.insert(50);
		tr.insert(125);
		tr.insert(150);
		tr.insert(20);
		tr.insert(75);
		tr.insert(20);
		tr.insert(90);
		tr.insert(50);
		tr.insert(125);
		tr.insert(150);
		tr.insert(75);
		tr.insert(90);
		
		tr.delete(20);
		tr.delete(20);
		tr.delete(20);
		tr.delete(150);
		tr.delete(100);
		tr.delete(150);
		tr.delete(125);
		tr.delete(125);
		tr.delete(50);
		tr.delete(50);
		tr.delete(50);
		tr.delete(75);
		tr.delete(90);
		tr.delete(75);
		tr.delete(90);

}
}
class DupTree_Test {

	public static void main(String[] args) {
		AbsTree tr = new DupTree(100);
		tr.insert(50);
		tr.insert(125);
		tr.insert(150);
		tr.insert(20);
		tr.insert(75);
		tr.insert(20);
		tr.insert(90);
		tr.insert(50);
		tr.insert(125);
		tr.insert(150);
		tr.insert(75);
		tr.insert(90);
		
		tr.delete(20);
		tr.delete(20);
		tr.delete(20);
		tr.delete(150);
		tr.delete(100);
		tr.delete(150);
		tr.delete(125);
		tr.delete(125);
		tr.delete(50);
		tr.delete(50);
		tr.delete(50);
		tr.delete(75);
		tr.delete(90);
		tr.delete(75);
		tr.delete(90);
	}
}
