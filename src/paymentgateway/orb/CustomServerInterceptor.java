package paymentgateway.orb;

import org.omg.Dynamic.Parameter;
import org.omg.PortableInterceptor.ClientRequestInfo;
import org.omg.PortableInterceptor.ClientRequestInterceptor;
import org.omg.PortableInterceptor.RequestInfo;
import org.omg.PortableInterceptor.ServerRequestInfo;
import org.omg.PortableInterceptor.ServerRequestInterceptor;

public class CustomServerInterceptor extends org.omg.CORBA.LocalObject implements ServerRequestInterceptor
{
	public String name() {
		return "CustomServerInterceptor";
	}

	public void destroy() {}

	// ClientRequestInterceptor operations 
/*	public void send_request(ClientRequestInfo ri) {
		logger(ri, "send_request");
	}

	public void send_poll(ClientRequestInfo ri) {
		logger(ri, "send_poll");
	}

	public void receive_reply(ClientRequestInfo ri) {
		logger(ri, "receive_reply");
	}

	public void receive_exception(ClientRequestInfo ri) {
		logger(ri, "receive_exception");
	}

	public void receive_other(ClientRequestInfo ri) {
		logger(ri, "receive_other");
	}
*/
	// Server interceptor methods 
	public void receive_request_service_contexts(ServerRequestInfo ri) {
		logger(ri, "receive_request_service_contexts");
	}

	public void receive_request(ServerRequestInfo ri) {
		logger(ri, "receive_request");
	}

	public void send_reply(ServerRequestInfo ri) {
		logger(ri, "send_reply");
	}

	public void send_exception(ServerRequestInfo ri) {
		logger(ri, "send_exception");
	}

	public void send_other(ServerRequestInfo ri) {
		logger(ri, "send_other");
	}

	// Trivial Logger 
	public void logger(RequestInfo ri, String point) {
		System.out.println("Request ID:" + ri.request_id()
			+ " at " + name() + "." + point);
	}
}