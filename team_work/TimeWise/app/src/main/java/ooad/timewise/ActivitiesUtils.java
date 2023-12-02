package ooad.timewise;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ActivitiesUtils {
    public static void switchToActivity(Class<?> activityClass, Context context) {
        Intent intent = new Intent(context, activityClass);
        context.startActivity(intent);
    }
    public static void showInfo(String info, Context context){
        Toast.makeText(context, info, Toast.LENGTH_LONG).show();
    }
}
