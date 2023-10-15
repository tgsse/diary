import org.gradle.kotlin.dsl.resolver.buildSrcSourceRootsFilePath
import java.io.File
import java.io.FileInputStream
import java.util.Properties

class Secrets {
    private val prop: Properties

    init {
        prop = Properties().apply {
            load(FileInputStream(File("apiKeys.properties")))
        }
    }

    val baseUrl: String = prop.getProperty("BASE_URL") ?: ""
    val oauthWebClientId: String = prop.getProperty("OAUTH_WEB-CLIENT_ID") ?: ""
    val mongoServiceId: String = prop.getProperty("MONGO_SERVICE_ID") ?: ""
    val ixStorePassword: String = prop.getProperty("STORE_PASSWORD") ?: ""
    val ixKeyPassword: String = prop.getProperty("KEY_PASSWORD") ?: ""
    val ixKeyAlias: String = prop.getProperty("KEY_ALIAS") ?: ""
    val ixKeystorePath: String = prop.getProperty("KEYSTORE_PATH") ?: ""
}
