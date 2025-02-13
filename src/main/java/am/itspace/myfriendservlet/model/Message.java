package am.itspace.myfriendservlet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    private int id;
    private User fromId;
    private User toId;
    private String message;
    private Date date;

}
