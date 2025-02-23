package com.noxcrew.launchy.data

import com.noxcrew.launchy.logic.Downloader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.absolutePathString
import kotlin.io.path.inputStream

@Serializable
data class Versions(
    val groups: Set<Group>,
    @SerialName("modGroups")
    private val _modGroups: Map<GroupName, Set<Mod>>,
    val servers: Set<Server> = emptySet(),
    val fabricVersion: String,
    val minecraftVersion: String
) {
    val nameToGroup: Map<GroupName, Group> = groups.associateBy { it.name }

    @Transient
    val modGroups: Map<Group, Set<Mod>> = _modGroups.mapNotNull { nameToGroup[it.key]?.to(it.value) }.toMap()
    val nameToMod: Map<ModName, Mod> = modGroups.values
        .flatten()
        .associateBy { it.name }

    companion object {
        suspend fun readLatest(url: String, target: Path, ignoreLocal: Boolean = false): Versions = withContext(Dispatchers.IO) {
            // Check against an environment variable that no user should ever have
            if (!ignoreLocal && System.getenv()["MCC_LAUNCHY_DEV"] == "13518961351") {
                println("Reading from local versions")
                val file = Path("versions.yml")
                Formats.yaml.decodeFromStream(serializer(), file.inputStream())
            } else {
                println("Fetching latest versions from $url to ${target.absolutePathString()}")
                Downloader.download(url, target)
                Formats.yaml.decodeFromStream(serializer(), target.inputStream())
            }
        }
    }
}
