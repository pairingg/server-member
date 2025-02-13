package pairing.member.dto.response;

import lombok.Getter;
import pairing.member.common.Drinking;
import pairing.member.common.Smoking;

import java.util.List;

@Getter
public class DrinkAndSmokeResponse {
    private List<Smoking> smokings = Smoking.getAll();
    private List<Drinking> drinkings = Drinking.getAll();
}
