/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KaamelottEvent;

import KaamelottControl.GameInterface;

/**
 *
 * @author nitnek
 */
public abstract class Event {
    protected GameInterface gi;
    abstract int getType();

    public Event(GameInterface gi){
        this.gi= gi;
    }
}
