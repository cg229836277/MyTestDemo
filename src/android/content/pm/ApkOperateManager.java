package android.content.pm;

import java.io.File;
import de.greenrobot.event.EventBus;
import android.content.Context;
import android.content.Intent;
import android.content.pm.IPackageDeleteObserver;
import android.content.pm.IPackageInstallObserver;
import android.content.pm.IPackageMoveObserver;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.RemoteException;
import android.util.Log;
 
public class ApkOperateManager {
    public static String TAG = "ApkOperateManager";
    public final static String PACKAGE_INSTALLED = "package_installed";
    public final static String PACKAGE_DELETED = "package_deleted";
 
    /***安装apk */
    public static void installApk(Context context, String fileName) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://" + fileName),"application/vnd.android.package-archive");
        context.startActivity(intent);
    }
 
    /**卸载apk */
    public static void uninstallApk(Context context, String packageName) {
        Uri uri = Uri.parse("package:" + packageName);
        Intent intent = new Intent(Intent.ACTION_DELETE, uri);
        context.startActivity(intent);
    }
 
    /**
     * 静默安装
     * */
    public static void installApkDefaul(Context context, String fileName,String packageName) {
        Log.d(TAG, "jing mo an zhuang:" + packageName + ",fileName:" + fileName);
        File file = new File(fileName);
        int installFlags = 0;
        if (!file.exists())
            return;
        installFlags |= PackageManager.INSTALL_REPLACE_EXISTING;
        if (hasSdcard()) {
            installFlags |= PackageManager.INSTALL_EXTERNAL;
        }
        PackageManager pm = context.getPackageManager();
        try {
            IPackageInstallObserver observer = new MyPakcageInstallObserver(context,fileName, packageName);
            Log.i(TAG, "########installFlags:" + installFlags + "packagename:" + packageName);
            pm.installPackage(Uri.fromFile(file), observer, installFlags,packageName);
        } catch (Exception e) {
        	Log.e("PackageInstaller", "installApkDefaul error");
        }
 
    }
 
    /* 静默卸载 */
    public static void uninstallApkDefaul(Context context, String action,String packageName) {
        PackageManager pm = context.getPackageManager();
        IPackageDeleteObserver observer = new MyPackageDeleteObserver(context, action, packageName);
        pm.deletePackage(packageName, observer, 0);
    }
 
    /* 静默卸载回调 */
     private static class MyPackageDeleteObserver extends IPackageDeleteObserver.Stub {
        Context cxt;
        String action;
        String pkname;
 
        public MyPackageDeleteObserver(Context c, String action, String pkname) {
            this.cxt = c;
            this.action = action;
            this.pkname = pkname;
        }
 
        @Override
        public void packageDeleted(String packageName, int returnCode) {
            Log.d(TAG, "returnCode = " + returnCode + ",action:" + action
                    + "packageName:" + packageName + ",pkname:" + pkname);// 返回1代表卸载成功
            if (returnCode == 1) {
            }             
             
//            Intent it = new Intent();
//            it.setAction(action);
//            it.putExtra("uninstall_returnCode", returnCode);
//            cxt.sendBroadcast(it);
            
            EventBus.getDefault().post(PACKAGE_DELETED);
        }
    }
 
    /* 静默安装回调 */
    private static class MyPakcageInstallObserver extends
            IPackageInstallObserver.Stub {
        Context cxt;
        String appName;
        String filename;
        String pkname;
 
        public MyPakcageInstallObserver(Context c,String filename, String packagename) {
            this.cxt = c;
            this.filename = filename;
            this.pkname = packagename;
        }
 
        @Override
        public void packageInstalled(String packageName, int returnCode) {
            Log.i(TAG,"returnCode = " + returnCode);// 返回1代表安装成功
//            Intent it = new Intent();
//            it.setAction("INSTALL_COMPLETED");
            File f = new File(filename);
            if (f.exists()) {
                f.delete();
            }
//            cxt.sendBroadcast(it);
            EventBus.getDefault().post(PACKAGE_INSTALLED);
        }
    }
 
    /**
     * sd卡不存在
     */
    public static final int NO_SDCARD = -1;
 
    /**
     * 移动应用到SD Card
     * 
     * @param context
     * @param pkname
     * @return
     */
    public static void movePackage(Context context, String pkname) {
        PackageManager pm = context.getPackageManager();
        MovePackageObserver mpo = new MovePackageObserver();
        pm.movePackage(pkname, mpo, PackageManager.INSTALL_EXTERNAL);
    }
 
    /**
     * 移动应用的回调
     */
    public static class MovePackageObserver extends IPackageMoveObserver.Stub {
 
        public MovePackageObserver() {
        }
 
        @Override
        public void packageMoved(String packageName, int returnCode) throws RemoteException {
            Log.i(TAG, "packagename:" + packageName + ",returnCode:" + returnCode);
        }
    }
 
    /**
     * 判断有无sd卡
     * */
    public static boolean hasSdcard() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED) || status.equals("/mnt/sdcard")) {
            Log.i(TAG, "has sdcard....");
            return true;
        } else {
            return false;
        }
    }
}
