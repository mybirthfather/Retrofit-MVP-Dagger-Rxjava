package com.lrl.app.utils;

/**
 * 更新版本下载
 * Created by jiabaofeng on 2018/1/15.
 */
public class UpdateVerUtil {
//    public static final String APK_DOWNLOAD_PATH = "/Android/data/"+ App.getApplication().getPackageName()+"/apk/";
//    private Activity activity;
//    private TextView tvProgress;
//    private ProgressBar pbBar;
//    private File updateFile;
////    private MaterialViewDialog dialog;
//    private boolean isMust;
//
//    public UpdateVerUtil(Activity activity){
//        this.activity = activity;
//    }
//
//    /**
//     * 版本更新的提示
//     * @param updateVerinfoBean  没有最新版本信息则返回null
//     */
//    public void updateVer(UpdateVerinfoBean updateVerinfoBean) {
//        //弹出提示框
//        if(null != updateVerinfoBean && !TextUtils.isEmpty(updateVerinfoBean.getIs_force())){
//            if(activity.isFinishing())return;
//            final MaterialDialog dialog = new AlertDialogUtil().createDialog(activity);
//            dialog.content(updateVerinfoBean.getApp_note());
//            //是否需要强制升级（0：否；1：是）
//            if(isMust = updateVerinfoBean.getIs_force().equals("1")){
//                dialog.btnTextColor(activity.getResources().getColor(R.color.text_color_register));
//                dialog.btnNum(1);
//                dialog.btnText("立即升级");
//                dialog.setOnBtnClickL(new OnBtnClickClick(dialog,updateVerinfoBean));
//            }else{
//                dialog .btnText("以后再说", "立即升级");
//                dialog.setOnBtnClickL(null,new OnBtnClickClick(dialog,updateVerinfoBean));
//            }
//            dialog.widthScale(0.85f);
//            dialog.title("更新提示")
//                    .show();
//            dialog.setCancelable(!isMust);
//            dialog.setCanceledOnTouchOutside(!isMust);
//        }
//    }
//
//    /**
//     * 版本更新的提示
//     * @param updateVerinfoBean  没有最新版本信息则返回null
//     */
//    public void updateVerClick(UpdateVerinfoBean updateVerinfoBean) {
//        //弹出提示框
//        if(null != updateVerinfoBean && !TextUtils.isEmpty(updateVerinfoBean.getIs_force())){
//            updateVer(updateVerinfoBean);
//        }else{  //没有更新
//            if(activity.isFinishing())return;
//            final MaterialDialog dialog = new AlertDialogUtil().createDialog(activity);
//            dialog.content("您当前的版本已经是最新版本！版本号：V" + UIUtil.getVersionName())
//                    .btnText("确定")
//                    .btnNum(1)
//                    .btnTextColor(activity.getResources().getColor(R.color.text_color_register))
//                    .title("更新提示")
//                    .widthScale(0.85f)
//                    .show();
//        }
//    }
//
//    private class OnBtnClickClick implements OnBtnClickL {
//        UpdateVerinfoBean updateVerinfoBean;
//        MaterialDialog dialog;
//        OnBtnClickClick(MaterialDialog dialog,UpdateVerinfoBean updateVerinfoBean){
//            this.dialog = dialog;
//            this.updateVerinfoBean = updateVerinfoBean;
//        }
//
//        @Override
//        public void onBtnClick() {
//            dialog.cancel();
//            BaseActivity.requestSdWritePermissionStatic(activity,new Consumer<Boolean>() {
//                @Override
//                public void accept(Boolean hasPermission) throws Exception {
//                    // 在android 6.0之前会默认返回true
//                    if (hasPermission) {
//                        showDownloadDialog(activity,updateVerinfoBean.getApp_path());
//                    } else {
//                        // 未获取权限
//                        UIUtil.showToastSafe("存储卡使用授权失败，下载失败");
//                    }
//                }
//            });
//        }
//    }
//
//
//    /**
//     * 升级下载的dialog
//     * @param activity
//     */
//    public void showDownloadDialog(final Activity activity, String apkDownPath) {
//        dialog = new MaterialViewDialog(activity);
//        View contentView = activity.getLayoutInflater().inflate(R.layout.layout_download_dialog,null);
//        tvProgress = contentView.findViewById(R.id.tvProgress);
//        pbBar = contentView.findViewById(R.id.pbBar);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.contentView(contentView);
//        dialog.hasTitle(false)
//                .bgColor(0)
//                .widthScale(0.85f)
//                .show();
//        // 创建文件
//        updateFile = new File(getSDCardPath()+APK_DOWNLOAD_PATH );
//        if (!updateFile.exists()) {
//            try {
//                updateFile.mkdirs();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        File[] childFile = updateFile.listFiles();
//        if(childFile.length != 0){
//            for(File f : childFile){
//                f.delete();
//            }
//        }
//        updateFile = new File(updateFile, apkDownPath.substring(apkDownPath.lastIndexOf("/")+1));
//        if (updateFile.exists()) {
//            try {
//                updateFile.delete();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        // 开始下载
//        FileDownloader.getImpl().create(apkDownPath)
//                .setPath(updateFile.getPath()).setListener(new FileDownloadListener() {
//            @Override
//            protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
//
//            }
//
//            @Override
//            protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
//                double x_double = soFarBytes * 1.0;
//                double tempresult = x_double / totalBytes;
//                DecimalFormat df1 = new DecimalFormat("0.00"); // ##.00%
//                // 百分比格式，后面不足2位的用0补齐
//                String result = df1.format(tempresult);
//                tvProgress.setText("下载进度："+(int) (Float.parseFloat(result) * 100) + "%");
//                pbBar.setMax(100);
//                pbBar.setProgress((int) (Float.parseFloat(result) * 100));
//            }
//
//            @Override
//            protected void completed(BaseDownloadTask task) {
//                if(null != dialog && dialog.isShowing()){
//                    dialog.dismiss();
//                }
//                //强制升级必须关闭activity
//                if(isMust){
//                    activity.finish();
//                }
//                // 下载成功
//                installApk(updateFile, activity);
//            }
//
//            @Override
//            protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
//
//            }
//
//            @Override
//            protected void error(BaseDownloadTask task, Throwable e) {
//                if(null != dialog && dialog.isShowing()){
//                    dialog.dismiss();
//                }
//                UIUtil.showToastSafe("下载失败，请重试！");
//            }
//
//            @Override
//            protected void warn(BaseDownloadTask task) {
//
//            }
//        }).start();
//    }
//
//
//
//    // 获取SDCard的目录路径功能
//    public static String getSDCardPath() {
//        File sdcardDir = null;
//        // 判断SDCard是否存在
//        boolean sdcardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
//        if (sdcardExist) {
//            sdcardDir = Environment.getExternalStorageDirectory();
//        }
//        return sdcardDir.toString();
//    }
//
//
//    // 下载完成后打开安装apk界面
//    public static void installApk(File file, Context context) {
//        Intent intent = new Intent();
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
//        intent.setDataAndType(Uri.fromFile(file),
//                "application/vnd.android.package-archive");
//        context.startActivity(intent);
//    }
}
