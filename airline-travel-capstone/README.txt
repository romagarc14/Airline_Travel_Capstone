 __  _
 \ `/ |
  \__`!
  / ,' `-.__________________
 '-'\_____                LI`-.
    <____()-=O=O=O=O=O=[]====--)
hjw   `.___ ,-----,_______...-'
           /    .'
          /   .' AIRLINE TRAVEL CAPSTONE
         /  .'   PROJECT BY ROSA GARCIA      
         `-'
------------------------------------------------

SCENARIO
A client is looking to migrate their analogue flight information system to digital. The client partners with 5 major airlines to enable service to travelers nationwide. There are 6 to 12 hubs nationwide and not all airlines go to all hubs. 

On a daily basis there are, on average, 30 flights across all airports and utilize a mixture of all airports and airlines. Each flight varies in price based on distance or hub. Assume that each stop along the way adds 45 minutes to the total travel time but reduces price. 

------------------------------------------------

SPECIFICATIONS
In the flight information system, the client should be able to do the following:
· Add new airlines and associate what airports it serves
· Delete airlines
· Add new flights and associate which airline the flights are with, as well as the destination and departure airport
· Delete flights
· Retrieve all flight information
· Retrieve all Airport Hub information
· Retrieve all Airline information
· Calculate flight prices based on distance
· The cheapest or shortest flight between two airports
· Retrieve all flight information associated with a specific airline at a   specific airport

------------------------------------------------

ACTUAL ROUTES
CRUD Operations for each individual class
· In Airlines
  - Create airline
  - Update or Delete airline by Id
  - Retrieve all airlines, retrieve by Id, and retrieve by Name
· In Airports
  - Create airport (linked to airlines)
  - Update or Delete airport by Id
  - Retrieve all airports, retrieve by Id, retrieve by Name, retrieve by City, and retrieve by Airline Id
· In Flights 
  - Create flight (linked to airlines)
  - Update or Delete flight by Id
  - Retrieve all flights, retrieve by Id, retrieve all flights by airline Id, retrieve all flights by Price*, retrieve all flights by Minutes Traveled**, retrieve all by Destination, retrieve flights by Airport and Airline, retrieve flights by Airport and Destination***

*Price is calculated by Distance, Minutes Traveled, and Airline Rate. Price is sorted by ascending order, showing the least expensive flight first.
**Minutes Traveled is sorted by ascending order, showing the lowest number of minutes first.
***Flights retrieved by Airport and Destination can be sorted by minutes traveled or by price. These options show the cheapest or shortest flights between airports.

------------------------------------------------
CUSTOM ROUTES
Airlines: 
· Find by Name
     /airlines/name/{name}

Airports
· Find by Name
     /airports/name/{name}
· Find by City
     /airports/city/{city}	
· Find by Airline Id
     /airports/airlines/{airlineId}

Flights
· Find all, sort by Price
      /flights/price
· Find all, sort by Minutes Traveled
      /flights/time
· Find by Airline Id
      /flights/airlines/{airlineId}
· Find by Airport Name
      /flights/airports/{airportName}
· Find by Destination
      /flights/destination/{destination}
· Find by Airport Name and Airline Id
      /flights/{airportName}/{airlineId}
· Find distance between two locations, sorted by Price
      /cheap/{airportName}/{destination}
· Find distance between two locations, sorted by Minutes Traveled
      /short/{destination}/{airportName}

------------------------------------------------

DEFAULT ENTRIES
Airlines:
(1)  Alaska (SEA, SFO, LAX)
(2)  American (DCA, DFW, JFK, ORD, PHL, PHX)
(3)  Delta (ATL, LAX, JFK, SEA)
(4)  Spirit (ORD, DFW)
(5)  United (DEN, IAH, LAX, ORD, SFO) 


Airport Hub and City:
(1)  ATL  Atlanta
(2)  DCA  Washington D.C.
(3)  DFW  Dallas
(4)  DEN  Denver
(5)  IAH  Houston
(6)  JFK  New York City
(7)  LAX  Los Angeles
(8)  ORD  Chicago
(9)  PHL  Philadelphia
(10) PHX  Phoenix
(11) SEA  Seattle
(12) SFO  San Francisco


Flights (30 Total): 
Travel time assumes ideal conditions, i.e., no winds with constant speed, and the same aircraft. Additional stops add 45 minutes to the total time. Price is provided below; however, it is automatically caluated when the flight is added.

ALASKA		rate (0.95)
	SEA to SFO |  90m |  680 miles | $358.89
	SFO to SEA |  90m |  680 miles | $358.89
	LAX to SEA | 120m |  960 miles | $380.00
	LAX to SFO to SEA | 135m + 45m |  960 miles | $253.33

AMERICAN	rate (1.0)
	
	DCA to DFW | 150m | 1180 miles | $393.33
	DFW to ORD | 105m |  800 miles | $380.95
	JFK to ORD |  90m |  625 miles | $347.22
	ORD to JFK |  90m |  625 miles | $347.22
	PHX to DFW | 120m |  885 miles | $368.75
	DFW to JFK | 180m | 1350 miles | $375.00
	ORD to DFW to PHX | 225m + 45m | 1450 miles | $268.52
	DCA to PHL to JFK |  40m + 45m |  290 miles | $170.59
	DFW to DCA to JFK | 180m + 45m | 1350 miles | $300.00

DELTA		rate (1.1)
	LAX to SEA | 120m |  960 miles | $440.00
	ATL to JFK |  90m |  800 miles | $488.89
	JFK to ATL |  90m |  800 miles | $488.89
	JFK to SEA | 300m | 2260 miles | $414.33
	JFK to LAX | 315m | 2365 miles | $412.94
	LAX to JFK | 315m | 2365 miles | $412.94
	LAX to ATL to JFK | 330m + 45m | 2365 miles | $346.87

SPIRIT		rate (0.85)
	DFW to ORD | 105m |  800 miles | $323.81
	ORD to DFW | 105m |  800 miles | $323.81

UNITED		rate (1.05)
	IAH to DEN | 110m |  880 miles | $420.00
	LAX to IAH | 180m | 1370 miles | $399.58
	IAH to ORD | 120m |  940 miles | $411.25
	DEN to SFO | 120m |  945 miles | $413.44
	ORD to LAX | 225m | 1740 miles | $406.00
	ORD to SFO | 240m | 1855 miles | $405.78
	ORD to DEN to LAX | 240m + 45m | 1740 miles | $320.53	
	ORD to DEN to SFO | 240m + 45m | 1855 miles | $341.71
	
------------------------------------------------

Price Calculation:
	a = airline rate coefficient
	d = distance
	t = time
	c = conversion (50)

	price = (a * d * c) / t

------------------------------------------------

Test Sample:
JETBLUE		rate (0.9)
	DFW to JFK | 180m | 1350 miles | $337.50
	
------------------------------------------------	

