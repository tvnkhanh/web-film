package ptit.wibulord.webfilm.model;

import lombok.*;

// Annotations
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
    //To
    private String recipient;
    private String msgBody;
    private String subject;
}