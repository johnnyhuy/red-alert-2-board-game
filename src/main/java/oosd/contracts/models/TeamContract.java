package oosd.contracts.models;

import de.vksi.c4j.Target;
import de.vksi.c4j.ClassInvariant;
import oosd.models.player.Team;

import static de.vksi.c4j.Condition.*;

public class TeamContract extends Team
{
	@Target
	private Team target;
	
    @ClassInvariant
    public void classInvariant()
    {
    	assert target.getName().length() > MINIMUM_NAME_SIZE : "name > NAME_SIZE";
    }
	
	public TeamContract(String name)
	{
		super(name);
		
		if(preCondition())
		{
			assert !name.isEmpty();
		}
	}
	
	@Override
	public String getName()
	{
		if (preCondition())
		{
			assert target.getName().length() > MINIMUM_NAME_SIZE : "name > NAME_SIZE";
		}
		return ignored();
	}
	

}
