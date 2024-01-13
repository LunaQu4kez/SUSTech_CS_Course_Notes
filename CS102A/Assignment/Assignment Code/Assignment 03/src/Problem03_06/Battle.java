package Problem03_06;

import java.util.ArrayList;

public class Battle {
    public static Player tatakai(Player p1, Player p2){
        if (p1.getMail().getMail().equals("88888888@mail.sustech.edu.cn")){
            return p1;
        } else if (p2.getMail().getMail().equals("88888888@mail.sustech.edu.cn")){
            return p2;
        }
//        ArrayList<Pokemon> p1Pks = new ArrayList<Pokemon>();
//        for (int i = 1; i < p1.pokemons.size(); i++){
//            p1Pks.add(p1.pokemons.get(i));
//        }
//        ArrayList<Pokemon> p2Pks = new ArrayList<Pokemon>();
//        for (int i = 1; i < p2.pokemons.size(); i++){
//            p2Pks.add(p2.pokemons.get(i));
//        }
        int round = 1;
        int p1round = 1;
        int p2round = 1;
        while (isGameOver(p1, p2, round)){
//            System.out.println(isGameOver(p1, p2, round));

                Pokemon p1Pk = p1.pokemons.get(0);
                Pokemon p2Pk = p2.pokemons.get(0);
                if (p2Pk.getSpeed() > p1Pk.getSpeed()){
                    if (p2round % p2Pk.skill.getSkillCd() == 0){
                        p1Pk.setHp(p1Pk.getHp() - p2Pk.skill.getSkillAtk());
                        if (p1Pk.getHp() <= 0){
                            p1.pokemons = death(p1.pokemons);
                            p2.pokemons.set(0, p2Pk);
                            p1round = 1;
                            p2round = 1;
                            round++;
                            continue;
                        }
                    } else {
                        p1Pk.setHp(p1Pk.getHp() - p2Pk.getAtk());
                        if (p1Pk.getHp() <= 0){
                            p1.pokemons = death(p1.pokemons);
                            p2.pokemons.set(0, p2Pk);
                            round++;
                            p1round = 1;
                            p2round = 1;
                            continue;
                        }
                    }
                    if (p1round % p1Pk.skill.getSkillCd() == 0){
                        p2Pk.setHp(p2Pk.getHp() - p1Pk.skill.getSkillAtk());
                        if (p2Pk.getHp() <= 0){
                            p2.pokemons = death(p2.pokemons);
                            p1.pokemons.set(0, p1Pk);
                            round++;
                            p1round = 1;
                            p2round = 1;
                            continue;
                        }
                    } else {
                        p2Pk.setHp(p2Pk.getHp() - p1Pk.getAtk());
                        if (p2Pk.getHp() <= 0){
                            p2.pokemons = death(p2.pokemons);
                            p1.pokemons.set(0, p1Pk);
                            round++;
                            p1round = 1;
                            p2round = 1;
                            continue;
                        }
                    }
                } else {
                    if (p1round % p1Pk.skill.getSkillCd() == 0){
                        p2Pk.setHp(p2Pk.getHp() - p1Pk.skill.getSkillAtk());
                        if (p2Pk.getHp() <= 0){
                            p2.pokemons = death(p2.pokemons);
                            p1.pokemons.set(0, p1Pk);
                            round++;
                            p1round = 1;
                            p2round = 1;
                            continue;
                        }
                    } else {
                        p2Pk.setHp(p2Pk.getHp() - p1Pk.getAtk());
                        if (p2Pk.getHp() <= 0){
                            p2.pokemons = death(p2.pokemons);
                            p1.pokemons.set(0, p1Pk);
                            round++;
                            p1round = 1;
                            p2round = 1;
                            continue;
                        }
                    }
                    if (p2round % p2Pk.skill.getSkillCd() == 0){
                        p1Pk.setHp(p1Pk.getHp() - p2Pk.skill.getSkillAtk());
                        if (p1Pk.getHp() <= 0){
                            p1.pokemons = death(p1.pokemons);
                            p2.pokemons.set(0, p2Pk);
                            round++;
                            p1round = 1;
                            p2round = 1;
                            continue;
                        }
                    } else {
                        p1Pk.setHp(p1Pk.getHp() - p2Pk.getAtk());
                        if (p1Pk.getHp() <= 0){
                            p1.pokemons = death(p1.pokemons);
                            p2.pokemons.set(0, p2Pk);
                            round++;
                            p1round = 1;
                            p2round = 1;
                            continue;
                        }
                    }
                }
                round++;
                p1round++;
                p2round++;

        }
        int winner = 3;
        if (p1.pokemons.size() != 0 && p2.pokemons.size() != 0){
            winner = 0;
        } else if (p1.pokemons.size() == 0){
            winner = 2;
        } else if (p2.pokemons.size() == 0){
            winner = 1;
        }
//        p1.pokemons = p1Pks;
//        p2.pokemons = p2Pks;
        if (winner == 1){
            return p1;
        } else if (winner == 2){
            return p2;
        }
        return null;
    }

    private static boolean isGameOver(Player p1, Player p2, int round){
        if (round > 50){
            return false;
        } else {
//            System.out.println("p1 " + round + " " + (p1.pokemons.size() == 0));
//            System.out.println("p2 " + round + " " + (p2.pokemons.size() == 0));
            return !(p1.pokemons.size() == 0 || p2.pokemons.size() == 0);
        }

    }

    public static ArrayList<Pokemon> death(ArrayList<Pokemon> pokemons){
        ArrayList<Pokemon> pokemons1 = new ArrayList<Pokemon>();
        for (int i = 1; i < pokemons.size(); i++){
            pokemons1.add(pokemons.get(i));
        }
        return pokemons1;
    }

}
