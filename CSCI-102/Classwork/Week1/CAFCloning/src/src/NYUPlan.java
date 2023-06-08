package src;

public class NYUPlan implements Cloneable{
	
public String major;
public int gradyear;

public NYUPlan clone( ) throws CloneNotSupportedException {
	return (NYUPlan) super.clone();
}

}
