package fr.motormingle.api.dto.manufacturer.get;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class ManufacturerItemGet {
    @SerializedName("id")
    @NonNull
    private Long id;

    @SerializedName("name")
    @NonNull
    private String name;
}
