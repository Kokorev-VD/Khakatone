package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public class GrassFeeding {

    private Sprite skin = new Sprite(new Texture(""));

    // блок для перемещения
    private double vX = 3;
    private double vY = 4;
    private int shX = 2;
    private int counter = 0;

    // блок мутаций, на которые влияет среда
    private boolean swimming = true; // море
    private boolean walking = false; // суша
    private boolean hiking = false; // горы
    private boolean heating = false; // северный полюс
    private boolean cooling = false; // пустыня

    // блок мутаций, на которые влияют плотоядные
    private boolean armor = false; // панцирь
    private boolean speed = false; // ускорение засчет конечностей

    // еда для потомства
    private boolean food = false;

    // состояние здоровья
    private boolean alive = true;

    // конструктор класса
    public GrassFeeding(int x, int y) {
        skin.setSize(25,25);
        skin.setX(x-5);
        skin.setY(y+5);
    }

    // метод размножения
    private void repr(ArrayList<GrassFeeding> ar){
        if(food) {
            food = false;
            ar.add(new GrassFeeding((int) skin.getX(), (int) skin.getY()));
        }
    }

    // метод поедания
    private void eat(ArrayList<Grass> grass){
        for(Grass g:grass){
            if(Math.pow(g.getSkin().getX()-skin.getX(), 2) < 100 && Math.pow(g.getSkin().getY()-skin.getY(), 2) < 100 && !g.isEat() && !food){
                g.setEat(true);
                food = true;
            }
        }
    }

    // метод для нахождения ближайшей хавки
    private Grass findClosestFood(ArrayList<Grass> grass){
        double min = 100000;
        Grass res = null;
        for(Grass g: grass){
            if(Math.sqrt(Math.pow(skin.getX()-g.getSkin().getX(), 2) + Math.pow(skin.getY()-g.getSkin().getY(), 2)) <= min){
                min = Math.sqrt(Math.pow(skin.getX()-g.getSkin().getX(), 2) + Math.pow(skin.getY()-g.getSkin().getY(), 2));
                res = g;
            }
        }
        return res;
    }

    //метод нахождения ближайшего плотоядного
    private MeatEating findClosestME(ArrayList<MeatEating> me){
        double min = 100000;
        MeatEating res = null;
        for(MeatEating g: me){
            if(Math.sqrt(Math.pow(skin.getX()-g.getSkin().getX(), 2) + Math.pow(skin.getY()-g.getSkin().getY(), 2)) <= min){
                min = Math.sqrt(Math.pow(skin.getX()-g.getSkin().getX(), 2) + Math.pow(skin.getY()-g.getSkin().getY(), 2));
                res = g;
            }
        }
        return res;
    }

    // метод поведения
    public void lifecycle(ArrayList<GrassFeeding> ar, ArrayList<Grass> grass, ArrayList<MeatEating> me){
        repr(ar);
        eat(grass);
        Grass ex = findClosestFood(grass);
        if(ex != null) {
            vX = (skin.getX() - ex.getSkin().getX()) / Math.sqrt(Math.pow(skin.getX() - ex.getSkin().getX(), 2) + Math.pow(skin.getY() - ex.getSkin().getY(), 2)) * 5;
            vY = (skin.getY() - ex.getSkin().getX()) / Math.sqrt(Math.pow(skin.getX() - ex.getSkin().getX(), 2) + Math.pow(skin.getY() - ex.getSkin().getY(), 2)) * 5;
        }
        else{
            MeatEating mex = findClosestME(me);
            vX = (skin.getX() - mex.getSkin().getX()) / Math.sqrt(Math.pow(skin.getX() - mex.getSkin().getX(), 2) + Math.pow(skin.getY() - mex.getSkin().getY(), 2)) * 5;
            vY = (skin.getY() - mex.getSkin().getX()) / Math.sqrt(Math.pow(skin.getX() - mex.getSkin().getX(), 2) + Math.pow(skin.getY() - mex.getSkin().getY(), 2)) * 5;
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

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
