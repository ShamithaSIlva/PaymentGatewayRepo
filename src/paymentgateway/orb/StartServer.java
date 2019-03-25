package paymentgateway.orb;

import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import paymentgateway.SecurePaymentProcessor;
import paymentgateway.SecurePaymentProcessorHelper;

public class StartServer {
	 
	  public static void main(String args[]) {
	    try
	    {
	    	
	    	// INTERCEPTORS
		  	Properties p = new Properties();
			p.put("org.omg.PortableInterceptor.ORBInitializerClass.paymentgateway.orb.InterceptorORBInitializer", "");
			ORB orb = ORB.init(args, p);
		    // INTERCEPTORS
	      // create and initialize the ORB //// get reference to rootpoa &amp; activate the POAManager
	      //ORB orb = ORB.init(args, null);      
	      POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
	      rootpoa.the_POAManager().activate();
	 
	      // create servant and register it with the ORB
	      SecurePaymentProcessorORB paymentProcessorORB = new SecurePaymentProcessorORB();
	      paymentProcessorORB.setORB(orb); 
	 
	      // get object reference from the servant
	      org.omg.CORBA.Object ref = rootpoa.servant_to_reference(paymentProcessorORB);
	      SecurePaymentProcessor href = SecurePaymentProcessorHelper.narrow(ref);
	 
	      org.omg.CORBA.Object objRef =  orb.resolve_initial_references("NameService");
	      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
	 
	      NameComponent path[] = ncRef.to_name( "ABC" );
	      ncRef.rebind(path, href);	       
	 
	      System.out.println("Payment Gateway Server ready and waiting ...");
	 
	      // wait for invocations from clients
	      for (;;){
		  orb.run();
	      }
	    } 
	 
	      
	    catch (Exception e) 
	    {
	        System.err.println("ERROR: " + e);
	        e.printStackTrace(System.out);
	    } 
	  }
	}
