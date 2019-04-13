package oosd.contracts.models;

import java.util.List;

import de.vksi.c4j.Target;
import oosd.models.player.Player;
import oosd.models.units.Unit;
import oosd.models.units.behaviour.UnitBehaviour;

import static de.vksi.c4j.Condition.*;

public class UnitContract extends Unit
{
	@Target
	private Unit target;
	
	public UnitContract(Player player)
	{
		super(player);
		
		if(preCondition())
		{
			assert !player.getPlayerName().isEmpty();
		}
	}
	
    @Override
    public void setCaptured(boolean captured) {
        if(preCondition())
        {
        	assert captured == false || captured == true;
        }
    }
	
	@Override
	public List<Class<? extends Unit>> getWinnables()
	{
		if(preCondition())
		{
			assert target.getWinnables().size() > 0;
		}
		return null;
	}

	@Override
	public String getName()
	{
		if(preCondition())
		{
			assert target.getName().length() > 0;
		}
		return null;
	}

	@Override
	public String getImage()
	{
		return ignored();
	}

	@Override
	public UnitBehaviour getUnitBehaviour()
	{
		return ignored();
	}
}
