package com.husam.to_do.data

import android.os.Parcelable
import androidx.annotation.NonNull
import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

// Your model has to extend RealmObject. Furthermore, the class must be annotated with open (Kotlin classes are final
// by default).
@Parcelize
open class Task(
    // You can put properties in the constructor as long as all of them are initialized with
    // default values. This ensures that an empty constructor is generated.
    // All properties are by default persisted.
    // Properties can be annotated with PrimaryKey or Index.
    // If you use non-nullable types, properties must be initialized with non-null values.
    @PrimaryKey var id: Long = 0,

    @NonNull var content: String = "",

    @NonNull var priority: String = Priority.LOW.toString(),

    @NonNull var status: String = Status.ACTIVE.toString(),

    @NonNull var created: Date = Date(),

    var migrateDate: Date = Date(),

    var hide: Boolean = false,

    // One-to-many relations is simply a RealmList of the objects which also subclass RealmObject
    @Ignore var deleted: Date? = null,


    ) : RealmObject(), Parcelable {
    // The Kotlin compiler generates standard getters and setters.
    // Realm will overload them and code inside them is ignored.
    // So if you prefer you can also just have empty abstract methods.
}

