package KaamelottCapacities;
import KaamelottItemization.Effect;
import KaamelottCharacter.Character;
import KaamelottCharacter.Characteristic;
import java.util.Map;


public class Attack extends Capacity {
    
    private int dmg;
    private Effect effect;
    

 
    public Attack(int dmg, String name, Character target, Character source) {
        super(name, target, source);
        this.dmg = dmg;
        effect= new Effect();
    }

    public Attack(int dmg, Effect effect, String name) {
        super(name);
        this.dmg = dmg;
        this.effect = effect;
    }

    public Attack(String name) {
        super(name);
    }
    
    public int getValue() {
        return dmg;
    }
    
    
    
    @Override
    public Effect doEffect()
    {
        Map<Characteristic, Integer> characteristic = getTarget().getCharacteristic(); 
    
        dmg=2*getSource().getCharacteristic().get(Characteristic.STRENGTH);
        int dextTar=getTarget().getCharacteristic().get(Characteristic.DEXTERITY);
        int dextSrc=getSource().getCharacteristic().get(Characteristic.DEXTERITY);
        int defTar=getTarget().getCharacteristic().get(Characteristic.DEFENSE);
        
        int randomNum =(int)(Math.random()*100); 
        int dodge =dextTar-dextSrc;
        if (dodge>=randomNum)
            {dmg=0;}
        dmg=dmg*(100-defTar)/100;
        
        return effect = new Effect(getTarget(),-dmg,Characteristic.HEALTH);
    }

    public Effect getEffect() {
        return effect;
    }


  
    
}
