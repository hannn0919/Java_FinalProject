package  House.house;
public class HouseTest {
    public static void main(String[] args){
        House house = new House();

        System.out.println("Role:"+house.getRole());
        System.out.println("Level:"+house.getLevel());
        System.out.println("Exp:"+house.getExp());
        System.out.println("Money:"+house.getHoldMoney());
        System.out.println("Item:"+house.getItem("時間加倍券"));
        System.out.println("Equipment:"+house.getEquipment("翅膀"));

        System.out.println();

        house.editExp(7000);
        house.editHoldMoney(3000);
        System.out.println("從商店中購買了裝備:翅膀!");
        house.editHoldMoney(-10000);
        house.setEquipment("翅膀");
        System.out.println("從商店中購買了道具:時間加倍券!");
        house.editHoldMoney(-52000);
        house.setItem("時間加倍券");
        house.editHoldMoney(-51111111);

        System.out.println();

        System.out.println("Role:"+house.getRole());
        System.out.println("Level:"+house.getLevel());
        System.out.println("Exp:"+house.getExp());
        System.out.println("Money:"+house.getHoldMoney());
        //System.out.println("Item:"+house.getItem("時間加倍券"));
        //System.out.println("Equipment:"+house.getEquipment("翅膀"));

    }
}
