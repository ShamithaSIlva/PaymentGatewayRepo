package paymentgateway.orb;

import org.omg.CORBA.ORB;

import paymentgateway.SecurePaymentProcessorPOA;
import paymentgateway.util.EncryptionDecryption;

public class SecurePaymentProcessorORB extends SecurePaymentProcessorPOA
{
	 private ORB orb;

	 public void setORB(ORB orb_val) {
	   orb = orb_val; 
	 }
	 
	@Override
	public String processPayment( String account_number, String iban, String amount )
	{
		// After Decrypt
		System.out.println( "Account Number ENC: "+EncryptionDecryption.decrypt( account_number )+" IBAN ENC: "+EncryptionDecryption.decrypt( iban )+ " Ammount ENC: "+EncryptionDecryption.decrypt( amount ));
		return "Transaction Successful..!!";
	}

	@Override
	public void shutdown()
	{
		orb.shutdown(false);
	}

}
