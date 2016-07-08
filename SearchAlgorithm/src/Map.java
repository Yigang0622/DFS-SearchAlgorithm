import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by Mike on 7/7/2016.
 */
public class Map {

    public MapPoint[][] getMapArr() {
        return mapArr;
    }

    private MapPoint[][] mapArr;
    int mapHeight;
    int mapWidth;

    public Map (String path){
        readMapFromFile(path);
    }


    public void setPoint(int i,int j,int type){
        mapArr[i][j].setType(type);
    }

    public MapPoint getPoint(int i,int j){
        return mapArr[i][j];
    }



    public void printMap(){
        for (int i =0;i<mapHeight;i++){
            for (int j = 0;j<mapWidth;j++){
                System.out.print(mapArr[i][j].getType()+"\t");
            }
            System.out.println();
        }
    }

    public void readMapFromFile(String path){

        int mapWidth = 0;
        int mapHeight = 0;

        int[][] tempMap = new int[100][100];

        try {
            String encoding="GBK";
            File file=new File(path);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    mapWidth = lineTxt.length();

                    for (int i=0;i<mapWidth;i++){
                        tempMap[mapHeight][i] = Integer.parseInt(String.valueOf(lineTxt.charAt(i)));
                    }

                    mapHeight++;
                }
                read.close();
            }else{
                System.out.println("Can't find specific file");
            }
        } catch (Exception e) {
            System.out.println("Read Error");
            e.printStackTrace();
        }

        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;


        this.mapArr = new MapPoint[mapHeight][mapWidth];
        for (int i=0;i<mapHeight;i++){
            for (int j=0;j<mapWidth;j++){
                this.mapArr[i][j] = new MapPoint(tempMap[i][j]);
            }
        }



    }

}
