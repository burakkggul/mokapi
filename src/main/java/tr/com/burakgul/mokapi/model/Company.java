package tr.com.burakgul.mokapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.burakgul.mokapi.dto.request.CompanyRequest;

@Getter
@Setter
@NoArgsConstructor
public class Company {
    private String name;
    private String catchPhrase;
    private String bs;

    public void setCompanyRequest(CompanyRequest companyRequest) {
        this.name = companyRequest.getName();
        this.catchPhrase = companyRequest.getCatchPhrase();
        this.bs = companyRequest.getBs();
    }
}
