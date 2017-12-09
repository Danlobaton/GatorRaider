package ufl.cs1.controllers;

import game.controllers.DefenderController;
import game.models.Defender;
import game.models.Game;

import java.util.List;

public final class StudentController implements DefenderController
{
    public void init(Game game) { }

    public void shutdown(Game game) { }

    public int[] update(Game game, long timeDue)
    {
        int[] actions = new int[Game.NUM_DEFENDER];
        List<Defender> enemies = game.getDefenders();
        actions[0] = uniqueBehavior1(game, enemies.get(0), timeDue);
        actions[1] = uniqueBehavior2(game, enemies.get(1), timeDue);
        actions[2] = uniqueBehavior3(game, enemies.get(2), timeDue);
        actions[3] = uniqueBehavior4(game, enemies.get(3), timeDue);
        return actions;
    }

    public int boardState(Game game, long timeDue)
    {
        int mode=0;
        return mode;
    }

    public int uniqueBehavior1(Game game, Defender defender, long timeDue)
    {
        // RED Ghost
        int direction = 0;
        int attackerX = game.getAttacker().getLocation().getX();
        int attackerY = game.getAttacker().getLocation().getY();
        int defenderX = defender.getLocation().getX();
        int defenderY = defender.getLocation().getY();
        int differenceY = Math.abs(attackerY - defenderY);
        int differenceX = Math.abs(attackerX - defenderX);

        if(!defender.isVulnerable())
        {
            if(differenceY > differenceX)
            {
                if(attackerY > defenderY)
                {
                    direction = 2;
                }
                else if(attackerY < defenderY)
                {
                    direction = 0;
                }
            }
            else if(differenceY < differenceX)
            {
                if(attackerX > defenderX)
                {
                    direction = 1;
                }
                else if(attackerX < defenderX)
                {
                    direction = 3;
                }
            }
        }
        else if(defender.isVulnerable())
        {
            if(differenceY > differenceX)
            {
                if(attackerY > defenderY)
                {
                    direction = 0;
                }
                else if(attackerY < defenderY)
                {
                    direction = 2;
                }
            }
            else if(differenceY < differenceX)
            {
                if(attackerX > defenderX)
                {
                    direction = 3;
                }
                else if(attackerX < defenderX)
                {
                    direction = 1;
                }
            }
        }
        return direction;
    }


    public int uniqueBehavior2(Game game, Defender defender, long timeDue)
    {
        // PINK Ghost
        boolean approach = true;
        int VULNERABLE_TIME_LIMIT= 40;
        int DISTANCE_TO_PILL = 40;

        if (defender.isVulnerable()) {
          approach = false;
          int vulnerable_time = defender.getVulnerableTime();
          if (vulnerable_time < VULNERABLE_TIME_LIMIT) {
            approach = true;
          }
        }
        else {
          for(int pill=0; pill<game.getPowerPillList().size(); pill++) {
            int distance_to_pill = game.getAttacker().getLocation().getPathDistance(game.getPowerPillList().get(pill));
            if (distance_to_pill < DISTANCE_TO_PILL) {
              approach = false;
            }
          }
        }
        int nextDirection = defender.getNextDir(game.getAttacker().getLocation(), approach);

        // for (int l=0; l<game.getAttacker().getPossibleLocations(false).size(); l++) {
        //   if (game.getAttacker().getPossibleLocations(false).get(l) != null)
        //   System.out.println(">>> " + l + ": " + game.getAttacker().getPossibleLocations(false).get(l).getX() + ":" + game.getAttacker().getPossibleLocations(false).get(l).getY());
        // }
        // return game.getAttacker().getPossibleDirs(false).get(0);
        return nextDirection;
    }
    public int uniqueBehavior3(Game game, Defender defender, long timeDue) {  //Xavier's

      // ORANGE Ghost

      int ghost_distance_to_pill[] = new int[game.getPowerPillList().size()];
      int attacker_distance_to_pill[] = new int[game.getPowerPillList().size()];
      int nextDirection = 0;
      int MIN_DISTANCE = 50;


      for(int pill=0; pill<game.getPowerPillList().size(); pill++) {
        ghost_distance_to_pill[pill] = defender.getLocation().getPathDistance(game.getPowerPillList().get(pill));
        attacker_distance_to_pill[pill] = game.getAttacker().getLocation().getPathDistance(game.getPowerPillList().get(pill));
      }
      if (ghost_distance_to_pill.length > 1) {
        if (ghost_distance_to_pill[0] > ghost_distance_to_pill[1]) {
          if (!defender.isVulnerable()) {
            nextDirection = defender.getNextDir(game.getPowerPillList().get(1), true);
            if ((ghost_distance_to_pill[1] - attacker_distance_to_pill[1]) < MIN_DISTANCE) {
              nextDirection = defender.getNextDir(game.getAttacker().getLocation(), true);
            }
          }
          else {
            nextDirection = defender.getNextDir(game.getAttacker().getLocation(), false);
          }
        }
        else {
          if (!defender.isVulnerable()) {
            nextDirection = defender.getNextDir(game.getPowerPillList().get(0), true);
            if ((ghost_distance_to_pill[1] - attacker_distance_to_pill[1]) < MIN_DISTANCE) {
              nextDirection = defender.getNextDir(game.getAttacker().getLocation(), true);
            }
          }
          else {
            nextDirection = defender.getNextDir(game.getAttacker().getLocation(), false);
          }
        }
      }
      else {
        if (ghost_distance_to_pill.length != 0) {
          if (!defender.isVulnerable()) {
            nextDirection = defender.getNextDir(game.getPowerPillList().get(0), true);
            if ((ghost_distance_to_pill[0] - attacker_distance_to_pill[0]) < MIN_DISTANCE) {
              nextDirection = defender.getNextDir(game.getAttacker().getLocation(), true);
            }
          }
          else {
            nextDirection = defender.getNextDir(game.getAttacker().getLocation(), false);
          }
        }
        else {
          nextDirection = defender.getNextDir(game.getAttacker().getLocation(), true);
        }
      }

      return nextDirection;
  }
    public int uniqueBehavior4(Game game, Defender defender, long timeDue)
    {
        // int nextDirection = 0;
        // for (int pill=0; pill< game.getCurMaze().getPillNodes().size(); pill++) {
        //
        //   nextDirection = defender.getNextDir(game.getCurMaze().getPillNodes().get(pill), true);
        // }
        int direction = 0;
        List<Integer> possibleDirs = defender.getPossibleDirs();
        if(possibleDirs.size() != 0)
        {
            direction = possibleDirs.get(Game.rng.nextInt(possibleDirs.size()));
        }
        else
        {
            direction = -1;
        }
        return direction;
    }

}
