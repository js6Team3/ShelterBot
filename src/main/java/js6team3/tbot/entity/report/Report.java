package js6team3.tbot.entity.report;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * The adopter daily report of the animal content, incl.
 * animal diet
 * animal well-being and health at new environment
 * animal change in behavior
 * actual animal photo
 * Ежедневный отчет усыновителя о содержании животного
 * В ежедневный отчет входит следующая информация:
 * Рацион животного
 * Общее самочувствие и привыкание к новому месту
 * Изменение в поведении: отказ от старых привычек, приобретение новых
 * Фото животного
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "report")
public class Report {
    //    Report number (номер отчета)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    //    reporting date & time (Дата и время предоставления отчета)
    @Column(name = "report_time")
    private LocalDateTime dayTime;

    //  adopter number (id усыновителя)
    @Column(name = "adopter_id")
    private Long adopterId;

    //  animal number (id животного)
    @Column(name = "animal_id")
    private Long animalId;

    // animal daily diet (Рацион животного)
    @Column(name = "diet")
    private String diet;

    // general health of animal (Общее самочувствие и привыкание к новому месту)
    @Column(name = "health")
    private String health;

    // change of animal's behavior (Изменение в поведении: отказ от старых привычек, приобретение новых)
    @Column(name = "behavior")
    private String behavior;

    // id daily photo link (Ежедневный отчет подтверждается ежедневным фото животного)
    @OneToOne
    private Photo photo;
}
