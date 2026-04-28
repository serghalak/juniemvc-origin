package guru.springframework.juniemvc.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private Integer id;
    private Integer version;

    @NotNull
    private String name;

    private String email;

    private String phoneNumber;

    @NotNull
    private String addressLine1;

    private String addressLine2;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String postalCode;

    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
