import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by Mike on 7/7/2016.
 */
public class RouteFinder {

    private int minDepth = 9999999; //最小遍历深度
    private Map map;
    private int startX;
    private int startY;
    private int targetX;
    private int targetY;
    private MapPoint[][] bestMap;

    private Queue tempQueue = new ArrayDeque();
    private Queue bestQueue = new ArrayDeque();

    public RouteFinder(Map map,int startX,int startY,int targetX,int targetY){
        this.map = map;
        this.startX = startY;
        this.startY = startX;
        this.targetX = targetY;
        this.targetY = targetX;
        map.setPoint(startX,startY,MapPoint.MAP_PONIT_OCCUPIED);
    }

    private void dfs(int x,int y,int depth){
        int[][] next = {
                {0,1},//Right
                {1,0},//Down
                {0,-1},//Left
                {-1,0}//Up
        };

        int nextX,nextY;

        if (x == targetX && y == targetY){ //到达目标位置
            if (depth<minDepth){
                minDepth = depth;

                bestMap = map.getMapArr();

                System.out.println();
                if (tempQueue.size()!=0){
                    this.bestQueue = tempQueue;
                    printMapWithRoute();
                }

            }
            tempQueue.clear();

            return; //达到最优路径
        }

        for (int k=0;k<=3;k++){
            nextX = x + next[k][0];
            nextY = y + next[k][1];

            if (nextX<0 || nextX>map.mapHeight-1 || nextY < 0 || nextY>map.mapWidth-1){
                continue;
            }

            if (!map.getPoint(nextX,nextY).isOccupied()){
                tempQueue.offer(new Point(x,y));
                map.setPoint(nextX,nextY,1);

                dfs(nextX,nextY,depth+1);
                tempQueue.poll();
                map.setPoint(nextX,nextY,0);
            }

        }
        return;
    }

    public void findBestRoute(){
     //   map.getPoint(startX,startY).setType(MapPoint.MAP_PONIT_OCCUPIED);
        System.out.println("Map Load Complete, 0 for empty, 2 for road block");
        System.out.println("从（"+startY+","+startX+")导航到（"+targetY+","+targetX+")");
        dfs(startX,startY,0);

        System.out.println("最短路径"+minDepth);
       // printMapWithRoute();


    }

    public void printMapWithRoute(){

//
//        for (Object pointObj:bestQueue){
//            Point point = (Point) pointObj;
//            System.out.print(point.toString());
//            bestMap[point.x][point.y].setType(MapPoint.MAP_PONIT_OCCUPIED);
//        }
//        System.out.println("\n");

        for (int i =0;i<map.mapHeight;i++){
            for (int j = 0;j<map.mapWidth;j++){
                if (bestMap[i][j].getType() == MapPoint.MAP_PONIT_OCCUPIED){
                    System.out.print("*"+"\t");
                }else if (bestMap[i][j].getType() == MapPoint.MAP_PONIT_OBSTRUCTION){
                    System.out.print("█"+"\t");
                }else {
                    System.out.print(" "+"\t");
                }

            }
            System.out.println();
        }
    }

    class Point{
        int x;
        int y;

        public Point(int x,int y){
            this.x = x;
            this.y = y;
        }

        public String toString(){
            return "("+ x +", " +y +")";
        }
    }
}
