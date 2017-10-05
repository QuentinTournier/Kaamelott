package KaamelottCharacter;

import KaamelottCapacities.Attack;
import KaamelottControl.GameInterface;

public class Crossbowman extends Character {

    public Crossbowman(GameInterface gi,String name) {
       
        this(gi, name,1);
    }
     
    public Crossbowman(GameInterface gi,String name,int level) {
       
        super(gi, name,"Crossbowman",level);
        this.characteristic.put(Characteristic.STRENGTH,40+2*(level-1));
        this.characteristic.put(Characteristic.DEXTERITY,40+2*(level-1));
        this.characteristic.put(Characteristic.INTELLIGENCE,20+2*(level-1));
        this.characteristic.put(Characteristic.HEALTH,150+100*(level-1));
        this.characteristic.put(Characteristic.DEFENSE,20+2*(level-1));
        addCapacity(new Attack(this.getDmg(),"Blow arrow",this,this)); 
        hp=getHp();
    }
}
