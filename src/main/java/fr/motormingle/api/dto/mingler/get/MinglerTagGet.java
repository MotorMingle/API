package fr.motormingle.api.dto.mingler.get;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class MinglerTagGet {
    @SerializedName("tag")
    @NonNull
    private String tag;
}
