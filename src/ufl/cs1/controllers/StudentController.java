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
	public int uniqueBehavior3(Game game, Defender defender, long timeDue)
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
