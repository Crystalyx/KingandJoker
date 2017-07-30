package Game.Entities.Modifiers;

public class Modifier
{
	public ModifierBase base;
	public String uuid;
	public boolean reqInHand = false;
	public boolean reqEquip = false;
	public boolean reqClear = false;
	/**
	 * 0 is set; 1 is add; 2 is percent add; 3 is modify
	 */
	public int operation;
	public double value;

	public Modifier(ModifierBase base, String uuid, double dvalue, int operation)
	{
		this.base = base;
		this.uuid = uuid;
		this.value = dvalue;
		this.operation = operation;
	}
	
	public void setReqEquip(boolean reqEquip)
	{
		this.reqEquip = reqEquip;
	}
	public void setReqInHand(boolean reqInHand)
	{
		this.reqInHand = reqInHand;
	}
	public void setReqClear(boolean reqClear)
	{
		this.reqClear = reqClear;
	}
}
