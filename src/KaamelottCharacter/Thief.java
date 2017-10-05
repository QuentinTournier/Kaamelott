package KaamelottCharacter;

import KaamelottCapacities.Attack;
import KaamelottControl.GameInterface;

public class Thief extends Character {
    
    public Thief(GameInterface gi,String name) {

        this(gi, name,1);
    }
    public Thief(GameInterface gi, String name, int level) {

        super(gi, name,"Thief",level);
        this.characteristic.put(Characteristic.STRENGTH,20+2*(level-1));
        this.characteristic.put(Characteristic.DEXTERITY,40+2*(level-1));
        this.characteristic.put(Characteristic.INTELLIGENCE,10+2*(level-1));
        this.characteristic.put(Characteristic.HEALTH,180+100*(level-1));
        this.characteristic.put(Characteristic.DEFENSE,30+2*(level-1));
        addCapacity(new Attack(this.getDmg(),"Traitor's blow",this,this));
        hp=getHp();
    }
    
}
