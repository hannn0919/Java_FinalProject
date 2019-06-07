package House.house;
//import Stock.main.StockWindow;

import java.util.HashMap;
import java.util.Map;

public class House {
    //private ArrayList<Items> itemsList = new ArrayList<Items>();
    //private ArrayList<Equipment> equipmentList = new ArrayList<Equipment>();
    private String role;
    private static int level;
    private static int exp;
    private static int holdMoney;
    private Map<String,Integer> map = new HashMap<String, Integer>();
    private float[] stock;
    private float[] stockPrs;
    private int[] stockTicket;
    private double[][][] data;

    public House()
    {
        this.holdMoney = 479487;
        this.level = 4;
        this.role = "鮮嫩小心肝";
        this.exp = 0;
        this.stock = new float[4];
        this.stockTicket = new int[4];
        this.stockPrs = new float[4];
        this.data = new double[4][1][10];
        for (int i = 0;i<4;i++) {
            stock[i] = (float) (10 * (i+1) );
            stockTicket[i] = 0;
            data[i][0][0]= stock[i];
            /*for(int j = 1;j<9;j++){
                data[i][0][j] = data[i][0][j-1] * (1F + StockWindow.getNextPercent() );
            }
            stockPrs[i] = StockWindow.getNextPercent();*/
            data[i][0][9] = data[i][0][8] * ( 1F + stockPrs[i]);
            stock[i] = (float) data[i][0][9];
        }


    }

    public House(Map<String,Integer> map)
    {
        //角色名稱
        setRole(level);
        //等級
        setLevel(level);
        //經驗值
        setExp(exp);
        //擁有金額
        setHoldMoney(holdMoney);
    }
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
        exp=num;
        if(1000<num&&num<=2000){
            level=2;
        } else if(num<=6000){
            level=3;
        }else if(num<10000){
            level=4;
        }else if(num>=10000) {
            exp=10000;
        }
        setLevel(level);
        setRole(level);
    }
    //set the number of the money
    public void setHoldMoney(int num)
    {
        holdMoney = num;
    }

    //set the Items
    public void setItem(String element)
    {
        if(map.containsKey(element)) {
            int numElem = map.get(element);
            map.put(element, (numElem+1));
        }
        else {
            map.put(element, 1);
        }
    }

    //set the Equipment
    public void setEquipment(String element)
    {
        map.put(element, 1);
    }

    //get the holding money
    public int getHoldMoney()
    {
        return holdMoney;
    }

    //get the level
    public int getLevel()
    {
        return level;
    }

    //get the exp
    public int getExp()
    {
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

    //use the Equipment
    public void dontUseEquipment(String element){
        if(map.containsKey(element)) {
            map.put(element,2);
        }
    }

    //get stock
    public float[] getStock()
    {
        return stock;
    }

    //set stock
    public void setStock(int i, float f)
    {
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
    public void setStockPrs(int i, float f)
    {
        stockPrs[i] = f;
    }

    //get stock
    public float[] getStockPrs()
    {
        return stockPrs;
    }

    public double[][][] getData(){
        return data;
    }

    public void updataData(){
        for(int i = 0;i<4;i++){
            for(int j = 0;j<9;j++){
                data[i][0][j] = data[i][0][j+1];
            }
            data[i][0][9] = getStock()[i];
        }
    }
}