package ptit.wibulord.webfilm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
    //To
    private String recipient;
    private String msgBody;
    private String subject;
}