package maple.demo.com.mymvparms.entity;

/**
 * Created by Gaoguanqi on 2018/4/3.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * 	"type": "text",
 "text": "刚才看到一个游戏，是这样的。你的姓首字母加上bb一起打出来会是什么？别人都是柔宝宝，佳宝宝，李宝宝。只有我是，拉粑粑。绝望。",
 "username": "莼_黑se [想无啦]",
 "uid": "19536037",
 "header": "http://wimg.spriteapp.cn/profile/large/2016/10/27/58117a12f3714_mini.jpg",
 "comment": 871,
 "top_commentsVoiceuri": null,
 "top_commentsContent": null,
 "top_commentsHeader": null,
 "top_commentsName": null,
 "passtime": "2018-01-16 11:56:01",
 "soureid": 27008979,
 "up": 324,
 "down": 48,
 "forward": 2,
 "image": null,
 "gif": null,
 "thumbnail": null,
 "video": null
 */
public class ShileEntity implements Parcelable {

    @SerializedName("type")
    private String type;

    @SerializedName("text")
    private String text;

    @SerializedName("username")
    private String username;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    protected ShileEntity(Parcel in) {
        this.type = in.readString();
        this.text = in.readString();
        this.username = in.readString();
    }

    public static final Creator<ShileEntity> CREATOR = new Creator<ShileEntity>() {
        @Override
        public ShileEntity createFromParcel(Parcel in) {
            return new ShileEntity(in);
        }

        @Override
        public ShileEntity[] newArray(int size) {
            return new ShileEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.type);
        parcel.writeString(this.text);
        parcel.writeString(this.username);
    }
}
