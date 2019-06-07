package House.house;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class House {
    //private ArrayList<Items> itemsList = new ArrayList<Items>();
    //private ArrayList<Equipment> equipmentList = new ArrayList<Equipment>();
    private String role;
    private static int level;
    private static int exp;
    private static int holdMoney;
    private Map<String,Integer> map = new HashMap<String, Integer>();
    private float[] stock;
    private float[] stockLast;
    private float[] stockPrs;
    private int[] stockTicket;

    public House() {
        this.holdMoney = 479487;
        this.level = 4;
        this.role = "鮮嫩小心肝";
        this.exp = 0;
        this.stock = new float[4];
        this.stockTicket = new int[4];
        this.stockLast = new float[4];
        this.stockPrs = new float[4];
        for (int i = 0;i<4;i++) {
            stock[i] = (float) (10 * (i+1) );
            stockTicket[i] = 0;
        }
    }

    /*public House(Map<String,Integer> map) {
        //角色名稱
        setRole(level);
        //等級
        setLevel(level);
        //經驗值
        setExp(exp);
        //擁有金額
        setHoldMoney(holdMoney);
    }*/

    //set the role
    public void setRole(int num){
        if (num == 1) role = "鮮嫩小心肝";
        else if(num == 2) role="尚未硬化的肝";
        else if(num == 3) role="已經快不行了的肝";
        else if(num == 4) role="小心肝硬化";
    }

    //set the number of the level
    public void setLevel(int num)
    {
        level = num;
    }

    //set the number of the exp
    public void setExp(int num)
    {
        exp = num;
    }

    //set the number of the money
    public void setHoldMoney(int num)
    {
        holdMoney = num;
    }

    //set the Items
    public void setItem(String element) {
        if(map.containsKey(element)) {
            int numElem = map.get(element);
            map.put(element, (numElem+1));
        }
        else {
            map.put(element, 1);
        }
    }

    //set the Equipment
    public void setEquipment(String element) {
        if( map.containsKey(element))  {
                JOptionPane.showMessageDialog(null, "肝，北七?你已經有了");
        }
        else {
            map.put(element, 1);
        }
    }

    //edit the holding money.If success, return true, or return false
    public boolean editHoldMoney(int cost) {
        if(holdMoney + cost>=0) {
            holdMoney += cost;
        }
        else{
            JOptionPane.showMessageDialog(null, "肝，窮逼八還敢買");
        }
        return true;
    }

    //edit the holding money.If success, return true, or return false
    public boolean editExp(int cost) {
        exp += cost;
        while(exp>=3000){
            exp-=3000;
            level++;
            setLevel(level);
            setRole(level);
        }
        return true;
    }

    //get the holding money
    public int getHoldMoney() {
        return holdMoney;
    }

    //get the level
    public int getLevel() {
        return level;
    }

    //get the exp
    public int getExp() {
        return exp;
    }

    //get the role
    public String getRole(){
        return role;
    }

    //get the item
    public int getItem(String element){
        if(map.containsKey(element))
        {
            return map.get(element);
        }
        else return 0;
    }

    //get the Equipment
    public int getEquipment(String element){
        if(map.containsKey(element))
        {
            return map.get(element);
        }
        return 0;
    }

    //use the Item
    public int useItem(String element){
        if(map.containsKey(element))
        {
            map.put(element,map.get(element)-1);
            return map.get(element);
        }
        else return 0;
    }

    //use the Equipment
    public int dontUseEquipment(String element){
        if(map.containsKey(element))
        {
            map.put(element,2);
            return map.get(element);
        }
        return 0;
    }

    //get stock
    public float[] getStock() {
        return stock;
    }

    //set stock
    public void setStock(int i, float f) {
        stock[i] = f;
    }

    //get ticket
    public int[] getStockTicket(){
        return stockTicket;
    }

    // set ticket
    public void setStockTicket( int i, int n){
        stockTicket[i] = n;
    }

    //set stock
    public void setStockLast(int i, float f)
    {
        stockLast[i] = f;
    }

    //get stock
    public float[] getStockLast()
    {
        return stockLast;
    }

    //set stock
    public void setStockPrs(int i, float f) {
        stockPrs[i] = f;
    }

    //get stock
    public float[] getStockPrs() {
        return stockPrs;
    }
}