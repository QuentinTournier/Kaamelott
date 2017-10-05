package KaamelottCharacter;

import KaamelottCapacities.Attack;
import KaamelottCapacities.Capacity;
import KaamelottCapacities.Spell;
import KaamelottControl.GameInterface;

public class Druid extends Character {
    
    public Druid(GameInterface gi,String name) {
       
        this(gi, name,1);
    }
    
    public Druid(GameInterface gi, String name, int level) {
       
        super(gi, name,"Druid",level);
        this.characteristic.put(Characteristic.STRENGTH,20+2*(level-1));
        this.characteristic.put(Characteristic.DEXTERITY,15+2*(level-1));
        this.characteristic.put(Characteristic.INTELLIGENCE,50+2*(level-1));
        this.characteristic.put(Characteristic.HEALTH,200+100*(level-1));
        this.characteristic.put(Characteristic.DEFENSE,20+2*(level-1));
        addCapacity(new Attack(this.getDmg(),"Stick hit",this,this));
        hp=getHp();
        Capacity capacity = new Spell(-2*characteristic.get(Characteristic.INTELLIGENCE),"Heal");
        this.getCapacities().add(capacity);
    }
}
