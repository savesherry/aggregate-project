package com.example.aggregate_methods.tools.permissions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;

import java.util.List;

/**
 * CREATE BY LiYang
 * ON 2021-02-18
 * SUPPLY : Thanks for watching
 */
public class RequestPermissions {
    public static void obtainPermission(Context context, String permission, PermissionCallback callback) {
        XXPermissions.with(context)
                .permission(permission)
                .request(new OnPermissionCallback() {
                    @Override
                    public void onGranted(List<String> permission, boolean all) {
                        if (all)
                            callback.onSuccess();
                        else
                            callback.onFailure();
                    }
                });
    }

    public static void obtainPermissions(Context context, String[] permissions, PermissionCallback callback) {
        XXPermissions.with(context)
                .permission(permissions)
                .request(new OnPermissionCallback() {
                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        if (all)
                            callback.onSuccess();
                        else
                            callback.onFailure();
                    }
                });
    }

    public static boolean isHasPermission(Context context, String permission) {
        if (XXPermissions.isGrantedPermission(context, permission))
            return true;
        else
            return false;
    }

    public static void settingPermission(Activity activity, String permission) {
        switch (permission) {
            case ConstantPermission.ACCESS_FINE_LOCATION:
            case ConstantPermission.ACCESS_COARSE_LOCATION:
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                activity.startActivityForResult(intent, ConstantPermission.requestPermissionCode);
                break;
            default:
                XXPermissions.startApplicationDetails(activity);
                break;
        }
    }
}
