package io.paulbaker.mitm;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by paul.baker on 6/15/17.
 */
@Data
@Component
@ConfigurationProperties("mitm")
public class ProxyProperties {
    private int port;

    private String keystoreDirectory;
    private String alias;
    private String password;
    private String commonName;
    private String organization;
    private String organizationalUnitName;
    private String certificateOrganization;
    private String certificateOrganizationUnitName;

    @PostConstruct
    public void initialize() {
        setKeystoreDirectory(getKeystoreDirectory().replaceAll("~", System.getProperty("user.home")));
    }
}
