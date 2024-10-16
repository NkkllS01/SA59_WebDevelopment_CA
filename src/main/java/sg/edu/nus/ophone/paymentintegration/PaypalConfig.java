package sg.edu.nus.ophone.paymentintegration;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

//Team3.Kuo Chi
@Configuration
public class PaypalConfig {

    @Value("${paypal.client.id}")
    private String clientId;
    @Value("${paypal.client.secret}")
    private String clientSecret;
    @Value("${paypal.mode}")
    private String mode;

    // mainly to let PayPal know whether this is for testing in sandbox or live
    @Bean
    public Map<String, String> paypalSdkConfig() {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("mode", mode);
        return configMap;
    }

    // for generating an access token used to authenticate API requests to PayPal
    @Bean
    public OAuthTokenCredential oAuthTokenCredential() {
        return new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
    }

    // managing API calls to PayPal, including sending requests and handling responses
    @Bean
    public APIContext apiContext() throws PayPalRESTException {
        // call for generating Access Token using our credentials
        APIContext context = new APIContext(oAuthTokenCredential().getAccessToken());
        // ensures the configuration map is attached to the API context, so PayPal can use the correct mode
        context.setConfigurationMap(paypalSdkConfig());
        return context;
    }

}
