package shibin.kmp.androidiconswitcher

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import io.github.shibin.iconswitcher.provider.RemoteProvider

class FirestoreProvider(
    private val firestore: FirebaseFirestore
) : RemoteProvider {

    private var listener: ListenerRegistration? = null

    override fun observe(
        onChanged: (String) -> Unit,
        onError: (Throwable) -> Unit
    ) {

        listener = firestore
            .collection("configuration")
            .document("icon")
            .addSnapshotListener { snapshot, error ->

                if (error != null) {
                    onError(error)
                    return@addSnapshotListener
                }

                val activeIcon =
                    snapshot?.getString("activeIcon")
                        ?: return@addSnapshotListener

                onChanged(activeIcon)
            }
    }

    override fun stop() {
        listener?.remove()
    }

}
