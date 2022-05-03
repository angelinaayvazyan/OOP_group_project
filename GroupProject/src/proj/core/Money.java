public class Money {
    private int initialMoney;

    public Money(){
        this.initialMoney = 1500;
    }

    public int getInitialMoney(){
        return this.initialMoney;
    }

    public void setInitialMoney(int initialMoney){
        if(initialMoney >= 0)
        this.initialMoney = initialMoney;
    }

    public void addMoney(int newMoney){
        this.initialMoney += newMoney;
    }
    public void subtractMoney (int newMoney) {
        this.initialMoney -= newMoney;
    }

    public boolean playerLost(){
        if(this.initialMoney < 0) return false;
        return true;
    }

}
