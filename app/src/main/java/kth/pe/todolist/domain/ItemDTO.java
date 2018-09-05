package kth.pe.todolist.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemDTO implements Parcelable {
    private Long id;
    private String title;
    private String content;

    public ItemDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    protected ItemDTO(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        title = in.readString();
        content = in.readString();
    }

    public static final Creator<ItemDTO> CREATOR = new Creator<ItemDTO>() {
        @Override
        public ItemDTO createFromParcel(Parcel in) {
            return new ItemDTO(in);
        }

        @Override
        public ItemDTO[] newArray(int size) {
            return new ItemDTO[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeString(title);
        parcel.writeString(content);
    }
}
