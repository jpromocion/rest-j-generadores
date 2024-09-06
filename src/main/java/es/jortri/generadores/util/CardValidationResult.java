package es.jortri.generadores.util;

import es.jortri.generadores.enumerados.CardCompany;

/**
 * Container for validation result
 * https://gist.github.com/halienm/8de7fffa3db82a806724112c5eb88d67
 */
public class CardValidationResult {

    private boolean valid;
    private CardCompany cardType;
    private String error;
    private String cardNo;

    public CardValidationResult(String cardNo, String error) {
        this.cardNo = cardNo;
        this.error = error;
    }
    public CardValidationResult(String cardNo, CardCompany cardType) {
        this.cardNo = cardNo;
        this.valid = true;
        this.cardType = cardType;
    }
    public boolean isValid() {
        return valid;
    }
    public CardCompany getCardType() {
        return cardType;
    }
    public String getError() {
        return error;
    }
    public String cardNo() {
        return this.cardNo;
    }
    public String getMessage() {
        return cardNo + "    >>    " + ((valid) ? ("card: " + this.cardType ): error) ;
    }
	
}
