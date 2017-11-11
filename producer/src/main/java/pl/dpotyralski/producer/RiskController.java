package pl.dpotyralski.producer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RiskController {

    private static final int MAX_BIDS = 10;
    private static final String MICROSOFT = "Microsoft";

    @PostMapping(path = "/risks")
    public ResponseEntity<RiskResponse> checkRisk(@RequestBody RiskCheckRequest request) {

        if (isRequestSafe(request)) {
            return ResponseEntity.ok(new RiskResponse(Status.NOT_OK));
        }

        return ResponseEntity.ok(new RiskResponse(Status.OK));
    }

    private boolean isRequestSafe(@RequestBody RiskCheckRequest request) {
        return MICROSOFT.equals(request.getCompany()) && Integer.compare(request.getAmount(), MAX_BIDS) > 0;
    }


}
