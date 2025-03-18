package org.itmo;

import java.time.LocalDateTime;
import java.util.*;

class SpyMessenger {

    // password, message, password
    HashMap<String, ArrayList<Message>> map = new HashMap<>();

    void sendMessage(String sender, String receiver, String message, String passcode) {
        if (map.containsKey(receiver)) {
            ArrayList<Message> messages = map.get(receiver);
            if (messages.size() > 5) {
                Optional<Message> del = messages.stream().min(Comparator.comparing(Message::getTime));
                del.ifPresent(messages::remove);
            }
            messages.add(new Message(LocalDateTime.now(), message, passcode));
            map.put(receiver, messages);
        }
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(new Message(LocalDateTime.now(), message, passcode));
        map.put(receiver, messages);
    }

    String readMessage(String user, String passcode) {
        LocalDateTime now = LocalDateTime.now().minusNanos(1500000000);
        if (map.containsKey(user)) {
            ArrayList<Message> messages = map.get(user);
            Optional<Message> m = messages.stream().filter(it -> it.getTime().isAfter(now) && Objects.equals(it.getPassword(), passcode)).findFirst();
            if (m.isPresent()) {
                messages.remove(m.get());
                return m.get().getMessage();
            }
        }
        return null;
    }

}