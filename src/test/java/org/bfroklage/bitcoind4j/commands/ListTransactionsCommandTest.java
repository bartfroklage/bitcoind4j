package org.bfroklage.bitcoind4j.commands;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import junit.framework.TestCase;
import org.apache.http.client.ClientProtocolException;

public class ListTransactionsCommandTest extends TestCase {

	public void testToJson() throws ClientProtocolException,
			URISyntaxException, IOException {
		String json = new ListTransactionsCommand().toJson();
		assertTrue(json.contains("\"listtransactions\""));
		assertTrue(json.contains("[\"\",10,0]"));
		assertTrue(json.contains("params"));
	}

	public void testParseResponse() {
		String response = "{\"result\":[{\"account\":\"\",\"address\":\"1XXPMkysxTjhRy5Vf44xhwgPf4QreZPGx\",\"category\":\"send\",\"amount\":-0.20854653,\"fee\":-0.00010000,\"confirmations\":3874,\"blockhash\":\"X07U3fRAV922IeNLNW2b81BIN3Y7IQSA8NTba4XTUOZReRZNDQ9SSaaIY3ANM96c\",\"blockindex\":567,\"blocktime\":1418669026,\"txid\":\"Xb8AWQFN9I0SU4FM8GcC1EcRaPYUd4GYPQWbOCTXY1XReH8KS7SBB1N8G4VAF0HX\",\"walletconflicts\":[],\"time\":1419012503,\"timereceived\":1419584177},{\"account\":\"\",\"address\":\"1XXsELCdjB69Jumk2X7Jpc1UCjoSKcjY1\",\"category\":\"receive\",\"amount\":0.00013063,\"confirmations\":3234,\"blockhash\":\"XcYPPLJEVDV81A5e77cPS1WYOKad3RNeAYJZBJ8QICLfNb9SPVHOK2GTTES6AFOR\",\"blockindex\":541,\"blocktime\":1419085458,\"txid\":\"XAFQD380Ba88OS8Mc9M1WKOESGR4EDI96f4K6Ud3H7AGOIbWCZNAde7XU1R8bNVS\",\"walletconflicts\":[],\"time\":1419085458,\"timereceived\":1419584192},{\"account\":\"\",\"address\":\"1XXpVUiCVrw9uhXfkp2QHMP95kc2Kv6t7\",\"category\":\"send\",\"amount\":-0.00012063,\"fee\":-0.00001000,\"confirmations\":3230,\"blockhash\":\"XDJCON46aMQT5UORSFPHCOeD6G3H5ZEZ5M9PXdaU8M9YED08EOM7aGSSNI9LB4ea\",\"blockindex\":363,\"blocktime\":1419086907,\"txid\":\"XYIVVOW2GHSb01VX2R3eUCZN4K0Q7VED68PD65C9T74fcUQHRPKU8S8PTAEdZ55D\",\"walletconflicts\":[],\"time\":1419086907,\"timereceived\":1419584192},{\"account\":\"\",\"address\":\"1XXK12DA2NQ44bHYkcqVSWhoy65Tg3BJL\",\"category\":\"send\",\"amount\":-0.50000000,\"fee\":0.00000000,\"confirmations\":2375,\"blockhash\":\"XfS9dbV75ZKSdL81SVEfSE0JCSWe6SUTFND6XbL1389d58LI82YQ3QC9OHV69GMD\",\"blockindex\":15,\"blocktime\":1419586302,\"txid\":\"XfdINQ9BPUE0MVbIPc7XC793WLR0VX792KfaPCLTLdcf1OITc4Bd5S1c7SM5IJGZ\",\"walletconflicts\":[],\"time\":1419586261,\"timereceived\":1419586261},{\"account\":\"\",\"address\":\"1XXkY2xPzWC5vYEhFNA9avfimSnndqP6P\",\"category\":\"send\",\"amount\":-2.00000000,\"fee\":0.00000000,\"confirmations\":2349,\"blockhash\":\"XfNSNb6IQ0MQZBTa3eDWSfWV5PbcSQ24VKYZ49YCcSTM9VZXMY34OUb5WF3COW2D\",\"blockindex\":31,\"blocktime\":1419600340,\"txid\":\"X34JUUS4TRJQcfDDRADOcI9LTfWaS4AFSa2U0FeR4e54UA3bIUNQTNLQGSTdT4ZS\",\"walletconflicts\":[],\"time\":1419597603,\"timereceived\":1419597603},{\"account\":\"\",\"address\":\"1XXGg3aBLY8JSNGVJx5zYd1uw8Wob9smT\",\"category\":\"send\",\"amount\":-2.00000000,\"fee\":-0.00030000,\"confirmations\":2217,\"blockhash\":\"XDJJKdSTMb14AeUGILOF4GPBCK3WaNIP8BOQVQ13CCKbaLPCTTJBJ4bY47GSb41S\",\"blockindex\":7,\"blocktime\":1419667026,\"txid\":\"XXSYaRdRQaN7JIcBGJANDCTPK1BGHYFP6SSRBQVb6KaadTYV7A8cPBJI9SED4Ee0\",\"walletconflicts\":[],\"time\":1419666612,\"timereceived\":1419666612},{\"account\":\"\",\"address\":\"1XXsELCdjB69Jumk2X7Jpc1UCjoSKcjY1\",\"category\":\"receive\",\"amount\":0.00017804,\"confirmations\":2025,\"blockhash\":\"XOSeLJKVXJeYTS3VKF1TZ9J64PbFUPOHAA1DFe18BQ75LdT1SESDOf2QePEBbC7R\",\"blockindex\":396,\"blocktime\":1419772208,\"txid\":\"XKME3QTAVE7IP1ICBBHIEEQ3VT7fb1RRXSS69XTK5M8DGBKZDO632N8MfVHPJIJa\",\"walletconflicts\":[],\"time\":1419772208,\"timereceived\":1420374964},{\"account\":\"\",\"address\":\"1XXNoHffMbkFpxqis7gW1AuPvWPpZqobb\",\"category\":\"send\",\"amount\":-0.00007804,\"fee\":-0.00010000,\"confirmations\":2025,\"blockhash\":\"XOSeLJKVXJeYTS3VKF1TZ9J64PbFUPOHAA1DFe18BQ75LdT1SESDOf2QePEBbC7R\",\"blockindex\":397,\"blocktime\":1419772208,\"txid\":\"X4OC9726MR4U9NadFYGNLAMWCBA5NH8LK31CYNE0NeLPCeGAScY1d1DGeWbK5Y2S\",\"walletconflicts\":[],\"time\":1419772208,\"timereceived\":1420374965},{\"account\":\"\",\"address\":\"1XXDAUWNuSzWQ6XHhKVGqdsn754DT25As\",\"category\":\"send\",\"amount\":-2.00000000,\"fee\":-0.00080000,\"confirmations\":865,\"blockhash\":\"XMBNAMUYPM9CZ1TE8QYLIefZZQ5AEeWK73GZXePFBSNW2SeePG3AZZEBHY7RRfbe\",\"blockindex\":971,\"blocktime\":1420393795,\"txid\":\"XI9KcW01EUM9LZ3B5EOH6E8WN254BE0UXGEKWIeKLCbZGCY8PHEDEM5EQcBc3CPE\",\"walletconflicts\":[],\"time\":1420393555,\"timereceived\":1420393555},{\"account\":\"\",\"address\":\"1XX86vzVf9wDSeKW6YU6DuNhwpmbpSWHw\",\"category\":\"send\",\"amount\":-2.03200000,\"fee\":-0.00080000,\"confirmations\":835,\"blockhash\":\"XXJJI42WXHYSP450aMdNMKKSadTEPXFXaTYPRTOVUFecFMFMbF6FJTSHPV2GGY67\",\"blockindex\":180,\"blocktime\":1420407135,\"txid\":\"XbNRHK1a06HF99GZdTLTbI9Od2Q0I9UMTZSfXYCfcRH4Z8MJa1TX7MJF46bMVSET\",\"walletconflicts\":[],\"time\":1420407060,\"timereceived\":1420407060}],\"error\":null,\"id\":\"1\"}";
		Object result = new ListTransactionsCommand().parseResponse(response);
		assertTrue(result instanceof List<?>);
		List<?> list = (List<?>) result;
		assertEquals(10, list.size());
	}
}
