package HitMouse.gameobject;
public class Score {
    private int score=0,last=0,djs=60,sd=3000;//sd->地鼠出現速度，last->上一隻地鼠出現位置，djs倒數計時
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
