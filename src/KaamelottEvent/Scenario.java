/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package KaamelottEvent;

import KaamelottCharacter.*;
import KaamelottControl.*;
import KaamelottControl.DisplayText;
import KaamelottControl.UI;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author p1511544
 */
public class Scenario {
    private List<Event> events;
    private List<Team> teams;
    private GameInterface ui;

    public Scenario() {
        ui = new UI();
        ui.init();
        teams = new ArrayList();
        events = new ArrayList();
    }


    public void addEvent(Event event){
        events.add(event);
    }
    public void addTeam(){
        List<KaamelottCharacter.Character> members=new ArrayList();
        Team teamA=new Team(ui,members,0);
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
        teams.get(1).addCharacterTeam(new Druid(ui, "XxNecroxX"));
        teams.get(1).addCharacterTeam(new Druid(ui, "OoNecrooO"));
        //team 2
        addTeam();
        teams.get(2).addCharacterTeam(new Crossbowman(ui, "Bobo"));
        teams.get(2).addCharacterTeam(new Druid(ui, "Bobheal"));
        //team 3
        addTeam();
        teams.get(3).addCharacterTeam(new Knight(ui, "Roukie"));
        teams.get(3).addCharacterTeam(new Thief(ui, "Roumain"));
        teams.get(3).addCharacterTeam(new Thief(ui, "Roucool"));
        //team 4
        addTeam();
        teams.get(4).addCharacterTeam(new Warrior(ui, "Dragunov"));
        teams.get(4).addCharacterTeam(new Knight(ui, "IG0R"));
        teams.get(4).addCharacterTeam(new Knight(ui, "Apalkov"));
        //team 5
        addTeam();
        teams.get(5).addCharacterTeam(new Knight(ui, "Leonardo"));
        teams.get(5).addCharacterTeam(new Thief(ui, "Donatello"));
        teams.get(5).addCharacterTeam(new Warrior(ui, "Michelangelo"));
        teams.get(5).addCharacterTeam(new Crossbowman(ui, "Raphael"));
        //team 6
        addTeam();
        teams.get(6).addCharacterTeam(new Druid(ui, "DarkHole"));
        teams.get(6).addCharacterTeam(new Druid(ui, "DarkWizard"));
        teams.get(6).addCharacterTeam(new Druid(ui, "DarkSidius"));
        teams.get(6).addCharacterTeam(new Druid(ui, "DarkMaul"));




    }

    public void gainObject(int  nbItem){
        AddItem addItem= new AddItem(ui, teams.get(0),nbItem);
        addEvent(addItem);
    }

    public Fight makeFight(Team teamA,Team teamB,int xp )
    {

        Fight fight=new Fight(ui, teamA,teamB,xp);
        return fight;
    }

    public CreateCharacter makeCreateCharacter()
    {
        CreateCharacter create=new CreateCharacter(ui);
        return create;
    }

    public Narrative makeTell(int numTell)
    {
        List<String> narration= new ArrayList();

        Narrative tell =new Narrative(ui,narration,teams.get(0),numTell);
        return tell;
    }
    public AddCapacity makeCapacity(int nbCapacity)
    {

        AddCapacity addCapacity= new AddCapacity(ui, nbCapacity);
        return addCapacity;
    }

    public void readScenario()
    {
        ui.display("In this story, the 1st character you chose will be the main character, and the two followings will be his partners");

        //Check if the user wants to start with defaults charac
        boolean autostart = false;
        String[] message = new String [3];
        message[0] = "Do you want to start the story with the Arthur, Lancelot and Merlin ?";
        message[1] = "Yes";
        message[2] = "No, I want to create my 3 own characters";
        autostart = ui.getNumber(message) == 1 ? true : false;
        if(autostart){
            teams.get(0).addCharacterTeam(new Knight(ui, "Arthur"));
            teams.get(0).addCharacterTeam(new Druid(ui, "Merlin"));
            teams.get(0).addCharacterTeam(new Knight(ui, "Lancelot"));
        }

        for(int i=0;i<events.size();i++)
        {

            switch (events.get(i).getType()) {
                case 0:  ((AddCapacity)events.get(i)).doAddCapacity(teams.get(0));
                    break;
                case 1:  if(i<5 && !autostart)((CreateCharacter)events.get(i)).addCharac(teams.get(0));
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
            String [] mess = new String[5];
            mess[0] = "Chose an action";
            mess[1] ="Use Potion";
            mess[2] ="Equip Character";
            mess[3] ="View Stats";
            mess[4] ="Continue adventure";


            int number=0;
            if(events.get(i).getType()==2 ||events.get(i).getType()==4){
                while (number!=4){
                    number = ui.getNumber(mess);
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
