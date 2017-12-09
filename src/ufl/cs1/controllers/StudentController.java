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
		//actions[0] = 1;
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
	public int uniqueBehavior3(Game game, Node node, Defender defender, long timeDue) {  //Xavier's
        int direction = 0;
        int numPwrPills = game.getCurMaze().getNumberPowerPills();
        int[] xRay = new int[numPwrPills];
        int[] yRay = new int[numPwrPills];
        int defenderX = defender.getLocation().getX();
        int defenderY = defender.getLocation().getY();
        int differenceYA = Math.abs(yRay[0] - defenderY);
        int differenceXA = Math.abs(xRay[0] - defenderX);
        int differenceYB = Math.abs(yRay[1] - defenderY);
        int differenceXB = Math.abs(xRay[1] - defenderX);
        int dist1 = node.getPathDistance(game.getPowerPillList().get(0));
        int dist2 = node.getPathDistance(game.getPowerPillList().get(1));

        for (int i = 0; i < numPwrPills; i++)
        //fill X and y co-ords separately
        {
            xRay[i] = game.getPowerPillList().get(i).getX();
            yRay[i] = game.getPowerPillList().get(i).getY();
        }
        //(104,108) (4,108) (4,8) (104,8)


        if (numPwrPills == 2) {
            if (dist1 >= dist2) {
                if (!defender.isVulnerable()) {
                    if (differenceYB > differenceXB) {
                        if (yRay[1] > defenderY) {
                            direction = 2;
                        } else if (yRay[1] < defenderY) {
                            direction = 0;
                        }
                    } else if (differenceYB < differenceXB) {
                        if (xRay[1] > defenderX) {
                            direction = 1;
                        } else if (xRay[1] < defenderX) {
                            direction = 3;
                        }
                    }
                } else if (defender.isVulnerable()) {
                    if (differenceYB > differenceXB) {
                        if (yRay[1] > defenderY) {
                            direction = 0;
                        } else if (yRay[1] < defenderY) {
                            direction = 2;
                        }
                    } else if (differenceYB < differenceXB) {
                        if (xRay[1] > defenderX) {
                            direction = 3;
                        } else if (xRay[1] < defenderX) {
                            direction = 1;
                        }
                    }
                }

            } else {
                if (!defender.isVulnerable()) {
                    if (differenceYA > differenceXA) {
                        if (yRay[0] > defenderY) {
                            direction = 2;
                        } else if (yRay[0] < defenderY) {
                            direction = 0;
                        }
                    } else if (differenceYA < differenceXA) {
                        if (xRay[0] > defenderX) {
                            direction = 1;
                        } else if (xRay[0] < defenderX) {
                            direction = 3;
                        }
                    }
                } else if (defender.isVulnerable()) {
                    if (differenceYA > differenceXA) {
                        if (yRay[0] > defenderY) {
                            direction = 0;
                        } else if (yRay[0] < defenderY) {
                            direction = 2;
                        }
                    } else if (differenceYA < differenceXA) {
                        if (xRay[0] > defenderX) {
                            direction = 3;
                        } else if (xRay[0] < defenderX) {
                            direction = 1;
                        }
                    }
                }
            }
        }
        return direction;
    }
	public int uniqueBehavior4(Game game, Defender defender, long timeDue)
	{
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

	public int boardState(Game game, long timeDue)
	{
		int mode=0;
		return mode;
	}
}
