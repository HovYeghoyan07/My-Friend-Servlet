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
public class FriendRequest {
    private int id;
    private User fromId;
    private User toId;
    private Date date;
}
