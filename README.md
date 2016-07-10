# DFS-SearchAlgorithm Demo

- Make a map txt

for example：

000000000
000000000
002222220
002020000
002020000
002020000
002000000
002000000

In which 0 for empty， 2 for  wall

- Sample 

```
  Map map = new Map("C:\\Users\\Mike\\Desktop\\map.txt");
  map.printMap();
  System.out.println();
  RouteFinder finder = new RouteFinder(map,0,0,4,3);
  finder.findBestRoute();
```

- output 

```
0	0	0	0	0	0	0	0	0	
0	0	0	0	0	0	0	0	0	
0	0	2	2	2	2	2	2	0	
0	0	2	0	2	0	0	0	0	
0	0	2	0	2	0	0	0	0	
0	0	2	0	2	0	0	0	0	
0	0	2	0	0	0	0	0	0	
0	0	2	0	0	0	0	0	0	

Map Load Complete, 0 for empty, 2 for road block

start nav from (0,0)to （3,4)
Best route21steps, total possible routes：3569664 total number of recursion45789375
Best route: 
(0,1) (0,2) (0,3) (0,4) (0,5) (0,6) (0,7) (0,8) (1,8) (2,8) (3,8) (4,8) (5,8) (6,8) (6,7) (6,6) (6,5) (6,4) (6,3) (5,3) (4,3) 
*	*	*	*	*	*	*	*	*	
 	 	 	 	 	 	 	 	*	
 	 	█	█	█	█	█	█	*	
 	 	█	 	█	 	 	 	*	
 	 	█	*	█	 	 	 	*	
 	 	█	*	█	 	 	 	*	
 	 	█	*	*	*	*	*	*	
 	 	█	 	 	 	 	 	 	

Process finished with exit code 0

```

