package com.devix.employemanagement.dtos.dashboardDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnouncementDto {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
}
