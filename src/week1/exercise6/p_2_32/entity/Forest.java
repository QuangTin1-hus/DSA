package week1.exercise6.p_2_32.entity;

import java.util.Random;

public class Forest {
    private static Forest instance;
    private Animal[][] data;
    private Random random;

    private Forest(int maxX, int maxY) {
        this.data = new Animal[maxX][maxY];
        this.random = new Random();
    }

    public static Forest getInstance(int maxX, int maxY) {
        if (instance == null)
            instance = new Forest(maxX, maxY);
        return instance;
    }

    public Animal[][] getData() {
        return data;
    }

    public Forest add(EAnimal eAnimal, int quantity) {
        switch (eAnimal) {
            case FISH -> {
                for (int i = 0; i < quantity; i++) {
                    boolean isMale = random.nextBoolean();
                    Animal fish = new Fish("fist" + i, isMale, random.nextInt(1, 9));
                    addAnimal(fish);
                }
            }
            case BEAR -> {
                for (int i = 0; i < quantity; i++) {
                    boolean isMale = random.nextBoolean();
                    Animal bear = new Bear("fist" + i, isMale, random.nextInt(15, 25));
                    addAnimal(bear);
                }
            }
        }
        return this;
    }

    public Forest add(Animal animal, int x, int y) {
        if (isValueNull(x, y))
            data[x][y] = animal;
        return this;
    }

    public void go() {
        int count = 0;
        boolean[][] isGo = new boolean[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                go(i, j, isGo);
                if (data[i][j] instanceof Fish) {
                    count++;
                }
            }
        }
     if (count == 0){
         System.exit(1);
     }
    }

    private void go(int x, int y, boolean[][] isGo) {
        if (data[x][y] != null && !isGo[x][y]) {
            int newX = random.nextInt(3) - 1 + x;
            int newY = random.nextInt(3) - 1 + y;

            if (!isValuePos(newX, newY)) {
                //Go
                if (isValueNull(newX, newY)) {
                    isGo[newX][newY] = true;
                    data[newX][newY] = data[x][y];
                    data[x][y] = null;
                } else {
                    Animal animal = data[x][y];
                    Animal oldAnimal = data[newX][newY];

                    if (animal.getClass() == oldAnimal.getClass()) {
                        //Go and Reproduction
                        if (animal.isMale() != oldAnimal.isMale()) {
                            // Reproduction
                            int newAniX = random.nextInt(data.length);
                            int newAniY = random.nextInt(data[0].length);
                            if (isValueNull(newAniX, newAniY) && !isValuePos(newAniX, newAniY)) {
                                data[newAniX][newAniY] = animal;
                            }
                        }
                    } else {
                        // Go an attack
                        if (animal.compareTo(oldAnimal) > 0) {
                            data[newX][newY] = animal;
                            data[x][y] = null;
                            isGo[newX][newY] = true;
                        } else if (animal.compareTo(oldAnimal) <= 0) {
                            data[x][y] = null;
                            isGo[x][y] = true;
                        }
                    }
                }
            } else
                System.out.println("The forest limit has been exceeded");
        }
    }

    private boolean isValueNull(int x, int y) {
        return data[x][y] == null;
    }

    private boolean isValuePos(int x, int y) {
        return x < 0 || x >= data.length || y < 0 || y >= data[0].length;
    }

    public Animal addAnimal(Animal animal) {
        int x = 0, y = 0;
        do {
            x = random.nextInt(data.length);
            y = random.nextInt(data[0].length);
        } while (!isValueNull(x, y));
        data[x][y] = animal;
        return data[x][y];
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                s += data[i][j] + " ";
            }
            s += "\n";
        }
        return s;
    }
}
