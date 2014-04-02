import dispatch._, Defaults._
import org.json4s._
import org.json4s.native.JsonMethods._
import scala.math._

import javax.crypto.spec.SecretKeySpec
import javax.crypto.Mac

import org.apache.commons.codec.binary.Hex

import java.net.{URI, URLDecoder, URLEncoder}

import scala.language.postfixOps

object ScalaBTCeClient {

  	def main(args: Array[String])
  	{
		val _secret = "<your secret>"
		val _key = "<your key>"
		val _nonce = java.lang.System.currentTimeMillis() / 10000;

		val enc = "UTF-8";

		val arguments = scala.collection.mutable.Map[String,String](
			"nonce" -> _nonce.toString,	// Add the dummy nonce.
			"method" -> "getInfo"  		// Add the method to the post data.
		) 
		
		val postData = arguments map { 
			case (key, value) => 
				(URLEncoder.encode(key, enc) + "=" + URLEncoder.encode(value, enc)) 
		} mkString("&")

		val secret = new SecretKeySpec(_secret.getBytes(enc), "HmacSHA512")
		val mac = Mac.getInstance("HmacSHA512")
		mac.init(secret)

		val result: Array[Byte] = mac.doFinal(postData.getBytes(enc))

		val headers = scala.collection.mutable.Map[String,String](
			"Key" -> _key,
			"Sign" -> new String(Hex.encodeHex(mac.doFinal(postData.getBytes(enc))))
		)

		val myRequest = url("https://btc-e.com/tapi") << arguments <:< headers
		val dataPromise = Http(myRequest.POST OK as.String)

		println(dataPromise())
    }

}