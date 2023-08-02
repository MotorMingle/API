package fr.motormingle.api.dto.mingler.get;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class MinglerTagGet {
    @SerializedName("tag")
    private final String tag;
}
