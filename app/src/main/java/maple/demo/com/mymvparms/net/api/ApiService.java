package maple.demo.com.mymvparms.net.api;

import java.util.List;

import io.reactivex.Observable;
import maple.demo.com.mymvparms.entity.ResultEntity;
import maple.demo.com.mymvparms.entity.ShileEntity;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Gaoguanqi on 2018/3/28.
 */

public interface ApiService {
    @GET(IApiConfig.GAREN_SERVER_URL + "/satinGodApi")
    Observable<ResultEntity<List<ShileEntity>>> requestData(@Query("type") String type, @Query("page") String page);

}
