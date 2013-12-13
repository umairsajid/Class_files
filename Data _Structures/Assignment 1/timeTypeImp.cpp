timeType::timeType(int hrs, int mins, int secs)
{
	if (hrs >=0 && hrs < 23)
		hours = hrs;
	else 
		hours = 0;
	if (mins >= 0 && mins < 60)
		minutes = mins;
	else 
		minutes = 0;
	if (secs >= 0 && secs < 60)
		seconds = secs;	
	else 
		seconds = 0;
}

timeType::timeType()
{
	hours = 0;
	minutes = 0;
	seconds = 0;	
}

void timeType::set(int hrs, int mins, int secs)
{
	if (hrs >=0 && hrs < 23)
		hours = hrs;
	else 
		hours = 0;
	if (mins >= 0 && mins < 60)
		minutes = mins;
	else 
		minutes = 0;
	if (secs >= 0 && secs < 60)
		seconds = secs;	
	else 
		seconds = 0;
}

void timeType::increment()
{
	seconds +=1;
	if (seconds > 59)
	{
		seconds -= 60;
		minutes ++;
	}
	if (minutes > 59)
	{
		minutes -=60;
		hours ++;
	}
	if (hours > 23)
	{
		hours = 0;
	}
}

void timeType::write() const
{
	printf("%02d:%02d:%02d\n", hours, minutes, seconds);
}


bool timeType::equal(timeType otherTime) const
{
	if(hours*3600+minutes*60+seconds == 
	    otherTime.hours*3600+otherTime.minutes*60+otherTime.seconds)
		return true;
	return false;
}


bool timeType::lessThan(const timeType otherTime) const
{
	if(hours*3600+minutes*60+seconds < 
	    otherTime.hours*3600+otherTime.minutes*60+otherTime.seconds)
		return true;
	return false;
}

