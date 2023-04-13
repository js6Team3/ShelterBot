package js6team3.tbot.entity.report;

import jakarta.persistence.*;
import lombok.*;

/**
 * Animal daily photo: file name and path, file size (Ежедневная фотография животного: имя, путь и размер фото)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "photo")
public class ReportPhoto {
    // photo's id (Идентификатор фотографии)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    // photo's path and name (Путь и имя файла)
    @Column(name = "filename")
    private String fileName;

    // photo's size (Размер файла)
    @Column(name = "filesize")
    private long fileSize;

    // photo file (Массив бинарного файла фотографии)
    @Lob
    private byte[] fileBytes;

    // daily report link (Связь c ежедневным отчетом)
    @OneToOne
    private Report report;
}
