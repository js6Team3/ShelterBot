package js6team3.tbot.entity.report;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

/**
 * Animal daily photo: file name and path, file size (Ежедневная фотография животного: имя, путь и размер фото)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "photo")
public class Photo {
    // photo's id (Идентификатор фотографии)
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    private String id;

    // animal id (id животного)
    @Column(name = "animal_id")
    private Long animalId;

    // Photo's date and time (дата и время фотографии)
    @Column(name = "photo_time")
    private LocalDateTime dayTime;

    // photo's path and name, and media type (Путь и имя файла, тип медиа данных)  photo      bytea
    private String name;
    private String contentType;

    // photo's size (Размер файла)
    private Long size;

    // photo file (Массив бинарного файла фотографии)
    @Lob
    private byte[] filePhoto;

    // daily report link (ежедневное фото животного прилагается к ежедневному отчету)
    @OneToOne
    private Report report;
}