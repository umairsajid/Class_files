public int place(int[] listOne, int[] listTwo, int index)
    {
        int time, loindex=0, ltindex=0, spot=1;

        if (index == 1)
        {
            if (listOne[loindex] <= listTwo[ltindex])
            {
                return listOne[loindex];
            }    
            else 
                return listTwo[ltindex];
        }
        if (listOne[loindex] <= listTwo[ltindex])
            {
                time = listOne[loindex];
            }    
            else 
                time = listTwo[ltindex];
        while(index != spot)
        {
            //checks if both lists in still in same time
            if (listOne[loindex] == listTwo[ltindex] && listOne[loindex] == time) 
            {
                loindex++;
                ltindex++;
            }
            //checks if both lists in a new time
            else if (listOne[loindex] == listTwo[ltindex] && listOne[loindex] != time)
            {
                //loindex++;
                //ltindex++;
                spot++;
                time = listOne[loindex];
            }
            //when listtwo goes into new time group
            else if (listOne[loindex] < listTwo[ltindex] && listOne[loindex] == time)
            {
                while (listOne[loindex] != listTwo[ltindex])
                {
                    loindex++;
                    if (listOne[loindex] != time && listOne[loindex] != listTwo[ltindex])
                    {
                        spot++;
                        if(index == spot)
                            return listOne[loindex];
                    }
                }
                time = listOne[loindex];
                spot++;
            }    
            //when listone goes into new time group
            else if (listOne[loindex] > listTwo[ltindex] && listTwo[ltindex] == time)
            {
                while (listOne[loindex] != listTwo[ltindex])
                {
                    ltindex++;
                    if(listTwo[ltindex] != time && listTwo[ltindex] != listOne[loindex])
                    {
                        spot++;
                        if(index == spot)
                            return listOne[loindex];
                    }
                }
                time = listTwo[ltindex];
                spot++;
            }
            //when both go into new time groups
            else if (listOne[loindex] != time && listTwo[ltindex] != time)
            {
                if (listOne[loindex] > listTwo[ltindex])
                    time = listTwo[ltindex];
                else
                    time = listOne[loindex];
                spot++;
            }    
        }     
        return time;
    }