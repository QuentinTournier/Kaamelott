package KaamelottCharacter;

import KaamelottCapacities.Attack;
import KaamelottControl.GameInterface;
import KaamelottItemization.Item;
import KaamelottItemization.Weapon;

public final class Knight extends Character {
    
    
    public Knight(GameInterface gi,String name) {
       
        this(gi, name,1);
    }
    
    public Knight(GameInterface gi, String name, int level) {
       
        super(gi, name,"Knight",level);
        this.characteristic.put(Characteristic.STRENGTH,50+2*(level-1));
        this.characteristic.put(Characteristic.DEXTERITY,25+2*(level-1));
        this.characteristic.put(Characteristic.INTELLIGENCE,0+2*(level-1));
        this.characteristic.put(Characteristic.HEALTH,200+100*(level-1));
        this.characteristic.put(Characteristic.DEFENSE,30+2*(level-1));
        addCapacity(new Attack(this.getDmg(),"Sword hit",this,this));
        hp=getHp();   
        
    }
    
      
}
