package io.mehow.credentials

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.credentials.providerevents.ProviderEventsManager
import androidx.credentials.providerevents.transfer.CredentialTypes
import androidx.credentials.providerevents.transfer.ImportCredentialsRequest
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.importButton).setOnClickListener {
            lifecycleScope.launch { importCredentials() }
        }
    }

    private suspend fun importCredentials() {
        val request = ImportCredentialsRequest(
            credentialTypes = setOf(CredentialTypes.CREDENTIAL_TYPE_BASIC_AUTH),
            knownExtensions = emptySet(),
        )
        val message = try {
            val response = ProviderEventsManager.create(this).importCredentials(this, request)
            "Received ${response.response.responseJson.length} chars from ${response.callingAppInfo.packageName}"
        } catch (e: Exception) {
            Log.e("Credentials", "Import failed", e)
            "Failed: ${e.message}"
        }
        Log.d("Credentials", message)
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
