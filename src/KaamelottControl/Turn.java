package KaamelottControl;
import KaamelottCharacter.Character;
import KaamelottItemization.Consumable;
import KaamelottCapacities.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Turn {
    private final Team teamA;
    private final Team teamB;
    private List<Action> actionA;
    private List<Action> actionB;
    Controller contA;
    Controller contB;
    GameInterface gi;

    

    public Turn(GameInterface gi, Team teamA, Team teamB,Controller contA,Controller contB) {
        this.gi = gi;
        this.teamA = teamA;
        this.teamB = teamB;
        this.contA=contA;
        this.contB=contB;
        actionA= new ArrayList();
        actionB= new ArrayList();
        
    }
   public Action getActionI(List<Action> listAction,int i){
       return listAction.get(i);
   }
   public Character getSourceActionI(List<Action> listAction,int i){
       return listAction.get(i).getSource();
   }
   public Character choseCharacter(Controller cont,int tour){
       Team team;
       List<Integer> listNb= new ArrayList();
       String mess;
       String message;
       
           
       if (tour%2==0)      
           mess="Chose your character \n";
       else
           mess="Chose your target \n";
       
       if (tour%3==0){ 
           team=teamA;
       } 
       else
       {
           team=teamB;
       }
       if (cont instanceof AIController)
       {
           for (int i=0;i<team.getTeamNumber();i++){
               if (team.getCharacterI(i).isAlive())
                return team.getCharacterI(i);
           }
           
       }
       int nbCharacter=team.getTeamNumber();
           for (int i=0;i<team.getTeamNumber();i++)
           {
               if(team.getCharacterI(i).isAlive()){
                   message=team.getCharacterI(i).getHp()+"HP\n";
                   listNb.add(i);
               }
                                 
                else
                   message="dead \n";
               mess=mess+i+"-"+team.getCharacterI(i).getName()+" "+message;
           }
     int numCharac= enterCharac(cont,listNb,mess);
     return team.getCharacterI(numCharac);
   } 
   
   public int enterCharac(Controller Cont,List<Integer> listNb,String mess){

       
     if (Cont instanceof HumanController )
     {
       return gi.getNumber(mess);}
     else 
         return listNb.get(0);
   }
   
   public Action choseAction(Controller cont,Character character){
        String mess="Chose what to do: \n"+"0-Attack \n"+"1-Use object \n";
        int num=0;
            String messError="chose a number between 0 and 1";
        if (cont instanceof HumanController)    
                num= gi.getNumber(mess);
        if (num==0)
           return choseAttack(cont,character);
        else
           return choseConsumable(cont,character);
   }
   
   public Action choseConsumable(Controller cont,Character character){
       List<Consumable> listConsumables= character.getConsumables();
        String mess="Chose an object to use";
        int max=listConsumables.size();
        for(int i=0; i<max; i++) 
            {mess=mess+"\n"+i+"-"+listConsumables.get(i).getName()+"("+listConsumables.get(i).getNumber()+")";}
            mess=mess+"\n"+max+" -Return";
            String messError="chose a number between 0 and "+max;
       int num= gi.getNumber(mess);
       if(num==max)
           return choseAction(cont,character);
       
       return character.getConsumableI(num);
   }
   
   public Action choseAttack(Controller cont,Character character){
       int max=character.getNbCapacity();
        String mess="Chose an action \n";      
        if(cont instanceof AIController)
            return character.getCapacityI(0);
        for(int i=0; i<max; i++) 
            {mess=mess+i+"-"+character.getNameCapacityI(i)+"\n";}
        mess=mess+max+"-Return";
        
            String messError="chose a number between 0 and "+max;
       int num= gi.getNumber(mess);
       if(num==max)
            return choseAction(cont,character);
            
       return character.getCapacityI(num);
   }
   
   public void playTurn(){
       teamA.showHp();
       teamB.showHp();
       actionA= new ArrayList();
       actionB= new ArrayList();
       
       boolean changeTarget;
       int j=0;
       for(int i=0;i<teamA.getTeamNumber();i++){
           
           changeTarget=true;
           if(teamA.getCharacterI(i).isAlive()){             
            if(contA instanceof HumanController)
                   gi.display("\n"+teamA.getCharacterI(i).getName()+" "+"must");
           actionA.add(choseAction(contA,teamA.getCharacterI(i)));
           getActionI(actionA,j).setSource(teamA.getCharacterI(i));
        
       if (getActionI(actionA,j) instanceof Spell){
           if (getActionI(actionA,j).getValue()<0){
               getActionI(actionA,j).setTarget(choseCharacter(contA,3)); // Chose target in team A
               changeTarget=false;}
       }
            
       if (getActionI(actionA,j) instanceof Consumable)
           if (((Consumable)getActionI(actionA,j)).getEffect().getValue()>0)
                changeTarget=false;
            
       if(changeTarget)
            getActionI(actionA,j).setTarget(choseCharacter(contA,1)); // Chose target in team B
       j++;
       }
       }
       
       //Attacks teamA done
       j=0;
       for(int i=0;i<teamB.getTeamNumber();i++){
           changeTarget=true;
           
            if(teamB.getCharacterI(i).isAlive()){
                
                if(contB instanceof HumanController)
                    gi.display("\n"+teamB.getCharacterI(i).getName()+" "+"must");
            actionB.add(choseAction(contB,teamB.getCharacterI(i)));
            getActionI(actionB,j).setSource(teamB.getCharacterI(i));
           
       if (getActionI(actionB,j) instanceof Spell){
           if (getActionI(actionB,j).getValue()<0){
               getActionI(actionB,j).setTarget(choseCharacter(contB,1)); // Chose target in team B
               changeTarget=false;
           }
       }
            
       if (getActionI(actionB,j) instanceof Consumable)
           if (((Consumable)getActionI(actionB,j)).getEffect().getValue()>0)
                changeTarget=false;
            
       if(changeTarget)
            getActionI(actionB,j).setTarget(choseCharacter(contB,3)); // Chose target in team A
       j++;
            }
       }
       //we put the speed of the action here
       for(int i=0;i<actionA.size();i++){
           actionA.get(i).setSpeed();
       }
       for(int i=0;i<actionB.size();i++){
           actionB.get(i).setSpeed();
       }
       // we sort the action with their speed here
       sortListAction();
       //we do the dmgs here
       
       for(int i=0;i<actionB.size();i++){
           if(getActionI(actionB,i).getSource().isAlive()){
           getActionI(actionB,i).doEffect().applyEffect(getActionI(actionB,i).getTarget());
           if(getActionI(actionB,i).getEffect().getValue()<0)
           {
            gi.display(getActionI(actionB,i).getTarget().getName()+ " lost "+ -getActionI(actionB,i).getEffect().getValue()+"HP");
           }
           else
           {
               gi.display(getActionI(actionB,i).getTarget().getName()+ " won "+ getActionI(actionB,i).getEffect().getValue()+"HP");
           }
           }
       }
       
      
                
   }
   
  public int getIndexOfMax() {
    int indexMax=actionA.size()-1;
    int valueMax=actionA.get(indexMax).getSpeed();
    for (int i = 0; i < actionA.size(); i++) {
        int f = actionA.get(i).getSpeed();
        if (f>valueMax) {
            valueMax = f;
            indexMax = i;
        }
    }
    return indexMax;
}
  public void sortListAction(){
    for(int i=0;i<actionB.size();i++){
        actionA.add(actionB.get(i));
    }
    actionB= new ArrayList();
    int nbActionA=actionA.size();
    for (int i = 0; i <nbActionA ; i++) {
            int max=getIndexOfMax();
            actionB.add(actionA.get(max));
            actionA.remove(max);
        }
    }
     
  
   
}
