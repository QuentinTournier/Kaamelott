/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package KaamelottEvent;

import KaamelottControl.*;


/**
 *
 * @author p1511544
 */
public class Fight extends Event {
    private Team teamA;
    private Team teamB;
    private final int type=2;
    private Controller contA;
    private Controller contB;
    private int xp;

    public Fight(GameInterface gi, Team teamA, Team teamB,int xp) {
        super(gi);
        this.teamA = teamA;
        this.teamB = teamB;
        this.contA=new HumanController();
        this.contB=new AIController();
        this.xp=xp;
    }

    public int getType() {
        return type;
    }
    
    
    public void doFight(){
        
        String message="------------------------------------------------------------------\n You're beginning a fight against: \n";
        for (int i=0;i<teamB.getTeamNumber();i++)
        {
         message=message+teamB.getCharacterI(i).getName()+"\n";   
        }
        gi.display(message);
        Turn turn=new Turn(gi, teamA,teamB,contA,contB);
        while (teamA.isTeamAlive()&&teamB.isTeamAlive())
        {
            turn.playTurn();
        }
        if(teamA.isTeamAlive())
            gi.display("You overcame your ennemy, Well done !");
        else{
            gi.display("You have been defeated");
             System.exit(0);
        }
            
        
        for (int i=0;i<teamA.getTeamNumber();i++)
        {
         if (teamA.getCharacterI(i).earnXp(xp)){
             gi.display("You earned a level with"+" "+ teamA.getCharacterI(i).getName());
         }
        }
    }
    
}
