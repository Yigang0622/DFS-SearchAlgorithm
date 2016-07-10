import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

    private LinkedList tempList = new LinkedList();
    private LinkedList bestList = new LinkedList();

    private int routeCounter = 0;
    private int recurseCounter = 0;

    public RouteFinder(Map map,int startX,int startY,int targetX,int targetY){
        this.map = map;
        this.startX = startX;
        this.startY = startY;
        this.targetX = targetX;
        this.targetY = targetY;
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
            routeCounter++;

            if (depth<minDepth){
                minDepth = depth;
                bestMap = map.getMapArr();
                bestList.clear();
                bestList.addAll(tempList);
            }
            return; //达到最优路径
        }

        for (int k=0;k<=3;k++){
            nextX = x + next[k][0];
            nextY = y + next[k][1];

            if (nextX<0 || nextX>map.mapHeight-1 || nextY < 0 || nextY>map.mapWidth-1){
                continue;
            }

            if (!map.getPoint(nextX,nextY).isOccupied()){

                tempList.add(new Point(nextX,nextY));
                map.setPoint(nextX,nextY,1);
                dfs(nextX,nextY,depth+1);
                recurseCounter++;
                tempList.removeLast();
                map.setPoint(nextX,nextY,0);
            }

        }
        return;
    }

    public void findBestRoute(){

        System.out.println("Map Load Complete, 0 for empty, 2 for road block\n");
        System.out.println("start nav from ("+startY+","+startX+")to （"+targetY+","+targetX+")");
        dfs(startX,startY,0);
        System.out.println("Best route"+minDepth+"steps, total possible routes："+routeCounter+" total number of recursion"+recurseCounter);
        printMapWithRoute();

    }

    private void printMapWithRoute(){

        System.out.println("Best route: ");

        while (!bestList.isEmpty()){
            Point point = (Point) bestList.poll();
            System.out.print("("+point.x+","+point.y+") ");
            bestMap[point.x][point.y].setType(MapPoint.MAP_PONIT_OCCUPIED);
        }

        System.out.println();

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
