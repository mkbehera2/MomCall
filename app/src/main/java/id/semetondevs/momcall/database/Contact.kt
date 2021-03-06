package id.semetondevs.momcall.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

@Entity(tableName = "contact") 
data class Contact(
        @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Long?,
        @ColumnInfo(name = "voice_call_Id") var voiceCallId: Long?,
        @ColumnInfo(name = "video_call_id") var videoCallId: Long?,
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "number") var number: String,
        @ColumnInfo(name = "photo_path") var photo: String?,
        var photoUrl: String?) : Parcelable {

    @Ignore constructor() : this(null, 0, 0, "", "", "", "")

    fun isEditMode(): Boolean {
        if (this.id == null) return false
        if (this.id == 0L) return false
        return true
    }

    constructor(source: Parcel) : this(
            source.readValue(Long::class.java.classLoader) as Long?,
            source.readValue(Long::class.java.classLoader) as Long?,
            source.readValue(Long::class.java.classLoader) as Long?,
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeValue(id)
        writeValue(voiceCallId)
        writeValue(videoCallId)
        writeString(name)
        writeString(number)
        writeString(photo)
        writeString(photoUrl)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Contact> = object : Parcelable.Creator<Contact> {
            override fun createFromParcel(source: Parcel): Contact = Contact(source)
            override fun newArray(size: Int): Array<Contact?> = arrayOfNulls(size)
        }
    }    
    
}