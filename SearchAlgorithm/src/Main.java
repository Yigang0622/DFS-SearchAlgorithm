public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Map map = new Map("C:\\Users\\Mike\\Desktop\\map.txt");
        map.printMap();
        System.out.println();
        RouteFinder finder = new RouteFinder(map,0,0,3,4);
        finder.findBestRoute();
    }
}
