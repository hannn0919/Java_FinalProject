package HitMouse.gameobject;
public class Score {
    private int score=0,last=0,djs=60,sd=1500;//sd->地鼠出現速度，last->上一隻地鼠出現位置，djs倒數計時
    private int addScore=1,minusScore=1;
    public int getAddScore(){
        return addScore;
    }
    public void setAddScore(int addScore){
        this.addScore=addScore;
    }
    public int getMinusScore(){
        return minusScore;
    }
    public void setMinusScore(int minusScore){
        this.minusScore=minusScore;
    }


    public int getSd() {
        return sd;
    }
    public void setSd(int sd) {
        this.sd = sd;
    }
    private long starttime=0;
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        if(score>=0)
             this.score = score;
    }
    public int getLast() {
        return last;
    }
    public void setLast(int last) {
        this.last = last;
    }
    public int getDjs() {
        return djs;
    }
    public void setDjs(int djs) {
        this.djs = djs;
    }
    public long getStarttime() {
        return starttime;
    }
    public void setStarttime(long starttime) {
        this.starttime = starttime;
    }
}
