A[] rLane.street_length < 100 and mLane.street_length > 100
E<> rLane.street_length <= 100 and mLane.street_length <=  100
A[] ((carOne.position == 5 and carOne.sensor1 == 1 and carOne.sensor2 ==1 and carOne.changed == true) imply (carTwo.position != 5 ))
A[] ((carOne.position == 5 and carOne.sensor1 == 1 and carOne.sensor2 ==1 and carOne.changed == true) imply (mLane.middleLane == true ))