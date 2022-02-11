package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public class MeatEating {
    private Sprite skin = new Sprite(new Texture("meat_eating.png"));

    // блок для перемещения
    private double vX = 5;
    private double vY = 5;
    private int shX = 2;
    private int counter = 0;

    // блок мутаций, на которые влияет среда
    private boolean swimming = true; // море
    private boolean walking = false; // суша
    private boolean hiking = false; // горы
    private boolean heating = false; // северный полюс
    private boolean cooling = false; // пустыня

    // еда для потомства
    private boolean food = false;

    // конструктор класса
    public MeatEating(int x, int y) {
        skin.setSize(25,25);
        skin.setX(x-5);
        skin.setY(y+5);
    }

    // метод размножения
    private void repr(ArrayList<MeatEating> me){
        if(food) {
            food = false;
            me.add(new MeatEating((int) skin.getX(), (int) skin.getY()));
        }
    }

    // метод поедания
    private void eat(ArrayList<GrassFeeding> ar){
        for(GrassFeeding g:ar){
            if(Math.pow(g.getSkin().getX()-skin.getX(), 2) < 100 && Math.pow(g.getSkin().getY()-skin.getY(), 2) < 100 && g.isAlive() && !food){
                g.setAlive(false);
                food = true;
            }
        }
    }

    // метод для нахождения ближайшей хавки
    private GrassFeeding findClosestFood(ArrayList<GrassFeeding> ar){
        double min = 100000;
        GrassFeeding res = null;
        for(GrassFeeding g: ar){
            if(Math.sqrt(Math.pow(skin.getX()-g.getSkin().getX(), 2) + Math.pow(skin.getY()-g.getSkin().getY(), 2)) <= min){
                min = Math.sqrt(Math.pow(skin.getX()-g.getSkin().getX(), 2) + Math.pow(skin.getY()-g.getSkin().getY(), 2));
                res = g;
            }
        }
        return res;
    }

    // метод поведения
    public void lifecycle(ArrayList<GrassFeeding> ar, ArrayList<Grass> grass, ArrayList<MeatEating> me){
        repr(me);
        eat(ar);
        GrassFeeding ex = findClosestFood(ar);
        if(ex != null) {
            vX = (skin.getX() - ex.getSkin().getX()) / Math.sqrt(Math.pow(skin.getX() - ex.getSkin().getX(), 2) + Math.pow(skin.getY() - ex.getSkin().getY(), 2)) * 5;
            vY = (skin.getY() - ex.getSkin().getX()) / Math.sqrt(Math.pow(skin.getX() - ex.getSkin().getX(), 2) + Math.pow(skin.getY() - ex.getSkin().getY(), 2)) * 5;
        }
        counter++;
        if(counter % 2 == 1){
            skin.setX(skin.getX() + shX);
        }
        else{
            skin.setX(skin.getX() - shX);
        }
        if(counter == 1000000){
            counter = 0;
        }
        skin.setX((float) (skin.getX() + vX));
        skin.setX((float) (skin.getY() + vY));
    }

    public Sprite getSkin() {
        return skin;
    }

    public void setSkin(Sprite skin) {
        this.skin = skin;
    }
}
