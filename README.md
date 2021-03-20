# aggregate-project

maven { url 'https://jitpack.io' }

implementation 'com.github.savesherry:aggregate-project:1.0.4'


### 等待loading

##### 展示
            ProgressHelper.show(this);
##### 取消
            ProgressHelper.hide();

### 防点击按钮多次点击

            button.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {

            }
            });

### 底部弹框

            dialog = new SingleChoiceDialog(this, list, "标题位置");
            dialog.setListener(position -> {
                Toast.makeText(MainActivity.this, "点击事件" + position, Toast.LENGTH_SHORT).show();
            });
            dialog.show(parentLayout);

### 请求权限

            RequestPermissions.obtainPermission(this, ConstantPermission.ACCESS_FINE_LOCATION, new PermissionCallback() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this, "同意", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure() {
                Toast.makeText(MainActivity.this, "不同意", Toast.LENGTH_SHORT).show();
            }
            });
            
### 图片缩略图

            GlideEngine.instantiation().loadImageSize(this, imageView, CustomConstant.imagePath,
                (width, height) -> {
                    Methods.imageViewScale(imageView, width, height);
                });
                
### 缩略图放大功能

            Intent intent = new Intent(ThumbnailActivity.this, EnlargeActivity.class);
            intent.putExtra("imagePath", CustomConstant.imagePath);
            // ready for transition options
            ActivityTransitionOptions options =
                    ActivityTransitionOptions.makeTransitionOptions(
                            ThumbnailActivity.this,
                            findViewById(R.id.imageView));
            // start transition
            ActivityTransition.startActivity(intent, options);
            
### 添加搜索页面（待完善跳转列表）

            List<String> conductList = new ArrayList<>();
            conductList.addAll(Arrays.asList(CustomConstant.conductList));
            Intent intent = new Intent(this, SearchActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("conductList", (Serializable) conductList);
            intent.putExtras(bundle);
            startActivity(intent);
                
### 添加OKGO请求方式post以及download方法

            String url = "对应地址";
            HashMap<String, Object> params = new HashMap<>();
            params.put(key, value);
            HttpTools.post(this, url, params, new JsonResponseListener() {
                @Override
                public void onSuccess(JSONObject response) {
                    Logger.e(TAG, response.toString());
                }

                @Override
                public void onFailure(String result) {
                    Logger.e(TAG, result);
                }
            });
            
            String url = " 下载地址";
            HttpTools.download(this, url, 存放路径, 文件名, new DownloadResponseListener() {
                @Override
                public void onProgress(String downloadLength, String totalLength, String downloadSpeed, int percentage) {
                    dialog.setProgressBar(percentage, "下载速度" + downloadSpeed + "，请您耐心等待！");
                }

                @Override
                public void onFinish(File file) {
                    if (file.exists())
                        Toast.makeText(OkGoActivity.this, "当前文件下载完毕", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(OkGoActivity.this, "当前文件下载失败", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }

                @Override
                public void onFailure(String error) {
                    dialog.dismiss();
                }
            });


