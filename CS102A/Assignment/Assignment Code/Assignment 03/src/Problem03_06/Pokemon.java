package Problem03_06;

public class Pokemon {
    private String name;
    private int hp;
    private int atk;
    private int level;
    private int speed;
    private int rateAtk;
    private int rateHp;
    Skill skill;

    public Pokemon(String name, int hp, int atk, Skill skill, int level, int speed, int rateAtk, int rateHp) {
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.level = level;
        this.speed = speed;
        this.rateAtk = rateAtk;
        this.rateHp = rateHp;
        this.skill = skill;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHp() {
        return hp;
    }

    public int getAtk() {
        return atk;
    }

    public int getSpeed() {
        return speed;
    }

    public void levelUP(int up){
        this.atk += up * rateAtk;
        this.hp += up * rateHp;
        this.level += up;
    }

    public void learnSkill(Skill skill){
        this.skill = skill;
    }
}
