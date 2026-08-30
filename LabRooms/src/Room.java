import java.util.ArrayList;

public class Room {
    private String name;
    private ArrayList<Room> accessibleRooms;
    public Room(String name) {
        this.name = name;
        this.accessibleRooms = new ArrayList<>();
    }
    public String getName() {
        return this.name;
    }
    public ArrayList<String> getAccessibleRoomNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Room room : this.accessibleRooms) {
            names.add(room.getName());
        }
        return names;
    }
    public void addAccessibleRoom(Room room) {
        if (!this.accessibleRooms.contains(room)) {
            this.accessibleRooms.add(room);
        }
    }
    public Room getAccessibleRoom(String roomName) throws Exception {
        for (Room room : this.accessibleRooms) {
            if (room.getName().equals(roomName)) {
                return room;
            }
        }
        throw new Exception("Accessible room not found: " + roomName);
    }
    public static void connectRooms(Room room1, Room room2) {
        room1.addAccessibleRoom(room2);
        room2.addAccessibleRoom(room1);
    }
}