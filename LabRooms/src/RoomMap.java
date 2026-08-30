import java.util.ArrayList;

public class RoomMap {
    private ArrayList<Room> rooms;
    private Room currentRoom;
    public RoomMap() {
        this.rooms = new ArrayList<>();
        this.currentRoom = null;
    }
    public void addRoom(Room room) throws Exception  {
        for (Room r : this.rooms) {
            if (r.getName().equals(room.getName()))  {
                throw new Exception("Room with name " + room.getName() + " already exists.");
            }
        }
        this.rooms.add(room);
    }
    public void addRooms(Room... mRooms) throws Exception {
        for (Room room : mRooms) {
            addRoom(room);
        }
    }
    public void setCurrentRoom(String roomName) throws Exception {
        for (Room room : this.rooms) {
            if (room.getName().equals(roomName)) {
                this.currentRoom = room;
            }
        }
        throw new Exception("Room not found: " + roomName);
    }
    public String getCurrentRoomName() throws Exception {
        if (this.currentRoom == null) {
            throw new Exception("Current room is not set.");
        }
        return this.currentRoom.getName();
    }
    public ArrayList<String> getAccessibleRoomNames() {
        if (this.currentRoom == null) {
            return new ArrayList<>();
        }
        return this.currentRoom.getAccessibleRoomNames();
    }

    public void moveToRoom(String roomName) throws Exception{
        if (this.currentRoom == null)  {
            throw new Exception("Current room is not set.");
        }
        Room targetRoom = this.currentRoom.getAccessibleRoom(roomName);
        this.currentRoom = targetRoom;
    }
    public boolean isValidPath(String... roomNames) {
        if (this.currentRoom == null) {
            return false;
        }
        Room temp = this.currentRoom;
        for (String name : roomNames) {
            try {
                temp= temp.getAccessibleRoom(name);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
    public void movePath(String... roomNames) throws Exception {
        if (!isValidPath(roomNames)) {
            throw new Exception("Invalid path provided.");
        }
        for (String name : roomNames) {
            moveToRoom(name);
        }
    }
    @Override
    public String toString() {
        String result = "";
        for (Room room : this.rooms) {
            result += room.getName();
            if (this.currentRoom != null && room == this.currentRoom) {
                result += " <- current room";
            }
            result += "\n";
        }
        return result;
    }
}
