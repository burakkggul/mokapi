package tr.com.burakgul.mokapi.dto.response;

import lombok.Getter;
import lombok.Setter;
import tr.com.burakgul.mokapi.model.Company;

@Getter
@Setter
public class CompanyResponse {

    private String name;
    private String catchPhrase;
    private String bs;

    public CompanyResponse(Company company) {
        this.name = company.getName();
        this.catchPhrase = company.getCatchPhrase();
        this.bs = company.getBs();
    }
}
