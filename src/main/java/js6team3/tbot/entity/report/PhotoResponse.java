package js6team3.tbot.entity.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * a POJO object used to listing photo
 *
 * @author Andrei Popov
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoResponse {
    private String id;
    private Long animalId;
    private LocalDateTime dayTime;
    private String name;
    private String contentType;
    private Long size;
    private String url;
}