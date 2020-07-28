package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._

class HelloControllerSpec extends PlaySpec with GuiceOneAppPerTest {

  "get" should {
    "クエリーパラメータがある場合は「Hello, namae!」というレスポンスを返す" in {
      val name = "namae"
      val request  = FakeRequest(GET, s"/?name=$name")
      val response = route(app, request).get
      assert(status(response) === 200)
      assert(contentAsString(response) === s"Hello, $name!")
    }

    """クエリーパラメータがない場合は「Please give a name as a query parameter named "name".」というレスポンスを返す""" in {
      val request  = FakeRequest(GET, "/")
      val response = route(app, request).get
      assert(status(response) === 200)
      assert(contentAsString(response) === """Please give a name as a query parameter named "name".""")
    }
  }
}