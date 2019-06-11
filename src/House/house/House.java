package House.house;

import Stock.main.StockWindow;
import java.util.HashMap;
import java.util.Map;

public class House {
    private String role;
    private static int level;
    private static int exp;
    private static int holdMoney;
    private Map<String,Integer> map = new HashMap<String, Integer>();
    private Map<String,Integer> map2 = new HashMap<String, Integer>();
    private float[] stock;
    private float[] stockPrs;
    private int[] stockTicket;
    private double[][][] data;
    private int cardTotalMoney;
    private int cardTotalExp;
    private int cardTotalPair;
    private int cardTotalPlay;
    private int hamsterTotalMoney;
    private int hamsterTotalExp;
    private int hamsterTotalMistake;
    private int hamsterTotalPlay;
    private int froggerTotalMoney;
    private int froggerTotalExp;
    private int froggerTotalDied;
    private int froggerTotalPlay;
    private int dinosaurTotalMoney;
    private int dinosaurTotalExp;
    private int dinosaurTotalDied;
    private int dinosaurTotalPlay;
    public House()
    {
        this.holdMoney = 1000;
        this.level = 1;
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
            for(int j = 1;j<9;j++){
                data[i][0][j] = data[i][0][j-1] * (1F + StockWindow.getNextPercent() );
            }
            stockPrs[i] = StockWindow.getNextPercent();
            data[i][0][9] = data[i][0][8] * ( 1F + stockPrs[i]);
            stock[i] = (float) data[i][0][9];
        }
        map.put("經驗加倍券",0);
        map.put("金錢加倍券",0);
        map.put("電蚊拍",0);
        map.put("增時卡",0);
        map.put("地下道鑰匙",0);
        map.put("警察卡",0);
        map.put("老師卡",0);
        map.put("加倍卡",0);
        map.put("再看一次",0);

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
        if(num>=1000&&num<3000){
            level=2;
        } else if(num>=3000&&num<6000){
            level=3;
        }else if(num>=6000&&num<10000){
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
    public void setItem(String element,int x)
    {
        map.put(element, x);
    }

    //set the Equipment
    public void setEquipment(String element)
    {
        map.put(element, 2);
    }

    public void gameSettlementSomething(int game,int money,int EXP){
        if(game==1){
            cardTotalMoney+=money;
            cardTotalExp+=EXP;
        }else if(game==2){
            hamsterTotalMoney+=money;
            hamsterTotalExp+=EXP;
        }else if(game==3){
            froggerTotalMoney+=money;
            froggerTotalExp+=EXP;
        }else if(game==4){
            dinosaurTotalMoney+=money;
            dinosaurTotalExp+=EXP;
        }
    }

    public void gameSettlementItem(String element){
        if(map2.containsKey(element)) {
            int num = map2.get(element);
            map2.put(element, num + 1);
        }
        else
            map2.put(element,1);
    }

    public void gameSettlementmistake(int game,int time){
        if(game==1){
            cardTotalPair+=time;
            cardTotalPlay++;
        }else if(game==2){
            hamsterTotalMistake+=time;
            hamsterTotalPlay++;
        }else if(game==3){
            froggerTotalDied+=time;
            froggerTotalPlay++;
        }else if(game==4){
            dinosaurTotalDied+=time;
            dinosaurTotalPlay++;
        }
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
    public void useEquipment(String element){
            map.put(element,1);
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