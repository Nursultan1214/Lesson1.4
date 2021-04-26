package com.geektech;

import java.util.Random;
import java.util.function.DoubleToIntFunction;

public class Main {

    public static int[] heroesHealth = {270, 280, 250, 220};
    public static String[] heroesNames = {"Lu Kang ", "Jax ",
            "Scorpion ", "Medic"};
    public static int[] heroesStrike = {20, 15, 25, 20};

    public static String bossName = "Shao Kahn ";
    public static int bossHealth = 700;
    public static int bossStrike = 50;
    public static String superStrike = "";
    public static int roundNumber = 0;

    public static void main(String[] args) {
        // write your code here
        printStatistics();
        System.out.println("------The game started-------");

        while (!isGameFinished()) {
            round();
        }
    }

    public static void round() {
        roundNumber++;
        System.out.println("-----Round " + roundNumber + "-----");
        superStrike = getSuperStrikeHero();
        medicLechit();
        bossHits();
        heroesHits();
        printStatistics();
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!! " +
                    "Mortal Kombat finished");
            return true;
        }

        boolean allHeroesDead = true;

        for (int heroHealth : heroesHealth) {
            if (heroHealth > 0) {
                allHeroesDead = false;
                break;
            }
        }

        if (allHeroesDead) {
            System.out.println(bossName +
                    " Won!!! Mortal Combat finished");
        }
        return allHeroesDead;
    }

    public static void heroesHits() {
        Random random = new Random();
        int coeff = random.nextInt(9) + 2;
        for (int i = 0; i < heroesStrike.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0 &&  heroesHealth[3] != heroesHealth[i]) {
                if (superStrike == heroesNames[i]) {
                    bossHealth = bossHealth - heroesStrike[i] * coeff;
                    System.out.println("Super strike damage " +
                            superStrike + " " + (heroesStrike[i] * coeff));
                } else {
                    bossHealth = bossHealth - heroesStrike[i];
                }
            }
            if (bossHealth < 0) {
                bossHealth = 0;
            }
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                heroesHealth[i] = heroesHealth[i] - bossStrike;
            }
            if (heroesHealth[i] < 0) {
                heroesHealth[i] = 0;
            }
        }
    }

    public static String getSuperStrikeHero() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesNames.length);
        return heroesNames[randomIndex];
    }

    public static void printStatistics() {
        System.out.println(bossName + "= health " + bossHealth +
                " strike [" + bossStrike + "]");
        for (int i = 0; i < heroesNames.length; i++) {
            System.out.println(heroesNames[i] + "= health " +
                    heroesHealth[i] + " strike [" +
                    heroesStrike[i] + "]");
        }
    }

    public static void medicLechit() { // Мы создаем метод в котором медик лечит героев\
        if (heroesHealth[3] > 0) {
            Random random = new Random();
            int critical = random.nextInt(5);//Мы создали критикал чтобы увеличивать лечение
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] < 100 && heroesHealth[i] > 0 && heroesHealth[3] != heroesHealth[i]) { // heroesHealth[здесь пишем позицию] и получаем жизнь героя.
                    // heroesHealth[i]>0 - проверка на живучесть это будет
                    // heroesHealth[3] != heroesHealth[i] - исключает из списка
                    heroesHealth[i] = heroesHealth[i] + critical * heroesStrike[3];
                    // critical * heroesStrike[3] - Это значит что, с помощью рандома выбирается число на которое медик будет лечит
                    System.out.println("Медик вылечил "+ heroesNames[i]);
                    break;
                }
            }

        }

    }

}

