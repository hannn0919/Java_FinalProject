package House.house;
import java.util.ArrayList;
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

    public House()
    {
        this.holdMoney = 479487;
        this.level = 1;
        this.role = "鮮嫩小心肝";
        this.exp = 0;
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
        exp = num;
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

    //edit the holding money.If success, return true, or return false
    public boolean editHoldMoney(int cost)
    {
        if(holdMoney + cost>=0) {
            holdMoney += cost;
        }
        else{
            System.out.println("肝，窮逼八還敢買");
        }
        return true;
    }
    //edit the holding money.If success, return true, or return false
    public boolean editExp(int cost)
    {
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
            System.out.println("使用道具:"+element);
            map.put(element,map.get(element)-1);
            return map.get(element);
        }
        else return 0;
    }
    //get the equipment
    public int getEquipment(String element){
        if(map.containsKey(element))
        {
            System.out.println("使用裝備:"+element);
            map.put(element,map.get(element)-1);
            return map.get(element);
        }
        return 0;
    }
}