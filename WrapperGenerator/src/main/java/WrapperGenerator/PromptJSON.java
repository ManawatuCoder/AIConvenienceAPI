package WrapperGenerator;

import java.util.ArrayList;
import java.util.List;

public class PromptJSON {
    public List<Message> messages = new ArrayList<>();

    public static class Message {
        public String role;
        public String content;
        public String roles[] = {"user", "system"};

        public Message(int role, String content) { //Should role be required every call?
            this.role = roles[role];
            this.content = content;
        }
    }

    public void addMessage(int role, String content){
        messages.add(new Message(role, content));
    }

    int messageCount(){
        return messages.size();
    }
}
