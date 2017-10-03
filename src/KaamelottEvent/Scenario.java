/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package KaamelottEvent;

import KaamelottCapacities.*;
import KaamelottCharacter.Characteristic;
import KaamelottCharacter.*;
import KaamelottControl.*;
import KaamelottControl.DisplayText;
import KaamelottGraphical.UI;
import KaamelottItemization.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author p1511544
 */
public class Scenario {
    private List<Event> events;
    private DisplayText display;
    private List<Team> teams;
    private UI ui;

    public Scenario() {
        this.display = new DisplayText();
        ui = new UI();
        teams = new ArrayList();
        events = new ArrayList();
    }

  
    public void addEvent(Event event){
        events.add(event);
    }
    public void addTeam(){
        List<KaamelottCharacter.Character> members=new ArrayList();     
        Team teamA=new Team(members,0);
        teams.add(teamA);
    }
    
    public void makeScenario()
    {
        createTeams();
        addEvent(makeCreateCharacter());
        addEvent(makeCreateCharacter());
        addEvent(makeCreateCharacter());
        addEvent(makeTell(1));
        gainObject(1);
        gainObject(11);
        addEvent(makeTell(2));
        addEvent(makeFight(teams.get(0),teams.get(1),100));
        addEvent(makeCapacity(0));
        gainObject(21);
        addEvent(makeTell(3));
        addEvent(makeFight(teams.get(0),teams.get(2),150));
        gainObject(31);
        gainObject(2);
        addEvent(makeTell(4));
        addEvent(makeFight(teams.get(0),teams.get(3),200));
        gainObject(22);
        gainObject(3);
        addEvent(makeTell(5));
        addEvent(makeCreateCharacter());
        addEvent(makeTell(6));
        addEvent(makeFight(teams.get(0),teams.get(4),250));
        gainObject(23);
        gainObject(32);
        addEvent(makeTell(7));
        addEvent(makeFight(teams.get(0),teams.get(5),300));
        gainObject(24);
        addEvent(makeTell(8));
        addEvent(makeCreateCharacter());
        addEvent(makeTell(9));
        addEvent(makeFight(teams.get(0),teams.get(6),350));
        addEvent(makeTell(9));
        
        

        
        
             
    }
    
    
    public void createTeams(){
        addTeam();    
        //team 1
        addTeam();
         teams.get(1).addCharacterTeam(new Druid("XxNecroxX"));
         teams.get(1).addCharacterTeam(new Druid("OoNecrooO"));
        //team 2
        addTeam();
        teams.get(2).addCharacterTeam(new Crossbowman("Bobo"));
        teams.get(2).addCharacterTeam(new Druid("Bobheal"));
        //team 3
        addTeam();
        teams.get(3).addCharacterTeam(new Knight("Roukie"));
        teams.get(3).addCharacterTeam(new Thief("Roumain"));
        teams.get(3).addCharacterTeam(new Thief("Roucool"));
        //team 4
        addTeam();
        teams.get(4).addCharacterTeam(new Warrior("Dragunov"));
        teams.get(4).addCharacterTeam(new Knight("IG0R"));
        teams.get(4).addCharacterTeam(new Knight("Apalkov"));
        //team 5
        addTeam();
        teams.get(5).addCharacterTeam(new Knight("Leonardo"));
        teams.get(5).addCharacterTeam(new Thief("Donatello"));
        teams.get(5).addCharacterTeam(new Warrior("Michelangelo"));
        teams.get(5).addCharacterTeam(new Crossbowman("Raphael"));
        //team 6
        addTeam();
        teams.get(6).addCharacterTeam(new Druid("DarkHole"));
        teams.get(6).addCharacterTeam(new Druid("DarkWizard"));
        teams.get(6).addCharacterTeam(new Druid("DarkSidius"));
        teams.get(6).addCharacterTeam(new Druid("DarkMaul"));
        
        
        

    }
    
    public void gainObject(int  nbItem){
        AddItem addItem= new AddItem(teams.get(0),nbItem);
         addEvent(addItem);
    }
    
    public Fight makeFight(Team teamA,Team teamB,int xp )
    {        

        Fight fight=new Fight(teamA,teamB,display,xp);
        return fight;
    }
    
    public CreateCharacter makeCreateCharacter()
    {
        CreateCharacter create=new CreateCharacter(display);
        return create;
    }
    
    public Narrative makeTell(int numTell)
    {
        List<String> narration= new ArrayList();
        
        Narrative tell =new Narrative(narration,display,teams.get(0),numTell);   
        return tell;
    }
    public AddCapacity makeCapacity(int nbCapacity)
    {
        
        AddCapacity addCapacity= new AddCapacity(nbCapacity);   
        return addCapacity;
    }
    
     public void readScenario()
    {
        display.display("In this story, the 1st character you chose will be the main character, and the two followings will be his partners");
        display.display("If you want, you can call the main character Arthur, and his partners Lancelot and Merlin");
        for(int i=0;i<events.size();i++)
        {

            switch (events.get(i).getType()) {
            case 0:  ((AddCapacity)events.get(i)).doAddCapacity(teams.get(0));
                     break;
            case 1:  ((CreateCharacter)events.get(i)).addCharac(teams.get(0));
                     break;
            case 2:  ((Fight)events.get(i)).doFight();
                     break;
            case 3:  ((Narrative)events.get(i)).Tell();
                     break;
            case 4:  ((AddItem)events.get(i)).doAddItem();
                     break;
            
            default: {
                     break;}
            
            } 
            String message="Chose an action \n 1. Use Potion"+"\n"
            +" 2. Equip Character"+"\n"
            +" 3. View Stats"+"\n"
            +" 4. Continue adventure"+"\n";
            
            String messError="Chose a number between "+1 +" and "+4;
        
        int number=0;
        if(events.get(i).getType()==2 ||events.get(i).getType()==4){
        while (number!=4){
        number=display.getNumber(1,4,message,messError);
        switch (number) {
            case 1:  teams.get(0).takeObject();
                     break;
            case 2:  
                    teams.get(0).equipCharacter();                   
                     break;
            case 3:  
                    teams.get(0).showStats();
                     break;
            case 4:
            
            default: {
                     break;}
            
                    }     
                }
            } 
        }
    }
    
}
