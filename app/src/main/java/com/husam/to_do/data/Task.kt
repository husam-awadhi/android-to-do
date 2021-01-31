package com.husam.to_do.data

import android.os.Parcelable
import androidx.annotation.NonNull
import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
open class Task(

    @PrimaryKey var id: Long = 0,

    @NonNull var content: String = "",

    @NonNull var priority: String = Priority.LOW.toString(),

    @NonNull var status: String = Status.ACTIVE.toString(),

    @NonNull var created: Date = Date(),

    var migrateDate: Date = Date(),

    var hide: Boolean = false,

    @Ignore var deleted: Date? = null,


    ) : RealmObject(), Parcelable
