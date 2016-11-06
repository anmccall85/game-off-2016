package com.socialinept.imaginary.battle;


import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by lild227 on 11/5/2016.
 */
public class Battle {
    private ArrayList<Entity> entities = new ArrayList<Entity>();
    private ArrayList<Team> teams = new ArrayList<Team>();
    private HashMap<Entity, Point> positions = new HashMap<Entity, Point>();
    private ServerMap serverMap;

    public Battle(){
        serverMap = new ServerMap();
        for(int i = 0; i < 3; i++){
            Entity e = new Entity();
            entities.add(e);
            positions.put(e, new Point((int)(Math.random()*serverMap.maxX*serverMap.maxY), (int)(Math.random()*serverMap.maxY)));
        }
    }

    public ArrayList<Entity> getEntities(){
        return entities;
    }

    public Point getEntityPosition(Entity e){
        return positions.get(e);
    }
}
