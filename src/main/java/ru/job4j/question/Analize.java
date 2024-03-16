package ru.job4j.question;

import java.util.HashMap;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        HashMap<Integer, String> previousMap = new HashMap<>();
        HashMap<Integer, String> currentMap = new HashMap<>();
        for (User oneUser : previous) {
            previousMap.put(oneUser.getId(), oneUser.getName());
        }
        for (User oneUser : current) {
            currentMap.put(oneUser.getId(), oneUser.getName());
        }
        for (User oneUser : current) {
            int tempId = oneUser.getId();
            if (previousMap.containsKey(tempId)) {
                String tempName = previousMap.get(tempId);
                if (!oneUser.getName().equals(tempName)) {
                    info.setChanged(info.getChanged() + 1);
                }
                previousMap.remove(tempId);
                currentMap.remove(tempId);
            }
        }
        info.setDeleted(previousMap.size());
        info.setAdded(currentMap.size());
        return info;
    }
}
