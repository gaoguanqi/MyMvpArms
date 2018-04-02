package maple.demo.com.mymvparms.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * 用户信息
 */
@Entity
public class UserEntity implements Parcelable {

    @Id
    private long id;
    //用户id
    @Unique
    private String userid;
    //token
    @SerializedName("token")
    private String token;

    //用户手机号
    @SerializedName("phone")
    private String phone;


    public UserEntity() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userid);
        dest.writeString(this.token);
        dest.writeString(this.phone);
    }


    protected UserEntity(Parcel in) {
        this.userid = in.readString();
        this.token = in.readString();
        this.phone = in.readString();
    }


    @Generated(hash = 166764319)
    public UserEntity(long id, String userid, String token, String phone) {
        this.id = id;
        this.userid = userid;
        this.token = token;
        this.phone = phone;
    }




  

    public static final Creator<UserEntity> CREATOR = new Creator<UserEntity>() {
        @Override
        public UserEntity createFromParcel(Parcel source) {
            return new UserEntity(source);
        }

        @Override
        public UserEntity[] newArray(int size) {
            return new UserEntity[size];
        }
    };
}
