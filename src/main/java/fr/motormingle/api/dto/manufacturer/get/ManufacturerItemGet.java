package fr.motormingle.api.dto.manufacturer.get;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ManufacturerItemGet {
    @SerializedName("id")
    private final Long id;

    @SerializedName("name")
    private final String name;
}
